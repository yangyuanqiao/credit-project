/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.service.appraise;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.creditact.entity.appraise.RecordsAppraise;
import com.amass.credit.modules.creditact.dao.appraise.RecordsAppraiseDao;

/**
 * 评价行为信息Service
 * @author yangyq
 * @version 2017-06-21
 */
@Service
@Transactional(readOnly = true)
public class RecordsAppraiseService extends CrudService<RecordsAppraiseDao, RecordsAppraise> {

	public RecordsAppraise get(String id) {
		return super.get(id);
	}
	
	public List<RecordsAppraise> findList(RecordsAppraise recordsAppraise) {
		return super.findList(recordsAppraise);
	}
	
	public Page<RecordsAppraise> findPage(Page<RecordsAppraise> page, RecordsAppraise recordsAppraise) {
		return super.findPage(page, recordsAppraise);
	}
	
	@Transactional(readOnly = false)
	public void save(RecordsAppraise recordsAppraise) {
		super.save(recordsAppraise);
	}
	
	@Transactional(readOnly = false)
	public void delete(RecordsAppraise recordsAppraise) {
		super.delete(recordsAppraise);
	}
	
}