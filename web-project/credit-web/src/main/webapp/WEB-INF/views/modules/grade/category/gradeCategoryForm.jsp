<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评分选项类别管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			 // 判断数值类型，包括整数和浮点数
		    jQuery.validator.addMethod("isNumber", function(value, element) {   
		    	 if(parseFloat(value) <= 0)
		    		 return false;
		         return this.optional(element) || /^[-\+]?\d+$/.test(value) || /^[-\+]?\d+(\.\d+)?$/.test(value) ;       
		    }, "匹配数值类型错误，包括大于0的整数和浮点数");  
			 
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				rules:{
					proportion:{
						isNumber:true
							
	                    }                
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
		<li><a href="${ctx}/grade/category/gradeCategory/">评分选项类别列表</a></li>
		<li class="active"><a href="${ctx}/grade/category/gradeCategory/form?id=${gradeCategory.id}&parent.id=${gradeCategoryparent.id}">评分选项类别<shiro:hasPermission name="grade:category:gradeCategory:edit">${not empty gradeCategory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="grade:category:gradeCategory:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gradeCategory" action="${ctx}/grade/category/gradeCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">评分主体对象：</label>
			<div class="controls">
				<form:select path="subType" class="input-xlarge required">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属上级选项类别:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${gradeCategory.parent.id}" labelName="parent.name" labelValue="${gradeCategory.parent.id}"
					title="上一级类别编号" url="/grade/category/gradeCategory/treeData" extId="${gradeCategory.id}" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">选项类别名称：</label>
			<div class="controls">
				<form:input path="cateName" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">权重(%)：</label>
			<div class="controls">
				<form:input path="proportion" htmlEscape="false" maxlength="40" class="input-xlarge" id="proportion" />
				<span class="help-inline">必须为数字 </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="400" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="grade:category:gradeCategory:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>