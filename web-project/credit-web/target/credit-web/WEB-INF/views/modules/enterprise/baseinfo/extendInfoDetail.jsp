<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>其它信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${enterpriseBase.id}&type=opt">企业管理者信息</a></li>
		<li><a
			href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${enterpriseBase.id}&type=man">企业经营状况</a></li>
		<li class="active"><a href="${ctx}/sys/subpro/subProperty/detail?subType=1&subId=${enterpriseBase.id}">其它信息</a></li>
		<li ><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=1&subId=${enterpriseBase.id}">事件行为</a></li>
		
	</ul>
	<br />
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="checkEventInfo">
			<div style="margin: 0 10px;">
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>属性名称</th>
							<th>属性代码</th>
							<th>属性结果</th>
							<th>更新时间</th>
							<th>来源渠道</th>
							<shiro:hasPermission
								name="enterprise:extendinfo:enterpriseExtend:edit">
								<th>操作</th>
							</shiro:hasPermission>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${enterpriseExtendList}" var="enterpriseExtend">
							<tr>
								<td>
										${enterpriseExtend.extendName} </td>
								<td>${enterpriseExtend.extendCode}</td>
								<td>${enterpriseExtend.resultDesc}</td>
								<td><fmt:formatDate value="${enterpriseExtend.updateDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${fns:getDictLabel(enterpriseExtend.channel, 'DATA_CHANNEL', '')}</td>
								<shiro:hasPermission
									name="enterprise:extendinfo:enterpriseExtend:edit">
									<td><a
										href="${ctx}/enterprise/extendinfo/enterpriseExtend/form?id=${enterpriseExtend.id}">修改</a>
										<a
										href="${ctx}/enterprise/extendinfo/enterpriseExtend/delete?id=${enterpriseExtend.id}"
										onclick="return confirmx('确认要删除该主体扩展信息吗？', this.href)">删除</a>
									</td>
								</shiro:hasPermission>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<br />
		</div>
</body>
</html>