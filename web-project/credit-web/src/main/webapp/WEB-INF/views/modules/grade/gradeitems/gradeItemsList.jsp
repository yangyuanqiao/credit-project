<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评分选项管理管理</title>
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
		
	function openWin(fid){
			
			$.jBox("iframe:${ctx}/gradelimit/limitrule/gradeRule/?itemId="+fid+"&from=items", {  
	            title: "配置规则",
	            top: '5%',
	            width: 950,  
	            height:  $(top.document).height()-180,  
	            buttons: { '关闭': true } ,
	            loaded:function(h){
	                $("#jbox-content").css("overflow-y","hidden");
	            }
	        });  
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/grade/gradeitems/gradeItems/">评分选项列表</a></li>
		<shiro:hasPermission name="grade:gradeitems:gradeItems:edit"><li><a href="${ctx}/grade/gradeitems/gradeItems/form">评分选项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gradeItems" action="${ctx}/grade/gradeitems/gradeItems/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主体对象：</label>
				<form:select path="subType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<%-- <li><label>父级类别：</label>
				<form:select path="fatherId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			--%>
			<li><label>评分选项：</label>
				<form:input path="itemName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li> 
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>选项编号</th> -->
				<th>评分选项</th>
				<th>主体对象</th>
				<th>一级类别</th>
				<th>二级类别</th>
				<th>评分选项值</th>
				<th>创建人</th>
				<th>创建时间</th>
				<shiro:hasPermission name="grade:gradeitems:gradeItems:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gradeItems">
			<tr>
			<%-- 	<td>
					${gradeItems.id}
				</td> --%>
				<td>
					${gradeItems.itemName}
				</td>
				<td>
					${fns:getDictLabel(gradeItems.subType, 'SUB_TYPE', '')}
				</td>
				<td>
					${gradeItems.fatherName}
				</td>
				<td>
					${gradeItems.childName}
				</td>
				<td>
					是/否
				</td>
				<td>
					${gradeItems.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${gradeItems.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="grade:gradeitems:gradeItems:edit"><td>
    				<a class="btn btn-small btn-primary" href="${ctx}/grade/gradeitems/gradeItems/form?id=${gradeItems.id}">修改</a>
					<a class="btn btn-small btn-primary" href="${ctx}/grade/gradeitems/gradeItems/delete?id=${gradeItems.id}" onclick="return confirmx('确认要删除该评分选项管理吗？', this.href)">删除</a>
					<a class="btn btn-small btn-primary" href="javascript:void(0);" onclick="return openWin('${gradeItems.id}');" >配置规则</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>