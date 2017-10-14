package com.amass.credit.common.utils;

import com.amass.credit.common.config.Global;

/**
 * 存储本系统需要用到的短信模板
 * @author liangzw
 *
 */
public class SmsTemplate {
	/**
	 * 接入系统编号
	 */
	public static final String SMSXTBH = Global.getConfig("SMSXTBH");
	
	/**
	 * 新党课模板。
	 * 内容为：新党课已经计划好啦！党课名：<PARAM1>，地址：<PARAM2>，由<PARAM3>主讲，时间：<PARAM4>，请准时参加！【厚街镇组织人事办】
	 * {@code：新党课已经计划好啦{xx}党课名{xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
   xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx}地址{xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
   xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx}由
   {xxxxxxxxxxxxxxxxxx}主讲{xx}时间{xxxxxxxxxxxxxxxxxxxxxxxxxxxx}请准时参加{xx}      其中多少个x就是长度了}
	 */
	public static final String SMS_SORT_COURSE = "0004";
}
