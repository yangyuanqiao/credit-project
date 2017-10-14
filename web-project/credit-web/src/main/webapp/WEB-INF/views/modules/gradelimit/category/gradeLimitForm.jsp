<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>等级限制项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
		$(document).ready(
				function() {
					
					var levelId = '${gradeLimit.levelId}';
					var subType = '${gradeLimit.subType}';
					if(subType !=''){
						getLevelList(subType,levelId);
						setTimeout(function(){
							$("#levelselect").val(levelId);
							$("#levelselect").trigger("change");
							//$("#levelselect").select2();
						}, 100);
						
					}else{
						//$("select[id=levelselect]").empty(); //清空  
					}
					
					
					//$("#name").focus();
					$("#subTypeSelect").change(function() {
						$("select[id=levelselect]").empty(); //清空  
						//$("#select[id=levelselect]").prepend("<option value=''>--请选择--</option>");
						var opt = $("#subTypeSelect").val();
						if(opt != ''){
							getLevelList(opt,'');
						}
					});
					
					
					
					//表单验证
					$("#inputForm").validate(
							{
								submitHandler : function(form) {
									loading('正在提交，请稍等...');
									form.submit();
								},
								errorContainer : "#messageBox",
								errorPlacement : function(error, element) {
									$("#messageBox").text("输入有误，请先更正。");
									if (element.is(":checkbox")
											|| element.is(":radio")
											|| element.parent().is(
													".input-append")) {
										error.appendTo(element.parent()
												.parent());
									} else {
										error.insertAfter(element);
									}
								}
							});
				});

		
		
		function getLevelList(subType,initVal) {
			    $("select[id=levelselect]").empty(); //清空  
				$.ajax({
							url : '${ctx}/gradelimit/category/gradeLimit/getLevelList',
							type : "post",
							data : {
								subType : subType
							},
							cache : false,
							error : function() {
								alert("ajax error");
							},
							success : function(data) {
								var modelList = data.levelList;
								if (modelList && modelList.length != 0) {
									$("select[id=levelselect]").append("<option value=''>--请选择--</option>");
									for (var i = 0; i < modelList.length; i++) {
										var option = "<option value=\""+modelList[i].id+"\"";  
										if(initVal == modelList[i].id){
											option +=" selected=\"selected\" ";
										}
			                    		option += ">"
											   + modelList[i].levelName
												+ "</option>"; //动态添加数据  
										$("select[id=levelselect]").append(option);
									}
								}
								/* setTimeout(function(){
									$("select[id=levelselect]").val(initVal);
								}, 1000);   */
								
							}
					});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/gradelimit/category/gradeLimit/">等级限制项列表</a></li>
		<li class="active"><a href="${ctx}/gradelimit/category/gradeLimit/form?id=${gradeLimit.id}">等级限制项<shiro:hasPermission name="gradelimit:category:gradeLimit:edit">${not empty gradeLimit.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="gradelimit:category:gradeLimit:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gradeLimit" action="${ctx}/gradelimit/category/gradeLimit/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">主体类别：</label>
			<div class="controls">
				<form:select path="subType" class="input-xlarge required" id="subTypeSelect">
						<form:option value="" label="--请选择--"/>
						<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">限制项名称：</label>
			<div class="controls">
				<form:input path="conditName" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
			
		</div>
		<%-- <div class="control-group">
			<label class="control-label">限制项代码：</label>
			<div class="controls">
				<form:input path="conditCode" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">限制项描述：</label>
			<div class="controls">
				<form:textarea path="conditDesc" htmlEscape="false" rows="3" maxlength="250" class="input-xlarge "/>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信用限制最高等级：</label>
			<div class="controls">
			<form:select path="levelId" class="input-xlarge required" id="levelselect" >
					<form:option value="" label="--请选择--"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优先级：</label>
			<div class="controls">
				<form:input path="conditSort" htmlEscape="false" maxlength="40" class="input-xlarge " value="1"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gradelimit:category:gradeLimit:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>