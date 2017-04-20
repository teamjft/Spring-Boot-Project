<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 11/4/17
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.s
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../static/bootstrap/css/bootstrap.css" />" rel="stylesheet">
    <style>
        .well{
            background-color: #4267b2;
        }
    </style>
<body class="well">
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect(""); %>
</sec:authorize>

<div class="container">
    <div class="row">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <c:if test="${param.error ne null}">
                        <div class="alert-danger">Invalid username and password.</div>
                    </c:if>
                    <c:if test="${param.logout ne null}">
                        <div class="alert-normal">You have been logged out.</div>
                    </c:if>
                </div>
                <div style="padding-top:30px" class="panel-body" >

                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                    <form action="/index" method="post">

                        <div>
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input class="form-control" type="text"  name="username"
                                       placeholder="User Name" />
                            </div>
                        </div>
                        <div>
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input class="form-control" type="password" name="password"
                                       placeholder="Password" />
                            </div>
                            <div>
                            </div>
                            <input type="submit" value="Sign In"  class="btn btn-success"  />
                        </div>
                        <div class="col-sm-12 controls">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
