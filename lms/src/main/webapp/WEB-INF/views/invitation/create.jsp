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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <c:import url="../template/header.jsp"></c:import>
    <link href="<c:url value="../../../static/bootstrap/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<c:url value="../../../static/bootstrap/css/bootstrap-datepicker.css" />" rel="stylesheet">
    <script src="../../../static/bootstrap/js/bootstrap-datepicker.min.js"></script>

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
                                    <spring:message code="send.invitation" var="sendInvitation"></spring:message>
                                    <h3 class="panel-title"><spring:message code="invite.user"></spring:message> </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <c:if test="${success ne null}">
                                            <div class="alert alert-success">
                                            ${success}
                                            </div>
                                        </c:if>
                                        <c:if test="${error ne null}">
                                            <div class="alert alert-danger">
                                                <strong><spring:message code="error"></spring:message> </strong> ${error}.
                                            </div>
                                        </c:if>
                                    </div>
                                    <form:form  role="form" commandName="invitation" method="post" action="/invite/inviteUser">
                                        <fieldset>
                                            <div class="form-group">
                                                <form:input path="email" class="form-control" placeholder="email" type="email"/>
                                                <form:errors path="email" cssClass="alert-danger"/>
                                            </div>
                                            <div>
                                                <div class="input-group date" data-provide="datepicker">
                                                    <input type="text" class="form-control" placeholder="Expired date">
                                                    <div class="input-group-addon">
                                                        <span class="glyphicon glyphicon-th"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <br>
                                            <c:if test="${admin ne null}">
                                                <div class="form-group">
                                                    <label class="radio-inline">
                                                        <form:radiobutton path="admin"  value="True"/>
                                                        <spring:message code="librarian.admin"></spring:message>
                                                    </label>

                                                </div>
                                            </c:if>
                                             <c:if test="${librarian ne null}">
                                                 <div class="form-group">
                                                     <label class="radio-inline">
                                                         <form:radiobutton path="librarian"  value="True"/>
                                                         <spring:message code="librarian"></spring:message>
                                                     </label>

                                                 </div>
                                            </c:if>
                                             <c:if test="${superAdmin ne null}">
                                                < <div class="form-group">
                                                 <label class="radio-inline">
                                                     <form:radiobutton path="superAdmin"  value="True"/>
                                                     <spring:message code="super.admin"></spring:message>
                                                 </label>

                                             </div>
                                            </c:if>

                                            <input class="btn btn-lg btn-success btn-block" type="submit" value="${sendInvitation}">
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

