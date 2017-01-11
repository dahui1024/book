<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="${essay.description }">
<meta name="description" content="${essay.description }">
<link rel="icon" href="/favicon.ico">

<title>${essay.name } | 杂书网</title>

<%@ include file="../../../html/css.html"%>

</head>

<body>

	<div class="container" id="#">
		<div class="page-header">
			<h3>${essay.name }<small>${essay.description }</small></h3>
		</div>
		<div id="toolbar" class="col-md-12 well" style="display: none;">
			<div class="checkbox">
				<label><input type="checkbox" name="hide_audio" id="hide_audio" value="隐藏语音" onchange="hideAudio(this)">隐藏语音</label>
			</div>
		</div>
		<div id="valid" class="col-md-12 well">
			<form role="form form-inline" action="upload_essay" method="post">
				<div class="row">
					<div class="form-group col-md-8">
						<input type="password" class="form-control input-md" id="password" name="password" required="required" placeholder="请输入秘钥">
					</div>
					<div class="form-group col-md-4">
					<button type="button" class="btn btn-danger btn-md btn-block" onclick="checkPassword()">校验</button>
				</div>
				</div>
			</form>
		</div>
		<div id="content" class="col-md-12">
			<div class="progress" style="display: none;">
				<div id="progress_bar" class="progress-bar" aria-valuemin="10" aria-valuemax="100">
					
				</div>
			</div>
		</div>
		
		<div class="col-md-12">
			<hr>
			<h3>想法</h3>
			<form role="form" action="" method="post">
				<div class="form-group col-md-12">
					<textarea class="form-control" rows="3" id="remark" name="remark" placeholder="有想法，就毫不吝啬的表达出来吧。" required="required"></textarea>
				</div>
				<div class="form-group col-md-6">
					<input type="text" class="form-control input-md" id="invitation_code" name="invitation_code" placeholder="邀请码：****，目前全部开放" required="required">
				</div>
				<div class="form-group col-md-6">
					<input type="hidden" value="${essay.id }" name="id">
					<button id="submit" type="button" onclick="publishComment('${essay.id }','/essay/comments')"  data-toggle="popover" data-trigger="click" data-placement="top" class="btn btn-danger btn-md btn-block ">发表</button>
				</div>
			</form>
		</div>
		<div id="comments" class="col-md-12">
			<c:if test="${!empty essay.comments}">
				<c:forEach items="${essay.comments }" var="comment" varStatus="s">
					<div class="panel panel-default ${comment.nickname }">
						<div class="panel-heading">
							${comment.nickname }
							<var>发表于：<fmt:formatDate value="${comment.create_time }" pattern="yyyy-MM-dd hh:mm:ss"/> </var>
						</div>
						<div class="panel-body">
							${comment.remark }
						</div>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty essay.comments}">
				<p class="lead text-center">无</p>
			</c:if>
			<hr>
		</div>
		
		<nav class="navbar-fixed-bottom">
			<ul class="pager">
				<li><a href="#comments"><span class="glyphicon glyphicon-comment"></span>评论区</a></li>
				<c:if test="${!empty chapter }">
					<li><a href="/books/${chapter.book_id }/${chapter.sn+1 }"><span class="glyphicon glyphicon-book"></span>下一章</a></li>
				</c:if>
				<li><a href="#"><span class="glyphicon glyphicon-chevron-up"></span>顶部</a></li>
				<li><a href="javascript:history.go(-1)"><span class="glyphicon glyphicon-chevron-left"></span>返回</a></li>
			</ul>
		</nav>
		
	</div>

	<%@ include file="../../../html/footer.html"%>

	<%@ include file="../../../html/js.html"%>
	<script type="text/javascript" src="/static/js/read.js"></script>
	<script type="text/javascript">
		function checkPassword(){
			var pass = $("#password").val();	
			$.ajax({
				method : "POST",	
				data : {password : pass},
				url : '/essays/ajax/check/${essay.id}',
				success : function(data) {
					if(data == "error"){
						alert("校验失败");
					}else{
						$("#valid").hide();
						$("#toolbar").show();
						
						loadContent("essay/${essay.id }/"+data+".txt","${chapter.id }", "${token}", "${ip}");
					}
				}
			});
		}
	</script>
</body>
</html>