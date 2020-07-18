<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<style>
		<%@include file="/resources/css/style.css"%>
	</style>


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.button.page.main"
	var="mainPage_button" />
<fmt:message bundle="${loc}" key="local.button.page.signin"
	var="signinPage_button" />
<fmt:message bundle="${loc}" key="local.button.page.signout"
	var="signoutPage_button" />
<fmt:message bundle="${loc}" key="local.button.page.men"
	var="men_button" />
<fmt:message bundle="${loc}" key="local.button.page.women"
	var="women_button" />
<fmt:message bundle="${loc}" key="local.button.page.basket"
	var="basket_button" />
<fmt:message bundle="${loc}" key="local.button.hi" var="hi_button" />

</head>
<body>
	<div id="header">


		<div class="header-nav headerChild">
			<div class="header-nav">
				<a href="<c:url value="/catalog?gender=Men" />">Men</a>
				<a href="<c:url value="/catalog?gender=Women" />">Women</a>
			</div>
		</div>

		<div class="shopName headerChild">
			<a class="siteName" href="<c:url value="/mainpage" /> ">iShop</a>
		</div>

		
		<div class="localisation headerChild">
			
			<table>
				<tr>
					<td>
					
					
					<sec:authorize access="authenticated" var="authenticated" />
					<c:choose>
						<c:when test="${authenticated}">
								<a href="<c:url value="/userinfopage" /> ">Hi, <sec:authentication
										property="principal.username" />
								</a> |
								
								<c:url var="basketpage" value="/basketpage"/>
                            	
                            	<a href="${basketpage}">Basket
									<c:if test="${basketSize <= 0}">
										<sup><c:out value=" " /></sup>
									</c:if> 
									<c:if test="${basketSize > 0}">
										<sup><c:out value="${basketSize}" /></sup>
									</c:if>
								</a> |
								
								<a id="signout" href="#" onclick="document.getElementById('signout-form').submit();">SignOut</a>
									
								<form id="signout-form" action="<c:url value="/signout"/>" method="POST">
									<sec:csrfInput/>
								</form>

								

							</c:when>
							<c:otherwise>
								
								<c:url var="basketpage" value="/basketpage"/>
							
								<a href="${basketpage}">Basket 
									<c:if test="${basketSize <= 0}">
										<sup><c:out value=" " /></sup>
									</c:if> <c:if test="${basketSize > 0}">
										<sup><c:out value="${basketSize}" /></sup>
									</c:if>
								</a> |
								<a href="<c:url value="/signin"/>">SignIn</a>
								
							</c:otherwise>


						</c:choose>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>