<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 20/4/17
  Time: 4:19 PM
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
                                <spring:message code="submit" var="submit"></spring:message>
                                <form:form action="/configuration/save" method="POST">
                                    <div align="center">
                                        <table>
                                            <tr>
                                                <td><spring:message code="user.name"></spring:message>: </td>
                                                <td><input type="text" name="userName" /></td>
                                            </tr>
                                            <tr>
                                                <td><spring:message code="new.password"></spring:message> </td>
                                                <td><input type="password" name="password" /></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td><input type="submit" value="${submit}" /></td>
                                            </tr>
                                        </table>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</body>
<c:import url="../template/footer.jsp"></c:import>
</html>
