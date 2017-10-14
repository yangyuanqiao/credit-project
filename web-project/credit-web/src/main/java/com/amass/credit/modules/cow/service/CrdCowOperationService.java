/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.cow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.cow.entity.CrdCowOperation;
import com.amass.credit.modules.cow.dao.CrdCowOperationDao;

/**
 * 牛行经营场所Service
 * @author yangyq
 * @version 2017-08-18
 */
@Service
@Transactional(readOnly = true)
public class CrdCowOperationService extends CrudService<CrdCowOperationDao, CrdCowOperation> {

	public CrdCowOperation get(String id) {
		return super.get(id);
	}
	
	public List<CrdCowOperation> findList(CrdCowOperation crdCowOperation) {
		return super.findList(crdCowOperation);
	}
	
	public Page<CrdCowOperation> findPage(Page<CrdCowOperation> page, CrdCowOperation crdCowOperation) {
		return super.findPage(page, crdCowOperation);
	}
	
	@Transactional(readOnly = false)
	public void save(CrdCowOperation crdCowOperation) {
		super.save(crdCowOperation);
	}
	
	@Transactional(readOnly = false)
	public void delete(CrdCowOperation crdCowOperation) {
		super.delete(crdCowOperation);
	}
	
}