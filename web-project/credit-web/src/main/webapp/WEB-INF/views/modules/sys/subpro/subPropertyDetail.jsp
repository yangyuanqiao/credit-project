<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主体其它信息</title>
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
		<c:choose>
    		<c:when test="${subType==1}">
    			<li><a
				href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${subId}&type=opt">企业管理者信息</a></li>
				<li><a
				href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${subId}&type=man">企业经营状况</a></li>
				<li class="active"><a href="${ctx}/sys/subpro/subProperty/detail?subType=${subType}&subId=${subId}">其它信息</a></li>
				<li><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=1&subId=${subId}">事件行为</a></li>
				<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=1&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=1&subId=${subId}">评价行为</a></li>
    		</c:when>
    		<c:when test="${subType==2}">
    			<li ><a
					href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${subId}&type=license">许可证信息</a></li>
				<li ><a
						href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${subId}&type=leasecred">出租屋租赁信息</a></li>
				<li class="active"><a href="${ctx}/sys/subpro/subProperty/detail?subType=${subType}&subId=${subId}">其它信息</a></li>
					<li><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=2&subId=${subId}">事件行为</a></li>
				<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=2&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=2&subId=${subId}">评价行为</a></li>
    		</c:when>
    		<c:when test="${subType==3}">
    			<li class="active"><a href="${ctx}/sys/subpro/subProperty/detail?subType=${subType}&subId=${subId}">其它信息</a></li>
    				<li><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=3&subId=${subId}">事件行为</a></li>
				<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=3&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=3&subId=${subId}">评价行为</a></li>
    		</c:when>
    		<c:when test="${subType==4}">
    			<li class="active"><a href="${ctx}/sys/subpro/subProperty/detail?subType=${subType}&subId=${subId}">其它信息</a></li>
    			<%-- <li><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=4&subId=${subId}">事件行为</a></li>
				<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=4&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=4&subId=${subId}">评价行为</a></li> --%>
    		</c:when>
    	</c:choose>
			
		
	</ul>
<%-- 	<form:form id="searchForm" modelAttribute="subProperty" action="${ctx}/sys/subpro/subProperty/" method="post" class="breadcrumb form-search">
		
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>主体类别：</label>
				<form:input path="subType" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="itemName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>项目代码：</label>
				<form:input path="itemCode" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remark" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>属性代码</th>
				<th>属性项</th>
				<th>属性值</th>
				<!-- <th>备注</th>
				<th>更新时间</th> -->
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page}" var="subProperty">
			<tr>
				<td>
					${subProperty.itemCode}
				</td>
				<td>
					${subProperty.itemName}
				</td>
				<td>
					
					${fns:getDictLabel(subProperty.itemValue, 'yes_no', '-')} 
				</td>
			<%-- 	<td>
					${subProperty.remark}
				</td>
				<td>
					<fmt:formatDate value="${subProperty.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sys:subpro:subProperty:edit"><td>
    				<a href="${ctx}/sys/subpro/subProperty/form?id=${subProperty.id}">修改</a>
					<a href="${ctx}/sys/subpro/subProperty/delete?id=${subProperty.id}" onclick="return confirmx('确认要删除该主体属性信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>

</body>
</html>