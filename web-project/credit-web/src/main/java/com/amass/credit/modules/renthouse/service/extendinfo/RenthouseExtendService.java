/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.renthouse.service.extendinfo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.renthouse.entity.extendinfo.RenthouseExtend;
import com.amass.credit.modules.renthouse.dao.extendinfo.RenthouseExtendDao;

/**
 * 出租屋扩展信息Service
 * @author yangyq
 * @version 2017-06-22
 */
@Service
@Transactional(readOnly = true)
public class RenthouseExtendService extends CrudService<RenthouseExtendDao, RenthouseExtend> {

	public RenthouseExtend get(String id) {
		return super.get(id);
	}
	
	public List<RenthouseExtend> findList(RenthouseExtend renthouseExtend) {
		return super.findList(renthouseExtend);
	}
	
	public Page<RenthouseExtend> findPage(Page<RenthouseExtend> page, RenthouseExtend renthouseExtend) {
		return super.findPage(page, renthouseExtend);
	}
	
	@Transactional(readOnly = false)
	public void save(RenthouseExtend renthouseExtend) {
		super.save(renthouseExtend);
	}
	
	@Transactional(readOnly = false)
	public void delete(RenthouseExtend renthouseExtend) {
		super.delete(renthouseExtend);
	}
	
}