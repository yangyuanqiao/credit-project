<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公示内容管理</title>
	<script type="text/javascript" src="${ctxStatic}/ckeditor/ckeditor.js"></script>
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
		<li><a href="${ctx}/announce/content/announceContent/">公示目录列表</a></li>
		<li class="active"><a href="${ctx}/announce/content/announceContent/form?id=${announceContent.id}">公示目录<shiro:hasPermission name="announce:content:announceContent:edit">${not empty announceContent.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="announce:content:announceContent:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="announceContent" action="${ctx}/announce/content/announceContent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">主体类别：</label>
			<div class="controls">
				<form:select path="subType" class="input-xlarge required">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公示目录：</label>
			<div class="controls">
				<form:input path="contentName" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			<div class="control-group">
			<label class="control-label">目录描述：</label>
			<div class="controls">
				<form:input path="contentDesc" htmlEscape="false" maxlength="225" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目录内容模板：</label>
			<div class="controls"  style="width: 50%;">
				<textarea name="contentTemplate" id="contentTemplate" class="ckeditor input-xlarge">${announceContent.contentTemplate}</textarea>
				<span class="help-inline"> &nbsp;&nbsp;图片请使用超链接的方式引用进来</span>
			</div>
		</div>
	
		<%-- <div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="announce:content:announceContent:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>