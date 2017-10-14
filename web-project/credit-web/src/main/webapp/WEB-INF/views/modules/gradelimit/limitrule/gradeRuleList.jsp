<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规则管理</title>
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
		
			<%-- <shiro:hasPermission name="gradelimit:limitrule:gradeRule:edit"></shiro:hasPermission>
			<li><a href="${ctx}/gradelimit/category/gradeLimit/">条件限制列表</a></li>
			<shiro:hasPermission name="gradelimit:category:gradeLimit:edit"><li><a href="${ctx}/gradelimit/category/gradeLimit/form">条件限制添加</a></li></shiro:hasPermission> --%>
			<li class="active"><a href="${ctx}/gradelimit/limitrule/gradeRule/?itemId=${gradeRule.itemId}">规则列表</a></li>
			<li><a href="${ctx}/gradelimit/limitrule/gradeRule/form?itemId=${gradeRule.itemId}">规则添加</a></li>
		</ul>
		<c:if test="${not empty limitCat.conditName}">
			<div class="tab-pane fade in active" id="checkEventInfo">
				<div style="margin: 0 10px;">
					<table class="table table-striped table-bordered">
						<tbody>
								<thead>
							<tr>
								<th colspan="4">等级限制项信息</th>
							</tr>
						</thead>
								<tr>
									<td width="15%" style="text-align: right"><b>主体类别：</b></td>
									<td width="35%">${fns:getDictLabel(limitCat.subType, 'SUB_TYPE', '')}</td>
									<td width="15%" style="text-align: right"><b>限制项名称：</b></td>
									<td width="35%">${limitCat.conditName}</td>
								</tr>
								<tr>
									<td width="15%" style="text-align: right"><b>最高等级：</b></td>
									<td width="35%">${limitCat.gradeLevel.levelName}</td>
									<td width="15%" style="text-align: right"><b>限制项描述：</b></td>
									<td width="35%">${limitCat.conditDesc}</td>
								</tr>
		
						</tbody>
					</table>
				</div>
				<div class="clearfix"></div>
		</c:if>
		<c:if test="${not empty gradeItem.itemName}">
			<div class="tab-pane fade in active" id="checkEventInfo">
				<div style="margin: 0 10px;">
					<table class="table table-striped table-bordered">
						<tbody>
								<thead>
							<tr>
								<th colspan="4">评分选项信息</th>
							</tr>
						</thead>
								<tr>
									<td width="15%" style="text-align: right"><b>主体类别：</b></td>
									<td width="35%">${fns:getDictLabel(gradeItem.subType, 'SUB_TYPE', '')}</td>
									<td width="15%" style="text-align: right"><b>评分选项：</b></td>
									<td width="35%">${gradeItem.itemName}</td>
								</tr>
		
						</tbody>
					</table>
				</div>
				
		</c:if>
		
	<form:form id="searchForm" modelAttribute="gradeRule" action="${ctx}/gradelimit/limitrule/gradeRule/form" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="itemId"/>
		 <%-- <ul class="ul-form">
		 
		 
			<li><label>规则种类;参考数据字典RULE_TYPE：</label>
				<form:input path="ruleType" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>参照表：</label>
				<form:input path="referTable" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>参照字段名称：</label>
				<form:input path="referField" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>计算条件公式(参考数据字典EQUATION_TYPE)：</label>
				<form:input path="referEquation" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>参考结果值：</label>
				<form:input path="referVal" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>参考字段条件：</label>
				<form:input path="referCondit" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="新增条件规则"/></li> -->
			<li class="clearfix"></li>
		</ul>  --%>
	</form:form>
	<sys:message content="${message}"/>
	<div style="margin: 0 10px;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>规则编号</th>
				<th>规则类别</th>
				<th>参照信息表</th>
				<th>参照信息项</th>
				<th>计算公式</th>
				<th>参照数值</th>
				<th>计算条件</th>
				<th>选项结果</th>
				<!-- <th>更新时间</th> -->
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="gradeRule">
				<tr>
					<td>
						<a href="${ctx}/gradelimit/limitrule/gradeRule/form?id=${gradeRule.id}">${gradeRule.id}</a>
					</td>
					<td>
						${fns:getDictLabel(gradeRule.ruleType, 'RULE_TYPE', '')}
					</td>
					<td>
						${gradeRule.ext1}
					</td>
					<td>
						${gradeRule.ext2}
					</td>
					<td>
						${fns:getDictLabel(gradeRule.referEquation, 'EQUATION_TYPE', '')}
					</td>
					<td>
						${gradeRule.referVal}
					</td>
					<td>
						${gradeRule.referCondit}
					</td>
					<td>
						是
					</td>
					<%-- <td>
						<fmt:formatDate value="${gradeRule.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td> --%>
					
					<td style="width: 105px;">
	    				<a class="btn btn-small btn-primary" href="${ctx}/gradelimit/limitrule/gradeRule/form?id=${gradeRule.id}">修改</a>
						<a class="btn btn-small btn-primary" href="${ctx}/gradelimit/limitrule/gradeRule/delete?id=${gradeRule.id}" onclick="return confirmx('确认要删除该规则吗？', this.href)">删除</a>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<%-- <div class="pagination">${page}</div> --%>
</body>
</html>