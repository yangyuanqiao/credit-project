<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出租屋基础信息管理</title>
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
		<li class="active"><a href="${ctx}/renthouse/baseinfo/renthouseBase/">出租屋基础信息列表</a></li>
		<shiro:hasPermission name="renthouse:baseinfo:renthouseBase:edit"><li><a href="${ctx}/renthouse/baseinfo/renthouseBase/form">出租屋基础信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="renthouseBase" action="${ctx}/renthouse/baseinfo/renthouseBase/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			
			<li><label>门牌号：</label>
				<form:input path="houseNum" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>出租屋地址：</label>
				<form:input path="houseAddr" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>备案登记号：</label>
				<form:input path="rentCode" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>出租屋编号</th>
				<th>门牌号</th>
				<th>出租屋备案登记号</th>
				<th>出租屋地址</th>
				<!-- <th>是否安全文明出租屋</th>
				<th>是否安装监控</th>
				<th>是否购买意外保险</th> -->
				<th>出租层数</th>
				<th>同步时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="renthouseBase">
			<tr>
				<td>
					${renthouseBase.id}
				</td>
				<td>
					${renthouseBase.houseNum}
				</td>
				<td>
					${renthouseBase.rentCode}
				</td>
				<td>
					${renthouseBase.houseAddr}
				</td>
				<%-- <td>
					${fns:getDictLabel(renthouseBase.isSafeCivilized, 'yes_no', '')}
				</td>
				
				<td>
					${fns:getDictLabel(renthouseBase.hasMonitor, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(renthouseBase.hasInsurance, 'yes_no', '')}
				</td> --%>
				<td>
					${renthouseBase.levelNum}
				</td>
				<td>
					<fmt:formatDate value="${renthouseBase.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<a class="btn btn-small btn-primary" href="${ctx}/renthouse/baseinfo/renthouseBase/form?id=${renthouseBase.id}">查看详细</a>
				</td>
				<shiro:hasPermission name="renthouse:baseinfo:renthouseBase:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/renthouse/baseinfo/renthouseBase/form?id=${renthouseBase.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/renthouse/baseinfo/renthouseBase/delete?id=${renthouseBase.id}" onclick="return confirmx('确认要删除该出租屋基础信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
</body>
</html>