<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评分选项类别管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							subType: getDictLabel(${fns:toJson(fns:getDictList('SUB_TYPE'))}, row.subType),
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/grade/category/gradeCategory/">评分选项类别列表</a></li>
		<shiro:hasPermission name="grade:category:gradeCategory:edit"><li><a href="${ctx}/grade/category/gradeCategory/form">评分选项类别添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gradeCategory" action="${ctx}/grade/category/gradeCategory/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>评分主体对象：</label>
				<form:select path="subType" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>评分类别：</label>
				<form:input path="cateName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr class="active">
				<th>评分选项类别</th>
				
				<th >权重(%)</th>
				<th>创建人</th>
				<th>创建时间</th>
				<shiro:hasPermission name="grade:category:gradeCategory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td>
				{{row.cateName}}
			</td>
			
			
			
			<td>
				
			
					{{row.transferPropotion}}
				
			</td>
			
			<td>
				{{row.createByName}}
			</td>
			<td>
				{{row.createDate}}
			</td>
			<shiro:hasPermission name="grade:category:gradeCategory:edit"><td style="text-align: center;">

                <a  class="btn btn-small btn-primary"  title="修改"  href="${ctx}/grade/category/gradeCategory/form?id={{row.id}}">修改</a>
				<a  class="btn btn-small btn-primary" title="删除" href="${ctx}/grade/category/gradeCategory/delete?id={{row.id}}" onclick="return confirmx('确认要删除该项吗？', this.href)">删除</a>
				<a  class="btn btn-small btn-primary" href="${ctx}/grade/category/gradeCategory/form?parent.id={{row.id}}" title="添加下级">添加下级</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>