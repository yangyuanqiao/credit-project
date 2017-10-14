<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>牛行经营场所管理</title>
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
		<li><a href="${ctx}/cow/crdCowOperation/">牛行经营场所列表</a></li>
		<li class="active"><a href="${ctx}/cow/crdCowOperation/form?id=${crdCowOperation.id}">牛行经营场所<shiro:hasPermission name="cow:crdCowOperation:edit">${not empty crdCowOperation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cow:crdCowOperation:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<%-- <form:form id="inputForm" modelAttribute="crdCowOperation" action="${ctx}/cow/crdCowOperation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">经营场所名称：</label>
			<div class="controls">
				<form:input path="subName" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统一信用代码：</label>
			<div class="controls">
				<form:input path="creditNo" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">税务登记号：</label>
			<div class="controls">
				<form:input path="taxNo" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册号：</label>
			<div class="controls">
				<form:input path="orgRegistry" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">组织机构代码：</label>
			<div class="controls">
				<form:input path="orgCode" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工商登记地址：</label>
			<div class="controls">
				<form:input path="regAddr" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经营开始时间：</label>
			<div class="controls">
				<form:input path="setupDate" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="telNo" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="telMan" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经营牌照名称：</label>
			<div class="controls">
				<form:input path="licenseName" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经营场所面积：</label>
			<div class="controls">
				<form:input path="areaTotal" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">员工人数：</label>
			<div class="controls">
				<form:input path="numOfPeople" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">同步时间：</label>
			<div class="controls">
				<input name="creatDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${crdCowOperation.creatDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否年报：</label>
			<div class="controls">
				<form:input path="isCheck" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主体标志：</label>
			<div class="controls">
				<form:input path="subFlag" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cow:crdCowOperation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form> --%>
	
	
	<div id="myTabContent" class="tab-content">
	   	<div class="tab-pane fade in active" id="checkEventInfo">
	   		<div style="margin: 0 10px;">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th colspan="4">牛行经营主体基本信息</th>
						</tr>
					</thead>
					<tbody>
		       			<tbody>
		 			
		       				<tr>
								<td width="15%" style="text-align:right"><b>经营场所编号：</b></td>
			       				<td width="35%">${crdCowOperation.id}</td>
			       				<td width="15%" style="text-align:right"><b>经营场所名称：</b></td>
			       				<td width="35%">${crdCowOperation.subName}</td>
			       			</tr>
		       				<tr>
								<td width="15%" style="text-align:right"><b>统一信用代码：</b></td>
			       				<td width="35%">${crdCowOperation.creditNo}</td>
			       				<td width="15%" style="text-align:right"><b>税务登记号：</b></td>
			       				<td width="35%">${crdCowOperation.taxNo}</td>
		       				</tr>
		       				<tr>
									<td width="15%" style="text-align:right"><b>注册号：</b></td>
			       				<td width="35%">${crdCowOperation.orgRegistry}</td>
			       				<td width="15%" style="text-align:right"><b>组织机构代码：</b></td>
			       				<td width="35%">${crdCowOperation.orgCode}</td>
		       				</tr>
		       				<tr>
									<td width="15%" style="text-align:right"><b>工商登记地址：</b></td>
			       				<td width="35%">${crdCowOperation.regAddr}</td>
			       				<td width="15%" style="text-align:right"><b>经营开始时间：</b></td>
			       				<td width="35%"><fmt:formatDate value="${crdCowOperation.setupDate}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
		       				</tr>
		       				
		       				<tr>
									<td width="15%" style="text-align:right"><b>联系电话：</b></td>
			       				<td width="35%">${crdCowOperation.telNo}</td>
			       				<td width="15%" style="text-align:right"><b>联系人：</b></td>
			       				<td width="35%">${crdCowOperation.telMan}</td>
		       				</tr>
		       			
		       			
		       			
		       				<tr>
									<td width="15%" style="text-align:right"><b>经营牌照名称：</b></td>
			       				<td width="35%">${crdCowOperation.licenseName}</td>
			       				<td width="15%" style="text-align:right"><b>经营场所面积：</b></td>
			       				<td width="35%">${crdCowOperation.areaTotal}</td>
		       				</tr>
		       				
		       				<tr>
									<td width="15%" style="text-align:right"><b>员工人数：</b></td>
			       				<td width="35%">${crdCowOperation.numOfPeople}</td>
			       				<td width="15%" style="text-align:right"><b>同步时间：</b></td>
			       				<td width="35%">${crdCowOperation.creatDate}</td>
		       				</tr>
		       			
					</tbody>
		       			
		       			
					</tbody>
				</table>
			</div>
			<br/>
	  </div>
	<iframe id="jerichotabiframe_2" name="jerichotabiframe_2"
		src="${ctx}/sys/subpro/subProperty/detail?subType=4&subId=${crdCowOperation.id}" frameborder="0"
		scrolling="auto" style="width: 100%; height: 370px; border: 0px;">

	</iframe>
</body>
</html>