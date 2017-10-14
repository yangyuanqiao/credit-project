<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信用等级信息管理管理</title>
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
		<li class="active"><a href="${ctx}/grade/gradeLevel/">信用等级信息列表</a></li>
		<shiro:hasPermission name="grade:gradeLevel:edit"><li><a href="${ctx}/grade/gradeLevel/form">信用等级信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gradeLevel" action="${ctx}/grade/gradeLevel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>对象类别：</label>
				<form:select path="subjectType" class="input-medium" >
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>信用等级：</label>
				<form:input path="levelName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>	
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>主体对象</th>
				<th>信用等级</th>
				<th>信用分值范围</th>
				<th>风险说明</th>
				<th>创建人</th>
				<th>创建时间</th>
				<shiro:hasPermission name="grade:gradeLevel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gradeLevel">
			<tr>
				<td>${fns:getDictLabel(gradeLevel.subjectType, 'SUB_TYPE', '')}</td>
				<td>
					<a href="${ctx}/grade/gradeLevel/form?id=${gradeLevel.id}">${gradeLevel.levelName}</a>
			
				</td>
				<td>
					${gradeLevel.minScore}-${gradeLevel.maxScore}
				</td>
				<td >
					${fns:getDictLabel(gradeLevel.levelCode, 'RISK_TYPE', '')}
				</td>
				<td>
					${gradeLevel.createBy.name}
					
				</td>
				
				<td>
					<fmt:formatDate value="${gradeLevel.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="grade:gradeLevel:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/grade/gradeLevel/form?id=${gradeLevel.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/grade/gradeLevel/delete?id=${gradeLevel.id}" onclick="return confirmx('确认要删除该信用等级信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>