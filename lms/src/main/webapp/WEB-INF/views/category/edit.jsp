<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 18/4/17
  Time: 10:31 PM
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
                    <div class="row">
                        <div class="col-md-6 col-md-offset-2">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><spring:message code="category.edit"></spring:message> </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <c:if test="${error ne null}">
                                            <div class="alert alert-danger">
                                                <strong><spring:message code="error"></spring:message> </strong> ${error}.
                                            </div>
                                        </c:if>
                                    </div>
                                    <spring:message code="update.edit" var="updateCategory"></spring:message>
                                    <spring:message code="name" var="name"></spring:message>
                                    <form:form  role="form" commandName="category" method="post" action="/category/update">
                                        <fieldset>
                                            <div class="form-group">
                                                <form:input path="name" class="form-control" placeholder="${name}" type="text"/>
                                                <form:errors path="name" cssClass="alert-danger"/>
                                            </div>
                                            <form:hidden path="uuid" />
                                            <input class="btn btn-lg btn-success btn-block" type="submit" value="${updateCategory}">

                                        </fieldset>

                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--/row-->
            </div><!--/container -->
        </div>
    </div>
</div>
</div>
</body>
<c:import url="../template/footer.jsp"></c:import>
</html>
