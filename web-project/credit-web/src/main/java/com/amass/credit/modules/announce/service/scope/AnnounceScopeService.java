/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.announce.service.scope;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.announce.entity.content.AnnounceContent;
import com.amass.credit.modules.announce.entity.scope.AnnounceScope;
import com.amass.credit.modules.announce.dao.scope.AnnounceScopeDao;

/**
 * 公示范围Service
 * @author yangyq
 * @version 2017-06-23
 */
@Service
@Transactional(readOnly = true)
public class AnnounceScopeService extends CrudService<AnnounceScopeDao, AnnounceScope> {

	
	public AnnounceScope get(String id) {
		AnnounceScope announceScope = super.get(id);
		return announceScope;
	}
	
	public List<AnnounceScope> findList(AnnounceScope announceScope) {
		return super.findList(announceScope);
	}
	
	public Page<AnnounceScope> findPage(Page<AnnounceScope> page, AnnounceScope announceScope) {
		return super.findPage(page, announceScope);
	}
	
	@Transactional(readOnly = false)
	public void save(AnnounceScope announceScope) {
		super.save(announceScope);
		//List<AnnounceContent> list = announceScope.getContList();
		
	}
	
	@Transactional(readOnly = false)
	public void delete(AnnounceScope announceScope) {
		super.delete(announceScope);
	}
	
}