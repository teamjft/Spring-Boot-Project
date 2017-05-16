<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 12/5/17
  Time: 12:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <c:import url="../template/header.jsp"></c:import>
    <link href="<c:url value="../../../static/bootstrap/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="../../../static/custom/css/payment.css" />" rel="stylesheet">
    <style>
        .animationload {
            background-color: #fff;
            height: 100%;
            left: 0;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 10000;
        }
        .osahanloading {
            animation: 1.5s linear 0s normal none infinite running osahanloading;
            background: #fed37f none repeat scroll 0 0;
            border-radius: 50px;
            height: 50px;
            left: 50%;
            margin-left: -25px;
            margin-top: -25px;
            position: absolute;
            top: 50%;
            width: 50px;
        }
        .osahanloading::after {
            animation: 1.5s linear 0s normal none infinite running osahanloading_after;
            border-color: #85d6de transparent;
            border-radius: 80px;
            border-style: solid;
            border-width: 10px;
            content: "";
            height: 80px;
            left: -15px;
            position: absolute;
            top: -15px;
            width: 80px;
        }
        @keyframes osahanloading {
            0% {
                transform: rotate(0deg);
            }
            50% {
                background: #85d6de none repeat scroll 0 0;
                transform: rotate(180deg);
            }
            100% {
                transform: rotate(360deg);
            }
        }

    </style>
</head>
<body>
<div class="container col-md-12">
    <div class="row" >
        <div class="animationload">
            <div class="osahanloading"></div>
        </div>
        <c:import url="../template/sidebar.jsp"></c:import>
        <div class="col-md-9 jumbotron">
            <div class="row">
                <div class="cart-body">

                    <div class="col-md-6">
                        <!--CREDIT CART PAYMENT-->
                        <div>
                            <div class="form-group">
                                <c:if test="${error ne null}">
                                    <div class="alert alert-danger">
                                        <strong><spring:message code="error"></spring:message> </strong> ${error}.
                                    </div>
                                </c:if>
                                <c:if test="${errorDetails != null}">
                                    <div  class="well">
                                        <c:forEach items="${errorDetails}" var="error">
                                            <div class="alert alert-danger">
                                                <c:choose>
                                                    <c:when test="${fn:contains(error, '[0].')}">
                                                        <strong>${fn:substringAfter(error.field,  '[0].')}</strong> ${error.issue}.
                                                    </c:when>
                                                    <c:otherwise>
                                                        <strong>${error.field} </strong> ${error.issue}.
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="panel panel-info">

                            <div class="panel-heading"><span><i class="glyphicon glyphicon-lock"></i></span> <spring:message code="secure.paymet"/> </div>s
                            <div class="panel-body">
                                <form:form class="form-horizontal" commandName="paymentDetails" method="post" action="/payment/pay">
                                <div class="form-group">
                                    <div class="col-md-12"><strong><spring:message code="type.card"/> </strong></div>
                                    <div class="col-md-12">
                                        <form:select path="creditCardType" class="form-control" >
                                            <form:options items="${payment.creditCardType}" />
                                        </form:select>
                                    </div>
                                </div>
                                    <div class="form-group">
                                        <div class="col-md-12"><strong><spring:message code="credit.card.number"></spring:message></strong></div>

                                        <div class="col-md-12">
                                            <form:input path="number" class="form-control" type="text"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-12"><strong><spring:message code="card.cvv"/> </strong></div>
                                        <div class="col-md-12">
                                            <form:input path="cvv2" class="form-control"  type="NUMBER"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-12">
                                            <strong><spring:message code="expired.date"/> </strong>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                            <form:select path="expirationMonth" class="form-control" >
                                                <option value="01">01</option>
                                                <option value="02">02</option>
                                                <option value="03">03</option>
                                                <option value="04">04</option>
                                                <option value="05">05</option>
                                                <option value="06">06</option>
                                                <option value="07">07</option>
                                                <option value="08">08</option>
                                                <option value="09">09</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12">12</option>
                                            </form:select>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                            <form:select path="expirationYear" class="form-control" >
                                                <option value="2015">2015</option>
                                                <option value="2016">2016</option>
                                                <option value="2017">2017</option>
                                                <option value="2018">2018</option>
                                                <option value="2019">2019</option>
                                                <option value="2020">2020</option>
                                                <option value="2021">2021</option>
                                                <option value="2022">2022</option>
                                                <option value="2023">2023</option>
                                                <option value="2024">2024</option>
                                                <option value="2025">2025</option>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-12">
                                            <span><spring:message code="pay.secured"/> </span>
                                        </div>
                                        <div class="col-md-12">
                                            <ul class="cards">
                                                <li class="visa hand"><spring:message code="visa"/> </li>
                                                <li class="mastercard hand"><spring:message code="mastercard" /></li>
                                                <li class="amex hand"><spring:message code="amex"/></li>
                                            </ul>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                    <form:input type="hidden" path="orderCart.quantity"/>
                                    <form:input type="hidden" path="uuid"/>
                                    <form:input type="hidden" path="membershipPlanId"/>


                                    <div class="form-group">
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <button type="submit" class="btn btn-primary btn-submit-fix"><spring:message code="place.order"/> </button>
                                        </div>
                                    </div>
                            </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</body>
<c:import url="../template/footer.jsp"></c:import>
</html>
