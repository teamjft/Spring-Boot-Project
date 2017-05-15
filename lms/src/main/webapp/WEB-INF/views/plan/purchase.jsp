<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 15/5/17
  Time: 12:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  uri="/WEB-INF/tld/wrap.tld" prefix="w" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <c:import url="../template/header.jsp"></c:import>
    <link href="<c:url value="../../../static/custom/css/plan.css" />" rel="stylesheet">
</head>
<body  class="wellBody">
<div class="container col-md-12">
    <div class="row" >
        <c:import url="../template/sidebar.jsp"></c:import>

        <div class="col-md-9 jumbotron">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12 col-md-10 col-md-offset-1">
                        <form:form commandName="cart" method="post" action="/payment/create">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th><spring:message code="product"/> </th>
                                <th><spring:message code="quantity"></spring:message> </th>
                                <th class="text-center"><spring:message code="price"/> </th>
                                <th class="text-center"><spring:message code="total"/> </th>
                                <th> </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td class="col-sm-8 col-md-6">
                                    <div class="media">
                                        <a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"> </a>
                                        <div class="media-body">
                                            <h4 class="media-heading"><a href="#">${plan.name}</a></h4>
                                            <h5 class="media-heading"> per <a href="#">${plan.unit} ${plan.periodType}</a></h5>
                                            <span><spring:message code="status"/> </span><span class="text-success"><strong><spring:message code="in.stoke"/> </strong></span>
                                        </div>
                                    </div></td>
                                <td class="col-sm-1 col-md-1" style="text-align: center">
                                    <form:input type="number" path="quantity" class="form-control" id="quantity" value="1"/>
                                    <form:input type="hidden" path="planUuid" class="form-control"  value="${plan.uuid}"/>
                                </td>
                                <td class="col-sm-1 col-md-1 text-center"><strong id="currentPrice">${plan.price}</strong></td>
                                <td class="col-sm-1 col-md-1 text-center"><strong class="totalPrice">${plan.price}</strong></td>
                                <td class="col-sm-1 col-md-1">
                                    <button type="button" class="btn btn-danger">
                                        <span class="glyphicon glyphicon-remove"></span><a href="/user/userplan"><spring:message code="remove"/> </a>
                                    </button></td>
                            </tr>
                            <tr>
                                <td>   </td>
                                <td>   </td>
                                <td>   </td>
                                <td><h3><spring:message code="total"/>s </h3></td>
                                <td class="text-right"><h3><strong class="totalPrice">${plan.price}</strong></h3></td>
                            </tr>
                            <tr>
                                <td>   </td>
                                <td>   </td>
                                <td>   </td>
                                <td>   </td>
                                <td>
                                    <button type="submit" class="btn btn-success">
                                        <spring:message code="checkout"/> <span class="glyphicon glyphicon-play"></span>
                                    </button></td>
                            </tr>
                            </tbody>
                        </table>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</body>
<script type="text/javascript">
    var mainPrice = ${plan.price}
    $("input").change(function() {
        if ($("input").val() <= 0) {
            $("input").val(1)
        }
        var unit =  $("input").val();
        $(".totalPrice").text(unit*mainPrice);
    });

</script>

<c:import url="../template/footer.jsp"></c:import>
</html>
