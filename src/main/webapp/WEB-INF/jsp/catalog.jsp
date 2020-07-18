<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>Catalog</title>

	<style>
		<%@ include file="/resources/css/style.css"%>
	</style>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="local.catalog.Clothes" var="clothes" />
<fmt:message bundle="${loc}" key="local.catalog.Footwear" var="footwear" />
<fmt:message bundle="${loc}" key="local.catalog.Accessories"
	var="accessories" />
<fmt:message bundle="${loc}" key="local.catalog.Jeans" var="Jeans" />
<fmt:message bundle="${loc}" key="local.catalog.Sweatshirts"
	var="Sweatshirts" />
<fmt:message bundle="${loc}" key="local.catalog.Shorts" var="Shorts" />
<fmt:message bundle="${loc}" key="local.catalog.Outerwear"
	var="Outerwear" />
<fmt:message bundle="${loc}" key="local.catalog.Boots" var="name_Boots" />
<fmt:message bundle="${loc}" key="local.catalog.Sneakers"
	var="name_Sneakers" />
<fmt:message bundle="${loc}" key="local.catalog.Slippers"
	var="name_Slippers" />
<fmt:message bundle="${loc}" key="local.catalog.Shoes" var="name_Shoes" />
<fmt:message bundle="${loc}" key="local.catalog.Headgear" var="Headgear" />
<fmt:message bundle="${loc}" key="local.catalog.Umbrellas"
	var="Umbrellas" />
<fmt:message bundle="${loc}" key="local.catalog.Glasses" var="Glasses" />
<fmt:message bundle="${loc}" key="local.catalog.Gloves" var="Gloves" />
<fmt:message bundle="${loc}" key="local.button.addToBasket"
	var="button_addToBasket" />

</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/jsp/header.jsp" />
	</div>

	<div class=main>
		<div class="leftColumn">
			<div class="categoryList">
				<ul>
					<li><a
						href="<c:url value="/catalog?gender=${gender}&category=1"/>"
						class=category>Clothes</a>
						<div class="subcategoryList">
							<ul>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=1&subcategory=1"/>">
										<c:out value="Jeans" />
									</a>
								</li>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=1&subcategory=2"/>">
										<c:out value="Sweatshirts" />
									</a>
								</li>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=1&subcategory=3"/>">
										<c:out value="Shorts" />
									</a>
								</li>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=1&subcategory=4"/>">
										<c:out value="Outerwear" />
									</a>
								</li>
								
							</ul>
						</div></li>
					<li><a
						href="<c:url value="/catalog?gender=${gender}&category=2" />"
						class=category>Footwear</a>
						<div class="subcategoryList">
							<ul>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=2&subcategory=5"/>">
										<c:out value="Boots" />
									</a>
								</li>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=2&subcategory=6"/>">
										<c:out value="Sneakers" />
									</a>
								</li>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=2&subcategory=7"/>">
										<c:out value="Slippers" />
									</a>
								</li>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=2&subcategory=8"/>">
										<c:out value="Shoes" />
									</a>
								</li>
							</ul>
						</div></li>
					<li><a
						href="<c:url value="/catalog?gender=${gender}&category=3" />" 
						class=subcategory>Accessories</a>
						<div class="subcategoryList">
							<ul>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=3&subcategory=9"/>">
										<c:out value="Headger" />
									</a>
								</li>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=3&subcategory=10"/>">
										<c:out value="Umbrellas" />
									</a>
								</li>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=3&subcategory=11"/>">
										<c:out value="Glasses" />
									</a>
								</li>
								<li>
									<a href="<c:url value="/catalog?gender=${gender}&category=3&subcategory=12"/>">
										<c:out value="Gloves" />
									</a>
								</li>
							</ul>
						</div></li>
				</ul>
			</div>
		</div>

		<div class="listContainer">
			<c:forEach items="${productList}" var="product">
				<div class="oneGoodContainer">

					<a href="<c:url value="/goodsinfopage?goodsId=${product.id}" />">
						<img src="${pageContext.request.contextPath}/resources/img/${product.id}.jpg" />
					</a>
					<h3>

						<%-- /resources/img/${product.id}.jpg --%>

						<c:out value="${product.price}" />
						BYN
					</h3>
					<a href="<c:url value="/goodsinfopage?goodsId=${product.id}" />"> 
					<c:out value="${product.name}" />
					</a>
					
					<c:url var="basket" value="/putintobasket"/>
					
				</div>

			</c:forEach>
		</div>

	</div>


</body>
</html>