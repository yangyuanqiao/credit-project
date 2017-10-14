package com.amass.credit.modules.quartz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amass.credit.common.persistence.BaseDao;
import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.modules.quartz.entity.EnterpriseBase;
import com.amass.credit.modules.quartz.entity.GradeItems;
import com.amass.credit.modules.quartz.entity.GradeItemsOpt;
import com.amass.credit.modules.quartz.entity.GradeRecord;

@MyBatisDao
public interface GradeDao extends BaseDao{

	public List<GradeItems> scanGradeItems(String subType);
	
	public String getFeild(Map<String, String> map);
	
	public String countFeild(Map<String, String> map);
	
	
	public int insertGradeDetail(GradeItemsOpt itemsOpt);
	
	public int insertGradeDetailBatch(List<GradeItemsOpt> itemsOptList); 
	
	public List<EnterpriseBase> findAllList();
	
	public int insertGradeRecord(GradeRecord gradeRecord);
	
	public long sumScore(HashMap map);
	
	public List<HashMap>  subtractScore(HashMap map);
	
	public String selectBatch();
}
