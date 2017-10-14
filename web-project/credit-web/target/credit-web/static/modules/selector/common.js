	function searchComp() {
			top.$.jBox.open("iframe:${ctx}/market/subjectCompany/commSelector/?buscode=1", "从档案库查找企业", 800, 650, {
				buttons:{"确定":"ok", "关闭":true}, 
				submit:function(v, h, f){
					if (v=="ok"){
						var obj = h.find("iframe")[0].contentWindow.document;
						var comm_id = h.find("iframe")[0].contentWindow.$("#comm_id").val();//获取到弹出窗口所选的主体id
	                	var comm_name = h.find("iframe")[0].contentWindow.$("#comm_name").val();//获取弹出窗口所选的主体信息
						$("#comm_id").val(comm_id);
						$("#comm_name").val(comm_name);
					}
				}
			});
		}
	
	
