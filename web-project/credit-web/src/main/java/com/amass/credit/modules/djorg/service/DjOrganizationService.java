/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.djorg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.service.TreeService;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.djorg.entity.DjOrganization;
import com.amass.credit.modules.djorg.dao.DjOrganizationDao;
import com.amass.credit.modules.sys.utils.UserUtils;

/**
 * 组织Service
 * @author lzw
 * @version 2015-07-28
 */
@Service
@Transactional(readOnly = true)
public class DjOrganizationService extends TreeService<DjOrganizationDao, DjOrganization> {

	/**
	 * （若有）缓存中的组织结构
	 * @return
	 */
	public List<DjOrganization> findAll(){
		return UserUtils.getDjorgList();
	}
	/**
	 * （若有）缓存中的组织结构
	 * @return
	 */
	public List<DjOrganization> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getDjorgAllList();
		}else{
			return UserUtils.getDjorgList();
		}
	}
	public DjOrganization get(String id) {
		return super.get(id);
	}
	
	public List<DjOrganization> findList(DjOrganization djOrganization) {
		if (StringUtils.isNotBlank(djOrganization.getParentIds())){
			djOrganization.setParentIds(","+djOrganization.getParentIds()+",");
		}
		return super.findList(djOrganization);
	}
	
	@Transactional(readOnly = false)
	public void save(DjOrganization djOrganization) {
		super.save(djOrganization);
		UserUtils.removeCache(UserUtils.CACHE_DJORG_LIST);
		UserUtils.removeCache(UserUtils.CACHE_DJORG_LIST + UserUtils.getUser().getId());
	}
	
	@Transactional(readOnly = false)
	public void delete(DjOrganization djOrganization) {
		super.delete(djOrganization);
		UserUtils.removeCache(UserUtils.CACHE_DJORG_LIST);
		UserUtils.removeCache(UserUtils.CACHE_DJORG_LIST + UserUtils.getUser().getId());
	}
	
}