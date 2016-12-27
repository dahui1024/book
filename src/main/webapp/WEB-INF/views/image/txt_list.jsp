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

<title>${txt.title } —— ${txt.desc } | 中场分享</title>

<%@ include file="../../../html/css.html"%>
</head>

<body>

	<div class="container" id="#">

		<div class="page-header">
			<h1>${txt.title }<small>${txt.desc }</small></h1>
		</div>

		<c:forEach items="${txt_list }" var="txt" varStatus="s">
			<div class="row well  table-hover">
				<div class="col-md-7"><h1>${txt.title }</h1></div>
				<div class="col-md-1 text-center"><span class="label label-default">TXT</span></div>
				<div class="col-md-2 text-center" >
					<a href="/txts/${txt.id }/${txt.current_sn }/1" class="btn btn-default btn-sm" role="button">
					<span class="glyphicon glyphicon-play"></span>在线阅读</a>
				</div>
				<div class="col-md-2 text-center">
					<a href="/txts/${txt.id }/${txt.current_sn }" class="btn btn-default btn-sm" role="button">
					<span class="glyphicon glyphicon-list"></span>目录</a>
				</div>
			</div>
		</c:forEach>
	
		<div class="row">
			<a href="/" class="col-md-4 btn btn-warning btn-lg" role="button"><span class="glyphicon glyphicon-home"></span>首页</a>
			<a href="#" class="col-md-4 btn btn-primary btn-lg" role="button"><span class="glyphicon glyphicon-arrow-up"></span>顶部</a>
			<a href="javascript:history.go(-1)" class="col-md-4 btn btn-warning btn-lg" role="button"><span class="glyphicon glyphicon-arrow-left"></span>返回</a>
		</div>
	</div>


	<%@ include file="../../../html/js.html"%>

</body>
</html>