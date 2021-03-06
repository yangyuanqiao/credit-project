<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模具主体信息管理</title>
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
		<li class="active"><a href="${ctx}/enterprise/baseinfo/enterpriseBase/">模具主体信息列表</a></li>
		<shiro:hasPermission name="enterprise:baseinfo:enterpriseBase:edit"><li><a href="${ctx}/enterprise/baseinfo/enterpriseBase/form">模具主体信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="enterpriseBase" action="${ctx}/enterprise/baseinfo/enterpriseBase/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业名称：</label>
				<form:input path="subName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>统一信用代码：</label>
				<form:input path="creditNo" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>注册号：</label>
				<form:input path="orgRegistry" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>组织机构代码：</label>
				<form:input path="orgCode" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>企业编号</th>
				<th>企业名称</th>
				<th>统一信用代码</th>
				<th>税务登记号</th>
				<th>注册号</th>
				<th>组织机构代码</th>
				<th>工商登记地址</th>
				<th>企业人数</th>
				<th>是否年报</th>
				<th>同步时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="enterpriseBase">
			<tr>
				<td>
					${enterpriseBase.id}
				</td>
				<td><a href="${ctx}/enterprise/baseinfo/enterpriseBase/form?id=${enterpriseBase.id}">
					${enterpriseBase.subName}
				</a></td>
				<td>
					${enterpriseBase.creditNo}
				</td>
				<td>
					${enterpriseBase.taxNo}
				</td>
				<td>
					${enterpriseBase.orgRegistry}
				</td>
				<td>
					${enterpriseBase.orgCode}
				</td>
				<td>
					${enterpriseBase.regAddr}
				</td>
				<td>
					${enterpriseBase.numOfPeople}
				</td>
				<td>
					${fns:getDictLabel(enterpriseBase.isCheck, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${enterpriseBase.creatDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<a class="btn btn-small btn-primary" href="${ctx}/enterprise/baseinfo/enterpriseBase/form?id=${enterpriseBase.id}">查看详细</a>
				</td>
				<shiro:hasPermission name="enterprise:baseinfo:enterpriseBase:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/enterprise/baseinfo/enterpriseBase/form?id=${enterpriseBase.id}">修改</a>
					<a class="btn btn-small btn-primary"  href="${ctx}/enterprise/baseinfo/enterpriseBase/delete?id=${enterpriseBase.id}" onclick="return confirmx('确认要删除该模具主体信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>