<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="输入框值"%>
<%@ attribute name="longitude" type="java.lang.String" required="true" description="经度值"%>
<%@ attribute name="latitude" type="java.lang.String" required="true" description="纬度值"%>
<i id="${id}" class="${not empty value?value:' hide'}"></i>&nbsp;<label id="${id}">${not empty value?value:'无'}</label>&nbsp;
<input id="${id}" name="${name}" type="hidden" value="${value}"
	longitude="${longitude}" latitude="${latitude}" />
<a id="${id}Button" href="javascript:" class="btn">选择</a>
&nbsp;&nbsp;


<script type="text/javascript">
	$("#${id}Button").click(function(){
		top.$.jBox.open("iframe:${ctx}/tag/mapselect?longitude=${longitude}&latitude=${latitude}", "选择位置", 700, $(top.document).height()-180, {
            buttons:{"确定":"ok", "清除":"clear", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                	var longtitude = h.find("iframe")[0].contentWindow.$("#longitude").val();//获取到弹出窗口所选的经度
                	var latitude = h.find("iframe")[0].contentWindow.$("#latitude").val();//获取到弹出窗口所选的纬度
	                $("#longitude${id}").attr("value",longtitude);//赋值到标签
	                $("#latitude${id}").attr("value",latitude);//赋值到标签
                }else if (v=="clear"){
	                $("#longitude${id}").value();
	                $("#latitude${id}").value();
                }
            }, loaded:function(h){
                $(".jbox-content", top.document).css("overflow-y","hidden");
            }
        });
	});
</script>