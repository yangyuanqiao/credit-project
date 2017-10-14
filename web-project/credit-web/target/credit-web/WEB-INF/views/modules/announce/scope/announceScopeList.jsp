<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公示范围管理</title>
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
		<li class="active"><a href="${ctx}/announce/scope/announceScope/">公示范围列表</a></li>
		<shiro:hasPermission name="announce:scope:announceScope:edit"><li><a href="${ctx}/announce/scope/announceScope/form">公示范围添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="announceScope" action="${ctx}/announce/scope/announceScope/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主体类别：</label>
				<form:select path="subType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>公示对象：</label>
				<form:select path="announSub" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('ANNOUN_SUB')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>范围编号</th> -->
				<th>主体类别</th>
				<th>公示对象</th>
				<th>公示开始时间</th>
				<th>公示结束时间</th>
				<th>公示目录范围</th>
				<th>备注</th>
				<th>创建人</th>
				<shiro:hasPermission name="announce:scope:announceScope:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="announceScope">
			<tr>
				<%-- <td>
					<a href="${ctx}/announce/scope/announceScope/form?id=${announceScope.id}">${announceScope.id}</a>
				</td> --%>
				<td>
					${fns:getDictLabel(announceScope.subType, 'SUB_TYPE', '')}
				</td>
				<td>
					${fns:getDictLabel(announceScope.announSub, 'ANNOUN_SUB', '')}
				</td>
				<td>
					
					<fmt:formatDate value="${announceScope.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<fmt:formatDate value="${announceScope.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					
				</td>
				<td>
					${announceScope.announContent}
				</td>
				<td>
					${announceScope.remark}
				</td>
				<td>
					${announceScope.createBy.name}
				</td>
				<shiro:hasPermission name="announce:scope:announceScope:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/announce/scope/announceScope/form?id=${announceScope.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/announce/scope/announceScope/delete?id=${announceScope.id}" onclick="return confirmx('确认要删除该公示范围吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>