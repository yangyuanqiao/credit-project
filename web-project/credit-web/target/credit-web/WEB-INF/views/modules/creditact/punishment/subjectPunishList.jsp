<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>处罚行为信息管理</title>
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
		<form id="importForm" action="${ctx}/creditact/punishment/recordsPunishment/import" method="post" enctype="multipart/form-data" class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
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
						<input class="btn btn-info" type="button" onclick="location.href='${ctx}/creditact/punishment/recordsPunishment/import/template';" value="下载模板"/>　
					</td>
				</tr>
			</table>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<c:choose>
			<c:when test="${subType==1}">
				<li><a href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${subId}&type=opt">企业管理者信息</a></li>
				<li><a href="${ctx}/enterprise/baseinfo/enterpriseBase/detailInfo?id=${subId}&type=man">企业经营状况</a></li>
				<li ><a href="${ctx}/sys/subpro/subProperty/detail?subType=1&subId=${subId}">其它信息</a></li>
				<li><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=1&subId=${subId}">事件行为</a></li>
				<li  class="active"><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=1&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=1&subId=${subId}">评价行为</a></li>
			</c:when>
			<c:when test="${subType==2}">
			<li ><a
				href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${subId}&type=license">许可证信息</a></li>
			<li ><a
				href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${subId}&type=leasecred">出租屋租赁信息</a></li>
				<li><a
				href="${ctx}/sys/subpro/subProperty/detail?subType=2&subId=${subId}">其它信息</a></li>
				<li ><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=2&subId=${subId}">事件行为</a></li>
				<li  class="active"><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=2&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=2&subId=${subId}">评价行为</a></li>
			</c:when>
			<c:when test="${subType==3}">
				<li><a
				href="${ctx}/sys/subpro/subProperty/detail?subType=3&subId=${subId}">其它信息</a></li>
				<li ><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=3&subId=${subId}">事件行为</a></li>
				<li  class="active"><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=3&subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=3&subId=${subId}">评价行为</a></li>
			</c:when>
			<c:when test="${subType==4}">
				<li ><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subId=${subId}">事件行为</a></li>
				<li  class="active"><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subId=${subId}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subId=${subId}">评价行为</a></li>
			</c:when>
		</c:choose>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="recordsPunishment" action="${ctx}/creditact/punishment/recordsPunishment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主体编号：</label>
				<form:input path="subId" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>处罚文书编号：</label>
				<form:input path="punishFile" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>处罚类别：</label>
				<form:select path="punishType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('PUNISH_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>处罚标题：</label>
				<form:input path="punishTitle" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主体编号</th>
				<th>处罚文书编号</th>
				<th>处罚类别</th>
				<th>处罚标题</th>
				<th>处罚内容</th>
				<th>处罚机构</th>
				<th>处罚时间</th>
				<th>处罚状态</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="creditact:punishment:recordsPunishment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:if test="${empty page.list}">
			<tr>
				  <td align="center">
							
				  					<i style="font-size: 20px"><b>暂无处罚行为信息！</b></i>
				  				
				  </td>
			</tr>
		</c:if>
		<c:forEach items="${page.list}" var="recordsPunishment">
			<tr>
				<td>
					${recordsPunishment.subId}
				</td>
				<td>
					${recordsPunishment.punishFile}
				</td>
				<td>
					${fns:getDictLabel(recordsPunishment.punishType, 'PUNISH_TYPE', '')}
				</td>
				<td>
					${recordsPunishment.punishTitle}
				</td>
				<td>
					${recordsPunishment.punishDesc}
				</td>
				<td>
					${fns:getDictLabel(recordsPunishment.punishDepart, 'DEPARMENT', '')}
				</td>
				<td>
					<fmt:formatDate value="${recordsPunishment.punishDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(recordsPunishment.punishStatus, 'PUNISH_STATUS', '')}
				</td>
				<td>
					${recordsPunishment.remark}
				</td>
				<td>
					<fmt:formatDate value="${recordsPunishment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="creditact:punishment:recordsPunishment:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/creditact/punishment/recordsPunishment/form?id=${recordsPunishment.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/creditact/punishment/recordsPunishment/delete?id=${recordsPunishment.id}" onclick="return confirmx('确认要删除该处罚行为信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>