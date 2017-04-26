<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 25/4/17
  Time: 4:08 PM
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
        <c:import url="../template/sidebar.jsp"></c:import>

        <div class="col-md-9">
            <div class="jumbotron">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 col-md-offset-2">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Invite User </h3>
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
                                    <form:form  role="form" commandName="category" method="post" action="/invite/inviteUser">
                                        <fieldset>
                                            <div class="form-group">
                                                <form:input path="email" class="form-control" placeholder="email" type="email"/>
                                            </div>
                                            <c:if test="${admin ne null}">
                                                <div class="form-group">
                                                    <label class="radio-inline">
                                                        <form:radiobutton path="admin"  value="True"/>
                                                       Library Admin
                                                    </label>

                                                </div>
                                            </c:if>
                                             <c:if test="${librarian ne null}">
                                                 <div class="form-group">
                                                     <label class="radio-inline">
                                                         <form:radiobutton path="librarian"  value="True"/>
                                                         Librarian
                                                     </label>

                                                 </div>
                                            </c:if>
                                             <c:if test="${superAdmin ne null}">
                                                < <div class="form-group">
                                                 <label class="radio-inline">
                                                     <form:radiobutton path="superAdmin"  value="True"/>
                                                     Super Admin
                                                 </label>

                                             </div>
                                            </c:if>

                                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Send Invitation">
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
</html>

