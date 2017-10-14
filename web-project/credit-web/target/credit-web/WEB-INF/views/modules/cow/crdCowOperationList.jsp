<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>牛行经营场所管理</title>
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
		<li class="active"><a href="${ctx}/cow/crdCowOperation/">牛行经营场所列表</a></li>
		<shiro:hasPermission name="cow:crdCowOperation:edit"><li><a href="${ctx}/cow/crdCowOperation/form">牛行经营场所添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="crdCowOperation" action="${ctx}/cow/crdCowOperation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>经营场所名称：</label>
				<form:input path="subName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>统一信用代码：</label>
				<form:input path="creditNo" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>税务登记号：</label>
				<form:input path="taxNo" htmlEscape="false" maxlength="40" class="input-medium"/>
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
				<th>经营场所名称</th>
				<th>统一信用代码</th>
				<th>税务登记号</th>
				<th>注册号</th>
				<th>组织机构代码</th>
				<th>工商登记地址</th>
				<th>经营开始时间</th>
				<th>联系电话</th>
				<th>联系人</th>
				
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="crdCowOperation">
			<tr>
				<td><a href="${ctx}/cow/crdCowOperation/form?id=${crdCowOperation.id}">
					${crdCowOperation.subName}
				</a></td>
				<td>
					${crdCowOperation.creditNo}
				</td>
				<td>
					${crdCowOperation.taxNo}
				</td>
				<td>
					${crdCowOperation.orgRegistry}
				</td>
				<td>
					${crdCowOperation.orgCode}
				</td>
				<td>
					${crdCowOperation.regAddr}
				</td>
				<td>
					${crdCowOperation.setupDate}
				</td>
				<td>
					${crdCowOperation.telNo}
				</td>
				<td>
					${crdCowOperation.telMan}
				</td>
				<%-- <td>
					${crdCowOperation.licenseName}
				</td>
				<td>
					${crdCowOperation.areaTotal}
				</td>
				<td>
					${crdCowOperation.numOfPeople}
				</td>
				<td>
					<fmt:formatDate value="${crdCowOperation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<td>
    				<a class="btn btn-small btn-primary" href="${ctx}/cow/crdCowOperation/form?id=${crdCowOperation.id}">查看详细</a>
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>