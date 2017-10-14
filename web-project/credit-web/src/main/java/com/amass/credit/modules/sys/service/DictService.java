/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.service.CrudService;
import com.amass.credit.common.utils.JedisUtils;
import com.amass.credit.modules.sys.dao.DictDao;
import com.amass.credit.modules.sys.entity.Dict;
import com.amass.credit.modules.sys.utils.DictUtils;
import com.amass.credit.modules.sys.utils.UserUtils;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		JedisUtils.del(DictUtils.CACHE_DICT_MAP+UserUtils.getUser().getId());
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		JedisUtils.del(DictUtils.CACHE_DICT_MAP+UserUtils.getUser().getId());
	}

}
