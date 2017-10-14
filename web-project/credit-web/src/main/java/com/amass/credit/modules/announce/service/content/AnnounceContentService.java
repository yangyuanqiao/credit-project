/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.announce.service.content;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.persistence.Page;
import com.amass.credit.common.service.CrudService;
import com.amass.credit.modules.announce.entity.content.AnnounceContent;
import com.amass.credit.modules.announce.dao.content.AnnounceContentDao;

/**
 * 公示内容Service
 * @author yangyq
 * @version 2017-06-23
 */
@Service
@Transactional(readOnly = true)
public class AnnounceContentService extends CrudService<AnnounceContentDao, AnnounceContent> {

	public AnnounceContent get(String id) {
		return super.get(id);
	}
	
	public List<AnnounceContent> findList(AnnounceContent announceContent) {
		return super.findList(announceContent);
	}
	
	public Page<AnnounceContent> findPage(Page<AnnounceContent> page, AnnounceContent announceContent) {
		return super.findPage(page, announceContent);
	}
	
	@Transactional(readOnly = false)
	public void save(AnnounceContent announceContent) {
		super.save(announceContent);
	}
	
	@Transactional(readOnly = false)
	public void delete(AnnounceContent announceContent) {
		super.delete(announceContent);
	}
	
}