<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 18/4/17
  Time: 3:30 PM
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
</head>
<body  class="wellBody">
<div class="container col-md-12">
    <div class="row" >
        <c:import url="../template/sidebar.jsp"></c:import>

        <div class="col-md-9">
            <div class="jumbotron">
                <div class="container">
                    <div class="well">

                        <div class="container">
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
                                    <h4><spring:message code="categories"></spring:message> :</h4>
                                    <div class="table-responsive">


                                        <table id="mytable" class="table table-bordred table-striped">

                                            <thead>

                                            <th><spring:message code="category"></spring:message> </th>
                                            <th><spring:message code="created.on"></spring:message></th>
                                            <th><spring:message code="edit"></spring:message></th>

                                            </thead>
                                            <tbody>
                                            <c:forEach items="${categories}" var="categoty">
                                                <tr>
                                                    <td>${categoty.name}</td>
                                                    <td>${categoty.createdOn}</td>
                                                    <td><p data-placement="top" data-toggle="tooltip" title="Edit"><a class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" href="/category/edit/${categoty.uuid}" ><span class="glyphicon glyphicon-pencil"></span></a></p></td>
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
                                                            <li class="active"><a href="/category/index?currentPageNumber=${i}">${i}</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li><a href="/category/index?currentPageNumber=${i}">${i}</a></li>
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
</body>
<c:import url="../template/footer.jsp"></c:import>
</html>
