<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>小区楼盘管理</title>
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
		<li class="active"><a href="${ctx}/estatehousing/basicinfo/housingEstate/">小区楼盘列表</a></li>
		<shiro:hasPermission name="estatehousing:basicinfo:housingEstate:edit"><li><a href="${ctx}/estatehousing/basicinfo/housingEstate/form">小区楼盘添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="housingEstate" action="${ctx}/estatehousing/basicinfo/housingEstate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>楼盘名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>楼盘编号：</label>
				<form:input path="serialNumber" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>楼盘类别：</label>
				<form:select path="propType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>开发商名：</label>
				<form:input path="developer" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>楼盘地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>楼盘名称</th>
				<th>楼盘编号</th>
				<th>开发商名</th>
				<th>楼盘地址</th>
				<th>楼盘期数</th>
				<th>开盘日期</th>
				<th>容积率</th>
				<th>绿化率</th>
				<th>产权年限</th>
				<th>规划户数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="housingEstate">
			<tr>
				<td><a href="${ctx}/estatehousing/basicinfo/housingEstate/form?id=${housingEstate.id}">
					${housingEstate.name}
				</a></td>
				<td>
					${housingEstate.serialNumber}
				</td>
				<td>
					${housingEstate.developer}
				</td>
				<td>
					${housingEstate.address}
				</td>
				<td>
					${housingEstate.periods}
				</td>
				<td>
					<fmt:formatDate value="${housingEstate.openDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${housingEstate.plotRatio}
				</td>
				<td>
					${housingEstate.greeningRate}
				</td>
				<td>
					${housingEstate.yearTerm}
				</td>
				<td>
					${housingEstate.useCount}
				</td>
				<td>
    				<a class="btn btn-primary" href="${ctx}/estatehousing/basicinfo/housingEstate/form?id=${housingEstate.id}">查看详细</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>