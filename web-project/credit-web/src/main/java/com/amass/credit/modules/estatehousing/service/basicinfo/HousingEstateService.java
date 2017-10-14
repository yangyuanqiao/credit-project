/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.estatehousing.service.basicinfo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.estatehousing.entity.basicinfo.HousingEstate;
import com.amass.credit.modules.estatehousing.dao.basicinfo.HousingEstateDao;

/**
 * 小区楼盘Service
 * @author yangyq
 * @version 2017-08-15
 */
@Service
@Transactional(readOnly = true)
public class HousingEstateService extends CrudService<HousingEstateDao, HousingEstate> {

	public HousingEstate get(String id) {
		return super.get(id);
	}
	
	public List<HousingEstate> findList(HousingEstate housingEstate) {
		return super.findList(housingEstate);
	}
	
	public Page<HousingEstate> findPage(Page<HousingEstate> page, HousingEstate housingEstate) {
		return super.findPage(page, housingEstate);
	}
	
	@Transactional(readOnly = false)
	public void save(HousingEstate housingEstate) {
		super.save(housingEstate);
	}
	
	@Transactional(readOnly = false)
	public void delete(HousingEstate housingEstate) {
		super.delete(housingEstate);
	}
	
}