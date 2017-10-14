<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评价行为信息管理</title>
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
		<li><a href="${ctx}/creditact/appraise/recordsAppraise/">评价行为信息列表</a></li>
		<li class="active"><a href="${ctx}/creditact/appraise/recordsAppraise/form?id=${recordsAppraise.id}">评价行为信息<shiro:hasPermission name="creditact:appraise:recordsAppraise:edit">${not empty recordsAppraise.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="creditact:appraise:recordsAppraise:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="recordsAppraise" action="${ctx}/creditact/appraise/recordsAppraise/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">主体编号：</label>
			<div class="controls">
				<form:input path="subId" htmlEscape="false" maxlength="40" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主体类别：</label>
			<div class="controls">
				<form:input path="subType" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主体名称：</label>
			<div class="controls">
				<form:input path="subName" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">被评定类别：</label>
			<div class="controls">
				<form:select path="actType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('APPRAISE_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评定标题：</label>
			<div class="controls">
				<form:input path="gainTitle" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评定内容：</label>
			<div class="controls">
				<form:input path="gainContent" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评定结果：</label>
			<div class="controls">
				<form:input path="gainResult" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评定时间：</label>
			<div class="controls">
				<form:input path="gainDate" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评定部门：</label>
			<div class="controls">
				<form:select path="department" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('DEPARMENT')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评定状态：</label>
			<div class="controls">
				<form:input path="statue" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="creditact:appraise:recordsAppraise:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>