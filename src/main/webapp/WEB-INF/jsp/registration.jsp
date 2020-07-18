<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>

	<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css" />

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="local" var="loc" />

	<fmt:message bundle="${loc}" key="local.registration.label.name" var="registration_name_label" />
	<fmt:message bundle="${loc}" key="local.registration.label.surname" var="registration_surname_label" />
	<fmt:message bundle="${loc}" key="local.registration.label.phoneNumber" var="registration_phoneNumber_label" />
	<fmt:message bundle="${loc}" key="local.label.email" var="registration_email_label" />
	<fmt:message bundle="${loc}" key="local.label.password" var="registration_password_label" />
	<fmt:message bundle="${loc}" key="local.registration.button" var="registration_button" />
	<fmt:message bundle="${loc}" key="local.registration.message.error" var="registration_error_message" />

</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/jsp/header.jsp" />
	</header>

	<main>
		<div class="formcontainer marginT">

			<div class="registrationContainer">
			
				<span class="title">Registration</span>

				<form:form action="registration" modelAttribute="regUser" method="post">
				
					<form:label path="name">Username: </form:label><br />
					<form:input type="text" path="username" /> <br /> 

					<form:label path="name">Name: </form:label><br />
					<form:input type="text" path="name" /> <br /> 
					
					<form:label path="surname">Surname: </form:label><br /> 
					<form:input	type="text" path="surname" /><br /> 
					
					<form:label path="phoneNumber">Phone number: </form:label> <br />
					<form:input type="text" path="phoneNumber" maxlength="19" placeholder="+375 (__) ___-__-__"/> <br /> 
						
					<form:label path="email">Email: </form:label><br />
					<form:input path="email" type="email" /><br /> 
					
					<form:label path="password">Password: </form:label><br /> 
					<form:password path="password"/><br />
					<br /> 
					
					<form:button class="button" name="registration">Registration</form:button><br />

				</form:form>

				<br/><br/>

				<c:if test="${param.registration == 'error'}">
					<span class="errorMessage">Registration Error. Please, check your submited data</span>
				</c:if>


			</div>
		</div>
	</main>
</body>
</html>
