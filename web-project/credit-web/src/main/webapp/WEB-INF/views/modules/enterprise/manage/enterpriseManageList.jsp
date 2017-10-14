<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业经营状况管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/enterprise/manage/enterpriseManage/import" method="post" enctype="multipart/form-data" class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<table>
				<tr style="height:50px">
					<td>选择导入数据：</td>
					<td>
						&nbsp;&nbsp;&nbsp;<input id="uploadFile" name="file" type="file" style="width:210px"/>　
					</td>
				</tr>
				<tr style="height:50px">
					<td colspan="2">
						<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="  导  入  "/>&nbsp;&nbsp;&nbsp;
						<input class="btn btn-info" type="button" onclick="location.href='${ctx}/enterprise/manage/enterpriseManage/import/template';" value="下载模板"/>　
					</td>
				</tr>
			</table>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/enterprise/manage/enterpriseManage/">企业经营状况列表</a></li>
		<shiro:hasPermission name="enterprise:manage:enterpriseManage:edit"><li><a href="${ctx}/enterprise/manage/enterpriseManage/form">企业经营状况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="enterpriseManage" action="${ctx}/enterprise/manage/enterpriseManage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业编号：</label>
				<form:input path="subId" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>企业名称：</label>
				<form:input path="subName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>统一信用代码：</label>
				<form:input path="creditNo" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>企业编号</th>
				<th>企业名称</th>
				<th>企业统一信用代码</th>
				<th>年业务收入(万)</th>
				<th>资产负债率(%)</th>
				<th>主营业务利润率(%)</th>
				<th>总资产增长率(%)</th>
				<th>营业收入增长率(%)</th>
				<th>资产总额(万)</th>
				<th>经营年份</th>
				<th>导入时间</th>
				<shiro:hasPermission name="enterprise:manage:enterpriseManage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="enterpriseManage">
			<tr>
				<td><a href="${ctx}/enterprise/manage/enterpriseManage/form?id=${enterpriseManage.id}">
					${enterpriseManage.subId}
				</a></td>
				<td>
					${enterpriseManage.subName}
				</td>
				<td>
					${enterpriseManage.creditNo}
				</td>
				<td>
					<fmt:formatNumber value="${enterpriseManage.income}"  type="currency"/> 
				</td>
				<td>
					<fmt:formatNumber type="percent"  value="${enterpriseManage.deptProfit}" />
				</td>
				<td>
					<fmt:formatNumber type="percent"  value="${enterpriseManage.mainProfit}" />
				</td>
				<td>
					<fmt:formatNumber type="percent" maxIntegerDigits="3" value="${enterpriseManage.assetsProfit}" />
				</td>
				<td>
					<fmt:formatNumber type="percent" maxIntegerDigits="3" value="${enterpriseManage.increaseProfit}" />
				</td>
				<td>
					
					<fmt:formatNumber value="${enterpriseManage.totalAssets}"  type="currency"/> 
				</td>
				<td>
					${enterpriseManage.happenYear}
				</td>
				<td>
					<fmt:formatDate value="${enterpriseManage.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="enterprise:manage:enterpriseManage:edit"><td>
    				<a href="${ctx}/enterprise/manage/enterpriseManage/form?id=${enterpriseManage.id}">修改</a>
					<a href="${ctx}/enterprise/manage/enterpriseManage/delete?id=${enterpriseManage.id}" onclick="return confirmx('确认要删除该企业经营管理信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>