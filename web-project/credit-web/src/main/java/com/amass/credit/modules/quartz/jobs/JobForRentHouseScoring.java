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
import com.amass.credit.modules.quartz.entity.GradeItems;
import com.amass.credit.modules.quartz.entity.GradeItemsOpt;
import com.amass.credit.modules.quartz.entity.GradeOption;
import com.amass.credit.modules.quartz.entity.GradeRecord;
import com.amass.credit.modules.quartz.entity.GradeRules;
import com.amass.credit.modules.quartz.exceptions.RuleException;
import com.amass.credit.modules.renthouse.dao.baseinfo.RenthouseBaseDao;
import com.amass.credit.modules.renthouse.entity.baseinfo.RenthouseBase;


/**
 * @ClassName TaskJob
 * @Description 数据抽取定时任务
 * @version 1.0
 */
@Service
public class JobForRentHouseScoring {

	
	
	private static Logger logger = LoggerFactory.getLogger(JobForRentHouseScoring.class);
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private RenthouseBaseDao renthouseDao;
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
		
		logger.debug("==============出租屋评分任务开始===================");
		//查找待评分的模具行业主体
		
		List<RenthouseBase>  hourseList = renthouseDao.findAllRentHouseList (new RenthouseBase());
		if(null ==hourseList ||  hourseList.isEmpty()){
			logger.debug("不存在待评分的出租屋主体");
			return;
		}
		String batchNum = gradeDao.selectBatch();//批次号
		for(int i=0;i<hourseList.size();i++){
			RenthouseBase hourse = hourseList.get(i);
			logger.debug("==============出租屋门牌号："+hourse.getHouseNum());
			if( itemsCompute(hourse,batchNum)){//评分项计算{
				levelLimitCompute(hourse,batchNum);//限制等级计算
				statisticsScore(hourse,batchNum);//统计最终结果
			}
			
		}
		
