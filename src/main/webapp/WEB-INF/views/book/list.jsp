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

<title>经典文集 | 中场分享</title>

<%@ include file="../../../html/css.html"%>
</head>

<body>

	<div class="container">

		<div class="page-header">
			<h1>经典文集<small>生活中的点滴作品</small></h1>
		</div>

		<c:forEach items="${books }" var="txt" varStatus="s">
			<div class="panel panel-default">
				<div class="panel-heading">
					${txt.name }<small>&nbsp;&nbsp;——&nbsp;&nbsp;${txt.author }</small>
				</div>
				<div class="panel-body">
					${txt.description }
				</div>
				<ul class="list-inline" style="padding: 0 15px;">
					<c:forEach items="${txt.labels }" var="label">
						<li class="">
							<span class="label label-default">${label }</span>
						</li>
					</c:forEach>
				</ul>
				<ul class="list-inline text-right" style="padding: 0 15px;">
					<li class="">
						<a href="/books/${txt.id }" class="btn btn-default btn-sm" role="button">
							<span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;&nbsp;&nbsp;详情
						</a>
					</li>
				</ul>
			</div>
		</c:forEach>

		<nav class="navbar-fixed-bottom">
			<ul class="pager">
				<li><a href="/"><span class="glyphicon glyphicon-home"></span>首页</a></li>
				<c:if test="${!empty page }">
					<li><a href="/books?page=${page }"><span class="glyphicon glyphicon-list-alt"></span>下一页</a></li>
				</c:if>
				<li><a href="javascript:history.go(-1)"><span class="glyphicon glyphicon-chevron-left"></span>返回</a></li>
				<li><a href="javascript:history.go(1)"><span class="glyphicon glyphicon-chevron-right"></span>向前</a></li>
			</ul>
		</nav>
	</div>
	
	<%@ include file="../../../html/footer.html"%>
	
	<%@ include file="../../../html/js.html"%>

</body>
</html>