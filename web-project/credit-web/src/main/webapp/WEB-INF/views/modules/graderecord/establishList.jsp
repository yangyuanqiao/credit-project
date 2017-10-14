<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评分记录管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
			.table-striped tbody tr td:nth-child(5){
			    background-color:#faf2cd
			
			}
			.table-striped  tbody tr td:nth-child(6){
			    background-color:#ccfafa
			
			}
			.table-striped tbody tr td:nth-child(7){
			    background-color:#f7d9fb
			
			}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
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
		<li class="active"><a href="${ctx}/graderecord/gradeRecord/establishList">小区楼盘评分记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="gradeRecord" action="${ctx}/graderecord/gradeRecord/establishList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="subType" name="subType" type="hidden" value="${gradeRecord.subType}"/>
		<ul class="ul-form">
			<%-- <li><label>主体类别：</label>
				<form:select path="subType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li><label>小区楼盘编号：</label>
				<form:input path="houseBase.houseCode" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>评定开始时间：</label>
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gradeRecord.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>评定结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gradeRecord.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>评定状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="--请选择--"/>
					<form:options items="${fns:getDictList('PF_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>评分记录号</th>
				<th>小区楼盘编号</th>
				<th>小区楼盘地址</th>
				<th>评定分数</th>
				<th>评定等级</th>
				<th>信用限制等级</th>
				<th>最终信用等级</th>
				<th>评定状态</th>
				<th>评定时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gradeRecord">
			<tr>
				<td>
					${gradeRecord.id}
				</td>
				<td>
					${gradeRecord.houseBase.houseCode}
				</td>
				<td>
					${gradeRecord.houseBase.houseAddr}
				</td>
				<td>
					${gradeRecord.score}
				</td>
				
				<td style="color:maroon;">
					${gradeRecord.scoreLevel}
				</td>
				
				<td style="color: red;">
					${gradeRecord.limitLevel}
				</td>
				<td style="color: green;">
					${gradeRecord.finalLevel}
				</td>
				<td>
					${fns:getDictLabel(gradeRecord.status, 'PF_STATUS', '')}
				</td>
				<td>
					<fmt:formatDate value="${gradeRecord.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<%-- <a href="${ctx}/graderecord/gradeRecord/form?id=${gradeRecord.id}">修改</a> --%>
					<a class="btn btn-small btn-primary" href="${ctx}/graderecord/gradeRecord/gradeRecordDetail?id=${gradeRecord.id}">评分明细</a>
					<%-- <c:if test="${gradeRecord.status == 1}"> 
						<a class="btn btn-small btn-danger" href="${ctx}/graderecord/gradeRecord/delete?id=${gradeRecord.id}" onclick="return confirmx('确认要设置为失效？系统将进行重新评分。', this.href)">设置为失效</a>
					</c:if> --%>
					<c:choose>
							<c:when test="${gradeRecord.status == 1}"> 
								<a class="btn btn-small btn-danger"  href="${ctx}/graderecord/gradeRecord/delete?id=${gradeRecord.id}"  onclick="return confirmx('确认要设置为失效？系统将对其进行重新评分。', this.href)">设置失效</a>
							</c:when>
							<c:otherwise> 
								<a class="btn btn-small btn-danger  btn-lg disabled"  disabled="true"  href="#"  onclick="return void(0);">设置失效</a>
							</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>