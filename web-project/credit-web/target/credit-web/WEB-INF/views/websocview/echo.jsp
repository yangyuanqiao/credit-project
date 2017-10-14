<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
    <title>WebSocket/SockJS Echo Sample (Adapted from Tomcat's echo sample)</title>
    <script src="/server-web/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <style type="text/css">
        #connect-container {
            float: left;
            width: 400px
        }

        #connect-container div {
            padding: 5px;
        }

        #console-container {
            float: left;
            margin-left: 15px;
            width: 400px;
        }

        #console {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 170px;
            overflow-y: scroll;
            padding: 5px;
            width: 100%;
        }

        #console p {
            padding: 0;
            margin: 0;
        }
    </style>

    <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>

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
    	$("#message").focus(function(){
    		if($(this).text() == "")
				$(this).text("");
		});
		$("#message").blur(function(){
			var val = $(this).text();
		});
    	updateUrl('/server-web/echo?username=<%=request.getParameter("username") %>&groupid=<%=request.getParameter("groupid") %>');
    	connect();
    });
    
    </script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets 
    rely on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div>
    <div id="connect-container">
        <div>
            <button id="connect" onclick="connect();">重连直播</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();">离开</button>
        </div>
        <div>
            <textarea id="message" style="width: 350px" ></textarea>
        </div>
        <div>
            <button id="echo" onclick="echo();" disabled="disabled">发送</button>
        </div>
    </div>
    <div id="console-container">
        <div id="console"></div>
    </div>
</div>
</body>
</html>
