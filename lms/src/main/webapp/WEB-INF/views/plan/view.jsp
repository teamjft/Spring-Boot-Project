<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 4/5/17
  Time: 11:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <div class="container">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title"><c:out value="${plan.name}"></c:out></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 ">
                                        <table class="table table-user-information">
                                            <tbody>
                                            <tr>
                                                <td><spring:message code="created.on"></spring:message> </td>
                                                <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${plan.createdOn}" /></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="description"></spring:message> </td>
                                                <td><c:out value="${plan.description}"></c:out></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="period.type"></spring:message> </td>
                                                <td><c:out value="${plan.periodType}"></c:out></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="unit"></spring:message> </td>
                                                <td><c:out value="${plan.unit}"></c:out></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="price"></spring:message> </td>
                                                <td>${plan.price} / ${plan.currency}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" href="/plan/index" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-backward"></i></a>
                                <span class="pull-right">
                                    <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" href="/plan/create" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-plus"></i></a>
                                    <%--     <a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                            <a data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>--%>
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
</body>
<c:import url="../template/footer.jsp"></c:import>
</html>
