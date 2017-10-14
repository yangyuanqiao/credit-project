/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.sys.service.subpro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.sys.dao.subpro.SubPropertyDao;
import com.amass.credit.modules.sys.entity.subpro.SubProperty;
import com.amass.credit.modules.sys.entity.subpro.SubPropertyVo;

/**
 * 主体属性信息Service
 * @author yangyq
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class SubPropertyService extends CrudService<SubPropertyDao, SubProperty> {

	@Autowired
	private SubPropertyDao subPropertyDao;
	public SubProperty get(String id) {
		return super.get(id);
	}
	
	public List<SubProperty> findList(SubProperty subProperty) {
		return super.findList(subProperty);
	}
	
	public Page<SubProperty> findPage(Page<SubProperty> page, SubProperty subProperty) {
		return super.findPage(page, subProperty);
	}
	
	@Transactional(readOnly = false)
	public void save(SubProperty subProperty) {
		super.save(subProperty);
	}
	
	@Transactional(readOnly = false)
	public void delete(SubProperty subProperty) {
		super.delete(subProperty);
	}
	public List<SubPropertyVo> findListBySubId(SubPropertyVo subPropertyVo) {
		return subPropertyDao.findListBySubId(subPropertyVo);
	}
}