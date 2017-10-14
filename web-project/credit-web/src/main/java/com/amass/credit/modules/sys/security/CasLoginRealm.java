package com.amass.credit.modules.sys.security;
import java.net.URLDecoder;  
import java.util.Collection;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import org.apache.commons.lang3.StringUtils;  
import org.apache.shiro.authc.AuthenticationException;  
import org.apache.shiro.authc.AuthenticationInfo;  
import org.apache.shiro.authc.AuthenticationToken;  
import org.apache.shiro.authc.SimpleAuthenticationInfo;  
import org.apache.shiro.authz.AuthorizationInfo;  
import org.apache.shiro.authz.SimpleAuthorizationInfo;  
import org.apache.shiro.cas.CasAuthenticationException;  
import org.apache.shiro.cas.CasRealm;  
import org.apache.shiro.cas.CasToken;  
import org.apache.shiro.session.Session;  
import org.apache.shiro.subject.PrincipalCollection;  
import org.apache.shiro.subject.SimplePrincipalCollection;  
import org.jasig.cas.client.authentication.AttributePrincipal;  
import org.jasig.cas.client.validation.Assertion;  
import org.jasig.cas.client.validation.TicketValidationException;  
import org.jasig.cas.client.validation.TicketValidator;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
import com.amass.credit.common.config.Global;  
import com.amass.credit.common.utils.SpringContextHolder;  
import com.amass.credit.common.web.Servlets;  
import com.amass.credit.modules.sys.entity.Menu;  
import com.amass.credit.modules.sys.entity.Role;  
import com.amass.credit.modules.sys.entity.User;  
import com.amass.credit.modules.sys.security.SystemAuthorizingRealm.Principal;  
import com.amass.credit.modules.sys.service.SystemService;  
import com.amass.credit.modules.sys.utils.LogUtils;  
import com.amass.credit.modules.sys.utils.UserUtils;  
  
  
public class CasLoginRealm extends CasRealm {  
  
    private Logger logger = LoggerFactory.getLogger(getClass());  
      
    private SystemService systemService;  
      
    /**  
     *   
     * @MethodName: doGetAuthenticationInfo   
     * @Description: 认证  
     * @param @param token  
     * @param @return  
     * @param @throws AuthenticationException  
     * @author   
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
    	    throws AuthenticationException
    	  {
    	    CasToken casToken = (CasToken)token;
    	    if (token == null) {
    	      return null;
    	    }

    	    String ticket = (String)casToken.getCredentials();
    	    if (!(org.apache.shiro.util.StringUtils.hasText(ticket))) {
    	      return null;
    	    }

    	    TicketValidator ticketValidator = ensureTicketValidator();
    	    try
    	    {
    	      Assertion casAssertion = ticketValidator.validate(ticket, getCasService());

    	      AttributePrincipal casPrincipal = casAssertion.getPrincipal();
    	      String userId = casPrincipal.getName();
    	      logger.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[] { ticket, getCasServerUrlPrefix(), userId });

    	      Map attributes = casPrincipal.getAttributes();

    	      casToken.setUserId(userId);
    	      String rememberMeAttributeName = getRememberMeAttributeName();
    	      String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);

    	      boolean isRemembered = (rememberMeStringValue != null) && (Boolean.parseBoolean(rememberMeStringValue));

    	      if (isRemembered) {
    	        casToken.setRememberMe(true);
    	      }

    	  /*    User user = null;

    	      if (user != null) {
    	        if ("0".equals(user.getNo()))
    	          throw new AuthenticationException("msg:该已帐号禁止登录.");

    	        return new SimpleAuthenticationInfo(new Principal(user, false), ticket, getName());
    	      }*/

    	      return null;
    	    }
    	    catch (TicketValidationException e) {
    	      throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
    	    }
    	  }
  
  
    /**  
     * 授权方案      
     *   
     *   
     * 在验证之后的授权信息      
     */  
      
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(  
            PrincipalCollection principals) {  
          
        Principal principal = (Principal) getAvailablePrincipal(principals);  
        // 获取当前已登录的用户  
        if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))){  
            Collection<Session> sessions = getSystemService().getSessionDao().getActiveSessions(true, principal, UserUtils.getSession());  
            if (sessions.size() > 0){  
                // 如果是登录进来的，则踢出已在线用户  
                if (UserUtils.getSubject().isAuthenticated()){  
                    for (Session session : sessions){  
                        getSystemService().getSessionDao().delete(session);  
                    }  
                }  
                // 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。  
                else{  
                    UserUtils.getSubject().logout();  
                    throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");  
                }  
            }  
        }  
        User user = getSystemService().getUserByLoginName(principal.getLoginName());  
        if (user != null) {  
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
            List<Menu> list = UserUtils.getMenuList();  
            for (Menu menu : list){  
                if (StringUtils.isNotBlank(menu.getPermission())){  
                    // 添加基于Permission的权限信息  
                    for (String permission : StringUtils.split(menu.getPermission(),",")){  
                        info.addStringPermission(permission);  
                    }  
                }  
            }  
            // 添加用户权限  
            info.addStringPermission("user");  
            // 添加用户角色信息  
            for (Role role : user.getRoleList()){  
                info.addRole(role.getName());  
            }  
            // 更新登录IP和时间  
            getSystemService().updateUserLoginInfo(user);  
            // 记录登录日志  
            LogUtils.saveLog(Servlets.getRequest(), "系统登录");  
            return info;  
        } else {  
            return null;  
        }  
    }  
      
      
      
    /**  
     * 获取系统业务对象  
     */  
    public SystemService getSystemService() {  
        if (systemService == null){  
            systemService = SpringContextHolder.getBean(SystemService.class);  
        }  
        return systemService;  
    }  
      
      
    /**  
     * 未使用客户端jar包    需要手动解析  
     */  
    public Map<String,String> getSsoUserInfo(String str){  
        Map<String,String> map = new HashMap<String, String>();  
        String str2 = str.replaceAll(" ", "").replaceAll("\t|\n", "").replace("=[",",").replace("]", "").replace("{", "").replace("}", "");  
        String[] strs = str2.split(",");  
        try {  
            for (String strl : strs) {  
                if(strl.startsWith("NAME") && strl.length()>4){  
                    map.put("NAME", URLDecoder.decode(strl.replace("NAME", ""), "UTF-8"));  
                }  
                if(strl.startsWith("LOGINNAME") && strl.length()>9){  
                    map.put("LOGINNAME", URLDecoder.decode(strl.replace("LOGINNAME", ""), "UTF-8"));  
                }  
            }  
        } catch (Exception e) {  
            // TODO: handle exception  
        }  
          
        return map;  
    }  
}  