package com.amass.credit.modules.sys.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.service.BaseService;
import com.amass.jbsms.api.client.dto.SmsRequest;
import com.amass.jbsms.api.client.dto.SmsResponse;
import com.amass.jbsms.api.client.service.SmsApi;
/**
 * 统一管理调用dubbo接口服务集合
 * @author zhiwei
 *
 */
@Service
@Transactional
public class DubboService extends BaseService {
	@Autowired
	private SmsApi smsApiImpl;
	
	public SmsResponse SendSMS(SmsRequest sm){
		return  smsApiImpl.submit(sm);
	}
}
