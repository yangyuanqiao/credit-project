<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>评分选项管理管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
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
		})	
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
		function fun(obj){
		    var div = document.getElementById("newOption");
		    if(obj.value=="新增选项值"){
		        div.style.display = "block";
		        obj.value = "关闭新增选项值";
		    }else{
		    	div.style.display = "none";
		        obj.value = "新增选项值";
		    }
		} 	
	 	function rule(obj){
		   var rules = document.getElementById("rules");
		   if(obj.value=="新增评分规则"){
		    	rules.style.display = "block";
		        obj.value = "关闭新增评分规则";
		    }
		   else{
		    	rules.style.display = "none";
		        obj.value = "新增评分规则";
		    }
		 } 
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/grade/gradeItems/">评分选项管理列表</a></li>
		<li class="active"><a
			href="${ctx}/grade/gradeItems/form?id=${gradeItems.id}">评分选项管理<shiro:hasPermission
					name="grade:gradeItems:edit">${not empty gradeItems.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="grade:gradeItems:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="gradeItems" action="${ctx}/grade/gradeItems/increase" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">对象类别：</label>
			<form:select path="subType" class="input-medium required" >
				<form:option value="" label="--请选择--" />
				<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"  id="subType"/>
			</form:select>
		</div>
		<div class="control-group">
			<label class="control-label required">一级类别：</label> <select id="fatherId" name="fatherId"
				class="input-medium" required onchange="father();"></select> <span class="help-inline"><font
				color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label class="control-label required">二级类别：</label> <select id="childId" name="childId"
				class="input-medium" required>
			</select>
		</div>
		
		<div class="control-group">
			<label class="control-label">选项名称：</label>
			<div class="controls" >
				<input name="itemName" type="text" required/>
				<font color="red">*</font>
			</div>
		</div>
	
		<div class="control-group"><lable>评分选项值</lable>  <input id="btnCancel" style="margin-left:50px" class="btn" type="button" value="新增选项值"
				onclick="fun(this)" /></div>
		
			<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:500px;">
				<thead>
					<tr>
						<th>值名称</th>
						<th>编码</th>
						<th>分值</th>
						<th>排序</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${gradeOption}" var="gradeOption" >
					<tr>
						<td>
							${gradeOption.optionName}
						</td>
						<td>
							${gradeOption.optionCode}
						</td>
	
						<td>
							${gradeOption.score}
						</td>
						<td>
						  ${gradeOption.sort}
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		
	
			<div id="newOption" style="display: none;">
				<div class="control-group">
					<label class="control-label required">选项值名称：</label>
					<div class="controls">
						<input name="optionName" type="text" required/>
					</div>
				</div> 
				<div class="control-group">
					<label class="control-label">选项值编码：</label>
					<div class="controls">
						<input name="optionCode" type="text" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">分值：</label>
					<div class="controls ">
						<input name="score" type="text" required />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">排序：</label>
					<div class="controls">
						<input name="gradeOption.sort" type="text"/>
					</div>
				</div> 
		 </div>
		<div class="control-group"><lable>评分规则</lable>  <input id="btnCancel" style="margin-left:50px" class="btn" type="button" value="新增评分规则"
				onclick="rule(this)" /></div>
		
			<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:800px;">
				<thead>
					<tr>
						<th>规则编号</th>
						<th>计算参表</th>
						<th>计算参照字段</th>
						<th>计算条件公式</th>
						<th>计算结果</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${gradeRule}" var="gradeRule" >
					<tr>
						<td>
							${gradeRule.id}
						</td>
						<td>
							${gradeRule.referTable}
						</td>
	
						<td>
							${gradeRule.referField}
						</td>
						<td>
						  ${gradeRule.referEquation}
						</td>
						<td>
						  ${gradeRule.referVal}
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		
	
			<div id="rules" style="display: none;">
				<div class="control-group">
					<label class="control-label">规则类别：</label>
					<div class="controls">
						<input name="ruleType" type="text"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">计算参照表：</label>
					<div class="controls">
						<input name="referTable" type="text"/>
					</div>
				</div> 
				<div class="control-group">
					<label class="control-label">计算参照字段：</label>
					<div class="controls">
						<input name="referField" type="text" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">计算条件公式：</label>
					<div class="controls">
						<input name="referEquation" type="text" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">计算结果：</label>
					<div class="controls">
						<input name="referVal" type="text" />
					</div>
				</div> 
		 </div>

		<div class="form-actions">
			<shiro:hasPermission name="grade:gradeItems:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>