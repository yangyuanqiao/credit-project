/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.creditact.service.event;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.creditact.entity.event.RecordsEvent;
import com.amass.credit.modules.creditact.dao.event.RecordsEventDao;

/**
 * 事件行为信息Service
 * @author yangyq
 * @version 2017-06-21
 */
@Service
@Transactional(readOnly = true)
public class RecordsEventService extends CrudService<RecordsEventDao, RecordsEvent> {

	public RecordsEvent get(String id) {
		return super.get(id);
	}
	
	public List<RecordsEvent> findList(RecordsEvent recordsEvent) {
		return super.findList(recordsEvent);
	}
	
	public Page<RecordsEvent> findPage(Page<RecordsEvent> page, RecordsEvent recordsEvent) {
		return super.findPage(page, recordsEvent);
	}
	
	@Transactional(readOnly = false)
	public void save(RecordsEvent recordsEvent) {
		super.save(recordsEvent);
	}
	
	@Transactional(readOnly = false)
	public void delete(RecordsEvent recordsEvent) {
		super.delete(recordsEvent);
	}
	
}