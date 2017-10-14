<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评分选项管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#subType").change(function(){
				$.ajax({
					url:'${ctx}/grade/gradeItems/topLevel',
					type:'post',
					dataType:'json' ,
					data:  {"subType":$("#subType").val()},
					success:function(data){
						$('#fatherId').empty();
	                    for(var i=0;i<data.length;i++){
	                    	if(data[i].parentId=="0"){
	                			$("#fatherId").append("<option value='"+data[i].id+"' onclick='father()'>"+data[i].cateName+"</option>");
	                    	}
	                	}
					}
					
				});
			}); 
			
		});
		function father(){
			$.ajax({
				url:'${ctx}/grade/gradeItems/topLevel',
				type:'post',
				dataType:'json' ,
				data: {"subType":$("#subType").val(),"childId":$("#fatherId").val()},
				success:function(data){
					$('#childId').empty();
                    for(var i=0;i<data.length;i++){
            			$("#childId").append("<option value='"+data[i].id+"'>"+data[i].cateName+"</option>");
                	}
				}
			});		
		} 
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
		<li class="active"><a href="${ctx}/grade/gradeItems/">评分选项管理列表</a></li>
		<shiro:hasPermission name="grade:gradeItems:edit"><li><a href="${ctx}/grade/gradeItems/form">评分选项管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gradeItems" action="${ctx}/grade/gradeItems/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>对象类别：</label>
				<form:select path="subType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false" id="subType"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</li>
			<li><label>一级类别：</label>
				<select  class="input-medium"  id="fatherId" name="fatherId" onchange="father();" >
					<option label="--请选择--"/>
				</select>
			</li>
			<li><label>二级类别：</label>
				<select  class="input-medium" id="childId" name="childId" >
				<select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>对象类别</th>
				<th>一级类别</th>
				<th>二级类别</th>
				<th>评分选项</th>
				<th>评分选项值</th>
				<th>创建人</th>
				<th>创建时间</th>
				<shiro:hasPermission name="grade:gradeItems:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gradeItems">
			<tr>
				<td><a href="${ctx}/grade/gradeItems/form?id=${gradeItems.id}">
				${gradeItems.subType}
					${fns:getDictLabel(gradeItems.subType, 'SUB_TYPE', '不明')}
				</a></td>
				<td>
				${gradeItems.id}	${gradeItems.fatherId}
				</td>
				<td>
					${gradeItems.childId}
				</td>
				<td>
					${gradeItems.itemName}
				</td>
				<td>
					${gradeItems.optionName}
				</td>
				<td>
					${gradeItems.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${gradeItems.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="grade:gradeItems:edit"><td>
    				<a href="${ctx}/grade/gradeItems/update?id=${gradeItems.id}">修改</a>
					<a href="${ctx}/grade/gradeItems/delete?id=${gradeItems.id}" onclick="return confirmx('确认要删除该评分选项管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>