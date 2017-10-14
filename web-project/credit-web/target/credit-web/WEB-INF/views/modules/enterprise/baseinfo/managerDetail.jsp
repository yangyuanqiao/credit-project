<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经营状况信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${enterpriseBase.id}&type=opt">企业管理者信息</a></li>
		<li   class="active" ><a href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${enterpriseBase.id}&type=man">企业经营状况</a></li>
		<li ><a href="${ctx}/sys/subpro/subProperty/detail?subType=1&subId=${enterpriseBase.id}">其它信息</a></li>
		<li><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=1&subId=${enterpriseBase.id}">事件行为</a></li>
		<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=1&subId=${enterpriseBase.id}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=1&subId=${enterpriseBase.id}">评价行为</a></li>
	</ul>
	<br />
	<div id="myTabContent" class="tab-content">
	   	<div class="tab-pane fade in active" id="checkEventInfo">
	   		<div style="margin: 0 10px;">
				<table class="table table-striped table-bordered">
					<thead>
					</thead>
					<tbody>
		 			<c:if test="${empty manage}">
						<div align="center">
			  					<br/>
			  					<i style="font-size: 20px"><b>经营状况信息</b></i>
			  					<br/>
			  					<br/>
			  				</div>
					
					</c:if>
					<c:if test="${!empty manage}">
							<tr>
							<td colspan="3"><p style="color:red;">温馨提示：经营状况为企业就近一年的经营状况，包含以下信息：</p></td>
							</tr>
		       				<tr>
								<td width="15%" style="text-align:right"><b>年业务收入(万)：</b></td>
			       				<td width="35%"><fmt:formatNumber value="${manage.income}"  type="currency"/></td>
			       				<td width="15%" style="text-align:right"><b>资产负债率(%)：</b></td>
			       				<td width="35%"><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${manage.deptProfit}" /></td></td>
			       			</tr>
		       				<tr>
								<td width="15%" style="text-align:right"><b>主营业务利润率(%)：</b></td>
			       				<td width="35%"><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${manage.mainProfit}" /></td>
			       				<td width="15%" style="text-align:right"><b>总资产增长率(%)：</b></td>
			       				<td width="35%"><fmt:formatNumber type="percent" maxIntegerDigits="3" value="${manage.assetsProfit}" /></td>
		       				</tr>
		       				<tr>
								<td width="15%" style="text-align:right"><b>资产总额(万)：</b></td>
			       				<td width="35%"><fmt:formatNumber value="${manage.totalAssets}"  type="currency"/></td>
			       				<td width="15%" style="text-align:right"><b>营业收入增长率(%)：</b></td>
			       				<td width="35%"> <fmt:formatNumber type="percent" maxIntegerDigits="3" value="${manage.increaseProfit}" /></td>
		       				</tr>
		       				<tr>
								<td width="15%" style="text-align:right"><b>经营年份：</b></td>
			       				<td width="35%">${manage.happenYear}</td>
			       				<td width="15%" style="text-align:right"><b>同步时间：</b></td>
			       				<td width="35%"><fmt:formatDate value="${manage.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		       				</tr>
		       		</c:if>	
					</tbody>
				</table>
			</div>
			<br/>
	  </div>
</body>
</html>