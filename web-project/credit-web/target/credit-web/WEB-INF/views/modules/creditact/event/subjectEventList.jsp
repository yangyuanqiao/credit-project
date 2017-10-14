<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>事件行为信息管理</title>
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
		<form id="importForm" action="${ctx}/creditact/event/recordsEvent/import" method="post" enctype="multipart/form-data" class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
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
						<input class="btn btn-info" type="button" onclick="location.href='${ctx}/creditact/event/recordsEvent/import/template';" value="下载模板"/>　
					</td>
				</tr>
			</table>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<ul class="nav nav-tabs">
		<c:choose>
			<c:when test="${subType==1}">
				<li><a href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${subId}&type=opt">企业管理者信息</a></li>
				<li><a href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${subId}&type=man">企业经营状况</a></li>
				<li ><a href="${ctx}/sys/subpro/subProperty/detail?subType=1&subId=${subId}">其它信息</a></li>
				<li class="active"><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=1&subId=${subId}">事件行为</a></li>
				<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=1&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=1&subId=${subId}">评价行为</a></li>
			</c:when>
			<c:when test="${subType==2}">
			<li ><a
				href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${subId}&type=license">许可证信息</a></li>
			<li ><a
				href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${subId}&type=leasecred">出租屋租赁信息</a></li>
				<li><a
				href="${ctx}/sys/subpro/subProperty/detail?subType=2&subId=${subId}">其它信息</a></li>
				<li class="active"><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=2&subId=${subId}">事件行为</a></li>
				<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=2&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=2&subId=${subId}">评价行为</a></li>
			</c:when>
			<c:when test="${subType==3}">
			<li><a
				href="${ctx}/sys/subpro/subProperty/detail?subType=3&subId=${subId}">其它信息</a></li>
				<li class="active"><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=3&subId=${subId}">事件行为</a></li>
				<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=3&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=3&subId=${subId}">评价行为</a></li>
			</c:when>
			<c:when test="${subType==4}">
				<li class="active"><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=4&subId=${subId}">事件行为</a></li>
				<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=4&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=4&subId=${subId}">评价行为</a></li>
			</c:when>
		</c:choose>
		
	</ul>
	</ul>
<%-- 	<form:form id="searchForm" modelAttribute="recordsEvent" action="${ctx}/creditact/event/recordsEvent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>事件类别：</label>
				<form:select path="eventType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('EVENT_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>事件标题：</label>
				<form:input path="eventName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>事件来源：</label>
				<form:input path="deparrment" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>记录编号</th>
				<th>主体编号</th>
				<th>事件类别</th>
				<th>事件标题</th>
				<th>事件具体内容</th>
				<th>事件发生时间</th>
				<th>事件影响程度</th>
				<th>事件处理状态</th>
				<th>事件来源</th>
				<th>更新时间</th>
				<shiro:hasPermission name="creditact:event:recordsEvent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:if test="${empty page.list}">
			<tr>
				  <td align="center">
							
				  					<i style="font-size: 20px"><b>暂无事件行为信息</b></i>
				  				
				  </td>
			</tr>
		</c:if>
		<c:forEach items="${page.list}" var="recordsEvent">
			<tr>
				<td>
					${recordsEvent.id}
				</td>
				<td>
					${recordsEvent.subId}
				</td>
				<td>
					${fns:getDictLabel(recordsEvent.eventType, 'EVENT_TYPE', '')}
				</td>
				<td>
					${recordsEvent.eventName}
				</td>
				<td>
					${recordsEvent.eventDetail}
				</td>
				<td>
					${recordsEvent.eventTime}
				</td>
				
				<td>
					${recordsEvent.dealTime}
				</td>
				<td>
					${recordsEvent.result}
				</td>
				<%-- <td>
					${recordsEvent.createTime}
				</td>
				<td>
					${recordsEvent.remark}
				</td> --%>
				<td>
					${recordsEvent.deparrment}
				</td>
				<td>
					<fmt:formatDate value="${recordsEvent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="creditact:event:recordsEvent:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/creditact/event/recordsEvent/form?id=${recordsEvent.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/creditact/event/recordsEvent/delete?id=${recordsEvent.id}" onclick="return confirmx('确认要删除该事件行为信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>