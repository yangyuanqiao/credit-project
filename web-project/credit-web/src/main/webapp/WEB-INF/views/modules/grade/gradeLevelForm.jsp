<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信用等级信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var max = $('#maxScore').val();
					var min = $('#minScore').val()
					if(parseInt(max) > parseInt(min)){
						loading('正在提交，请稍等...');
						form.submit();
					}else{
						alert("最大值不能比最小值小！");
						return ;
					}
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
		<li><a href="${ctx}/grade/gradeLevel/">信用等级信息列表</a></li>
		<li class="active">
			<a href="${ctx}/grade/gradeLevel/form?id=${gradeLevel.id}">信用等级信息
			<shiro:hasPermission name="grade:gradeLevel:edit">${not empty gradeLevel.id?'修改':'添加'}</shiro:hasPermission>
			<shiro:lacksPermission name="grade:gradeLevel:edit">查看</shiro:lacksPermission>
		</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gradeLevel" action="${ctx}/grade/gradeLevel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">信用主体对象：</label>
			<div class="controls">
				<form:select path="subjectType" class="input-xlarge required" name="subjectType">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">信用等级：</label>
			<div class="controls">
				<form:input path="levelName"  htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">信用分值范围：</label>
			<div class="controls">
				<form:input path="minScore" id="minScore" htmlEscape="false" maxlength="5"  class="input-small required" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'2')"/>
				<span class="help-inline"> 至 </span>
				<form:input path="maxScore" id="maxScore" htmlEscape="false" maxlength="5" class="input-small required" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'2')"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">等级说明：</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="5" maxlength="400" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险说明：</label>
			<div class="controls">
				<form:select path="levelCode" class="input-xlarge required">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('RISK_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信用等级排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font>用于比较等级的高低</span>
			</div>
		</div> 
		<div class="form-actions">
			<shiro:hasPermission name="grade:gradeLevel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>