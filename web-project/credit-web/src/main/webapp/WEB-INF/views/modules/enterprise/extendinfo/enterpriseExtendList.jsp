<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主体扩展信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/enterprise/extendinfo/enterpriseExtend/">主体扩展信息列表</a></li>
		<shiro:hasPermission name="enterprise:extendinfo:enterpriseExtend:edit"><li><a href="${ctx}/enterprise/extendinfo/enterpriseExtend/form">主体扩展信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="enterpriseExtend" action="${ctx}/enterprise/extendinfo/enterpriseExtend/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>属性名称</th>
				<th>属性代码</th>
				<th>属性值</th>
				<th>更新时间</th>
				<th>来源渠道</th>
				<shiro:hasPermission name="enterprise:extendinfo:enterpriseExtend:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="enterpriseExtend">
			<tr>
				<td><a href="${ctx}/enterprise/extendinfo/enterpriseExtend/form?id=${enterpriseExtend.id}">
					${enterpriseExtend.extendName}
				</a></td>
				<td>
					${enterpriseExtend.extendCode}
				</td>
				<td>
					${enterpriseExtend.resultDesc}
				</td>
				<td>
					<fmt:formatDate value="${enterpriseExtend.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(enterpriseExtend.channel, '', '')}
				</td>
				<shiro:hasPermission name="enterprise:extendinfo:enterpriseExtend:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/enterprise/extendinfo/enterpriseExtend/form?id=${enterpriseExtend.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/enterprise/extendinfo/enterpriseExtend/delete?id=${enterpriseExtend.id}" onclick="return confirmx('确认要删除该主体扩展信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>