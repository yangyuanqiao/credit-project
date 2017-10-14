<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评分选项管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
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
			
			$("#subSelect").change(function() {
				$("select[id=fatherId]").empty(); //清空  
				var opt = $("#subSelect").val();
				getCatoryList(opt,'',"fatherId");
			});
			
			
			$("#fatherId").change(function() {
				$("select[id=child]").empty(); //清空  
				var subType = $("#subSelect").val();
				var father = $("#fatherId").val();
				getCatoryList(subType,father,"child");
			});
			
			
			var id = '${gradeItems.id}';
			if(id !=''){
				//("#subSelect").change();
				$("#subSelect").trigger("change");
				setTimeout(function(){
					$("#fatherId").val('${gradeItems.fatherId}');
					$("#fatherId").trigger("change");
					setTimeout(function(){
						$("#child").val('${gradeItems.childId}');
						$("#child").trigger("change");
					},100);
					
				}, 100);
				
				
			}
			
		});
		
		var relcount = 0;
		this.addOption = function(id){
			var selector = "";
			if(id=='addNew'){
				var optName = "<input class='required' type='text' id='optName"+relcount+"' value='' name='gradeOpt.optionName' />";
				var optionScore = "<input name='gradeOpt.score' rows='4' cols='50' maxlength=200></input>";
				var delSubject = "<input type='hidden' name='gradeOpt.optDel' value='0' />";
				var optId = "<input type='hidden' id='subjectid"+relcount+"' name='gradeOpt.id'>";
				var subjectType = "<input type='hidden' id='subjecttype"+relcount+"' name='meterRel.relationType'>";

				var html = '<div class="control-group" id="building_'+relcount+'"><table class="table table-bordered">' + delSubject + optId + subjectType +' <tr><td width="10%" style="text-align:right"><b>选项值名称：</b><span class="help-inline"><font color="red">*</font> </span></td><td width="10%">'+optName+'</td><td width="10%" style="text-align:right"><b>选项分值：</b></td><td width="10%" >'+optionScore+'</td><td><input class="btn btn-success" type="button" value="删除" onclick="removerelation(\'building_'+relcount+'\')"/></td></tr></table></div>';
				$("#addOptionNew").append(html);
			}
			
			relcount++;
		}
		this.removetag = function(idtag,infotag,btntag){
			$("#"+idtag).remove();
			$("#"+infotag).remove();
			$("#"+btntag).remove();
		}
		this.removerelation = function(div){
			$("#"+div+" > table > input:first").attr("value","1");//将删除的项修改为已删除
			$("#"+div).css("display","none");

		}
		
		//获取评分类别
		function getCatoryList(subType,initVal,select) {
			$.ajax({
						url : '${ctx}/grade/category/gradeCategory/getCategoryBySub',
						type : "post",
						data : {
							subType : subType,fatherId:initVal
						},
						cache : false,
						error : function() {
							alert("ajax error!");
						},
						success : function(data) {
							var modelList = data.levelList;
							if (modelList && modelList.length != 0) {
								for (var i = 0; i < modelList.length; i++) {
									var option = "<option value=\""+modelList[i].id+"\"";  
									if(initVal == modelList[i].id){
										option +=" selected=\"selected\" ";
									}
		                    		option += ">"
										   + modelList[i].cateName
											+ "</option>"; //动态添加数据  
									$("select[id="+select+"]").append(option);
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
		<li><a href="${ctx}/grade/gradeitems/gradeItems/">评分选项列表</a></li>
		<li class="active"><a href="${ctx}/grade/gradeitems/gradeItems/form?id=${gradeItems.id}">评分选项<shiro:hasPermission name="grade:gradeitems:gradeItems:edit">${not empty gradeItems.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="grade:gradeitems:gradeItems:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gradeItems" action="${ctx}/grade/gradeitems/gradeItems/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label" style="text-align: left;font-size: 15px"><b>评分选项：</b></label>
		</div>
		<div class="control-group">
			<label class="control-label">主体对象：</label>
			<div class="controls">
				<form:select path="subType" class="input-xlarge  required" id="subSelect">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('SUB_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一级类别：</label>
			<div class="controls">
				<form:select path="fatherId" class="input-xlarge required" id="fatherId">
					<form:option value="" label="--请选择--"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">二级类别：</label>
			<div class="controls">
				<form:select path="childId" class="input-xlarge required" id="child">
					<form:option value="" label="--请选择--"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评分选项：</label>
			<div class="controls">
				<form:input path="itemName" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">评分项代码：</label>
			<div class="controls">
				<form:input path="itemCode" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="itemStatus" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">选项排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="10" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<br/>
		<div class="control-group">
			<label class="control-label" style="text-align: left;font-size: 15px"><b>评分选项值：</b></label>
			<div class="controls"><input class="btn btn-success" type="button" value="添加" onclick="addOption('addNew')"/></div>
		</div>
		
		<!-- 修改选项值内容展示 -->
		<c:forEach items="${optList}" var="opt" varStatus="st">
					<div id="building_${st.index}_" class="control-group">
						<table class="table table-bordered">
						<input type="hidden" value="0" name="gradeOpt.optDel">
						<%-- <input id="buildingid_${st.index}_" type="hidden" name="meterRel.relationId" value="${opt.relationId}">
						<input id="relationType_${st.index}_" type="hidden" name="meterRel.relationType" value="${opt.relationType}">--%>
						<input id="relId_${st.index}_" type="hidden" name="gradeOpt.id" value="${opt.id}"> 
						<tbody>
						
							<tr>
								<td width="10%" style="text-align:right">
									<b>选项名称：</b>
									<span class="help-inline"><font color="red">*</font></span>
								</td>
								<td width="10%">
									<input class="required" type="text" id="optName0" value="${opt.optionName}" name="gradeOpt.optionName">
								</td>
								<td width="10%" style="text-align:right">
									<b>选项分值：</b>
								</td>
								<td width="10%" colspan="3">
									<input maxlength="200" rows="4" name="gradeOpt.score" value="${opt.score}"></input>
								</td>
								<td><input class="btn btn-success" type="button" value="删除" onclick="removerelation('building_${st.index}_')">
								</td>
							</tr>
						</tbody>
						</table>
					</div>
			</c:forEach>
			
		<!-- 新增一块评分选项  -->
		<div id="addOptionNew">
		</div>
		<!-- <div class="control-group">
			<table class="table table-striped table-bordered">
				<tr>
					<td width="15%" style="text-align:right">
						<b>选项值：</b>
						<span class="help-inline"><font color="red">*</font> </span>
					</td>
       				<td width="85%" colspan="3">
       					<input class="btn btn-info" type="button" value="添加评分选项值" onclick="addOption('addNew')"/>
       				</td>
				</tr>
			</table>
		</div> -->
		
		<div class="form-actions">
			<shiro:hasPermission name="grade:gradeitems:gradeItems:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>