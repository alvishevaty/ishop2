<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<style>
	<%@ include file="/resources/css/style.css"%>
</style>

<title><c:out value="${product.name}" /></title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="local.goods.price" var="price" />
<fmt:message bundle="${loc}" key="local.goods.description"
	var="description" />
<fmt:message bundle="${loc}" key="local.goods.brand" var="brand" />
<fmt:message bundle="${loc}" key="local.goods.size" var="size" />
<fmt:message bundle="${loc}" key="local.goods.season" var="season" />
<fmt:message bundle="${loc}" key="local.goods.itemNumber"
	var="itemNumber" />
<fmt:message bundle="${loc}" key="local.button.addToBasket"
	var="button_addToBasket" />
<fmt:message bundle="${loc}" key="local.catalog.Clothes" var="clothes" />
<fmt:message bundle="${loc}" key="local.catalog.Footwear" var="footwear" />
<fmt:message bundle="${loc}" key="local.catalog.Accessories"
	var="accessories" />

</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/header.jsp" />
	</header>

	<main>

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
		<div class="mainPage">

			<div class="goodsContainer">

				<div class="goodsImg gCchild">
					<img src="${pageContext.request.contextPath}/resources/img/${product.id}.jpg" />
				</div>

				<div class="goodsInfoColumn gCchild">
					<div class="goodsHeader">
						<h2>
							<c:out value="${requestScope.product.name}" />
						</h2>
					</div>
					<div class=goodsInfo>

						<div class=parameter>
							<span>Price:</span>
						</div>
						<div class=parameterValue>
							<span>${requestScope.product.price} BYN</span>
						</div>
						<br>
						<div class=parameter>
							<span>Description:</span>
						</div>
						<div class=parameterValue>
							<span>${requestScope.product.description}</span>
						</div>
						<!-- <br> -->
						<div class=parameter>
							<span>Brand:</span>
						</div>
						<div class=parameterValue>
							<span>${requestScope.product.manufacturer.name}</span>
						</div>
						<br>
						<div class=parameter>
							<span>Size:</span>
						</div>
						<div class=parameterValue>
							<span>${requestScope.product.size}</span>
						</div>
						<br>
						<div class=parameter>
							<span>Season:</span>
						</div>
						<div class=parameterValue>
							<span>${requestScope.product.season}</span>
						</div>
						<br>
						<div class=parameter>
							<span>Item number:</span>
						</div>
						<div class=parameterValue>
							<span>${requestScope.product.vendorCode}</span>
						</div>
						<br>
						<div class="basketbutton">
							
							<c:url var="basket" value="/putintobasket"/>
						
							<form:form action="${basket}" method="post">
								<input type="hidden" name="productId" value="${requestScope.product.id}" /> 
								<input class=button type="submit" value="Add to basket" />
							</form:form>
							
						</div>

					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>