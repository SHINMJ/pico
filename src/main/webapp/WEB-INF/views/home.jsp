<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
	<title>Home</title>
</head>
<body>
	
	<div class="header">
	<sec:authorize access="isAuthenticated()">
	    <sec:authentication property="principal" var="loginUser" />
	    <h2>${loginUser.name()} 회원님, 환영합니다.</h2>
	    <form action="/logout" method="post">
	        <sec:csrfInput/>
	        <button class="logout-btn" type="submit">로그아웃</button>
	    </form>
	</sec:authorize>
	</div>
	
	<div class="centered-div parent-container">
		<h2 class="mg-50">구매 금액별로 경품을 지급합니다.</h2>
		
		<form id="checkboxForm" class="checkbox-list" >
			    <sec:csrfInput/>
			    <c:forEach var="product" items="${products}">
				    <div class="checkbox-item checkbox-labels">
				        <input type="checkbox" id="${product.id()}" name="product" value="${product.id()}">
				        <label for="${product.id()}" >
				        	<div class="flex-container">
						        <div > ${product.name()} </div>
						        <div ><fmt:formatNumber type="number" maxFractionDigits="3" value="${product.price()}" /> 원</div>	
				        	</div>
				        </label>
				    </div>
				    
				</c:forEach>
		</form>
		
		<button class="btn"  type="button" id="orderBtn" >선택주문</button>
		    
	</div>
	
	<script
	  src="https://code.jquery.com/jquery-3.5.1.min.js"
	  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	  crossorigin="anonymous"></script>
	  
	  <script>
	  
		  $('#orderBtn').on("click", (e) => {
		        e.preventDefault()
		        
		        var checkedValues = []; // 체크된 체크박스의 값을 저장할 배열

		        $('input[name="product"]:checked').each(function() {
		            checkedValues.push($(this).attr('id')); // 체크된 체크박스의 ID를 배열에 추가
		        });
		        
		        console.log('선택된 체크박스:', checkedValues);
		        
		        if (!(checkedValues && checkedValues.length)){
		        	alert("상품을 선택해 주세요.");
		        	return;
		        }
		        
		        var csrfToken = $('input[name="_csrf"]').attr('value'); // CSRF 토큰을 가져옴

		        $.ajax({
		            type:'post',
		            url:'http://localhost:8080/order',
		            headers: { 'X-CSRF-TOKEN': csrfToken }, // 요청 헤더에 CSRF 토큰 추가
		            contentType: 'application/json;charset=UTF-8',
		            dataType: 'text', 
		            data:JSON.stringify(checkedValues),
		            success: function(data, textStatus) {
		                
		                if (data){
		                	console.log(data)
		                	alert(`경품은 `+ JSON.parse(data).name + `(이)가 지급됩니다.`);
		                }
		            },
		            error:function (data, textStatus) {
		                console.log('error');
		                console.log(data);
		                console.log(textStatus)
		            }
		        })   
	
		    })
	  </script>
</body>

</html>
