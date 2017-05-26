<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 24/5/17
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <c:import url="../template/header.jsp"></c:import>
    <link href="<c:url value="../../../static/bootstrap/css/font-awesome.min.css" />" rel="stylesheet">
</head>
<body  class="wellBody">
<div class="container col-md-12">
    <div class="row" >
        <c:import url="../template/sidebar.jsp"></c:import>
        <sec:authorize access="hasAnyRole('ROLE_LIBRARY_ADMIN','ROLE_LIBRARIAN')" var="isLibraryAdminOrlibrarian"></sec:authorize>
        <div class="col-md-9">
            <div class="jumbotron">
                <div class="container">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title"><spring:message code="details"/></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class=" col-md-9 col-lg-9 ">
                                        <table class="table table-user-information">
                                            <tbody>
                                            <tr>
                                                <td><spring:message code="user.name"></spring:message> </td>
                                                <td><c:out value="${issueBook.user.username}"></c:out></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="email"></spring:message> </td>
                                                <td><c:out value="${issueBook.user.email}"></c:out></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="book"></spring:message> </td>
                                                <td><c:out value="${issueBook.book.name}"></c:out></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="isbn"></spring:message> </td>
                                                <td><c:out value="${issueBook.book.isbn}"></c:out></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="assign"></spring:message> </td>
                                                <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${issueBook.createdOn}" /></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="returned"></spring:message> </td>
                                                <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${issueBook.issue.returnDate}" /></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="status"></spring:message> </td>
                                                <td><c:out value="${issueBook.issueBookStatus}"></c:out></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">

                                <c:if test="${isLibraryAdminOrlibrarian == true}">
                                <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" href="/issuebook/index" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-backward"></i></a>
                                <span class="pull-right">
                                    <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" href="/issuebook/create" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-plus"></i></a>
                                </span>
                                </c:if>
                                <c:if test="${isLibraryAdminOrlibrarian == false}">
                                    <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" href="javascript:void goBack()" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-backward"></i></a>
                                </c:if>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function goBack() {
        window.history.back();
    }
</script>
</div>
</body>
<c:import url="../template/footer.jsp"></c:import>
</html>
