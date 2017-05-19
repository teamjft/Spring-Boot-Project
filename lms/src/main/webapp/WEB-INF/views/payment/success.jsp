<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 18/5/17
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <c:import url="../template/header.jsp"></c:import>
    <link href="<c:url value="../../../static/bootstrap/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="../../../static/custom/css/paymentrecipt.css" />" rel="stylesheet">
</head>
<body  class="wellBody">
<div class="container col-md-12">
    <div class="row" >
        <c:import url="../template/sidebar.jsp"></c:import>

        <div class="col-md-9">
            <div class="pay-container">
                <div class="row">
                    <div class="receipt-main col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
                        <div>
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th><spring:message code="description"/> </th>
                                    <th><spring:message code="amount"/> </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td class="col-md-9">Payment for ${plan.name}</td>
                                    <c:set var = "amount" value = "${plan.price*paymentDetails.orderCart.quantity}" />
                                    <td class="col-md-3"><i class="fa fa-inr"></i> ${amount} - ${plan.currency}</td>
                                </tr>
                                <tr>
                                    <td class="text-right">
                                        <p>
                                            <strong>Total Amount:  ${amount}</strong>
                                        </p>
                                        <p>
                                            <strong>Balance Due: 0</strong>
                                        </p>
                                    </td>
                                    <td>
                                        <p>
                                            <strong><i class="fa fa-inr"></i> ${amount}</strong>
                                        </p>
                                        <p>
                                            <strong><i class="fa fa-inr"></i> 0</strong>
                                        </p>
                                    </td>
                                </tr>
                                <tr>

                                    <td class="text-right"><h2><strong>Total: </strong></h2></td>
                                    <td class="text-left text-danger"><h2><strong><i class="fa fa-inr"></i>  ${amount} - plan.currency</strong></h2></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
                            <div class="receipt-header receipt-header-mid receipt-footer">
                                <div class="col-xs-8 col-sm-8 col-md-8 text-left">
                                    <div class="receipt-right">
                                        <jsp:useBean id="now" class="java.util.Date" />
                                        <p><b><spring:message code="purchase.date"/> :</b>   ${now}</p>
                                        <h5 style="color: rgb(140, 140, 140);"><spring:message code="thanks.for.purchase"></spring:message> </h5>
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
