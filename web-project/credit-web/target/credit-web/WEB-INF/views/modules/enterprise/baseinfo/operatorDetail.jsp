<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>企业经营者信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${enterpriseBase.id}&type=opt">企业管理者信息</a></li>
		<li><a
			href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${enterpriseBase.id}&type=man">企业经营状况</a></li>
		<li><a href="${ctx}/sys/subpro/subProperty/detail?subType=1&subId=${enterpriseBase.id}">其它信息</a></li>
		<li ><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=1&subId=${enterpriseBase.id}">事件行为</a></li>
		<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=1&subId=${enterpriseBase.id}">处罚行为</a></li>
		<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=1&subId=${enterpriseBase.id}">评价行为</a></li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="checkEventInfo">
			<div style="margin: 0 10px;">
				<table class="table table-striped table-bordered">
					<tbody>
					<c:if test="${empty operator}">
						<div align="center">
			  					<br/>
			  					<i style="font-size: 20px"><b>无经营者信息</b></i>
			  					<br/>
			  					<br/>
			  				</div>
					
					</c:if>
					<c:if test="${!empty operator}">
							<tr>
								<td width="15%" style="text-align: right"><b>管理人员编号：</b></td>
								<td width="35%">${operator.id}</td>
								<td width="15%" style="text-align: right"><b>管理人员姓名：</b></td>
								<td width="35%">${operator.operatorName}</td>
							</tr>
							<tr>
								<td width="15%" style="text-align: right"><b>文化程度：</b></td>
								<td width="35%">${operator.operatorEdu}</td>
								<td width="15%" style="text-align: right"><b>联系电话：</b></td>
								<td width="35%">${operator.operatorTel}</td>
							</tr>
							<tr>
								<td width="15%" style="text-align: right"><b>联系地址：</b></td>
								<td width="35%">${operator.operatorAddr}</td>
								<td width="15%" style="text-align: right"><b>工作年限：</b></td>
								<td width="35%">${operator.workExperience}</td>
							</tr>
						</tr>
					
					</c:if>
						
					</tbody>
				</table>
			</div>
			<br />
		</div>
</body>
</html>