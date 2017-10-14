<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>出租屋租赁信息表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
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

		<li><a
			href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${renthouseBase.id}&type=license">许可证信息</a></li>
		<li class="active"><a
			href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${renthouseBase.id}&type=leasecred">出租屋租赁信息</a></li>
		<li><a
			href="${ctx}/sys/subpro/subProperty/detail?subType=2&subId=${renthouseBase.id}">其它信息</a></li>
			<li><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=2&subId=${renthouseBase.id}">事件行为</a></li>
				<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=2&subId=${renthouseBase.id}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=2&subId=${renthouseBase.id}">评价行为</a></li>
	</ul>
	<br />



	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="checkEventInfo">
			<div style="margin: 0 10px;">

				<c:if test="${empty leasecred}">
					<div align="center">
						<br /> <i style="font-size: 20px"><b>无出租屋租赁信息</b></i> <br />
					</div>

				</c:if>
				<c:if test="${!empty leasecred}">
					<table class="table table-striped table-bordered">
						<thead>
						</thead>
						<tbody>
						<tbody>

							<tr>
								<td width="15%" style="text-align: right"><b>出租人姓名：</b></td>
								<td width="35%">${leasecred.ownerId}</td>
								<td width="15%" style="text-align: right"><b>出租人是否业主：</b></td>
								<td width="35%">${leasecred.ownerFlag}</td>
							</tr>
							<tr>
								<td width="15%" style="text-align: right"><b>出租人现居住地址：</b></td>
								<td width="35%">${leasecred.owerAddr}</td>
								<td width="15%" style="text-align: right"><b>租赁开始时间：</b></td>
								<td width="35%"><fmt:formatDate value="${leasecred.rentSdate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
							</tr>
							<tr>
								<td width="15%" style="text-align: right"><b>租赁结束时间：</b></td>
								<td width="35%"><fmt:formatDate value="${leasecred.rentEdate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
								<td width="15%" style="text-align: right"><b>租赁备案登记号：</b></td>
								<td width="35%">${leasecred.rentCode}</td>
							</tr>
							<tr>
								<td width="15%" style="text-align: right"><b>出租面积：</b></td>
								<td width="35%">${leasecred.rentArea}</td>
								<td width="15%" style="text-align: right"><b>出租屋用途：</b></td>
								<td width="35%">${leasecred.rentUse}</td>
							</tr>

							<tr>
								<td width="15%" style="text-align: right"><b>出租部位：</b></td>
								<td width="35%">${leasecred.rentPart}</td>
								<td width="15%" style="text-align: right"><b>土地房屋权证号：</b></td>
								<td width="35%">${leasecred.landhouseCred}</td>
							</tr>

							<tr>
								<td width="15%" style="text-align: right"><b>房屋所有权证号：</b></td>
								<td width="35%">${leasecred.houseCred}</td>
								<td width="15%" style="text-align: right"><b>土地使用证号：</b></td>
								<td width="35%">${leasecred.landCred}</td>
							</tr>

						</tbody>


						</tbody>
					</table>
				</c:if>
			</div>
			<br />
		</div>
</body>
</html>