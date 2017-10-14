package com.amass.credit.modules.quartz.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.constant.SystemConstants;
import com.amass.credit.common.utils.IdGen;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.grade.dao.GradeLevelDao;
import com.amass.credit.modules.grade.entity.GradeLevel;
import com.amass.credit.modules.gradelimit.dao.detail.GradeLimitDetailDao;
import com.amass.credit.modules.gradelimit.entity.category.GradeLimit;
import com.amass.credit.modules.gradelimit.entity.detail.GradeLimitDetail;
import com.amass.credit.modules.gradelimit.service.category.GradeLimitService;
import com.amass.credit.modules.graderecord.entity.GradeLimitDetailVo;
import com.amass.credit.modules.quartz.dao.GradeDao;
import com.amass.credit.modules.quartz.entity.EnterpriseBase;
import com.amass.credit.modules.quartz.entity.GradeItems;
import com.amass.credit.modules.quartz.entity.GradeItemsOpt;
import com.amass.credit.modules.quartz.entity.GradeOption;
import com.amass.credit.modules.quartz.entity.GradeRecord;
import com.amass.credit.modules.quartz.entity.GradeRules;
import com.amass.credit.modules.quartz.exceptions.RuleException;
import com.amass.credit.modules.sys.utils.DictUtils;


/**
 * @ClassName TaskJob
 * @Description 数据抽取定时任务
 * @version 1.0
 */
@Service
public class ScoringJob {

	
	private static Logger logger = LoggerFactory.getLogger(ScoringJob.class);
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private GradeLevelDao gradeLevelDao;
	@Autowired
	private GradeLimitService  limitService;
	
	@Autowired
	private GradeLimitDetailDao dtDao;
	
	/**
	 * 执行评分任务
	 */
	
	public void execute(){
		
		logger.debug("==============模具企业评分任务开始===================");
		//查找待评分的模具行业主体
		List<EnterpriseBase>  enterpriseList = gradeDao.findAllList();
		if(null ==enterpriseList ||  enterpriseList.isEmpty()){
			logger.debug("不存在待评分的模具企业");
			return;
		}
		String batchNum = gradeDao.selectBatch();//批次号
		
		for(int i=0;i<enterpriseList.size();i++){
			EnterpriseBase enterprise = enterpriseList.get(i);
			logger.debug("============开始对 "+enterprise.getSubName()+" 进行评分 ==================");
			if(scoring(enterprise,batchNum)){  //评分项计算
				levelLimit(enterprise,batchNum);//限制等级计算
				statisticsScore(enterprise,batchNum);//统计最终结果
			}
			
		}
		//
		
		logger.debug("==============模具企业评分任务结束===================");
	}
	
	/**
	 * 各评分选项规则计算  (批量处理)
	 */
	@Transactional(readOnly = false)
	public boolean scoring(EnterpriseBase enterprise,String batchNum) {

		List<GradeItems> gradeItemslist = gradeDao.scanGradeItems(SystemConstants.SUB_TYPE_FOR_ENTERPRISE);//评分选项列表
		if(null == gradeItemslist || gradeItemslist.isEmpty()){
			logger.debug("=======找不到评分项====");
			return false;
		}
		List<GradeItemsOpt> itemsOptList = new ArrayList<GradeItemsOpt>();
		for (int i = 0; i < gradeItemslist.size(); i++) {
			GradeItems gradeItems = gradeItemslist.get(i); // 评分选项
			// 评分明细
			GradeItemsOpt itemsDetail = new GradeItemsOpt(); 
			logger.debug("====评分项为："+gradeItems.getItemName()+"============"+gradeItemslist.get(i).toString());
			// 评分选项规则列表
			List<GradeRules> gradeRulesList = gradeItems.getRuleList();
			String optVal= SystemConstants.RESULT_FOR_YES;
			if(gradeRulesList.size() > 0){
				for (int k = 0; k < gradeRulesList.size(); k++) {
					//读取规则
					GradeRules ruleV = gradeRulesList.get(k);
					//执行规则
					boolean runResult = runRules(ruleV,enterprise);
					logger.debug(" 规则编号为："+ruleV.getId()+" 的运行结果为  :"+runResult);
					if(runResult == false){
						optVal=SystemConstants.RESULT_FOR_NO;
						break;
					}
				}
			
			}else{
				logger.debug("=======找不到规则配置项====");
				optVal = SystemConstants.RESULT_FOR_NO;//找不到规则配置项默认为计算结果为否
			}
			//评分选项值列表
			List<GradeOption> gradeOptionList = gradeItems.getOptionList();
			if(null == gradeOptionList || gradeOptionList.isEmpty()){
				logger.debug("=======找不到选项值配置项====");
				continue;
			}else{
				for (int j = 0; j < gradeOptionList.size(); j++) {
					GradeOption gradeOption = gradeOptionList.get(j); // 评分选项值
					if(optVal.equals(gradeOption.getOptionCode())){
						itemsDetail.setOptId(gradeOption.getId());
						itemsDetail.setScore(gradeOption.getScore());
					}
				}
				
			}
			
			
			itemsDetail.setId(IdGen.uuid());
			itemsDetail.setSubId(String.valueOf(enterprise.getId()));
			itemsDetail.setItemId(gradeItems.getId());// 评分选项代码
			itemsDetail.setCreateDate(new Date());
			itemsDetail.setBatch(batchNum);
			//插入运算结果
			//gradeDao.insertGradeDetail(itemsDetail);
			itemsOptList.add(itemsDetail);
		}
		logger.debug("======= 批量插入评分选项值明细  ====");
		gradeDao.insertGradeDetailBatch(itemsOptList);
		return true;
		
	}
	
