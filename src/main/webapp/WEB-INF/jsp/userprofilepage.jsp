<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Profile</title>
<link rel="stylesheet" href="style.css">

		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.registration.label.name" var="name_lable" />
		<fmt:message bundle="${loc}" key="local.registration.label.surname" var="surname_lable" />
		<fmt:message bundle="${loc}" key="local.registration.label.phoneNumber" var="phoneNumber_lable" />
		<fmt:message bundle="${loc}" key="local.label.email" var="email_lable" />

</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/header.jsp" />
	</header>
	<main>
	<div class="mainPage">
		<div class="fullProfileContainer">
			<div class="profileContainer">
				<table>
				<col class="col1">
   				<col span="1" class="coln">
					<tr>
						<td><h3>Name: </h3></td>
						<td><c:out value="${userInfo.name}" /></td>
					</tr>
					<tr>
						<td><h3>Surname: </h3></td>
						<td><c:out value="${userInfo.surname}" /></td>
					</tr>
					<tr>
						<td><h3>Phone number: </h3></td>
						<td><c:out value="${userInfo.phoneNumber}" /></td>
					</tr>
					<tr>
						<td><h3>Email: </h3></td>
						<td><c:out value="${userInfo.email}" /></td>
					</tr>
				</table>
				
			</div>
		</div>
		</div>
	</main>
	
</body>
</html>