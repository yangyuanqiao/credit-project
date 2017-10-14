<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业经营者信息管理</title>
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
		<li class="active"><a href="${ctx}/enterprise/operator/enterpriseOperator/">经营者信息列表</a></li>
		<shiro:hasPermission name="enterprise:operator:enterpriseOperator:edit"><li><a href="${ctx}/enterprise/operator/enterpriseOperator/form">经营者信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="enterpriseOperator" action="${ctx}/enterprise/operator/enterpriseOperator/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>经营者编码：</label>
				<form:input path="subId" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="operatorName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>经营者编码</th>
				<th>姓名</th>
				<th>文化程度</th>
				<th>联系电话</th>
				<th>联系地址</th>
				<th>工作年限</th>
				<th>职称</th>
				<th>籍贯</th>
				<th>政治面貌</th>
				<th>备注</th>
				<shiro:hasPermission name="enterprise:operator:enterpriseOperator:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="enterpriseOperator">
			<tr>
				<td><a href="${ctx}/enterprise/operator/enterpriseOperator/form?id=${enterpriseOperator.id}">
					${enterpriseOperator.subId}
				</a></td>
				<td>
					${enterpriseOperator.operatorName}
				</td>
				<td>
					${fns:getDictLabel(enterpriseOperator.operatorEdu, '', '')}
				</td>
				<td>
					${enterpriseOperator.operatorTel}
				</td>
				<td>
					${enterpriseOperator.operatorAddr}
				</td>
				<td>
					${enterpriseOperator.workExperience}
				</td>
				<td>
					${fns:getDictLabel(enterpriseOperator.professional, '', '')}
				</td>
				<td>
					${fns:getDictLabel(enterpriseOperator.nativePlace, '', '')}
				</td>
				<td>
					${fns:getDictLabel(enterpriseOperator.politics, '', '')}
				</td>
				<td>
					${enterpriseOperator.remark}
				</td>
				<shiro:hasPermission name="enterprise:operator:enterpriseOperator:edit">
				<td>
    				<a class="btn btn-small btn-primary" href="${ctx}/enterprise/operator/enterpriseOperator/form?id=${enterpriseOperator.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/enterprise/operator/enterpriseOperator/delete?id=${enterpriseOperator.id}" onclick="return confirmx('确认要删除该企业经营者信息吗？', this.href)">删除</a>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>