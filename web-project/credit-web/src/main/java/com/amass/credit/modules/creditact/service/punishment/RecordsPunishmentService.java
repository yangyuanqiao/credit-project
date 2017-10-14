/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.service.punishment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.creditact.entity.punishment.RecordsPunishment;
import com.amass.credit.modules.creditact.dao.punishment.RecordsPunishmentDao;

/**
 * 处罚行为信息Service
 * @author yangyq
 * @version 2017-06-21
 */
@Service
@Transactional(readOnly = true)
public class RecordsPunishmentService extends CrudService<RecordsPunishmentDao, RecordsPunishment> {

	public RecordsPunishment get(String id) {
		return super.get(id);
	}
	
	public List<RecordsPunishment> findList(RecordsPunishment recordsPunishment) {
		return super.findList(recordsPunishment);
	}
	
	public Page<RecordsPunishment> findPage(Page<RecordsPunishment> page, RecordsPunishment recordsPunishment) {
		return super.findPage(page, recordsPunishment);
	}
	
	@Transactional(readOnly = false)
	public void save(RecordsPunishment recordsPunishment) {
		super.save(recordsPunishment);
	}
	
	@Transactional(readOnly = false)
	public void delete(RecordsPunishment recordsPunishment) {
		super.delete(recordsPunishment);
	}
	
}