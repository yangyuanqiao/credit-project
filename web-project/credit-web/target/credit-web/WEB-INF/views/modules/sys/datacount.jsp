<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
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
		
		function iFrameHeight(id) {
			var ifm= document.getElementById(id);
			ifm.height = parent.document.documentElement.scrollHeight-150;
			if(ifm.height < 600){
				ifm.height = 600;
			}
			ifm.width = parent.document.documentElement.scrollWidth;
			if(ifm.width < 400){
				ifm.width = 400;
			}else{
				ifm.width = ifm.width - 200;
			}
		}
	</script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/datacount">统计情况</a></li>
	</ul><br/> --%>

	<iframe src="${ctx}/report/jumpage"  frameborder="0" scrolling="no" id="basebuildingInfo" name="basebuildingInfo" onLoad="iFrameHeight('basebuildingInfo')"> 
	
	</iframe>
</body>
</html>