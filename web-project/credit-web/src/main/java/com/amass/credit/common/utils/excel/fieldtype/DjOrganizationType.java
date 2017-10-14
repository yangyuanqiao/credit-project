package com.amass.credit.common.utils.excel.fieldtype;

import org.apache.commons.lang3.StringUtils;

import com.amass.credit.modules.djorg.entity.DjOrganization;
import com.amass.credit.modules.sys.utils.UserUtils;

/**
 * 字段类型转换
 * @author zhiwei
 * @version 2015-07-29
 */
public class DjOrganizationType {
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val){
		for(DjOrganization e : UserUtils.getDjorgList()){
			if(StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}
	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val){
		if(val != null && ((DjOrganization)val).getName() != null){
			return ((DjOrganization)val).getName();
		}
		return "";
	}
}