		logger.debug("==============批次为："+batchNum+"出租屋评分任务结束===================");
	}
	
	/**
	 * 各评分选项规则计算
	 */
	@Transactional(readOnly = false)
	public boolean itemsCompute(RenthouseBase hourse,String batchNum) {

		List<GradeItems> gradeItemslist = gradeDao.scanGradeItems(SystemConstants.SUB_TYPE_FOR_RENTHOUSE);//评分选项列表
		if(null == gradeItemslist || gradeItemslist.isEmpty()){
			logger.debug("=======找不到评分项====");
			return false;
		}
		List<GradeItemsOpt> itemsOptList = new ArrayList<GradeItemsOpt>();//批量list
		for (int i = 0; i < gradeItemslist.size(); i++) {
			GradeItems gradeItems = gradeItemslist.get(i); // 评分选项
			// 评分明细
			GradeItemsOpt itemsOpt = new GradeItemsOpt(); 
			logger.debug("====评分项为："+gradeItems.getItemName()+"============"+gradeItemslist.get(i).toString());
			// 评分选项规则列表
			List<GradeRules> gradeRulesList = gradeItems.getRuleList();
			String optVal="Y";
			if(gradeRulesList.size() > 0){
				for (int k = 0; k < gradeRulesList.size(); k++) {
					//读取规则
					GradeRules ruleV = gradeRulesList.get(k);
					//执行规则
					boolean runResult = runRules(ruleV,hourse);
					if(runResult == false){
						optVal="N";
						break;
					}
				}
			
			}else{
				logger.debug("=======找不到规则配置项====");
				optVal="N";//找不到规则配置项默认为计算结果为否
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
						itemsOpt.setOptId(gradeOption.getId());
						itemsOpt.setScore(gradeOption.getScore());
					}
				}
				
			}
			
			
			itemsOpt.setId(IdGen.uuid());
			itemsOpt.setSubId(String.valueOf(hourse.getId()));
			itemsOpt.setItemId(gradeItems.getId());// 评分选项代码
			itemsOpt.setCreateDate(new Date());
			itemsOpt.setBatch(batchNum);
			
			itemsOptList.add(itemsOpt);
			//插入运算结果
			//gradeDao.insertGradeDetail(itemsOpt);
		}
		logger.debug("======= 批量插入评分选项值明细  ====");
		if(null != itemsOptList && itemsOptList.size() > 0){
			gradeDao.insertGradeDetailBatch(itemsOptList);
		}
		return true;
		
	}
	
	/**
	 * 信用级别限制计算
	 * @return
	 */
	public String levelLimitCompute(RenthouseBase hourse,String batchNum){
		logger.debug("▅▅▅▅▅▅▅▅ 计算信用限制等级开始  ▅▅▅▅▅▅▅▅");
		List<GradeLimit> limitvoList = limitService.getGradeLimitList(SystemConstants.SUB_TYPE_FOR_RENTHOUSE);
		if(null!= limitvoList && !limitvoList.isEmpty()){
			List<GradeLimitDetail> limitItemList = new ArrayList<GradeLimitDetail>();
			for(int i=0;i<limitvoList.size();i++){
				GradeLimit limitvo = limitvoList.get(i);
				logger.debug("▅▅▅▅▅▅▅▅ 计算"+limitvo.getConditName()+" ▅▅▅▅▅▅▅▅");
				List<GradeRules>  ruleList = limitvo.getGradeRule();
				if(null != ruleList && !ruleList.isEmpty()){
					String limitResult= SystemConstants.RESULT_FOR_YES;
					for(int k=0;k<ruleList.size();k++){
						GradeRules gradeRule = ruleList.get(k);
						//执行规则
						boolean runResult = runRules(gradeRule,hourse);
						
						if(runResult==true){
							limitResult = SystemConstants.RESULT_FOR_NO;
							break;
						}
						
					}
					GradeLimitDetail levelLimit = new GradeLimitDetail();
					levelLimit.setLimitId(Long.parseLong(limitvo.getId()));
					levelLimit.setSubId(Long.parseLong(hourse.getId()));
					levelLimit.setLimitResult(limitResult);
					levelLimit.setLevelId(String.valueOf(limitvo.getLevelId()));
					levelLimit.setCreateDate(new Date());
					levelLimit.setBatch(batchNum);
					limitItemList.add(levelLimit);
					//dtDao.insert(levelLimit);
				}
				
			}
			logger.debug("▅▅▅▅▅▅▅▅ 批量记录限制等级结果▅▅▅▅▅▅▅▅");
			if(null !=limitvoList && limitvoList.size() > 0){
				dtDao.insertBatch(limitItemList);
			}
		}
		
		return "";
	}
	
	/**
	 * 计算最后得分并记录
	 * @return
	 */

	public boolean statisticsScore(RenthouseBase hourse,String batchNum){
		logger.debug("============== 统计最终得分 ===================");
		GradeRecord gradeRecord = new GradeRecord();
		gradeRecord.setId(IdGen.randomLong());
		gradeRecord.setSubType(SystemConstants.SUB_TYPE_FOR_RENTHOUSE);
		gradeRecord.setSubId(Long.parseLong(hourse.getId()));
		HashMap<String,String> sumScoreParmsMap = new HashMap<String,String>();
		sumScoreParmsMap.put("subId", String.valueOf(hourse.getId()));
		sumScoreParmsMap.put("batch", batchNum);
		
		List<HashMap> subList = gradeDao.subtractScore(sumScoreParmsMap);
		System.out.println(subList.toString());
		//出租屋原始得分一百分，按照三级项目的扣分比例对应二级项目分值进行扣分，对应二级项目分值扣完为止。最后累计各一级要素得分，相加得出最终分值。
		float scorel = 100;
		for(int sl=0;sl<subList.size();sl++){
			HashMap<String,String> map = subList.get(sl);
			Double propotion =  Double.parseDouble(map.get("proportion"));
			Double score = Double.parseDouble(String.valueOf(map.get("score")));
			scorel = scorel + subtract(propotion,score);
		}
		
		gradeRecord.setScore(scorel);
		gradeRecord.setCreateDate(new Date());
		
		HashMap<String,String> limitParmsmap = new HashMap<String,String>();
		limitParmsmap.put("subId", String.valueOf(hourse.getId()));
		limitParmsmap.put("limitResult", SystemConstants.RESULT_FOR_YES);
		limitParmsmap.put("batch", batchNum);
		List<GradeLimitDetailVo> list = limitService.findLimitDetailList(limitParmsmap);
		GradeLevel scorelevel = changScoreToLevel(scorel);
		if(null !=list && list.size()>0){
			GradeLevel limitlevel = list.get(0).getLimit().getGradeLevel();
			gradeRecord.setLimitLevel(limitlevel.getLevelName());//限制等级
			if(scorelevel.getSort() >  limitlevel.getSort()){
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
		gradeRecord.setStatus(SystemConstants.PF_STATUS_AVIABLE);
		gradeRecord.setScoreLevel(scorelevel.getLevelName());
		gradeRecord.setBatch(batchNum);
		gradeDao.insertGradeRecord(gradeRecord);
		return true;
	}
	
	/**
	 * 出租屋扣分
	 * @param propotion
	 * @param score
	 * @return
	 */
	public static float subtract(double propotion,double score){
		float sum = (float) (propotion+score);
		if(sum <= 0)
		   return (float) -propotion;
		return -sum;
	}
	
	public static void main(String[] args) {
		HashMap<String,String> map = new HashMap();
		map.put("score", "-4.5");
		Double score = Double.parseDouble(map.get("score"));
		System.out.println(subtract(5.8,-6.1));
	}
	/**
	 * 规则执行
	 * @param rule
	 * @param sub_id
	 * @return
	 */
	public boolean runRules(GradeRules rule,RenthouseBase house){
		logger.debug("=========== 执行规则 =============="+rule);
		String target = rule.getReferVal();//目标值
		String caculVal = getResultValByRule(rule,house);//计算结果值
		if(caculVal == null || "".equals(caculVal))
			return false;
		return StringUtils.compareBig(caculVal,target,rule.getReferEquation());
	}
	
	/**
	 * 根据定义规则 动态计算需比对字段的信息
	 */
	private String getResultValByRule(GradeRules rule,RenthouseBase house) {
		String caculVal="";
		if(SystemConstants.RULE_TYPE_FOR_COMMON.equals(rule.getRuleType())){//逻辑计算规则
			caculVal = commonRule(rule,house);
		}else if (SystemConstants.RULE_TYPE_FOR_STATIST.equals(rule.getRuleType())){//统计规则
			caculVal = countRule(rule,house);
		}else{
			throw new RuleException("运行规则异常:" + rule.getRuleType() + "不是规则列表类型");
		}
		return caculVal;
	}
	
	
	/**
	 * 普通类规则执行
	 * @param rule
	 * @param sub_id
	 * @return
	 */
	private String commonRule(GradeRules rule,RenthouseBase house){
		Map<String, String> map = new HashMap<String, String>();
		map.put("columns", rule.getReferField());
		map.put("tableName", rule.getReferTable());
		map.put("referCondit", rule.getReferCondit());
		map.put("id", String.valueOf(house.getId()));
		String str = gradeDao.getFeild(map);
		logger.debug("====【运行普通计算规则】结果值："+str);
		return str;
	}
	
	/**
	 * 统计类规则执行
	 * @param rule
	 * @param sub_id
	 * @return
	 */
	private String countRule(GradeRules rule,RenthouseBase house){
		Map<String, String> map = new HashMap<String, String>();
		map.put("tableName", rule.getReferTable());
		map.put("columns", rule.getReferField());
		map.put("referCondit", rule.getReferCondit());
		map.put("id", String.valueOf(house.getId()));
		String str = gradeDao.countFeild(map);
		logger.debug("==============【运行统计规则计算】结果值："+str);
		return str;
	}
	
	/**
	 * 分数转换为等级
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	  public  GradeLevel changScoreToLevel(float score){
		  logger.debug("====【运行分数转化为对应信用级别】===== 【分数为】："+score);
		  GradeLevel levelparm= new GradeLevel();
		  levelparm.setDelFlag("0");
		  levelparm.setSubjectType(SystemConstants.SUB_TYPE_FOR_RENTHOUSE);
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