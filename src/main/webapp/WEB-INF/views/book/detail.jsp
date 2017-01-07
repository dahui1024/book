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

<title>${book.name } | 中场分享</title>

<%@ include file="../../../html/css.html"%>
<style type="text/css">
dt {
	padding-bottom: 10px;
}
</style>
</head>

<body>

	<div class="container">

		<div class="page-header">
			<h3>${book.name }<small>${book.description }</small></h3>
		</div>
		<c:forEach items="${chapter_list }" var="chapter" varStatus="s">
			<dl onclick="read('${book.id }','${chapter.sn }')" style="cursor:pointer;">
				<dt>
					<var><font color="red">${s.index+1}  </font></var>${chapter.title }
					<span class="glyphicon glyphicon-play-circle"></span>
				</dt>
				<dd>
					简要：
					<c:choose>
						<c:when test="${!empty chapter.summary }">
				  			 ${chapter.summary }
				  		</c:when>
						<c:otherwise>
							无
						</c:otherwise>
					</c:choose>
				</dd>
			</dl>
		</c:forEach>
		
		<nav class="navbar-fixed-bottom">
			<ul class="pager">
				<li><a href="/"><span class="glyphicon glyphicon-home"></span>首页</a></li>
				<c:if test="${!empty page }">
					<li><a href="/books?page=${page }"><span class="glyphicon glyphicon-list-alt"></span>下一页</a></li>
				</c:if>
				<li><a href="javascript:history.go(-1)"><span class="glyphicon glyphicon-chevron-left"></span>返回</a></li>
			</ul>
		</nav>
	</div>

	<%@ include file="../../../html/footer.html"%>

	<%@ include file="../../../html/js.html"%>
	<script type="text/javascript">
		function read(book_id, sn){
			location.href="/books/"+book_id+"/"+sn;
		}
	
	</script>
</body>
</html>