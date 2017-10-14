<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出租屋基础信息管理</title>
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
		<li><a href="${ctx}/renthouse/baseinfo/renthouseBase/">出租屋基础信息列表</a></li>
		<li class="active"><a href="${ctx}/renthouse/baseinfo/renthouseBase/form?id=${renthouseBase.id}">出租屋基础信息<shiro:hasPermission name="renthouse:baseinfo:renthouseBase:edit">${not empty renthouseBase.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="renthouse:baseinfo:renthouseBase:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
<%-- 	<form:form id="inputForm" modelAttribute="renthouseBase" action="${ctx}/renthouse/baseinfo/renthouseBase/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">出租屋编码：</label>
			<div class="controls">
				<form:input path="houseCode" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">门牌号：</label>
			<div class="controls">
				<form:input path="houseNum" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出租屋备案登记号：</label>
			<div class="controls">
				<form:input path="rentCode" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否安全文明出租屋：</label>
			<div class="controls">
				<form:select path="isSafeCivilized" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出租屋地址：</label>
			<div class="controls">
				<form:input path="houseAddr" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否安装监控：</label>
			<div class="controls">
				<form:select path="hasMonitor" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否购买意外保险：</label>
			<div class="controls">
				<form:select path="hasInsurance" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主体类型：</label>
			<div class="controls">
				<form:select path="subjectType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出租层数：</label>
			<div class="controls">
				<form:input path="levelNum" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="renthouse:baseinfo:renthouseBase:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form> --%>
	
	<table class="table table-striped table-bordered">
						<thead>
						</thead>
						<tbody>
						<tbody>

							<tr>
								<td width="15%" style="text-align: right"><b>出租屋编码：</b></td>
								<td width="35%">${renthouseBase.houseNum}</td>
								<td width="15%" style="text-align: right"><b>门牌号：</b></td>
								<td width="35%">${renthouseBase.houseNum}</td>
							</tr>
							<tr>
								<td width="15%" style="text-align: right"><b>出租屋地址：</b></td>
								<td width="35%">${renthouseBase.houseAddr}</td>
								<td width="15%" style="text-align: right"><b>是否安装监控：</b></td>
								<td width="35%">${fns:getDictLabel(renthouseBase.hasMonitor, 'yes_no', '')}</td>
							</tr>
							<tr>
								<td width="15%" style="text-align: right"><b>是否购买意外保险：</b></td>
								<td width="35%">${fns:getDictLabel(renthouseBase.hasInsurance, 'yes_no', '')}</td>
								<td width="15%" style="text-align: right"><b>租赁备案登记号：</b></td>
								<td width="35%">${renthouseBase.rentCode}</td>
							</tr>
							<tr>
								<td width="15%" style="text-align: right"><b>是否安全文明出租屋：</b></td>
								<td width="35%">${fns:getDictLabel(renthouseBase.isSafeCivilized, 'yes_no', '未知')} </td>
								<td width="15%" style="text-align: right"><b>出租层数：</b></td>
								<td width="35%">${renthouseBase.levelNum}</td>
							</tr>

						</tbody>


						</tbody>
					</table>
	<iframe id="jerichotabiframe_2" name="jerichotabiframe_2"
		src="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${renthouseBase.id}&type=license" frameborder="0"
		scrolling="auto" style="width: 100%; height: 370px; border: 0px;">

	</iframe>
</body>
</html>