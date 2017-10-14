/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.grade.service.gradeitems;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.grade.entity.gradeitems.GradeItems;
import com.amass.credit.modules.grade.entity.gradeoptions.GradeOption;
import com.amass.credit.modules.grade.service.gradeoptions.GradeOptionService;
import com.amass.credit.modules.sys.utils.DictUtils;
import com.amass.credit.modules.grade.dao.gradeitems.GradeItemsDao;
import com.amass.credit.modules.grade.dao.gradeoptions.GradeOptionDao;

/**
 * 评分选项管理Service
 * @author yangqy
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class GradeItemsService extends CrudService<GradeItemsDao, GradeItems> {

	
	@Autowired
	private GradeOptionService optService;
	@Autowired
	private GradeOptionDao gradeOptDao;
	
	public GradeItems get(String id) {
		GradeItems gradeItems = super.get(id);
		return gradeItems;
	}
	
	public List<GradeItems> findList(GradeItems gradeItems) {
		return super.findList(gradeItems);
	}
	
	public Page<GradeItems> findPage(Page<GradeItems> page, GradeItems gradeItems) {
		return super.findPage(page, gradeItems);
	}
	
	@Transactional(readOnly = false)
	public void save(GradeItems gradeItems) {
		
		super.save(gradeItems);//保存选项
		
		if(gradeItems.getGradeOpt()!=null){
			
			GradeOption opt = gradeItems.getGradeOpt();
			String[] optName = opt.getOptionName().split(",");
			String[] optScore = opt.getScore().split(",");
			String[] optId = opt.getId().split(",");
			String[] optDel = opt.getOptDel().split(",");
			//String[] optCode = opt.getOptionCode().split(",");
			for(int i = 0; i < optName.length;i++){
				if(optId.length - i > 0 && StringUtils.isNotBlank(optId[i])){
					GradeOption optSave = new GradeOption();
					optSave.setOptionName(optName[i]);
					optSave.setDelFlag(optDel[i]);
					optSave.setScore(optScore[i]);
					optSave.setId(optId[i]);
					optSave.setOptionCode(DictUtils.getDictValue(optName[i], "Y_N", "N"));
					optSave.setItemsId(Long.parseLong(gradeItems.getId()));;
					//optSave.setOptionCode(optCode[i]);
					logger.debug("======= 修改选项值 ========"+optSave);
					optSave.preUpdate();
					gradeOptDao.update(optSave);
				}else{
					
					GradeOption optSave = new GradeOption();
					optSave.setOptionName(optName[i]);
					optSave.setOptionCode(DictUtils.getDictValue(optName[i], "Y_N", "N"));
					optSave.setScore(optScore[i]);
					optSave.setItemsId(Long.parseLong(gradeItems.getId()));;
					//optSave.setOptionCode(optCode[i]);
					logger.debug("======= 添加选项值 ========"+optSave);
					optSave.preInsert();
					gradeOptDao.insert(optSave);
				}
				
				
			}
		}
		
		
	}
	
	@Transactional(readOnly = false)
	public void delete(GradeItems gradeItems) {
		super.delete(gradeItems);
	}
	
}