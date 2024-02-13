<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<c:set var="path" value="${pageContext.request.contextPath}"/>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${path}/resources/css/style.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>
	<div class="login-wrapper">
        <h2>Login</h2>
        <form action="/login" method="post" id="login-form">
  			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
            <input type="text" name="username" placeholder="아이디">
            <input type="password" name="password" placeholder="패스워드">
            <input type="submit" value="로그인">
        </form>
        <!-- 실패 메시지 표시 -->
	    <c:if test="${errMsg != null}">
	        <p class="login-error">로그인에 실패했습니다. 다시 시도해주세요.</p>
	    </c:if>
    </div>
</body>
</html>