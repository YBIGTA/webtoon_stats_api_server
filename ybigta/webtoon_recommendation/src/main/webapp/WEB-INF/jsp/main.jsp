<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>웹툰추천데모</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
.formHolder { text-align: center; }
.recommendListTitle { text-align: center; }
</style>
<body>
<div>
  <div class= "demo">
		<div class="col-sm-12">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="formHolder">
					<form class="form-inline">
						<input type="text" class="form-control" id="rec_input" size="50" placeholder="만화 이름" required>
						<button type="button" class="btn btn-danger" id="rec_btn">추천</button>
					</form>				
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>	  
		<br>
		<div class="col-sm-12">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<h2 class="recommendListTitle">추천 만화 목록</h2>
			</div>
			<div class="col-sm-2"></div>
		</div>	
		<br>

	<div class="row">
		<div class="col-sm-2 col-md-1"></div>				
		<div>
		   <div class = "col-sm-4 col-md-2">
		      <div class = "thumbnail">
		         <img id="rec_1_thumbnail" src = "img/webtoon.png" alt = "Generic placeholder thumbnail">
		      </div>
		      <div class = "caption">
		         <h3 id="rec_1_title" style="text-align:center">웹툰 제목</h3>
		         <p id="rec_1_genre" style="text-align:center">장르</p>
		         <p id="rec_1_author" style="text-align:center">작가</p>		         
		      </div>
		   </div>		
		</div>
		<div>
		   <div class = "col-sm-4 col-md-2">
		      <div class = "thumbnail">
		         <img id="rec_2_thumbnail" src = "img/webtoon.png" alt = "Generic placeholder thumbnail">
		      </div>
		      <div class = "caption">
		         <h3 id="rec_2_title" style="text-align:center">웹툰 제목</h3>
		         <p id="rec_2_genre" style="text-align:center">장르</p>
		         <p id="rec_1_author" style="text-align:center">작가</p>
		      </div>
		   </div>		
		</div>
		<div>
		   <div class = "col-sm-4 col-md-2">
		      <div class = "thumbnail">
		         <img id="rec_3_thumbnail" src = "img/webtoon.png" alt = "Generic placeholder thumbnail">
		      </div>
		      <div class = "caption">
		         <h3 id="rec_3_title" style="text-align:center">웹툰 제목</h3>
		         <p id="rec_3_genre" style="text-align:center">장르</p>
		         <p id="rec_3_author" style="text-align:center">작가</p>	         
		      </div>
		   </div>		
		</div>
		<div>
		   <div class = "col-sm-4 col-md-2">
		      <div class = "thumbnail">
		         <img id="rec_4_thumbnail" src = "img/webtoon.png" alt = "Generic placeholder thumbnail">
		      </div>
		      <div class = "caption">
		         <h3 id="rec_4_title" style="text-align:center">웹툰 제목</h3>
		         <p id="rec_4_genre" style="text-align:center">장르</p>
		         <p id="rec_4_author" style="text-align:center">작가</p>
		      </div>
		   </div>		
		</div>
		<div>
		   <div class = "col-sm-4 col-md-2">
		      <div class = "thumbnail">
		         <img id="rec_5_thumbnail" src = "img/webtoon.png" alt = "Generic placeholder thumbnail">
		      </div>
		      <div class = "caption">
		         <h3 id="rec_5_title" style="text-align:center">웹툰 제목</h3>
		         <p id="rec_5_genre" style="text-align:center">장르</p>
		         <p id="rec_5_author" style="text-align:center">작가</p>
		      </div>
		   </div>			
		</div>	
		<div class="col-sm-2 col-md-1"></div>		
	</div>
  </div> <!-- end demo -->
</div>
</body>
<script>
$("#rec_btn").click(function() {
	var webtoonName = $("#rec_input").val();
		
    $.ajax({
        url: "/api/webtoons/" + webtoonName,
        type: "get",
        success: function(data) {
        	if(data.result === "success") {
                for(var i = 0; i < data.recommendWebtoonList.length; i++) {
                	$("#rec_" + (i + 1) +"_thumbnail").attr("src","img/thumbnail/" + data.recommendWebtoonList[i].thumbnail);
                	$("#rec_" + (i + 1) +"_title").text(data.recommendWebtoonList[i].title);
                	$("#rec_" + (i + 1) +"_genre").text(data.recommendWebtoonList[i].genre);
                	$("#rec_" + (i + 1) +"_author").text(data.recommendWebtoonList[i].author);
                }
        	} else {
            	        		
        	}
            
        },
        error: function() {
            alert("error");
        }
    });
});
</script>
</html>