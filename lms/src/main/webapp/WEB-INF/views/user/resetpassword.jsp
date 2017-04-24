<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 24/4/17
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                    <h3 class="panel-title">Reset Password</h3>
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
                                                        <strong>Error!</strong> ${error.getDefaultMessage()}.
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>
                                    <c:choose>
                                        <c:when test="${success eq null}">
                                            <form:form  role="form" commandName="passwordConfirmation" method="post" action="/user/resetpassword">
                                                <fieldset>
                                                    <div class="form-group">
                                                        <form:input path="password" class="form-control" placeholder="New Password" type="password"/>
                                                        <form:input path="confirmPassword" class="form-control" placeholder="Confirm Password" type="password"/>
                                                        <form:hidden path="token" />
                                                    </div>
                                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Update password">
                                                </fieldset>

                                            </form:form>
                                        </c:when>
                                        <c:when test="${success ne null}">
                                            <p class="alert alert-success">${success}</p>
                                            <button class="btn btn-block"><a href="/">Home</a></button>
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

