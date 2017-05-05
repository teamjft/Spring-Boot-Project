<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                                <div class="col-md-12">
                                    <h3>
                                        <a class="btn icon-btn btn-primary pull-right" href="/book/create"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span> <spring:message code="add.book"></spring:message></a>
                                    </h3>
                                </div>
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
                                            <h4><spring:message code="books"></spring:message>:</h4>
                                            <div class="table-responsive">


                                                <table id="mytable" class="table table-bordred table-striped">

                                                    <thead>

                                                    <th><spring:message code="name"></spring:message> </th>
                                                    <th><spring:message code="isbn"></spring:message></th>
                                                    <th><spring:message code="created.on"></spring:message> </th>
                                                    <th><spring:message code="total.number.of.copies"></spring:message></th>
                                                    <th><spring:message code="edit"></spring:message> </th>
                                                    <th><spring:message code="view"></spring:message> </th>

                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${books}" var="categoty">
                                                        <tr>
                                                            <td><c:out value="${categoty.name}"></c:out></td>
                                                            <td><c:out value="${categoty.isbn}"></c:out></td>
                                                            <td><c:out value="${categoty.createdOn}"></c:out></td>
                                                            <td><c:out value="${categoty.totalNumberOfCopies}"></c:out></td>
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
<c:import url="../template/footer.jsp"></c:import>
</html>