	/**
	 * 信用级别限制计算 (批量处理)
	 * @return
	 */
	public String levelLimit(EnterpriseBase enterprise,String batchNum){
		logger.debug("▅▅▅▅▅▅▅▅ 计算信用限制等级开始  ▅▅▅▅▅▅▅▅");
		List<GradeLimit> limitvoList = limitService.getGradeLimitList(SystemConstants.SUB_TYPE_FOR_ENTERPRISE);
		if(null!= limitvoList && !limitvoList.isEmpty()){
			List<GradeLimitDetail> limitItemList = new ArrayList<GradeLimitDetail>();
			for(int i=0;i<limitvoList.size();i++){
				GradeLimit limitvo = limitvoList.get(i);
				logger.debug("▅▅▅▅▅▅▅▅ 计算限制等级项为  ："+limitvo.getConditName());
				List<GradeRules>  ruleList = limitvo.getGradeRule();
				if(null != ruleList && !ruleList.isEmpty()){
					String limitResult= SystemConstants.RESULT_FOR_YES;//限制结果
					for(int k=0;k<ruleList.size();k++){
						GradeRules gradeRule = ruleList.get(k);
						//执行规则
						boolean runResult = runRules(gradeRule,enterprise);
						logger.debug("规则编号为："+gradeRule.getId()+" 的运行结果为  :"+runResult);
						if(runResult==true){
							limitResult =SystemConstants.RESULT_FOR_NO;
							break;
						}
						
					}
					GradeLimitDetail lmtdetail = new GradeLimitDetail();
					lmtdetail.setLimitId(Long.parseLong(limitvo.getId()));
					lmtdetail.setSubId(enterprise.getId());
					lmtdetail.setLimitResult(limitResult);
					lmtdetail.setLevelId(String.valueOf(limitvo.getLevelId()));
					lmtdetail.setCreateDate(new Date());
					lmtdetail.setBatch(batchNum);
					limitItemList.add(lmtdetail);
					
				}
				
			}
			
			logger.debug("=== 批量记录限制等级结果====");
			dtDao.insertBatch(limitItemList);
		}
		logger.debug("▅▅▅▅▅▅▅▅ 计算信用限制等级结束 ▅▅▅▅▅▅▅▅");
		return "";
	}
	
	/**
	 * 计算最后得分并记录
	 * @return
	 */

	public boolean statisticsScore(EnterpriseBase enterprise,String batchNum){
		logger.debug("============== 统计最终得分开始 ===================");
		GradeRecord gradeRecord = new GradeRecord();
		gradeRecord.setId(IdGen.randomLong());
		gradeRecord.setSubType(SystemConstants.SUB_TYPE_FOR_ENTERPRISE);
		gradeRecord.setSubId(enterprise.getId());
		HashMap<String,String> sumScoreParmsMap = new HashMap<String,String>();
		sumScoreParmsMap.put("subId", String.valueOf(enterprise.getId()));
		sumScoreParmsMap.put("batch", batchNum);
		Long scorel=gradeDao.sumScore(sumScoreParmsMap);
		gradeRecord.setScore(scorel);
		gradeRecord.setCreateDate(new Date());
		HashMap<String,String> limitParmsmap = new HashMap<String,String>();
		limitParmsmap.put("subId", String.valueOf(enterprise.getId()));
		limitParmsmap.put("limitResult", SystemConstants.RESULT_FOR_YES);
		limitParmsmap.put("batch", batchNum);
		List<GradeLimitDetailVo> list = limitService.findLimitDetailList(limitParmsmap);
		GradeLevel scorelevel = changeScoreToLevel(scorel);
		if(null !=list && list.size()>0){
			GradeLevel limitlevel = list.get(0).getLimit().getGradeLevel();
			gradeRecord.setLimitLevel(limitlevel.getLevelName());//限制等级
			if(scorelevel.getSort() >  limitlevel.getSort()){//取大的
				gradeRecord.setFinalLevel(scorelevel.getLevelName());//最终等级 为分数等级
			}else{
				gradeRecord.setFinalLevel(limitlevel.getLevelName());//最终等级 限制等级
			}
		}else{
			gradeRecord.setLimitLevel(SystemConstants.NO_LIMMIT_LEVEL);
			gradeRecord.setFinalLevel(scorelevel.getLevelName());//最终等级 为分数等级
		}
		
		//int b[]={60,80,90,100};
		//String c[]={"D","C","B","A"};
		gradeRecord.setStatus(SystemConstants.PF_STATUS_AVIABLE);//评分有效
		gradeRecord.setBatch(batchNum);
		gradeRecord.setScoreLevel(scorelevel.getLevelName());//分数等级
		gradeDao.insertGradeRecord(gradeRecord);
		logger.debug("============== 统计"+enterprise.getSubName()+"最终得分结束 ===================");
		return true;
	}
	
