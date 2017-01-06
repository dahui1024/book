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

<title>${essay.name } | 中场分享</title>

<%@ include file="../../../html/css.html"%>
<style type="text/css">
p {
	color: #262626;
	cursor: pointer;
}
</style>

</head>

<body>

	<div class="container" id="#">
		<div class="page-header">
			<h3>${essay.name }<small>${essay.description }</small></h3>
		</div>
		<div id="toolbar" class="col-md-12 well">
			<div class="checkbox">
				<label><input type="checkbox" name="hide_audio" id="hide_audio" value="隐藏语音" onchange="hideAudio(this)">隐藏语音</label>
			</div>
		</div>
		<div id="content" class="col-md-12">
			<div class="progress">
				<div id="progress_bar" class="progress-bar" aria-valuemin="10" aria-valuemax="100">
					
				</div>
			</div>
		</div>
		
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


	<%@ include file="../../../html/js.html"%>
	<script type="text/javascript">
		//https://file.oss.bbcow.com
		//http://oikrsbhcw.bkt.clouddn.com
		var storage = window.localStorage;
		var isHideAudio = storage.getItem("isHideAudio");
		
		loadContent = function() {
			$.ajax({
				url : 'https://file.oss.bbcow.com/essay/${essay.id }.txt',
				success : function(data) {
					showData(data);
				}
			});
			// 滑动标签处
			if (storage.getItem("${chapter.id}.bookmark")){
				var index = storage.getItem("${chapter.id}.bookmark");
				location.href="#c"+index;
				
				$("#c"+index+"").append('<span class="glyphicon glyphicon-bookmark" style="color:blue"></span>');
			}
			if(isHideAudio == "true"){
				$("#hide_audio").attr("checked","checked");
			}
		}
		loadContent();
		
		function addBookmark(num){
			storage.setItem("${chapter.id}.bookmark", num);
			$("#c"+num+"").append('<span class="glyphicon glyphicon-bookmark" style="color:blue"></span>');
		}
		
		function showData(data){
			var arr = data.split("\n");
			var len = arr.length;
			var num = 1;
			for(var i=0; i < len;i++){
				if(arr[i] != ""){
					$("#content").append("<p onclick='addBookmark("+num+")' class='lead' id='c"+num+"'><var><font color='red'>"+num+"   </font></var>"+arr[i]+"</p>");
					
					$("#content").append(audio(num));
					
					progressBar(i/len*100);
					num++;
				}
			}
			progressBar(100);
		}
		function hideAudio(obj){
			if ($(obj).attr('checked') == undefined) {
				$(".audio").hide();
				$(obj).attr("checked","checked");
				
				storage.setItem("isHideAudio", true);
			} else {
				$(".audio").show();
				$(obj).removeAttr("checked");
				storage.setItem("isHideAudio", false);
			}
			
		}
		
		
		function progressBar(percent){
			$("#progress_bar").css("width" ,percent+"%");
			$("#progress_bar").attr("aria-valuenow", percent);
			$("#progress_bar").text(percent+"%");
			if(percent == 100){
				//$(".progress").hide();
			}
		}
		
		function audio(num) {
			var text = $("#c" + num + "").text();
			if(text != null){
				text = text.replace((num), "")
			}
			
			var x = document.createElement("AUDIO");
			x.setAttribute("preload", "none");
			x.setAttribute("src", "http://tsn.baidu.com/text2audio?tex="+text+"&lan=zh&tok=${token }&ctp=1&cuid=${ip}");
			x.setAttribute("controls", "controls");
			x.setAttribute("class", "audio");
			
			if (isHideAudio == "true"){
				$(x).css("display","none");
			}
			
			return x;
		}
	</script>
</body>
</html>