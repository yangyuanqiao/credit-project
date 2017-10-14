<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<frameset>
 	<frame src="http://192.168.1.246:8088/WebReport/ReportServer?reportlet=ccms_report%2Fother%2Fhrms_mould_house.cpt&op=view&username=${user.loginName}"/>
</frameset>
</html>