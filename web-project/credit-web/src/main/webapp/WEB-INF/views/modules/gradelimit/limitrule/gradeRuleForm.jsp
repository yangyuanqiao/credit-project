<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规则管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			$("#tableSelect").change(function() {
				var tableName = $(this).val();
				getTableFeildList(tableName);

			});
			
			$('input:radio[name="conditionradio"]').change(function() {
				var val = $(this).val();
				if('1' == val){//
					$("#conditionDiv").show();
				}else{
					$("#conditionDiv").hide();
				}

			});
			
			
			var id='${gradeRule.id}';
			var table= '${gradeRule.referTable}';
			var feild= '${gradeRule.referField}';
			if(id !=''){
				$("#tableSelect").val(table)
				$("#tableSelect").trigger("change");
				
				setTimeout(function(){
					$("#fieldSelect").val(feild);
					$("#fieldSelect").trigger("change");
					//$("#levelselect").select2();
				}, 100);
				
			}
			var referCondit="${gradeRule.referCondit}";
			if(referCondit !=''){
				$("input[name='conditionradio'][value='1']").attr("checked",true); 
				$('input:radio[name="conditionradio"]').change();
			}
			
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					$("#ext1").val($("#tableSelect").find("option:selected").text());
					$("#ext2").val($("#fieldSelect").find("option:selected").text());
					var radioType = $('input:radio[name="conditionradio"][checked]').val()
					var a =$("#referCondit").val();
					if(radioType=='1' && a.trim() !=''){
						$("#referCondit").val($("#fieldSelect2").find("option:selected").val()+" = '"+a+"'");
					}else{
						$("#referCondit").val('');
					}
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
		function getTableFeildList(tableName) {
			$("select[id=fieldSelect]").empty(); //清空  
			$("select[id=fieldSelect2]").empty(); //清空  
			$.ajax({
						url : '${ctx}/gradelimit/limitrule/gradeRule/getTableColumn',
						type : "post",
						data : {
							tableName : tableName
						},
						cache : false,
						error : function() {
							alert("ajax request error！");
						},
						success : function(data) {
							var modelList = data.columnList;
							if (modelList && modelList.length != 0) {
								for (var i = 0; i < modelList.length; i++) {
									var option = "<option value=\""+modelList[i].name+"\"";  
		                    option += ">"
											+ modelList[i].comments
											+ "</option>"; //动态添加数据  
									$("select[id=fieldSelect]").append(option);
									$("select[id=fieldSelect2]").append(option);
								}
							}
						}
					});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <shiro:hasPermission name="gradelimit:limitrule:gradeRule:edit"><li><a href="${ctx}/gradelimit/limitrule/gradeRule/form">规则添加</a></li></shiro:hasPermission>
		<li><a href="${ctx}/gradelimit/category/gradeLimit/">条件限制列表</a></li>
		<shiro:hasPermission name="gradelimit:category:gradeLimit:edit"><li><a href="${ctx}/gradelimit/category/gradeLimit/form">条件限制添加</a></li></shiro:hasPermission>
		<li ><a href="${ctx}/gradelimit/limitrule/gradeRule/">限制规则列表</a></li>
		<li class="active"><a href="${ctx}/gradelimit/limitrule/gradeRule/form?id=${gradeRule.id}">条件限制规则<shiro:hasPermission name="gradelimit:limitrule:gradeRule:edit">${not empty gradeRule.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gradelimit:limitrule:gradeRule:edit">查看</shiro:lacksPermission></a></li> --%>
		<li ><a href="${ctx}/gradelimit/limitrule/gradeRule/?itemId=${gradeRule.itemId}">规则列表</a></li>
		<li class="active"><a href="${ctx}/gradelimit/limitrule/gradeRule/form?itemId=${gradeRule.itemId}">规则添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gradeRule" action="${ctx}/gradelimit/limitrule/gradeRule/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="itemId"/>
		<form:hidden path="ext1" id="ext1" />
		<form:hidden path="ext2" id="ext2" />
		<sys:message content="${message}"/>	
		<%-- <div class="control-group" >
			<label class="control-label">条件编号：</label>
			<div class="controls">
				<form:input path="itemId" htmlEscape="false" maxlength="40" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">规则类别：</label>
			<div class="controls">
				<form:select path="ruleType" class="input-xlarge required" id="ruleTypeSelect">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('RULE_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">参照信息表：</label>
			<div class="controls">
				
				<form:select path="referTable" class="input-xlarge required" id="tableSelect">
				<form:option value="" label="--请选择--"/>
							<form:options items="${tableList}" itemLabel="nameAndComments" itemValue="name" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参照信息项：</label>
			<div class="controls">
				<form:select path="referField" class="input-xlarge required fieldSelect" id="fieldSelect">
				<form:option value="" label="--请选择--"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否有参照条件：</label>
			<div class="controls">
				<%-- <form:radio path="ruleName" htmlEscape="false"  class="input-xlarge "/> --%>
				<input type="radio" name="conditionradio" id="conditionradio" value="0" checked="checked" />否
				<input type="radio" name="conditionradio" id="conditionradio" value="1"  />是
			</div>
		</div>
		<div class="control-group"  id="conditionDiv" style="display: none;">
			<label class="control-label">参照条件：</label>
			<div class="controls">
				<form:select path="" class="input-small"  id="fieldSelect2">
					<form:option value="" label="--请选择--"/>
				</form:select>
				<form:select path="" class="input-small">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('EQUATION_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<form:input path="referCondit" htmlEscape="false" maxlength="255" class="input-small " id="referCondit"/>
				
			</div>
			<div class="controls">
			<span class="help-inline">用于对参照信息表的信息进行过滤后计算</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算公式：</label>
			<div class="controls">
				<form:select path="referEquation" class="input-xlarge required">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('EQUATION_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算参考结果值：</label>
			<div class="controls">
				<form:input path="referVal" htmlEscape="false" maxlength="40" class="input-xlarge "/>
				<span class="help-inline">必须为数字(是为1;否为0)</span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">计算优先级：</label>
			<div class="controls">
				<form:input path="itemLevel" htmlEscape="false" maxlength="10" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font>定义规则执行的优先级 </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算后选项值：</label>
			<div class="controls">
				<%-- <form:radio path="ruleName" htmlEscape="false"  class="input-xlarge "/> --%>
				<input type="radio" name="radio" id="radio" value="0" checked="checked" />是
				<span class="help-inline">选项符合上述规则计算得出的结果值</span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">扩展1：</label>
			<div class="controls">
				<form:input path="ext1" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扩展2：</label>
			<div class="controls">
				<form:input path="ext2" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扩展3：</label>
			<div class="controls">
				<form:input path="ext3" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div> --%>
		<!-- 添加规则 -->
		<!-- <div id="addbuildingnew">
		</div>
		<div class="control-group">
			<table class="table table-striped table-bordered">
				<tr>
					<td width="15%" style="text-align:right">
						<b>条件规则内容：</b>
						<span class="help-inline"><font color="red">*</font> </span>
					</td>
       				<td width="85%" colspan="3">
       					<input class="btn btn-info" type="button" value="添加条件规则" onclick="addrelationoperationui('addbuilding')"/>
       				</td>
				</tr>
			</table>
		</div> -->
		
		<div class="form-actions">
			<%-- <shiro:hasPermission name="gradelimit:limitrule:gradeRule:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
		
	</form:form>
</body>
</html>