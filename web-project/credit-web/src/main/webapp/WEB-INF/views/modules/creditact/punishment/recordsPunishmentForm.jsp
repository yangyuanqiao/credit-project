<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>处罚行为信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
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
		<li><a href="${ctx}/creditact/punishment/recordsPunishment/">处罚行为信息列表</a></li>
		<li class="active"><a href="${ctx}/creditact/punishment/recordsPunishment/form?id=${recordsPunishment.id}">处罚行为信息<shiro:hasPermission name="creditact:punishment:recordsPunishment:edit">${not empty recordsPunishment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="creditact:punishment:recordsPunishment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="recordsPunishment" action="${ctx}/creditact/punishment/recordsPunishment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">主体类别：</label>
			<div class="controls">
				<form:input path="subType" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主体编号：</label>
			<div class="controls">
				<form:input path="subId" htmlEscape="false" maxlength="40" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚文书编号：</label>
			<div class="controls">
				<form:input path="punishFile" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚类别：</label>
			<div class="controls">
				<form:select path="punishType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('PUNISH_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚标题：</label>
			<div class="controls">
				<form:input path="punishTitle" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚内容：</label>
			<div class="controls">
				<form:input path="punishDesc" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚机构：</label>
			<div class="controls">
				<form:select path="punishDepart" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('DEPARMENT')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚时间：</label>
			<div class="controls">
				<form:input path="punishDate" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚状态：</label>
			<div class="controls">
				<form:select path="punishStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('PUNISH_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="creditact:punishment:recordsPunishment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>