/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.amass.credit.modules.sys.utils;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.amass.credit.common.service.BaseService;
import com.amass.credit.common.utils.JedisUtils;
import com.amass.credit.common.utils.SpringContextHolder;
import com.amass.credit.modules.sys.dao.AreaDao;
import com.amass.credit.modules.djorg.dao.DjOrganizationDao;
import com.amass.credit.modules.djorg.entity.DjOrganization;
import com.amass.credit.modules.sys.dao.MenuDao;
import com.amass.credit.modules.sys.dao.RoleDao;
import com.amass.credit.modules.sys.dao.UserDao;
import com.amass.credit.modules.sys.entity.Area;
import com.amass.credit.modules.sys.entity.Menu;
import com.amass.credit.modules.sys.entity.Role;
import com.amass.credit.modules.sys.entity.User;
import com.amass.credit.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.amass.credit.common.config.Global;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	//private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);*/
	private static DjOrganizationDao djorgDao = SpringContextHolder.getBean(DjOrganizationDao.class);
	
	public static final String USER_CACHE = "DJ_SERVER:userCache";//用户实例
	public static final String USER_CACHE_ID_ = "DJ_SERVER:cacheid";//登录用户id
	public static final String USER_CACHE_LOGIN_NAME_ = "DJ_SERVER:ln";//登录用户名
	public static final String USER_CACHE_LIST_BY_DJORG_ID_ = "DJ_SERVER:djorg_";//党组织id

	public static final String CACHE_ROLE_LIST = "DJ_SERVER:roleList";//权限列表
	public static final String CACHE_MENU_LIST = "DJ_SERVER:menuList";//菜单列表
	public static final String CACHE_AREA_LIST = "DJ_SERVER:areaList";//区域列表
	public static final String CACHE_DJORG_LIST = "DJ_SERVER:hrorganiztionList";//组织列表
	public static final String CACHE_MEMBER_LIST = "DJ_SERVER:djmemberList";//用户列表
	
	

	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id){
		User user = (User)JedisUtils.getObject(USER_CACHE_ID_ + id);
		if (user ==  null){
			user = userDao.get(id);
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			JedisUtils.setObject(USER_CACHE_ID_ + user.getId(), user,3600);
			JedisUtils.setObject(USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user,3600);
		}
		return user;
	}
	
	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String loginName){
		User user = (User)JedisUtils.getObject(USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null){
			user = userDao.getByLoginName(new User(null, loginName));
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			JedisUtils.setObject(USER_CACHE_ID_ + user.getId(), user,3600);
			JedisUtils.setObject(USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user,3600);
		}
		return user;
	}
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_DJORG_LIST);
		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){
		JedisUtils.del(USER_CACHE_ID_ + user.getId());
		JedisUtils.del(USER_CACHE_LOGIN_NAME_ + user.getLoginName());
		JedisUtils.del(USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
		JedisUtils.del(CACHE_ROLE_LIST+getUser().getId());
		JedisUtils.del(CACHE_MENU_LIST + user.getId());
		if (user.getDjorg() != null && user.getDjorg().getId() != null){
			JedisUtils.del(USER_CACHE_LIST_BY_DJORG_ID_ + user.getDjorg().getId());
		}
	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		Principal principal = getPrincipal();
		if (principal!=null){
			User user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	public static List<Role> getRoleList(){
		User user = getUser();
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>)getCache(CACHE_ROLE_LIST+user.getId());
		if (roleList == null){
			if (user.isAdmin()){
				roleList = roleDao.findAllList(new Role());
			}else{
				Role role = new Role();
				//只能超级管理员能添加超级管理员
				if(!UserUtils.getUser().isAdmin()){
					role.getSqlMap().put("dsf", " AND a.`id` not in ("+Global.getConfig("ADMINISTRATORID")+") ");//ADMINISTRATORID作为超级管理员的id值
				}
				roleList = roleDao.findList(role);
			}
			putCache(CACHE_ROLE_LIST+user.getId(), roleList,1800);
		}
		return roleList;
	}
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<Menu> getMenuList(){
		User user = getUser();
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST+user.getId());
		if (menuList == null){
			if (user.isAdmin()){
				menuList = menuDao.findAllList(new Menu());
			}else{
				Menu m = new Menu();
				m.setUserId(user.getId());
				menuList = menuDao.findByUserId(m);
			}
			putCache(CACHE_MENU_LIST+user.getId(), menuList,1800);
		}
		return menuList;
	}
	
	/**
	 * 获取当前用户授权的区域
	 * @return
	 */
	public static List<Area> getAreaList(){
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>)getCache(CACHE_AREA_LIST);
		if (areaList == null){
			areaList = areaDao.findAllList(new Area());
			putCache(CACHE_AREA_LIST, areaList,1800);
		}
		return areaList;
	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 *//*
	public static List<Office> getOfficeList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_LIST);
		if (officeList == null){
			User user = getUser();
			if (user.isAdmin()){
				officeList = officeDao.findAllList(new Office());
			}else{
				Office office = new Office();
				office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				officeList = officeDao.findList(office);
			}
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}

	*//**
	 * 获取当前用户有权限访问的部门
	 * @return
	 *//*
	public static List<Office> getOfficeAllList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null){
			officeList = officeDao.findAllList(new Office());
		}
		return officeList;
	}*/
	/**
	 * 获取当前用户有权范文的党组织
	 */
	public static List<DjOrganization> getDjorgList(){
		User user = getUser();
		@SuppressWarnings("unchecked")
		List<DjOrganization> djorgList = (List<DjOrganization>)getCache(CACHE_DJORG_LIST+user.getId());
		if(djorgList == null){
			if(user.isAdmin()){
				djorgList = djorgDao.findAllList(new DjOrganization());
			}
			else{
				DjOrganization djorg = new DjOrganization();
				djorg.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				djorgList = djorgDao.findList(djorg);
			}
			putCache(CACHE_DJORG_LIST+user.getId(), djorgList,1800);
		}
		return djorgList;
	}
	public static List<DjOrganization> getDjorgAllList(){
		@SuppressWarnings("unchecked")
		List<DjOrganization> djorgList = (List<DjOrganization>)getCache(CACHE_DJORG_LIST);
		if(djorgList == null){
			djorgList = djorgDao.findAllList(new DjOrganization());
			putCache(CACHE_DJORG_LIST,djorgList,86400);
		}
		return djorgList;
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
//			subject.logout();
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = JedisUtils.getObject(key);
		//Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value,int timeout) {
		JedisUtils.setObject(key, value,0);
		//getSession().setAttribute(key, value);
	}

	/**
	 * 清理缓存，清理本地会话
	 * @param key
	 */
	public static void removeCache(String key) {
//		getCacheMap().remove(key);
		JedisUtils.del(key);
		getSession().removeAttribute(key);
	}
	
//	public static Map<String, Object> getCacheMap(){
//		Principal principal = getPrincipal();
//		if(principal!=null){
//			return principal.getCacheMap();
//		}
//		return new HashMap<String, Object>();
//	}
	
}
