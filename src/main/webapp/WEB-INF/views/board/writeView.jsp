<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery CDN -->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시판</title>

<!-- 자바스크립트 시작 -->
<script type="text/javascript">
$(document).ready(function(){
    var formObj = $("form[name='writeForm']");
    $(".write_btn").on("click", function(){
        if(fn_valiChk()){
            return false;
        }
        formObj.attr("action", "/board/write");
        formObj.attr("method", "post");
        formObj.submit();
    });
});

function fn_valiChk() {
    var regForm = $("form[name='writeForm'] .chk").length;
    for(var i = 0; i<regForm; i++){
        if($(".chk").eq(i).val() == "" || $(".chk").eq(i).val() == null) {
            alert($(".chk").eq(i).attr("title"));
            return true;
        }
    }
}
</script>
<!-- 자바스크립트 끝 -->
</head>
<body>
	<div id="root">
		<header>
			<h1>게시판</h1>
		</header>
		<hr>
		
		<div>
			<%@include file="nav.jsp" %>
		</div>
		<hr>
		
		<section id="container">
			<form role="form" method="post" action="/board/write" name="writeForm">
				<table>
					<tbody>
						<tr>
							<td>
								<label for="title">제목</label>
								<input type="text" id="title" name="title" class="chk" title="제목을 입력하세요">
							</td>
						</tr>
						<tr>
							<td>
								<label for="content">내용</label>
								<textarea id="content" name="content" class="chk" title="내용을 입력하세요"></textarea>
							</td>
						</tr>
						<tr>
							<td>
								<label for="writer">작성자</label>
								<input type="text" id="writer" name="writer" class="chk" title="작성자를 입력하세요">
							</td>
						</tr>
						<tr>
							<td>
								<button class="write_btn" type="submit">작성</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</section>
		<hr>
	</div>
</body>
</html>