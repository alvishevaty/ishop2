<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Authorization</title>
		
		<style>
			<%@include file="/resources/css/style.css"%>
		</style>

	</head>
	<body>
	
		<header>
     		<jsp:include page="/WEB-INF/jsp/header.jsp" />
  	 	</header>
  	 	
  	 	<main>
  	 		<div class="formcontainer">

				<div class="signinContainer">
					
					<span class="title">Welcome</span>
				
					<form action="<c:url value="/signin"/>" method="post">
						
						<input type="hidden" name="command" value="signin" /> 
				
						<span class="email">Username: </span><br /> 
						<input class="input1" type="text" name="username"/><br /> 
				
						<span class="password">Password: </span><br /> 
						<input class="input1" type="password" name="password" /><br /> 
						<br /> 
						
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				
						<input class="button" type="submit" value="Enter" />
					</form>
	
					<a href="<c:url value="/registrationpage" />"><button class="button">Registration</button></a>
					
					<br /><br />
					
					<c:if test="${param.signin == 'error'}">
	           			<span class="errorMessage">Authorization Error. Please, check your submited data</span>
	       			</c:if>	
				</div>
			</div>
		</main>	
	</body>
</html>