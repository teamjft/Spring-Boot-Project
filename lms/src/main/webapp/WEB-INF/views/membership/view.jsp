<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 25/4/17
  Time: 6:29 PM
  To change this template use File | Settings | File Templates.
--%>
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
                                <h3 class="panel-title">${membership.user.firstName} ${membership.user.lastName}</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 ">
                                        <table class="table table-user-information">
                                            <tbody>
                                            <tr>
                                                <td><spring:message code="created.on"></spring:message> </td>
                                                <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${membership.createdOn}" /></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="user.name"></spring:message> </td>
                                                <td>${membership.user.username}</td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="email"></spring:message> </td>
                                                <td><a href="mailto:${membership.user.email}">${membership.user.email}</a></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="membership.status"></spring:message> </td>
                                                <td>${membership.membershipStatus}</td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="expired.date"></spring:message> </td>
                                                <c:if test="${membership.expiredDate ne null}">
                                                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${membership.expiredDate}" /></td>
                                                </c:if>
                                                <c:if test="${membership.expiredDate eq null}">
                                                    <td><spring:message code="no.available"></spring:message></td>
                                                </c:if>
                                            </tr>

                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" href="mailto:${membership.user.email}" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                                <span class="pull-right">
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
