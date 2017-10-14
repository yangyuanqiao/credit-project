/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.renthouse.service.baseinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.license.dao.SubjectLicenseDao;
import com.amass.credit.modules.license.entity.SubjectLicense;
import com.amass.credit.modules.renthouse.dao.baseinfo.RenthouseBaseDao;
import com.amass.credit.modules.renthouse.dao.leasecred.RenthouseLeasecredDao;
import com.amass.credit.modules.renthouse.entity.baseinfo.RenthouseBase;
import com.amass.credit.modules.renthouse.entity.leasecred.RenthouseLeasecred;

/**
 * 出租屋基础信息Service
 * @author yangyq
 * @version 2017-06-22
 */
@Service
@Transactional(readOnly = true)
public class RenthouseBaseService extends CrudService<RenthouseBaseDao, RenthouseBase> {
	
	@Autowired
	private SubjectLicenseDao subjectLicenseDao;
	
	@Autowired
	private RenthouseLeasecredDao renthouseLeasecredDao;
	
	public RenthouseBase get(String id) {
		return super.get(id);
	}
	
	public List<RenthouseBase> findList(RenthouseBase renthouseBase) {
		return super.findList(renthouseBase);
	}
	
	public Page<RenthouseBase> findPage(Page<RenthouseBase> page, RenthouseBase renthouseBase) {
		return super.findPage(page, renthouseBase);
	}
	
	@Transactional(readOnly = false)
	public void save(RenthouseBase renthouseBase) {
		super.save(renthouseBase);
	}
	
	@Transactional(readOnly = false)
	public void delete(RenthouseBase renthouseBase) {
		super.delete(renthouseBase);
	}
	@Transactional(readOnly = false)
	public RenthouseLeasecred getRenthouseLeasecred(String subId){
		return renthouseLeasecredDao.getRenthouseLeasecred(subId);
	}
	@Transactional(readOnly = false)
	public List<SubjectLicense> getSubjectLicense(String subId){
		return subjectLicenseDao.getSubjectLicense(subId);
	}
}