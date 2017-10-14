<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出租屋扩展信息管理</title>
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
		<%-- <li ><a href="${ctx}/renthouse/baseinfo/renthouseBase/">出租屋基础信息列表</a></li>
		<li class="active"><a href="${ctx}/renthouse/extendinfo/renthouseExtend/">出租屋详细信息列表</a></li>
		<shiro:hasPermission name="renthouse:extendinfo:renthouseExtend:edit"><li><a href="${ctx}/renthouse/extendinfo/renthouseExtend/form">出租屋扩展信息添加</a></li></shiro:hasPermission> --%>
		
		<li><a
			href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${renthouseExtend.subId}&type=license">许可证信息</a></li>
		<li ><a
			href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${renthouseExtend.subId}&type=leasecred">出租屋租赁信息</a></li>
		<li class="active"><a
			href="${ctx}/renthouse/extendinfo/renthouseExtend?subId=${renthouseExtend.subId}&type=extend">其它信息</a></li>
			
	</ul>
	<form:form id="searchForm" modelAttribute="renthouseExtend" action="${ctx}/renthouse/extendinfo/renthouseExtend/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%-- <ul class="ul-form">
			<li><label>主体编号：</label>
				<form:input path="subId" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>属性名称：</label>
				<form:input path="extendName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> --%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>属性名称</th>
				<th>属性代码</th>
				<th>属性值</th>
				<th>来源渠道</th>
				<th>更新时间</th>
				<shiro:hasPermission name="renthouse:extendinfo:renthouseExtend:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="renthouseExtend">
			<tr>
				<%-- <td><a href="${ctx}/renthouse/extendinfo/renthouseExtend/form?id=${renthouseExtend.id}">
					${renthouseExtend.subId}
				</a></td> --%>
				<td>
					${renthouseExtend.extendName}
				</td>
				<td>
					${renthouseExtend.extendCode}
				</td>
				<td>
					${renthouseExtend.resultDesc}
				</td>
				<td>
					${renthouseExtend.remark}
				</td>
				<td>
					<fmt:formatDate value="${renthouseExtend.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="renthouse:extendinfo:renthouseExtend:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/renthouse/extendinfo/renthouseExtend/form?id=${renthouseExtend.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/renthouse/extendinfo/renthouseExtend/delete?id=${renthouseExtend.id}" onclick="return confirmx('确认要删除该出租屋扩展信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>