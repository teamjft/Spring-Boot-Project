<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 17/4/17
  Time: 1:16 PM
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
                    <div class="well">
                        <div class="media">
                            <a class="pull-left" href="#">
                                    <c:choose>
                                        <c:when test="${book.imageUrl ne null}">
                                            <img src="<c:url value="${book.imageUrl}"/>" class="img-responsive" alt="Cinque Terre" width="150" height="150">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="<c:url value="../../../static/custom/img/10546i3DAC5A5993C8BC8C.jpg"/>" class="img-responsive" alt="Cinque Terre" width="150" height="150">
                                        </c:otherwise>
                                    </c:choose>
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">${book.name}</h4>
                                <p class="text-right">By ${book.authorName}</p>
                                <p>${book.description}</p>
                                <ul class="list-inline list-unstyled">
                                    <li><span><i class="glyphicon glyphicon-tags"></i> <spring:message code="edition"></spring:message>  : ${book.edition} </span></li>
                                    <li>|</li>
                                    <span><i class="glyphicon glyphicon-barcode"></i> <spring:message code="publication.year"></spring:message>  : ${book.publicationYear}</span>
                                    <li>|</li>
                                    <span><i class="glyphicon  glyphicon-tasks"></i> <spring:message code="publisher"></spring:message>  : ${book.publisher}</span>
                                    <li>|</li>
                                    <span><i class="glyphicon  glyphicon-tasks"></i> <spring:message code="number.of.copies.available"></spring:message> : ${book.numberOfAvailableCopies}</span>
                                    <li>|</li>
                                    <span><i class="glyphicon glyphicon-bitcoin"></i> <spring:message code="price"></spring:message>  : ${book.price}</span>
                                    <li>|</li>
                                    <span><i class="glyphicon  glyphicon-tasks"></i> <spring:message code="number.of.copies.available"></spring:message> : ${book.totalNumberOfCopies}</span>
                                    <li>|</li>
                                    <c:forEach items="${categories}" var="categoty">
                                    <span>
                                        <button class="btn-sm btn-success">${categoty.name}</button></span>
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
