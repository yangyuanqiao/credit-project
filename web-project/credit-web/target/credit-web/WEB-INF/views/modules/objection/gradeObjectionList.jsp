<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>异议申请管理</title>
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
		<li class="active"><a href="${ctx}/objection/gradeObjection/">异议申请列表</a></li>
		<shiro:hasPermission name="objection:gradeObjection:edit"><li><a href="${ctx}/objection/gradeObjection/form">异议申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gradeObjection" action="${ctx}/objection/gradeObjection/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主体对象：</label>
				<form:input path="subId" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>异议申请类别：</label>
				<form:select path="applyType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>申请时间：</label>
				<input name="applyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gradeObjection.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>处理状态：</label>
				<form:select path="applyStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>申请人</th>
				<th>申请人手机号</th>
				<th>申请人邮箱地址</th>
				<th>异议申请类别</th>
				<th>异议申请原因</th>
				<th>申请时间</th>
				<th>备注</th>
				<th>处理状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="objection:gradeObjection:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gradeObjection">
			<tr>
				<td><a href="${ctx}/objection/gradeObjection/form?id=${gradeObjection.id}">
					${gradeObjection.subId}
				</a></td>
				<td>
					${gradeObjection.applyUser}
				</td>
				<td>
					${gradeObjection.applyTel}
				</td>
				<td>
					${gradeObjection.appEmail}
				</td>
				<td>
					${fns:getDictLabel(gradeObjection.applyType, '', '')}
				</td>
				<td>
					${gradeObjection.applyReason}
				</td>
				<td>
					<fmt:formatDate value="${gradeObjection.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gradeObjection.remark}
				</td>
				<td>
					${fns:getDictLabel(gradeObjection.applyStatus, '', '')}
				</td>
				<td>
					<fmt:formatDate value="${gradeObjection.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="objection:gradeObjection:edit"><td>
    				<a href="${ctx}/objection/gradeObjection/form?id=${gradeObjection.id}">修改</a>
					<a href="${ctx}/objection/gradeObjection/delete?id=${gradeObjection.id}" onclick="return confirmx('确认要删除该异议申请吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>