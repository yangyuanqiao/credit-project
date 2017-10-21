<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>辖区建筑添加</title>
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
		});
		
		function searchPrecinct() {
			top.$.jBox.open("iframe:${ctx}/bus/precinct/searchPrecinct", "选择辖区", 800, 450, {
				buttons:{"确定":"ok", "关闭":true}, 
				submit:function(v, h, f){
					if (v=="ok"){
						var obj = h.find("iframe")[0].contentWindow.document;
						$("#precinct_id").val(obj.getElementById("precinct_id").value);
						$("#precinct_name").val(obj.getElementById("precinct_name").value);
						$("#precinct_gridId").val(obj.getElementById("precinct_gridId").value);
					}
				}
			});
		}
		
		var building_ids = ",";
		
		function searchBuilding() {
			if($("#precinct_gridId").val()==''){
				$.jBox.tip("请先选择辖区", 'info');
				return false;
			}
			
			top.$.jBox.open("iframe:${ctx}/bus/building/list?isPrecinct=1&grid.id="+$("#precinct_gridId").val(), "添加建筑", 800, 450, {
				buttons:{"添加":"ok", "关闭":true}, 
				submit:function(v, h, f){
					if (v=="ok"){
						var obj = h.find("iframe")[0].contentWindow.document;
						
						var building_id = obj.getElementById("building_id").value;
						
						if(building_id == ''){
							return false;
						}
						
						var building_name = obj.getElementById("building_name").value;
						var building_org_name = obj.getElementById("building_org_name").value;
						var building_grid_name = obj.getElementById("building_grid_name").value;
						var building_housenumAddr1 = obj.getElementById("building_housenumAddr1").value;
						
						var building_id_ary = building_id.split(",");
						var building_name_ary = building_name.split("&split");
						var building_org_name_ary = building_org_name.split("&split");
						var building_grid_name_ary = building_grid_name.split("&split");
						var building_housenumAddr1_ary = building_housenumAddr1.split("&split");
						
						var addCount = 0;	//添加数
						var totalCount = 0;	//共添加数
						
						for(var i=0;i<building_id_ary.length;i++){
							if(building_ids.indexOf(','+building_id_ary[i]+',') < 0){
								var tr = '<tr id="building_tr'+building_id_ary[i]+'"><input type="hidden" name="buildingIds" value="'+building_id_ary[i]+'"/><td>'+building_org_name_ary[i]+' - '+building_grid_name_ary[i]+'</td><td>'+building_name_ary[i]+'</td><td>'+building_housenumAddr1_ary[i]+'</td><td><a href="javascript:void(0);" onclick="removeTr('+building_id_ary[i]+')">移除</a></td></tr>';
								$("#buildingPrecinctRelati_tbody").append(tr);
								building_ids += building_id_ary[i] + ",";
								addCount++;
							}
						}
						
						totalCount = building_ids.split(",").length - 2;
						
						obj.getElementById("messageBox2_msg").innerHTML = "已添加" + addCount + "个建筑，共添加" + totalCount + "个建筑";
						obj.getElementById("messageBox2").style.display = "";
						
						return false;
					}
				}
			});
		}
		
		function removeTr(id){
			building_ids = building_ids.replace(","+id+",",",");
			$("#building_tr"+id).remove();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<!-- <li><a href="${ctx}/bus/buildingPrecinctRelati/">辖区建筑配置列表</a></li> -->
		<li class="active"><a href="${ctx}/bus/buildingPrecinctRelati/form">辖区建筑添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="buildingPrecinctRelati" action="${ctx}/bus/buildingPrecinctRelati/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">辖区：</label>
			<div class="controls">
				<div class="input-append">
					<form:hidden path="precinct.id" id="precinct_id"/>
					<form:hidden path="precinct.grid.id" id="precinct_gridId"/>
					<form:input path="precinct.name" id="precinct_name" readonly="true" htmlEscape="false" maxlength="400" class="input-xlarge required" onclick="searchPrecinct()"/>
					<a class="btn" href="javascript:void(0)" onclick="searchPrecinct()"><i class="icon-search"></i></a>
				</div>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">建筑：</label>
			<div class="controls">
				<input class="btn btn-info" type="button" value="添加建筑" onclick="searchBuilding()"/>
				<span class="help-inline"><font color="red">*</font>&nbsp;&nbsp;只能添加没有配置辖区的建筑 </span>
			</div>
		</div>
		<div class="control-group" style="height: 400px;overflow-y:scroll;">
			<div style="margin-left: 30px;margin-right: 30px">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>组织</th>
							<th>建筑名</th>
							<th>门牌地址</th>
							<th>移除</th>
						</tr>
					</thead>
					<tbody id="buildingPrecinctRelati_tbody">
						
					</tbody>
				</table>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>