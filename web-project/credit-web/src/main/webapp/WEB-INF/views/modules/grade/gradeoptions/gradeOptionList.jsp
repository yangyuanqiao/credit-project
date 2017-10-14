<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评分选项值管理</title>
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
		<li class="active"><a href="${ctx}/grade/gradeoptions/gradeOption/">评分选项值列表</a></li>
		<shiro:hasPermission name="grade:gradeoptions:gradeOption:edit"><li><a href="${ctx}/grade/gradeoptions/gradeOption/form">评分选项值添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gradeOption" action="${ctx}/grade/gradeoptions/gradeOption/" method="post" class="breadcrumb form-search">
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
				<th>选项值编号</th>
				<th>评分选项代码</th>
				<th>评分选项值名称</th>
				<th>评分选项值代码</th>
				<th>分值</th>
				<th>排序</th>
				<th>创建人</th>
				<th>创建时间</th>
				<shiro:hasPermission name="grade:gradeoptions:gradeOption:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gradeOption">
			<tr>
				<td><a href="${ctx}/grade/gradeoptions/gradeOption/form?id=${gradeOption.id}">
					${gradeOption.id}
				</a></td>
				<td>
					${gradeOption.itemsId}
				</td>
				<td>
					${gradeOption.optionName}
				</td>
				<td>
					${gradeOption.optionCode}
				</td>
				<td>
					${gradeOption.score}
				</td>
				<td>
					${gradeOption.sort}
				</td>
				<td>
					${gradeOption.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${gradeOption.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="grade:gradeoptions:gradeOption:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/grade/gradeoptions/gradeOption/form?id=${gradeOption.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/grade/gradeoptions/gradeOption/delete?id=${gradeOption.id}" onclick="return confirmx('确认要删除该评分选项值吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>