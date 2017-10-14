<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>许可证信息</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	
	<li class="active"><a
			href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${renthouseBase.id}&type=license">许可证信息</a></li>
		<li ><a
			href="${ctx}/renthouse/baseinfo/renthouseBase/detailInfo?id=${renthouseBase.id}&type=leasecred">出租屋租赁信息</a></li>
		<li><a
			href="${ctx}/sys/subpro/subProperty/detail?subType=2&subId=${renthouseBase.id}">其它信息</a></li>
			<li><a href="${ctx}/creditact/event/recordsEvent/subeventlist?subType=2&subId=${renthouseBase.id}">事件行为</a></li>
				<li ><a href="${ctx}/creditact/punishment/recordsPunishment/subjectPunishList?subType=2&subId=${renthouseBase.id}">处罚行为</a></li>
				<li ><a href="${ctx}/creditact/appraise/recordsAppraise/subjectAppraiseList?subType=2&subId=${renthouseBase.id}">评价行为</a></li>
	</ul><br/>
	
	
	
	<div id="myTabContent" class="tab-content">
	   	<div class="tab-pane fade in active" id="checkEventInfo">
	   		<div style="margin: 0 10px;">
	   		<c:if test="${empty subjectLicense}">
						<div align="center">
			  					<br/>
			  					<i style="font-size: 20px"><b>无许可证信息</b></i>
			  					<br/>
			  				</div>
					
			</c:if>
			<c:if test="${!empty subjectLicense}">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>许可名称</th>
				<th>许可代码</th>
				<th>许可范围/许可项目</th>
				<th>许可证号</th>
				<th>发放日期</th>
				<th>有效日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${subjectLicense}" var="subjectLicense">
				<tr>
					<%-- <td><a href="${ctx}/renthouse/extendinfo/renthouseExtend/form?id=${renthouseExtend.id}">
						${renthouseExtend.subId}
					</a></td> --%>
					<td>
						${subjectLicense.licName}
					</td>
					<td>
						${subjectLicense.licCode}
					</td>
					<td>
						${subjectLicense.licNum}
					</td>
					<td>
						${subjectLicense.giveDate}
					</td>
					<td>
						${subjectLicense.giveDate}
					</td>
					<td>
						${subjectLicense.giveDate}
					</td>
					<%-- <td>
						<fmt:formatDate value="${subjectLicense.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td> --%>
					<%-- <shiro:hasPermission name="renthouse:extendinfo:renthouseExtend:edit"><td>
	    				<a href="${ctx}/renthouse/extendinfo/renthouseExtend/form?id=${renthouseExtend.id}">修改</a>
						<a href="${ctx}/renthouse/extendinfo/renthouseExtend/delete?id=${renthouseExtend.id}" onclick="return confirmx('确认要删除该出租屋扩展信息吗？', this.href)">删除</a>
					</td></shiro:hasPermission> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
			</div>
			<br/>
	  </div>
	  
</body>
</html>