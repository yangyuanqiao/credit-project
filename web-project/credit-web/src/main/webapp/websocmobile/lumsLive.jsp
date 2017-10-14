<%@page import="org.springframework.http.server.ServletServerHttpRequest"%>
<%@page import="org.springframework.http.server.ServerHttpRequest"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
    <title>课程直播</title>
    <script src="/server-web/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link charset="utf-8" rel="stylesheet" 	href="/server-web/static/bootstrap/2.3.1/css_main/bootstrap.min.css" />
	<script type="text/javascript" src="/server-web/static/bootstrap/2.3.1/js/bootstrap.min.js"></script>
	<link charset="utf-8" rel="stylesheet" 	href="/server-web/static/css/dialog.css" />
    <script src="/server-web/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link charset="utf-8" rel="stylesheet" 	href="/server-web/static/bootstrap/2.3.1/css_main/bootstrap.min.css" />
	<script type="text/javascript" src="/server-web/static/bootstrap/2.3.1/js/bootstrap.min.js"></script>
	<link charset="utf-8" rel="stylesheet" 	href="/server-web/static/css/dialog.css" />
    <style type="text/css">
        #connect-container {
            float: left;
            width: 400px
        }

        #connect-container div {
            padding: 5px;
        }

        #console-container {
            width: 100%;
        }

        #console {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            overflow-y: auto;
            padding: 5px;
            width: 100%;
        }

        #console p {
            padding: 0;
            margin: 0;
        }
        .panel-body {
		    height: 80%;
		}
		.form-groupBuy{
			text-align: left;
		}
		#messageDialog{
			width: 90%
		}
		.panel-footer{
			padding-bottom: 20px;
		}
		.textcontent:focus{
		border-color:#a47e3c;
		-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,0.075),0 0 6px #dbc59e;box-shadow:inset 0 1px 1px rgba(0,0,0,0.075),0 0 6px #dbc59e}
    </style>

    <script src="/server-web/static/jquery/sockjs-0.3.min.js"></script>

    <script type="text/javascript">
        var ws = null;
        var url = null;
        var transports = [];

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('echo').disabled = !connected;
        }

        function connect() {
            if (!url) {
                alert('Select whether to use W3C WebSocket or SockJS');
                return;
            }

            ws = (url.indexOf('sockjs') != -1) ? 
                new SockJS(url, undefined, {protocols_whitelist: transports}) : new WebSocket(url);

            ws.onopen = function () {
                setConnected(true);
                log('提示!: 已连接直播.',false);
                $("#echo").css("visibility","visible");
                $("#reconnnect").css("visibility","hidden");
            };
            ws.onmessage = function (event) {
            	if((event.data).indexOf("HeapByteBuffer")>0)
            		return;
            	//'Received: ' + 
            	//若果是自己发出的信息
            	if((event.data).indexOf("<%=request.getParameter("username") %>:")>-1)
            		log(event.data,true);
            	else
                	log(event.data,false);
            };
            ws.onclose = function (event) {
                setConnected(false);
                log('提示!: 已断开连接.',false);
                log(event,false);
                $("#echo").css("visibility","hidden");
                $("#reconnnect").css("visibility","visible")
            };
        }

        function disconnect() {
            if (ws != null) {
                ws.close();
                ws = null;
            }
            setConnected(false);
            history.go(-1);
        }

        function echo() {
            if (ws != null) {
                var message = document.getElementById('message').value;
                if(message == "")
                	return;
                ws.send(message);
                $("#message").text("");
            } else {
                alert('connection not established, please connect.');
            }
        }

        function updateUrl(urlPath) {
            if (urlPath.indexOf('sockjs') != -1) {
                url = urlPath;
                //document.getElementById('sockJsTransportSelect').style.visibility = 'visible';
            }
            else {
              if (window.location.protocol == 'http:') {
                  url = 'ws://' + window.location.host + urlPath;
              } else {
                  url = 'wss://' + window.location.host + urlPath;
              }
              //document.getElementById('sockJsTransportSelect').style.visibility = 'hidden';
            }
        }

        function updateTransport(transport) {
          transports = (transport == 'all') ?  [] : [transport];
        }
        
        function log(message,isself) {
            var console = document.getElementById('console');
            //制造一个div存放消息，区分自己与其他人的消息
            var fd = document.createElement('div');
            fd.style.width = '100%';
            var p  = document.createElement('div');
            p.style.wordBreak = 'break-all';
            p.style.wordWrap = 'break-word';
            p.style.width = '75%';
            p.style.backgroundColor = 'rgb(231,245,245)';
            //使边角变圆
            p.style.borderRadius = '6px';
        	p.style.padding = '10px';
            //自己发出的信息靠右
            if(isself == true){
                p.style.backgroundColor = 'rgb(231,231,231)';
            	p.style.float = 'right';
            }
            else{
            }
            p.appendChild(document.createTextNode(message));
            fd.style.height = p.style.height;
            fd.style.float = 'left';
            fd.appendChild(p);
            //
            console.appendChild(fd);
            //添加一个分割各条信息的行
            var br = document.createElement('div');
            br.style.width = '100%';
            br.style.height = '5px';
            console.appendChild(br);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        }

        $(document).ready(function(){
        	$("#console").css("height",$("#panelbody").height()-100);
        	$("#message").focus(function(){
        		if($(this).text() == "")
    				$(this).text("");
    		});
    		$("#message").blur(function(){
    			var val = $(this).text();
    		});
        	updateUrl('/server-web/lumsLive?username=<%=request.getParameter("username") %>&groupid=<%=request.getParameter("groupid") %>');
        	connect();
        });
        
    </script>
</head>
<body style="font-size: 26pt;">
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets 
    rely on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div>
    <div id="connect-container">
        <div>
            <button id="connect" onclick="connect();">重连会议</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();">离开</button>
        </div>
        <div>
        </div>
    </div>
</div>
<div class="panel panel-primary gdialog" id="messageDialog" style="height: 80%;position: absolute;z-index: 1001;">
		  <div class="panel-heading">课程直播
		  <a id="disconnect" class="close"  onclick="disconnect(),$('#messageDialog').css('z-index',1000);">&times;</a>
		  </div>
		  <div id="panelbody" class="panel-body">
			  <div class="form-groupBuy" style="margin-top: 20px;">
			    <label for="exampleInputFile">消息内容:</label>
			    <div id="console-container">
			        <div id="console"></div>
			    </div>
			  </div>
		      
		  </div>
		  <div class="panel-footer" style="padding:5px 10px;text-align: center;padding-bottom: 20px;z-index: 2000;">
		  <div class="form-groupBuy" style="float: left;">
			    <label for="Amobile">
					发言:
				</label>
				<input type="text" class="textcontent" id="message"
					  style="width: 100%;height:100px;margin-right: 150px;" />
			  </div>
			  <div style="float: right;margin-top: 55px;">
						     <a type="button" id="echo" class="btn btn-success" onclick="echo();" style="width: 200px;font-size: 26pt;"> 发送</a>
			  </div>
						     <a type="button" id="reconnnect" class="btn btn-success" onclick="connect();" style="width: 200px;visibility: hidden;">重连会议</a>
		  </div>
		</div>
</body>
</html>
