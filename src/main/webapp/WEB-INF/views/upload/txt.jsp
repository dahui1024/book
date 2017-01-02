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

    <title>文件上传 | 中场分享</title>

    <%@ include file="../../../html/css.html"%>
    
  </head>

  <body>

	<div class="container">

		<div class="page-header">
			<h1>资源分享专区<small>好看、好玩</small></h1>
		</div>

		<div class="row">
			<form role="form" action="upload_txt" method="post" enctype="multipart/form-data">
				<div class="form-group col-md-12">
					<label for="invitation_code">邀请码</label>
					<input type="text" class="form-control input-md" id="invitation_code" name="invitation_code" placeholder="邀请码：****" required="required">
				</div>
				<div class="form-group col-md-12">
					<label for="name">书名</label>
					<input type="text" class="form-control input-md" id="name" name="name" placeholder="键入书名" required="required">
				</div>
				<div class="form-group col-md-12">
					<label for="author">作者</label>
					<input type="text" class="form-control input-md" id="author" name="author" placeholder="键入作者" required="required">
				</div>
				<div class="form-group col-md-12">
					<label for="labels">标签</label>
					<div class="checkbox">
						<label><input type="checkbox" name="labels" value="小说">小说</label>
						<label><input type="checkbox" name="labels" value="论说">论说</label>
					</div>
				</div>
				<div class="form-group col-md-12">
					<label for="desc">作品描述</label>
					<textarea class="form-control" rows="3" id="desc" name="desc" placeholder="把这本书介绍给大家把。"></textarea>
				</div>
				<div class="form-group col-md-12">
					<label for="file">选择文件</label>
					<input type="file" id="file" name="file" class="input-lg" required="required">
					<p class="help-block">目前只支持TXT文档，禁止上传违法内容。</p>
				</div>
				<div class="form-group col-md-12">
					<button type="submit" class="btn btn-danger btn-lg btn-block">上传</button>
				</div>
			</form>
		</div>

	</div>
	<!-- /container -->

	<%@ include file="../../../html/js.html"%>
	
  </body>
</html>