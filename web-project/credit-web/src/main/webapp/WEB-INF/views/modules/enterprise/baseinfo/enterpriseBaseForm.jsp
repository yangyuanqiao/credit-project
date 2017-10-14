<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模具主体信息管理</title>
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
		
		function iFrameHeight(id) {
			var ifm= document.getElementById(id);
			ifm.height = ifm.contentDocument.body.scrollHeight;
		} 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/enterprise/baseinfo/enterpriseBase/">模具主体信息列表</a></li>
		<li class="active"><a href="${ctx}/enterprise/baseinfo/enterpriseBase/form?id=${enterpriseBase.id}">详细信息<shiro:hasPermission name="enterprise:baseinfo:enterpriseBase:edit">${not empty enterpriseBase.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="enterprise:baseinfo:enterpriseBase:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	
	
	<div id="myTabContent" class="tab-content">
	   	<div class="tab-pane fade in active" id="checkEventInfo">
	   		<div style="margin: 0 10px;">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th colspan="4">企业基本信息</th>
						</tr>
					</thead>
					<tbody>
		       			<tbody>
		 			
		       				<tr>
								<td width="15%" style="text-align:right"><b>企业名称：</b></td>
			       				<td width="35%">${enterpriseBase.subName}</td>
			       				<td width="15%" style="text-align:right"><b>统一信用代码：</b></td>
			       				<td width="35%">${enterpriseBase.creditNo}</td>
			       			</tr>
		       				<tr>
								<td width="15%" style="text-align:right"><b>税务登记号：</b></td>
			       				<td width="35%">${enterpriseBase.taxNo}</td>
			       				<td width="15%" style="text-align:right"><b>企业注册号：</b></td>
			       				<td width="35%">${enterpriseBase.orgRegistry}</td>
		       				</tr>
		       				<tr>
									<td width="15%" style="text-align:right"><b>组织机构代码：</b></td>
			       				<td width="35%">${enterpriseBase.orgCode}</td>
			       				<td width="15%" style="text-align:right"><b>工商登记地址：</b></td>
			       				<td width="35%">${enterpriseBase.regAddr}</td>
		       				</tr>
		       				<tr>
									<td width="15%" style="text-align:right"><b>经营范围：</b></td>
			       				<td width="35%">${enterpriseBase.businessScop}</td>
			       				<td width="15%" style="text-align:right"><b>经营方式：</b></td>
			       				<td width="35%">${fns:getDictLabel(enterpriseBase.businessWay, 'BUSINESS_WAY', '')}</td>
		       				</tr>
		       				
		       				<tr>
									<td width="15%" style="text-align:right"><b>经营类别：</b></td>
			       				<td width="35%">${fns:getDictLabel(enterpriseBase.businessType, 'BUSINESS_TYPE', '')}</td>
			       				<td width="15%" style="text-align:right"><b>成立日期：</b></td>
			       				<td width="35%"><fmt:formatDate value="${enterpriseBase.setupDate}" pattern="yyyy-MM-dd"/> </td>
		       				</tr>
		       				
		       				<tr>
									<td width="15%" style="text-align:right"><b>企业联系电话：</b></td>
			       				<td width="35%">${enterpriseBase.telNo}</td>
			       				<td width="15%" style="text-align:right"><b>是否年报：</b></td>
			       				<td width="35%">${fns:getDictLabel(enterpriseBase.isCheck, 'yes_no', '')}</td>
		       				</tr>
		       			
					</tbody>
		       			
		       			
					</tbody>
				</table>
			</div>
			<br/>
	  </div>
	  
	<iframe id="jerichotabiframe_2" name="jerichotabiframe_2"
		src="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${enterpriseBase.id}&type=opt" frameborder="0"
		scrolling="auto" style="width: 100%; height: 370px; border: 0px;">

	</iframe>
</body>
</html>