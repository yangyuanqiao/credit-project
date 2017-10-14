/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.common.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.amass.credit.common.utils.StringUtils;
import com.amass.credit.modules.djorg.entity.DjOrganization;
import com.amass.credit.modules.sys.entity.Role;
import com.amass.credit.modules.sys.entity.User;
import com.google.common.collect.Lists;

/**
 * Service基类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class BaseService {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 根据用户角色数据范围过滤(党组织)
	 * @param user 当前用户对象，通过“entity.getCurrentUser()”获取
	 * @param officeAlias 机构表别名，多个用“,”逗号隔开。
	 * @param mainAlias 主表别名，多个用“,”逗号隔开，传递空，忽略此参数
	 * @return 标准连接条件对象
	 */
	public static String dataScopeFilter(User user, String officeAlias, String userAlias) {

		StringBuilder sqlString = new StringBuilder();
		
		// 进行权限过滤，多个角色权限范围之间为或者关系。
		List<String> dataScope = Lists.newArrayList();
		
		// 超级管理员，跳过权限过滤
		if (!user.isAdmin()){
			boolean isDataScopeAll = false;
			for (Role r : user.getRoleList()){
				for (String oa : StringUtils.split(officeAlias, ",")){
					if (!dataScope.contains(r.getDataScope()) && StringUtils.isNotBlank(oa)){
						if (Role.DATA_SCOPE_ALL.equals(r.getDataScope())){
							isDataScopeAll = true;
						}
						else if (Role.DATA_SCOPE_THIS_AND_CHILD.equals(r.getDataScope())){
							sqlString.append(" OR " + oa + ".id = '" + user.getDjorg().getId() + "'");
							sqlString.append(" OR " + oa + ".parent_ids LIKE '" + user.getDjorg().getParentIds() + user.getDjorg().getId() + ",%'");
						}
						else if (Role.DATA_SCOPE_THIS.equals(r.getDataScope())){
							sqlString.append(" OR " + oa + ".id = '" + user.getDjorg().getId() + "'");
						}
						/*else if (Role.DATA_SCOPE_OFFICE_AND_CHILD.equals(r.getDataScope())){
							sqlString.append(" OR " + oa + ".id = '" + user.getOffice().getId() + "'");
							sqlString.append(" OR " + oa + ".parent_ids LIKE '" + user.getOffice().getParentIds() + user.getOffice().getId() + ",%'");
						}
						else if (Role.DATA_SCOPE_OFFICE.equals(r.getDataScope())){
							sqlString.append(" OR " + oa + ".id = '" + user.getOffice().getId() + "'");
						}*/
						//else if (Role.DATA_SCOPE_SELF.equals(r.getDataScope())){
						dataScope.add(r.getDataScope());
					}
				}
			}
			// 如果没有全部数据权限，并设置了用户别名，则当前权限为本人；如果未设置别名，当前无权限为已植入权限
			if (!isDataScopeAll){
				if (StringUtils.isNotBlank(userAlias)){
					for (String ua : StringUtils.split(userAlias, ",")){
						sqlString.append(" OR " + ua + ".id = '" + user.getId() + "'");
					}
				}else {
					for (String oa : StringUtils.split(officeAlias, ",")){
						//sqlString.append(" OR " + oa + ".id  = " + user.getOffice().getId());
						sqlString.append(" OR " + oa + ".id IS NULL");
					}
				}
			}else{
				// 如果包含全部权限，则去掉之前添加的所有条件，并跳出循环。
				sqlString = new StringBuilder();
			}
		}
		if (StringUtils.isNotBlank(sqlString.toString())){
			return " AND (" + sqlString.substring(4) + ")";
		}
		return "";
	}
	/**
	 * 组织数据范围过滤
	 * @param scope 数据范围
	 * @param oas 若涉及多个党组织表则使用","隔开多个表的代名
	 * @return 标准连接条件对象
	 */
	public static String orgScopeFilter(String scope, String oas, DjOrganization org) {

		StringBuilder sqlString = new StringBuilder();
		
		
		for (String oa : StringUtils.split(oas, ",")){
				if (Role.DATA_SCOPE_THIS_AND_CHILD.equals(scope)){
					sqlString.append(" OR " + oa + ".id = '" + org.getId() + "'");//党组织的id
					//父表的集合，使用like检索
					sqlString.append(" OR " + oa + ".parent_ids LIKE '" + org.getParentIds() + org.getId() + ",%'");
				}
				else if (Role.DATA_SCOPE_THIS.equals(scope)){
					sqlString.append(" OR " + oa + ".id = '" + org.getId() + "'");
				}
		}
		if (StringUtils.isNotBlank(sqlString.toString())){
			return " AND (" + sqlString.substring(4) + ")";
		}
		return "";
	}
	/**
	 * 未知信息过滤
	 * @param user 本用户
	 * @param aas 地区表别名，使用“,”隔开
	 * @return
	 */
	public static String areaScopeFilter(User user, String aas){
		StringBuilder sqlString = new StringBuilder();
		
		// 进行权限过滤，多个角色权限范围之间为或者关系。
		List<String> dataScope = Lists.newArrayList();
		
		// 超级管理员，跳过权限过滤
		if (!user.isAdmin()){
			boolean isDataScopeAll = false;
			for (Role r : user.getRoleList()){
				for (String oa : StringUtils.split(aas, ",")){
					if (!dataScope.contains(r.getDataScope()) && StringUtils.isNotBlank(oa)){
						if (Role.DATA_SCOPE_ALL.equals(r.getDataScope())){
							isDataScopeAll = true;
						}
						else if (Role.DATA_SCOPE_THIS_AND_CHILD.equals(r.getDataScope())){
							sqlString.append(" OR " + oa + ".id = '" + user.getDjorg().getArea().getId() + "'");
							sqlString.append(" OR " + oa + ".parent_ids LIKE '" + user.getDjorg().getArea().getParentIds() + user.getDjorg().getArea().getId() + ",%'");
						}
						else if (Role.DATA_SCOPE_THIS.equals(r.getDataScope())){
							sqlString.append(" OR " + oa + ".id = '" + user.getDjorg().getArea().getId() + "'");
						}
						/*else if (Role.DATA_SCOPE_OFFICE_AND_CHILD.equals(r.getDataScope())){
							sqlString.append(" OR " + oa + ".id = '" + user.getOffice().getId() + "'");
							sqlString.append(" OR " + oa + ".parent_ids LIKE '" + user.getOffice().getParentIds() + user.getOffice().getId() + ",%'");
						}
						else if (Role.DATA_SCOPE_OFFICE.equals(r.getDataScope())){
							sqlString.append(" OR " + oa + ".id = '" + user.getOffice().getId() + "'");
						}*/
						//else if (Role.DATA_SCOPE_SELF.equals(r.getDataScope())){
						dataScope.add(r.getDataScope());
					}
				}
				if (!isDataScopeAll){
					
					for (String oa : StringUtils.split(aas, ",")){
						//sqlString.append(" OR " + oa + ".id  = " + user.getOffice().getId());
						sqlString.append(" OR " + oa + ".id IS NULL");
					}
				
			}
			}
		}
		if (StringUtils.isNotBlank(sqlString.toString())){
			return " AND (" + sqlString.substring(4) + ")";
		}
		return "";
	}
	
}
