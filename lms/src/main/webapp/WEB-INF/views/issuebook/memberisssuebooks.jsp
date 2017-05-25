<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 24/5/17
  Time: 2:12 PM
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
                                <div class="form-group">
                                    <c:if test="${error ne null}">
                                        <div class="alert alert-danger">
                                            <strong><spring:message code="error"/></strong> ${error}.
                                        </div>
                                    </c:if>
                                    <c:if test="${success ne null}">
                                        <div class="alert alert-danger">
                                            <strong><spring:message code="success.message"/></strong> ${success}.
                                        </div>
                                    </c:if>
                                </div>
                                <spring:message code="user.name" var="name"></spring:message>
                                <spring:message code="submit" var="submit"></spring:message>
                                <div class="panel-body">
                                    <form action="/issuebook/validateUser">
                                        <fieldset>
                                            <div class="form-group">
                                                <input id="username" name="username" class="form-control" placeholder="${name}" type="text"/>
                                            </div>
                                            <input id="submit" class="btn btn-lg btn-success btn-block" type="submit" value="${submit}">
                                        </fieldset>
                                    </form>
                                </div>
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
