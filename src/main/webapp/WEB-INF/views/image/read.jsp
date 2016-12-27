<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="重温经典文学，分享经典文学。">
<meta name="author" content="">
<link rel="icon" href="/favicon.ico">

<title>${txt.title } | 中场分享</title>

<%@ include file="../../../html/css.html"%>
</head>

<body>

	<div class="container" id="#">

		<div class="page-header">
			<h1>${txt.title }<small>${txt.desc }</small></h1>
		</div>

		<div id="content" class="row">
		
		</div>
		<div class="row">
			<c:choose>
				<c:when test="${txt.next_sn > 0 }">
					<a href="/" class="col-md-2 btn btn-warning btn-lg" role="button"><span class="glyphicon glyphicon-home"></span>首页</a>
					<a href="/txts/${txt.id }" class="col-md-8 btn btn-primary btn-lg" role="button"><span class="glyphicon glyphicon-file"></span>下一章</a>
					<a href="#" class="col-md-1 btn btn-warning btn-lg" role="button"><span class="glyphicon glyphicon-arrow-up"></span>顶部</a>
					<a href="javascript:history.go(-1)" class="col-md-1 btn btn-warning btn-lg" role="button"><span class="glyphicon glyphicon-arrow-left"></span>返回</a>
				</c:when>
				<c:otherwise>
					<a href="/" class="col-md-4 btn btn-warning btn-lg" role="button"><span class="glyphicon glyphicon-home"></span>首页</a>
					<a href="#" class="col-md-4 btn btn-primary btn-lg" role="button"><span class="glyphicon glyphicon-arrow-up"></span>顶部</a>
					<a href="javascript:history.go(-1)" class="col-md-4 btn btn-warning btn-lg" role="button"><span class="glyphicon glyphicon-arrow-left"></span>返回</a>
				</c:otherwise>
			</c:choose>
		
		</div>
		
		
		<!--PC打分版-->
		<div id="SOHUCS" sid="${txt.title }"></div>
		<script charset="utf-8" type="text/javascript" src="https://changyan.sohu.com/upload/changyan.js" ></script>
		<script type="text/javascript">
			window._config = { showScore: true };
			window.changyan.api.config({
			appid: 'cysK7gU7o',
			conf: 'prod_63d9ae711ffce9af708d8a3d607e4d8e'
			});
		</script>
	</div>


	<%@ include file="../../../html/js.html"%>
	<script type="text/javascript">
		getTxt = function() {
			$.ajax({
				url : 'https://file.oss.bbcow.com/${txt.url }',
				success : function(data) {
					var arr = data.split("\n");
					//data.replace(/[\n]/g,"<br/>")
					for(var i=0; i<arr.length;i++){
						$("#content").append("<p class='lead'><font color='red'>"+(i+1)+"</font>"+arr[i]+"</p>");
					}
				}
			});
		}
		getTxt();
	</script>
</body>
</html>