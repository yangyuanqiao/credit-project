<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>位置选择</title>
	<meta name="decorator" content="blank"/>
    <style type="text/css">
		<!--
		.grid_info{margin-top: 8px;}
		.grid_info td{padding-left:10px;}
		.td_center td{text-align:center;padding-top:3px;}
		
		.houseInfo {width:450px;}
		.houseInfo img {border:0}
		.houseInfo p {margin:0;line-height:1.5;font-size:13px;}
		#searchbar {right:1px;top:10px;float:right;padding-right:10px;position: absolute;z-index: 1;}
		#localsearchBtn{height: 30px;padding-bottom: 5px;}
		-->
		
	</style>
	
</head>
<body>
		<div id="searchbar">
		<input type="text" id="localsearch" value="" size="10"/>
		<input type="button" value="查地点" class="btn btn-primary" onclick="localsearch()" id="localsearchBtn"/>
		</div>
		<input type="hidden" value="${code}" />
		<div id="BuildingMapContains" >
		</div>
		<input type="hidden" value="${longitude}" id="longitude"></input>
		<input type="hidden" value="${latitude}" id="latitude"></input>
	<script type="text/javascript"
			src="http://api.map.baidu.com/api?v=2.0&ak=HR21B5hY1FAuDtSv93A8vrvq"></script>
    <script type="text/javascript">

    	$("#BuildingMapContains").css("height",$(top.document).height()-260);
    	var markers = new Array();
    	//百度地图容器
    	var mapContentId = "BuildingMapContains";
    	
    	//百度地图API功能
    	var map = new BMap.Map(mapContentId,{mapType:BMAP_HYBRID_MAP});
    	var dataMap = {
    			
    			longitude : "${longitude}",
    	    	latitude : "${latitude}"
    	};
    	
    	//标注样式
    	var icon = new BMap.Icon('${ctxStatic}/map/mapfiles/markers2/marker_sprite.png', new BMap.Size(57, 34), {anchor: new BMap.Size(10, 30)});
    	//中心点坐标113.677248:22.942092
    	var point = new BMap.Point(dataMap.longitude == "" ? 113.677248:dataMap.longitude, dataMap.latitude == "" ? 22.941218:dataMap.latitude);
    	
    	//设置中心点,设置最大缩放级别
    	map.centerAndZoom(point, 18);
    	map.addControl(new BMap.NavigationControl());
    	
    	//允许缩放
    	map.enableScrollWheelZoom(true);
    	
    	var marker = new BMap.Marker(point, {
    	    icon: icon
    	});  // 创建标注
    	map.addEventListener("click", changemark);
		map.addOverlay(marker);
    	
		var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		});
		function localsearch(){
			//alert($("#localsearch").val());
			local.search($("#localsearch").val());
		}
		//local.search("故宫");

    	function changemark(e){
    		//将点击的地图位置设置为表单中的值
    		$("#longitude").attr("value",e.point.lng);
    		$("#latitude").attr("value",e.point.lat);
    		//alert(e.point.lng+":"+e.point.lat)
    		map.removeOverlay(marker);
    		point = new BMap.Point(e.point.lng,e.point.lat);
    		marker = new BMap.Marker(point, {
        	    icon: icon
        	});
    		map.addOverlay(marker);
    		
    	}
    </script>
</body>