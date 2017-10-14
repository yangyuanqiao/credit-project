<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公示内容管理</title>
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
		<li class="active"><a href="${ctx}/announce/content/announceContent/">公示目录列表</a></li>
		<shiro:hasPermission name="announce:content:announceContent:edit"><li><a href="${ctx}/announce/content/announceContent/form">公示目录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="announceContent" action="${ctx}/announce/content/announceContent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主体类别：</label>
				<form:select path="subType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>公示目录：</label>
				<form:input path="contentName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>内容编号</th> -->
				<th>主体类别</th>
				<th>公示目录</th>
				<th>目录描述</th>
				<th>备注</th>
				<th>排序</th>
				<th>创建人</th>
				<th>创建时间</th>
				<shiro:hasPermission name="announce:content:announceContent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="announceContent">
			<tr>
				<%-- <td>
					<a href="${ctx}/announce/content/announceContent/form?id=${announceContent.id}">${announceContent.id}</a>
				</td> --%>
				<td>
					${fns:getDictLabel(announceContent.subType, 'SUB_TYPE', '')}
				</td>
				<td>
					${announceContent.contentName}
				</td>
				<td>
					${announceContent.contentDesc}
				</td>
				<td>
					${announceContent.remark}
				</td>
				<td>
					${announceContent.sort}
				</td>
				<td>
					${announceContent.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${announceContent.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="announce:content:announceContent:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/announce/content/announceContent/form?id=${announceContent.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/announce/content/announceContent/delete?id=${announceContent.id}" onclick="return confirmx('确认要删除该公示内容吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>