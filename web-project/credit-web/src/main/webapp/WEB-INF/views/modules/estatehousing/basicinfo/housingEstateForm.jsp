<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>小区楼盘管理</title>
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
		<li><a href="${ctx}/estatehousing/basicinfo/housingEstate/">小区楼盘列表</a></li>
		<li class="active"><a href="${ctx}/estatehousing/basicinfo/housingEstate/form?id=${housingEstate.id}">小区楼盘<shiro:hasPermission name="estatehousing:basicinfo:housingEstate:edit">${not empty housingEstate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="estatehousing:basicinfo:housingEstate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	
	
	<div id="myTabContent" class="tab-content">
	   	<div class="tab-pane fade in active" id="checkEventInfo">
	   		<div style="margin: 0 10px;">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th colspan="4">小区基本信息</th>
						</tr>
					</thead>
					<tbody>
		       			<tbody>
		 			
		       				<tr>
								<td width="15%" style="text-align:right"><b>楼盘名称：</b></td>
			       				<td width="35%">${housingEstate.name}</td>
			       				<td width="15%" style="text-align:right"><b>楼盘编号：</b></td>
			       				<td width="35%">${housingEstate.serialNumber}</td>
			       			</tr>
		       				<tr>
								<td width="15%" style="text-align:right"><b>开发商名：</b></td>
			       				<td width="35%">${housingEstate.developer}</td>
			       				<td width="15%" style="text-align:right"><b>楼盘地址：</b></td>
			       				<td width="35%">${housingEstate.address}</td>
		       				</tr>
		       				<tr>
									<td width="15%" style="text-align:right"><b>楼盘期数：</b></td>
			       				<td width="35%">${housingEstate.periods}</td>
			       				<td width="15%" style="text-align:right"><b>开盘日期：</b></td>
			       				<td width="35%"><fmt:formatDate value="${housingEstate.openDate}" pattern="yyyy-MM-dd"/> </td>
		       				</tr>
		       				<tr>
									<td width="15%" style="text-align:right"><b>容积率：</b></td>
			       				<td width="35%">${housingEstate.plotRatio}</td>
			       				<td width="15%" style="text-align:right"><b>绿化率：</b></td>
			       				<td width="35%">${housingEstate.greeningRate}</td>
		       				</tr>
		       				
		       				<tr>
									<td width="15%" style="text-align:right"><b>产权年限：</b></td>
			       				<td width="35%">${housingEstate.yearTerm}</td>
			       				<td width="15%" style="text-align:right"><b>规划户数：</b></td>
			       				<td width="35%">${housingEstate.useCount}</td>
		       				</tr>
		       			
		       			
					</tbody>
		       			
		       			
					</tbody>
				</table>
			</div>
			<br/>
	  </div>
	<iframe id="jerichotabiframe_2" name="jerichotabiframe_2"
		src="${ctx}/sys/subpro/subProperty/detail?subType=3&subId=${housingEstate.id}" frameborder="0"
		scrolling="auto" style="width: 100%; height: 370px; border: 0px;">

	</iframe>
</body>
</html>