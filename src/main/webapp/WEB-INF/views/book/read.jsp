<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="重温经典文学，分享经典文学。">
<meta name="author" content="">
<link rel="icon" href="/favicon.ico">

<title>${chapter.title } | 中场分享</title>

<%@ include file="../../../html/css.html"%>
<style type="text/css">
p {
	color: #262626;
}
</style>

</head>

<body>

	<div class="container" id="#">
		<div class="page-header">
			<h3>${chapter.title }<small>${chapter.summary }</small></h3>
		</div>
		<div id="content" class="col-md-12"></div>
		<div class="col-md-12">
			<hr>
			<h3>想法</h3>
			<form role="form" action="/chapter/comments#comments" method="post">
				<div class="form-group col-md-12">
					<textarea class="form-control" rows="3" id="remark" name="remark" placeholder="有想法，就毫不吝啬的表达出来吧。" required="required"></textarea>
				</div>
				<div class="form-group col-md-6">
					<input type="text" class="form-control input-md" id="invitation_code" name="invitation_code" placeholder="邀请码：****，目前全部开放" required="required">
				</div>
				<div class="form-group col-md-6">
					<input type="hidden" value="${chapter.id }" name="id">
					<button type="submit" class="btn btn-danger btn-md btn-block ">发表</button>
				</div>
			</form>
		</div>
		<div id="comments" class="col-md-12">
			<c:if test="${!empty chapter.comments}">
				<c:forEach items="${chapter.comments }" var="comment" varStatus="s">
					<div class="panel panel-default">
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
			<c:if test="${empty chapter.comments}">
				<p class="lead text-center">无</p>
			</c:if>
			<hr>
		</div>
		
		<nav>
			<ul class="pager">
				<li><a href="/"><span class="glyphicon glyphicon-home"></span>首页</a></li>
				<c:if test="${!empty chapter }">
					<li><a href="/books/${chapter.book_id }/${chapter.sn+1 }"><span class="glyphicon glyphicon-list-alt"></span>下一章</a></li>
				</c:if>
				<li><a href="#"><span class="glyphicon glyphicon-arrow-up"></span>顶部</a></li>
				<li><a href="javascript:history.go(-1)"><span class="glyphicon glyphicon-arrow-left"></span>返回</a></li>
			</ul>
		</nav>
		
	</div>


	<%@ include file="../../../html/js.html"%>
	<script type="text/javascript">
		//https://file.oss.bbcow.com
		getTxt = function() {
			$.ajax({
				url : 'http://oikrsbhcw.bkt.clouddn.com/txt/${chapter.book_id }/${chapter.sn }.txt',
				success : function(data) {
					var arr = data.split("\n");
					var num = 1;
					for(var i=0; i<arr.length;i++){
						if(arr[i] != ""){
							$("#content").append("<p class='lead' id='c"+i+"'><var><font color='red'>"+num+"   </font></var>"+arr[i]+"</p>");
							$("#content").append(audio(i));
							
							num++;
						}
					}
				}
			});
		}
		getTxt();
		
		
		function audio(num) {
			var text = $("#c" + num + "").text();
			if(text != null){
				text = text.replace((num+1), "")
			}
			var x = document.createElement("AUDIO");
			x.setAttribute("preload", "none");
			x.setAttribute("src", "http://tsn.baidu.com/text2audio?tex="+text+"&lan=zh&tok=${token }&ctp=1&cuid=${ip}");
			x.setAttribute("controls", "controls");
			x.setAttribute("class", "audio");
			return x;
		}
	</script>
</body>
</html>