	/**
	 * 规则执行
	 * @param rule
	 * @param sub_id
	 * @return
	 */
	public boolean runRules(GradeRules rule,EnterpriseBase enterprise){
		logger.debug("=========== 执行规则 =============="+rule);
		String target = rule.getReferVal();//目标值
		String caculVal = getResultValByRule(rule,enterprise);//计算结果值
		logger.debug(" >>>>>>>>>>>>>> 按照规则查询结果为："+ caculVal+" ,公式为："+DictUtils.getDictLabels(rule.getReferEquation(), "EQUATION_TYPE", "为空")+">>>> 对比的目标值为："+target);
		
		if(caculVal == null || "NULL".equals(caculVal))
			return false;
		if(SystemConstants.OPERATION_EXPRESSION_ET.equals(rule.getReferEquation())){
			return "NULL".equals(caculVal) ? false : true;
		}
		return StringUtils.compareBig(caculVal,target,rule.getReferEquation());
	}
	
	/**
	 * 根据定义规则 动态计算需比对字段的信息
	 */
	private String getResultValByRule(GradeRules rule,EnterpriseBase enterprise) {
		String caculVal="";
		if(SystemConstants.RULE_TYPE_FOR_COMMON.equals(rule.getRuleType())){//逻辑计算规则
			caculVal = commonRule(rule,enterprise);
		}else if (SystemConstants.RULE_TYPE_FOR_STATIST.equals(rule.getRuleType())){//统计规则
			caculVal = countRule(rule,enterprise);
		}else{
			throw new RuleException("运行规则异常:" + rule.getRuleType() + "不是规则列表类型");
		}
		return caculVal == null ? "NULL" : caculVal;
	}
	
	
	/**
	 * 普通类规则执行
	 * @param rule
	 * @param sub_id
	 * @return
	 */
	private String commonRule(GradeRules rule,EnterpriseBase enterprise){
		Map<String, String> map = new HashMap<String, String>();
		map.put("columns", rule.getReferField());
		map.put("tableName", rule.getReferTable());
		map.put("referCondit", rule.getReferCondit());
		map.put("id", String.valueOf(enterprise.getId()));
		String str = gradeDao.getFeild(map);
		//logger.debug("====【运行普通计算规则】结果值："+str);
		return str;
	}
	
	/**
	 * 统计类规则执行
	 * 根据企业主体信息
	 * @param rule
	 * @param sub_id
	 * @return
	 */
	private String countRule(GradeRules rule,EnterpriseBase enterprise){
		Map<String, String> map = new HashMap<String, String>();
		map.put("tableName", rule.getReferTable());
		map.put("columns", rule.getReferField());
		map.put("referCondit", rule.getReferCondit());
		map.put("id", String.valueOf(enterprise.getId()));
		String str = gradeDao.countFeild(map);
		//logger.debug("==============【运行统计规则计算】结果值："+str);
		return str;
	}
	
	/**
	 * 分数转换为等级
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	  public  GradeLevel changeScoreToLevel(long score){
		  logger.debug("====【执行分数转化为对应信用级别运算】=====：");
		  GradeLevel levelparm= new GradeLevel();
		  levelparm.setDelFlag("0");
		  levelparm.setSubjectType(SystemConstants.SUB_TYPE_FOR_ENTERPRISE);
		  List<GradeLevel> levelList = gradeLevelDao.findAllList(levelparm);
	      for(int i=0;i<levelList.size();i++){
	    		GradeLevel level = levelList.get(i);
	    		int d = Integer.parseInt(level.getMaxScore() );
	    		if( score <= d){
	    			return level;
	    		}
	    	}
	    	return null;
	    }
	
}