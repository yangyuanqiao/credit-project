package com.amass.credit.common.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amass.credit.modules.sys.service.DubboService;
import com.amass.jbsms.api.client.dto.SmsRequest;
import com.amass.jbsms.api.client.dto.SmsResponse;

/**
 * 调用短信群发接口
 * 
 * @author liangzw
 *
 */
public class SmsUtil {

	
	@Autowired
	DubboService dubboService;

	public String sendSMS(List<String> tels, String smstemplate,List<String> params,String xtbh){
		StringBuilder resp_msg = new StringBuilder("");
		dubboService = SpringContextHolder.getBean(DubboService.class);
		SmsRequest sms = new SmsRequest();
		//系统编号
		sms.setXtbh(xtbh);
		sms.setTemplate(smstemplate);//短信模板
		String msgparams="";
		//设定消息体内容
		for(int i = 0,size = params.size();i<size;i++){
			msgparams+=params.get(i)+"#";
		}
		msgparams = msgparams.substring(0, msgparams.length()-1);
		sms.setMsgparams(msgparams);//set参数内容
		sms.setSchtime("");//预约发送时间

		//每次最多一千条，多于1000条分批次发送
		int k = tels.size()/1000;
		for(int r=0;r<=k;r++){

			//检查是否整千,是跳出
			if((r)*1000 == tels.size())
				break;
			StringBuilder telnums = new StringBuilder();
			//剩余小于1000条
			if(k == r)
				for(int i=0,left=(tels.size()-k*1000);i<left;i++){
					telnums.append(tels.get(r*1000+i));
					telnums.append("#");
				}
			//剩余大于1000条
			else
				for(int i=0;i<1000;i++){
					telnums.append(tels.get(r*1000+i));
					telnums.append("#");
				}
			//去除结尾的”#“
			telnums.replace(telnums.length()-1, telnums.length(), "");
			sms.setTelnos(telnums.toString());
			//发送短信
			SmsResponse smsrespo = dubboService.SendSMS(sms);
			if(!"0".equals(smsrespo.getResult()))
				resp_msg.append(smsrespo.getDescription()+" 短信异常代码：" + smsrespo.getResult());
		}
		return resp_msg.toString();
	}
	
}
