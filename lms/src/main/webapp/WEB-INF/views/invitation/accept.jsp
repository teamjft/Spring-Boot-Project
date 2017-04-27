<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 26/4/17
  Time: 4:25 PM
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

        <div class="col-md-8 col-md-offset-2">
            <div class="jumbotron">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 col-md-offset-2">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><spring:message code="recover.password"></spring:message> </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <c:if test="${error ne null}">
                                            <div class="alert alert-danger">
                                                <strong>Error!</strong> ${error}.
                                            </div>
                                        </c:if>
                                        <c:if test="${errors != null}">
                                            <div  class="well">
                                                <c:forEach items="${errors}" var="error">
                                                    <div class="alert alert-danger">
                                                        <strong><spring:message code="error"></spring:message> </strong> ${error.getDefaultMessage()}.
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>
                                    <c:choose>
                                        <c:when test="${success eq null}">
                                            <spring:message code="required.user.name" var="requiredUserName"/>
                                            <spring:message code="first.name" var="firstName"/>
                                            <spring:message code="last.name" var="lastName"/>
                                            <spring:message code="new.password" var="newPassword"/>
                                            <spring:message code="confirm.password" var="confirmPassword"></spring:message>
                                            <spring:message code="create.account" var="createAccount"></spring:message>
                                            <form:form  role="form" commandName="user" method="post" action="/invitation/accept">
                                                <fieldset>
                                                    <div class="form-group">
                                                        <form:input path="username" class="form-control" placeholder="${requiredUserName}"/>
                                                        <form:input path="firstName" class="form-control" placeholder="${firstName}"/>
                                                        <form:input path="lastName" class="form-control" placeholder="${lastName}"/>
                                                        <form:input path="passwordConfirmationBean.password" class="form-control" placeholder="${newPassword}" type="password"/>
                                                        <form:input path="passwordConfirmationBean.confirmPassword" class="form-control" placeholder="${confirmPassword}" type="password"/>
                                                        <form:hidden path="passwordConfirmationBean.token" />
                                                    </div>
                                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="${createAccount}">
                                                </fieldset>

                                            </form:form>
                                        </c:when>
                                        <c:when test="${success ne null}">
                                            <p class="alert alert-success">${success}</p>
                                            <button class="btn btn-block"><a href="/"><spring:message code="take.me.home"></spring:message></a></button>
                                        </c:when>
                                    </c:choose>


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
</html>

