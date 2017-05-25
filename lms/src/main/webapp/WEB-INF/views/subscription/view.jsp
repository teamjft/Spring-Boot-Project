<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 24/5/17
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <c:import url="../template/header.jsp"></c:import>
    <link href="<c:url value="../../../static/bootstrap/css/font-awesome.min.css" />" rel="stylesheet">
</head>
<body  class="wellBody">
<div class="container col-md-12">
    <div class="row" >
        <c:import url="../template/sidebar.jsp"></c:import>

        <div class="col-md-9">
            <div class="jumbotron">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="text-center"><strong><spring:message code="invoice.details"/> </strong></h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <c:forEach items="${membershipSubscription.invoices}" var="invoice">
                                        <table class="table table-condensed">
                                            <thead>
                                            <tr>
                                                <td><strong><spring:message code="created.on"/> </strong></td>
                                                <td><strong><spring:message code="start.date"/> </strong></td>
                                                <td><strong><spring:message code="end.date"/> </strong></td>
                                                <td class="text-center"><strong><spring:message code="payed.on"/> </strong></td>
                                                <td class="text-center"><strong><spring:message code="transactionId"/> </strong></td>
                                                <td class="text-center"><strong><spring:message code="paymentStatus"/> </strong></td>
                                                <td class="text-center"><strong><spring:message code="paymentMethod"/> </strong></td>
                                                <td class="text-right"><strong><spring:message code="payAmount"/></strong></td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${invoice.payments}" var="payment">
                                                <tr>
                                                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${payment.createdOn}"/></td>
                                                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${invoice.startDate}"/></td>
                                                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${invoice.endDate}"/></td>
                                                    <td class="text-center"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${payment.paymentDate}"/></td>
                                                    <td class="text-center">${payment.transactionId}</td>
                                                    <td class="text-center">${payment.paymentStatus}</td>
                                                    <td class="text-center">${payment.paymentMethod}</td>
                                                    <td class="text-center">${payment.payAmount} / ${payment.currency}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:forEach>
                                </div>
                                <div class="panel-footer">
                                    <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" href="/subscription/index" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-backward"></i></a>
                                    <span class="pull-right">
                                </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</body>
<c:import url="../template/footer.jsp"></c:import>
</html>
