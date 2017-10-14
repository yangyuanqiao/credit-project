<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公示范围管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			 jQuery.validator.methods.compareDate = function(value, element, param) { 
		            //var startDate = jQuery(param).val() + ":00";补全yyyy-MM-dd HH:mm:ss格式 
		            //value = value + ":00"; 
		            
		            var startDate = $("#beginTime").val();
		            var endDate = $("#endTime").val(); 
		            var date1 = new Date(Date.parse(startDate.replace("-", "/"))); 
		            var date2 = new Date(Date.parse(endDate.replace("-", "/"))); 
		            return date1 < date2; 
        	}; 
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				 rules:{  
		                "endTime": { 
		                    compareDate: true
		                } 
		            }, 
		            messages:{ 
		                "endTime":{  
		                    compareDate: "结束日期必须大于开始日期!" 
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
		<li><a href="${ctx}/announce/scope/announceScope/">公示范围列表</a></li>
		<li class="active"><a href="${ctx}/announce/scope/announceScope/form?id=${announceScope.id}">公示范围<shiro:hasPermission name="announce:scope:announceScope:edit">${not empty announceScope.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="announce:scope:announceScope:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="announceScope" action="${ctx}/announce/scope/announceScope/save" method="post" class="form-horizontal">
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
			<label class="control-label">公示对象：</label>
			<div class="controls">
				<form:select path="announSub" class="input-xlarge required">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('ANNOUN_SUB')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
	<%-- 	<div class="control-group">
			<label class="control-label">公示期限：</label>
			<div class="controls">
				<form:input path="announDate" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">公示开始时间：</label>
			<div class="controls">
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate"
					value="<fmt:formatDate value="${announceScope.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公示结束时间：</label>
			<div class="controls">
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-xlarge Wdate"
					value="<fmt:formatDate value="${announceScope.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公示目录范围：</label>
			<div class="controls">
				<%-- <form:input path="announContent" htmlEscape="false" maxlength="40" class="input-xlarge "/> --%>
				<form:select path="contList" class="input-xlarge required">
					<form:options items="${contentList}" itemLabel="contentName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font>公示目录可以选择多个 </span>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="announce:scope:announceScope:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>