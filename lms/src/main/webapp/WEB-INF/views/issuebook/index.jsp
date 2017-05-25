<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 24/5/17
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <c:import url="../template/header.jsp"></c:import>
    <link href="<c:url value="../../../static/bootstrap/css/font-awesome.min.css" />" rel="stylesheet">
</head>
<body>
<div class="container col-md-12">
    <div class="row" >
        <c:import url="../template/sidebar.jsp"></c:import>

        <div class="col-md-9">
            <div class="jumbotron">
                <div class="container">
                    <div class="well">

                        <div class="container">
                            <h3><spring:message code="categories"></spring:message> <a class="btn icon-btn btn-primary pull-right" href="/issuebook/create">
                                <span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span> <spring:message code="issue.book"></spring:message></a></h3></div>
                            <div class="row">
                                <div class="form-group">
                                    <c:if test="${error ne null}">
                                        <div class="alert alert-danger">
                                            <strong><spring:message code="error"></spring:message> </strong> ${error}.
                                        </div>
                                    </c:if>
                                    <c:if test="${success ne null}">
                                        <div class="alert alert-danger">
                                            <strong><spring:message code="success.message"></spring:message> </strong> ${success}.
                                        </div>
                                    </c:if>

                                </div>

                                <div class="col-md-12">
                                    <div class="table-responsive">


                                        <table id="mytable" class="table table-bordred table-striped">

                                            <thead>

                                            <th><spring:message code="user.name"></spring:message> </th>
                                            <th><spring:message code="issue.date"></spring:message></th>
                                            <th><spring:message code="returned.date"></spring:message></th>
                                            <th><spring:message code="view"></spring:message></th>

                                            </thead>
                                            <tbody>
                                            <c:forEach items="${issues}" var="issueBook">
                                                <tr>
                                                    <td><c:out value="${issueBook.user.username}"></c:out></td>
                                                    <td><fmt:formatDate type="date" dateStyle="short" timeStyle="short" value = "${issueBook.createdOn}"/></td></td>
                                                    <td>
                                                        <fmt:formatDate type="date" dateStyle="short" timeStyle="short" value = "${issueBook.issue.returnDate}"/></td>
                                                    <td><p data-placement="top" data-toggle="tooltip" title="View"><a class="btn btn-primary btn-xs" data-title="View" data-toggle="modal" href="/issuebook/view/${issueBook.uuid}" ><span class="glyphicon glyphicon-eye-open"></span></a></p></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <div>
                                            <div class="clearfix"></div>
                                            <ul class="pagination pull-right">
                                                <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                                                    <c:choose>
                                                        <c:when test="${currentIndex == i}">
                                                            <li class="active"><a href="/issuebook/index?currentPageNumber=${i}">${i}</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li><a href="/issuebook/index?currentPageNumber=${i}">${i}</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </ul>
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
</html>
