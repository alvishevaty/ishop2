<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		
		<style>
			<%@include file="/resources/css/style.css"%>
		</style>
		
		<title>iShop</title>

		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="local" var="loc" />
	

		<fmt:message bundle="${loc}" key="local.registration.message.success" var="registration_success_message" />
		<fmt:message bundle="${loc}" key="local.mainpage.message" var="home_page_message" />
	
	</head>
	<body>

		<header>
   		   <jsp:include page="/WEB-INF/jsp/header.jsp" />
 	 	</header>
 	  
		<main>
			<div class="registrationSuccessMessage">
				<c:if test="${param.registration == 'success'}">
					<span class="successRegistrationMessage">User successfully registered</span>
				</c:if>
			</div>
			<div class="welcomeMessageContainer">
				<span class="welcomeMessage">Welcome to iShop!</span>
			</div>
		</main>
	</body>
</html>