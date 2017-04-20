<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                                            <img src="http://res.cloudinary.com/jellyfish-technologies/image/upload/${book.imageUrl}" class="img-responsive" alt="Cinque Terre" width="150" height="150">
                                        </c:when>
                                        <c:otherwise>
                                            /opt/img/${book.imageUrl}
                                            <img src="<c:url value="../../../static/custom/img/10546i3DAC5A5993C8BC8C.jpg"/>" class="img-responsive" alt="Cinque Terre" width="150" height="150">
                                        </c:otherwise>
                                    </c:choose>
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">${book.name}</h4>
                                <p class="text-right">By ${book.authorName}</p>
                                <p>${book.description}</p>
                                <ul class="list-inline list-unstyled">
                                    <li><span><i class="glyphicon glyphicon-tags"></i> Edition : ${book.edition} </span></li>
                                    <li>|</li>
                                    <span><i class="glyphicon glyphicon-barcode"></i> Publication Year : ${book.publicationYear}</span>
                                    <li>|</li>
                                    <span><i class="glyphicon  glyphicon-tasks"></i> Publisher : ${book.publisher}</span>
                                    <li>|</li>
                                    <span><i class="glyphicon  glyphicon-tasks"></i> Number Of Available Copies : ${book.numberOfAvailableCopies}</span>
                                    <li>|</li>
                                    <span><i class="glyphicon glyphicon-bitcoin"></i> price : ${book.price}</span>
                                    <li>|</li>
                                    <span><i class="glyphicon  glyphicon-tasks"></i> Number Of Available Copies : ${book.numberOfAvailableCopies}</span>
                                    <li>|</li>
                                    <span><i class="glyphicon  glyphicon-tasks"></i> Total Number Of Copies : ${book.totalNumberOfCopies}</span>
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
</html>
