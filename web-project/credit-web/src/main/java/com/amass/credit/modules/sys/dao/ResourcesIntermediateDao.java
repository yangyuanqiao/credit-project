package com.amass.credit.modules.sys.dao;

import com.amass.credit.common.persistence.annotation.MyBatisDao;
import com.amass.credit.common.persistence.CrudDao;
import com.amass.credit.modules.sys.entity.ResourcesIntermediate;

@MyBatisDao
public interface ResourcesIntermediateDao extends CrudDao<ResourcesIntermediate> {

	void deletebyIds(ResourcesIntermediate del);

}
