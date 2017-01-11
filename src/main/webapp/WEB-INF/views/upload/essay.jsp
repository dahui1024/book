<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="小说,小说网,杂书,原创网络文学,如何阅读一本书">
	<meta name="description" content="杂书网，免费小说、经典文集分享。">
    <link rel="icon" href="/favicon.ico">

    <title>短文上传 | 杂书网</title>

    <%@ include file="../../../html/css.html"%>
    
  </head>

  <body>

	<div class="container">

		<div class="page-header">
			<h1>短文分享专区<small>好看、好玩</small></h1>
		</div>

		<form role="form" action="upload_essay" method="post">
			<div class="row">
				<div class="form-group col-md-12">
					<label for="name">标题</label>
					<input type="text" class="form-control input-md" id="name" name="name" placeholder="键入标题" required="required">
				</div>
				<div class="form-group col-md-12">
					<label for="password">秘钥<var>如需保密，请输入密码</var></label>
					<input type="password" class="form-control input-md" id="password" name="password" placeholder="如需保密，请输入秘钥">
				</div>
				<div class="form-group col-md-12">
					<label for="description">简介</label>
					<textarea class="form-control" rows="3" id="description" name="description" placeholder="可以留下个人信息、作品信息或任何信息，这很重要哦。" required="required"></textarea>
				</div>
				<div class="form-group col-md-12">
					<label for="content">正文</label>
					<textarea class="form-control" rows="50" id="content" name="content" placeholder="键入正文" required="required"></textarea>
				</div>
			</div>
			<nav class="navbar-fixed-bottom container">
				<button type="submit" class="btn btn-danger btn-lg btn-block">发布</button>
			</nav>
			
		</form>

	</div>
	<!-- /container -->
	
	<%@ include file="../../../html/footer.html"%>
	
	<%@ include file="../../../html/js.html"%>
	
  </body>
</html>