<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主体属性信息管理</title>
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
		<li class="active"><a href="${ctx}/sys/subpro/subProperty/">主体属性信息列表</a></li>
		<shiro:hasPermission name="sys:subpro:subProperty:edit"><li><a href="${ctx}/sys/subpro/subProperty/form">主体属性信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="subProperty" action="${ctx}/sys/subpro/subProperty/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>主体类别：</label>
				<form:select path="subType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>项目名称：</label>
				<form:input path="itemName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>项目代码：</label>
				<form:input path="itemCode" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remark" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主体类别</th>
				<th>项目名称</th>
				<th>项目代码</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="sys:subpro:subProperty:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="subProperty">
			<tr>
				<td><a href="${ctx}/sys/subpro/subProperty/form?id=${subProperty.id}">
					${fns:getDictLabel(subProperty.subType, 'SUB_TYPE', '')}
				</a></td>
				<td>
					${subProperty.itemName}
				</td>
				<td>
					${subProperty.itemCode}
				</td>
				<td>
					${subProperty.remark}
				</td>
				<td>
					<fmt:formatDate value="${subProperty.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sys:subpro:subProperty:edit"><td>
    				<a href="${ctx}/sys/subpro/subProperty/form?id=${subProperty.id}">修改</a>
					<a href="${ctx}/sys/subpro/subProperty/delete?id=${subProperty.id}" onclick="return confirmx('确认要删除该主体属性信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>