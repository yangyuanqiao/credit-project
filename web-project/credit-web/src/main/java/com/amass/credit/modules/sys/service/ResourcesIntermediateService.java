package com.amass.credit.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.sys.dao.ResourcesIntermediateDao;
import com.amass.credit.modules.sys.entity.ResourcesIntermediate;

@Service
@Transactional(readOnly=true)
public class ResourcesIntermediateService extends
		CrudService<ResourcesIntermediateDao, ResourcesIntermediate> {

	@Override
	public ResourcesIntermediate get(String id) {
		// TODO Auto-generated method stub
		return super.get(id);
	}

	@Override
	public ResourcesIntermediate get(ResourcesIntermediate entity) {
		// TODO Auto-generated method stub
		return super.get(entity);
	}

	@Override
	public List<ResourcesIntermediate> findList(ResourcesIntermediate entity) {
		// TODO Auto-generated method stub
		return super.findList(entity);
	}

	@Override
	public Page<ResourcesIntermediate> findPage(
			Page<ResourcesIntermediate> page, ResourcesIntermediate entity) {
		// TODO Auto-generated method stub
		return super.findPage(page, entity);
	}

	@Override
	public void save(ResourcesIntermediate entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void delete(ResourcesIntermediate entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

}
