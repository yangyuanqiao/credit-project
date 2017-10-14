<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>巡检记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
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
</script>
<style type="text/css">
	.float_left{
		float: left;
	}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${gradeRecord.subType == 1}">
			<li><a href="${ctx}/graderecord/gradeRecord/">模具企业评分记录列表</a></li>
			<li class="active"><a href="${ctx}/graderecord/gradeRecord/gradeRecordDetail?id=${gradeRecord.id}">模具企业评分明细</a></li>
		</c:if>

		<c:if test="${gradeRecord.subType == 2}">
			<li><a href="${ctx}/graderecord/gradeRecord/renthouselist?subType=2">出租屋评分记录列表</a></li>
				<li class="active"><a href="${ctx}/graderecord/gradeRecord/gradeRecordDetail?id=${gradeRecord.id}">出租屋评分明细</a></li>
		</c:if>

		
	</ul>

	<div class="table-responsive">
		<c:forEach items="${gradeDetailVoLsit}" var="gradeDetailVo">
			<table class="table ">
				<caption style="text-align: left;font-size: 15px"> <h3>${gradeDetailVo.fatherCatName}</h3></caption>
				<thead>
					<tr>
						<th style="width: 40%;">评分选项</th>
						<th style="width: 20%;">计算结果(是/否)</th>
						<th style="width: 20%;">选项分数</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${gradeDetailVo.gradeItemsVo}" var="gradeItemsVo">
					<tr>
						<td>${gradeItemsVo.itemsName}</td>
						<td>${gradeItemsVo.optName}</td>
						<td>${gradeItemsVo.score}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:forEach>
		
		<table class="table">
				<caption style="text-align: left;font-size: 15px" ><h3>信用限制等级</h3></caption>
				<thead>
					<tr>
						<th style="width: 40%;">限制选项</th>
						<th style="width: 20%;">计算结果(是/否)</th>
						<th style="width: 20%;">限制等级</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${levelLimitVoList}" var="a">
						<tr>
							<td>${a.limit.conditName}</td>
							<td>${fns:getDictLabel(a.detail.limitResult, 'Y_N', '否')}</td>
							<td>${a.limit.gradeLevel.levelName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<div class="form-actions">
		<input id="btnCancel" class="btn" type="button" value="返 回"
			onclick="history.go(-1)" />
	</div>
</body>
</html>