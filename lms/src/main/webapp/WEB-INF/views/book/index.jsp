<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="../template/header.jsp"></c:import>
    <link href="<c:url value="../../../static/bootstrap/css/font-awesome.min.css" />" rel="stylesheet">
<%--    <c:import url="../../../static/custom/css/book.css"></c:import>--%>
</head>
<body  class="wellBody" ng-app="appController">
<div class="container col-md-12">
    <div class="row" >
        <c:import url="../template/sidebar.jsp"></c:import>

        <div class="col-md-9">
            <%--<div class="jumbotron">--%>
                    <div class="row">
                        <div class="container">
                            <div class="jumbotron">
                            <div class="well">

                                <div class="container">
                                    <div class="row">
                                        <div class="form-group">
                                            <c:if test="${error ne null}">
                                                <div class="alert alert-danger">
                                                    <strong>Error!</strong> ${error}.
                                                </div>
                                            </c:if>
                                            <c:if test="${success ne null}">
                                                <div class="alert alert-danger">
                                                    <strong>Success Message!</strong> ${success}.
                                                </div>
                                            </c:if>

                                        </div>

                                        <div class="col-md-12">
                                            <h4>Books:</h4>
                                            <div class="table-responsive">


                                                <table id="mytable" class="table table-bordred table-striped">

                                                    <thead>

                                                    <th>Name</th>
                                                    <th>Isbn</th>
                                                    <th>Created On</th>
                                                    <th>Total Number Of copies</th>
                                                    <th>Edit</th>
                                                    <th>View</th>

                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${books}" var="categoty">
                                                        <tr>
                                                            <td>${categoty.name}</td>
                                                            <td>${categoty.isbn}</td>
                                                            <td>${categoty.createdOn}</td>
                                                            <td>${categoty.totalNumberOfCopies}</td>
                                                            <td><p data-placement="top" data-toggle="tooltip" title="Edit"><a class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" href="/book/edit/${categoty.uuid}" ><span class="glyphicon glyphicon-pencil"></span></a></p></td>
                                                            <td><p data-placement="top" data-toggle="tooltip" title="View"><a class="btn btn-primary btn-xs" data-title="View" data-toggle="modal" href="/book/view/${categoty.uuid}" ><span class="glyphicon glyphicon-eye-open"></span></a></p></td>
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
                                                                    <li class="active"><a href="/book/index?currentPageNumber=${i}">${i}</a></li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <li><a href="/book/index?currentPageNumber=${i}">${i}</a></li>
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
                <%--</div>--%><!--/container -->
        </div>
    </div>
</div>
</div>
</body>
</html>
