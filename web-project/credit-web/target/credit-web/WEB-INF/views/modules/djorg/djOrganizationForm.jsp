<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>组织管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/djorg/djOrganization/">组织列表</a></li>
		<li class="active"><a href="${ctx}/djorg/djOrganization/form?id=${djOrganization.id}&parent.id=${djOrganizationparent.id}"党组织<shiro:hasPermission name="djorg:djOrganization:edit">${not empty djOrganization.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="djorg:djOrganization:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="djOrganization" action="${ctx}/djorg/djOrganization/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">社区:</label>
			<div class="controls" class="input-medium">
                <sys:treeselect id="area" name="area.id" value="${djOrganization.area.id}" labelName="area.name" labelValue="${djOrganization.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="required" />
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">组织名称：</label>
			<div class="controls" class="input-medium">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上级组织:</label>
			<div class="controls" class="input-medium">
				<sys:treeselect id="parent" name="parent.id" value="${djOrganization.parent.id}" labelName="parent.name" labelValue="${djOrganization.parent.name}"
					title="父级编号" url="/djorg/djOrganization/treeData" extId="${djOrganization.id}" cssClass="required" allowClear="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls" class="input-medium"> 
				<form:input path="sort" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="djorg:djOrganization:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>