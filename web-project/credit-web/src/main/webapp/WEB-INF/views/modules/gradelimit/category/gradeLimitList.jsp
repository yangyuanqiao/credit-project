<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>条件限制管理</title>
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
		function openWin(id){
			
			$.jBox("iframe:${ctx}/gradelimit/limitrule/gradeRule/?itemId="+id+"&from=limitlist", {  
	            title: "配置规则",
	            top: '5%',
	            width: 910,  
	            height: $(top.document).height()-180,  
	            buttons: { '关闭': true },
	            loaded:function(h){
	                $("#jbox-content").css("overflow-y","hidden");
	            }
	        });  
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gradelimit/category/gradeLimit/">等级限制项列表</a></li>
		<shiro:hasPermission name="gradelimit:category:gradeLimit:edit"><li><a href="${ctx}/gradelimit/category/gradeLimit/form">等级限制项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gradeLimit" action="${ctx}/gradelimit/category/gradeLimit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主体类别：</label>
				<form:select path="subType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>限制项名称：</label>
				<form:input path="conditName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>限制项编号</th> -->
				<th>主体类别</th>
				<th>限制项名称</th>
				<th>信用限制最高等级</th>
				<th>限制项描述</th>
				<th>优先级</th>
				<th>更新时间</th>
				<shiro:hasPermission name="gradelimit:category:gradeLimit:edit"><th >操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gradeLimit">
			<tr>
				<%-- <td><a href="${ctx}/gradelimit/category/gradeLimit/form?id=${gradeLimit.id}">
					${gradeLimit.id}
				</a></td> --%>
				<td>
					${fns:getDictLabel(gradeLimit.subType, 'SUB_TYPE', '')}
				</td>
				<td>
					${gradeLimit.conditName}
				</td>
				<td>
					${gradeLimit.gradeLevel.levelName}
				</td>
				<td>
					${gradeLimit.conditDesc}
				</td>
				
				<td>
					${gradeLimit.conditSort}
				</td>
				<td>
					<fmt:formatDate value="${gradeLimit.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="gradelimit:category:gradeLimit:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/gradelimit/category/gradeLimit/form?id=${gradeLimit.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/gradelimit/category/gradeLimit/delete?id=${gradeLimit.id}" onclick="return confirmx('确认要删除该条件限制吗？', this.href)">删除</a>
					<a class="btn btn-small btn-primary" href="javascript:void(0);" onclick="return openWin('${gradeLimit.id}')">配置规则</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>