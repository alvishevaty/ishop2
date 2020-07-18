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
			<%@include file="/resources/css/style.css"%>
		</style>

<title>Basket</title>

</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/header.jsp" />
	</header>
	<main>
		<div class="fullContainer">
			<div class="emptyBasketContainer">
				<c:if test="${basketSize <= 0 || productsList==null}">
					<div class="emptyBasketMessage">
						<span>There are no goods in the basket</span>
					</div>
				</c:if>
			</div>
			<div class="basketContainer">
				<c:if test="${basketSize > 0}">
					<div class=goodsWindow>
						<div class=fullPrice>

							<c:set var="fullPrice" value="${0}" />
							<c:forEach items="${productsList}" var="product">
								<c:set var="fullPrice" value="${fullPrice + product.price}" />
							</c:forEach>
							<span>Full price: </span>
							<c:out value="${fullPrice}" />
							<span class=meas>BYN </span>
						</div>

						<c:forEach items="${productsList}" var="product">
							<div class=oneGoodMiniContainer>

								<a href="<c:url value="/goodsinfopage?goodsId=${product.id}" />">
									<img src="${pageContext.request.contextPath}/resources/img/${product.id}.jpg" />
								</a>
								<h4>
									<c:out value="${product.price}" />
									BYN
								</h4>
								
								<div class="deleteButton">
									
									<c:url var="deletefrombasket" value="/deletefrombasket"/>
								
									<form:form action="${deletefrombasket}" method="post">
										<input type="hidden" name="productId" value="${product.id}" /> 
										<input class=button type="submit" value="Remove from basket" />
									</form:form>
								</div>
							</div>
						</c:forEach>
					</div>

					<div class="orderFormcontainer">
					
						<c:url var="order" value="/order"/>
				
						<form:form action="${order}" method="get">

							<span class="formTitle">Delivery Address: </span><br />
							
							<div class=orderParameter> 
								<span class="label">Country: </span> 
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="Country" name="country" />
							</div>
							<br/> 
							<div class=orderParameter>
								<span
								class="label">City: </span>  
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="City" name="city" />
							</div>
							<br /> 
							<div class=orderParameter>
								<span class="label">Street: </span>  
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="Street" name="street" />
							</div>
							<br /> 
							<div class=orderParameter>
								<span class="label">House: </span> 
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="House" name="house" />
							</div>
							<br /> 
							<div class=orderParameter>
								<span class="label">Flat: </span> 
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="Flat" name="flat" />
							</div>
							<br /> 
							<div class=orderParameter>
								<span class="label">Post code: </span>
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="Post code" name="postCode" />
							</div>
							<br /> 
							
							<span class="formTitle">Delivery: </span><br />
							
							<div class=orderParameter> 
								<span class="deliveryType">Delivery type: </span> 
							</div>
							<div class=orderParameterValue>
						
								<input type="radio" id="pickup" name="deliveryType" value="Pickup" />
								<label for="pickup">Pickup</label><br>
								<input type="radio" id=courierDelivery name="deliveryType" value="CourierDelivery" />
								<label for="courierDelivery">Courier delivery</label><br>
							
							</div>
							<br/> 
							
							<div class=orderParameter>
								<span class="date">Delivery date: </span>
							</div>
							<div class=orderParameterValue>
								<input type=date id="date" name="deliveryDate" />
							</div>
							<br /> 
							
							<span class="formTitle">Payment: </span><br />
							
							<div class=orderParameter> 
								<span class="paymentType">Payment type: </span> 
							</div>
							<div class=orderParameterValue>
							
								<input type="radio" id="cash" name="paymentMethod" value="Cash"/>
								<label for="cash">Cash</label><br>
								<input type="radio" id="card" name="paymentMethod" value="Card"/>
								<label for="card">Card</label><br>
						
							</div>
							<br/> 
							
							<div class=orderParameter>
								<span class="amount">Amount: </span>
							</div>
							<div class=orderParameterValue>
								<input type="hidden" name="amount" value="${fullPrice}" />
								<span><c:out value="${fullPrice}"/> BYN</span>
							</div>
							<br/> 
							
							<input class="button orderSubmit" type="submit" value="Submit" />
							
						</form:form>

					</div>
				</c:if>
			</div>
		</div>
	</main>
</body>
</html>