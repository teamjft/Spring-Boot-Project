<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 24/4/17
  Time: 11:05 PM
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

        <<div class="col-md-9">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="well well-sm">
                        <form:form class="form-horizontal" commandName="emailTemplate" action="/configuration/updateTemplate" method="post">
                            <fieldset>
                                <legend class="text-center"><spring:message code="edit.template.type"></spring:message> ${emailTemplate.notificationType}</legend>
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><spring:message code="available.parameters"></spring:message> :</h3>
                                    </div>
                                    <div class="panel-body">
                                        <c:forEach items="${emailTemplate.availableField}" var="field">
                                            <p><small>${field}</small></p>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <c:if test="${error ne null}">
                                        <div class="alert alert-danger">
                                            <strong><spring:message code="error"></spring:message> </strong> ${error}.
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
                                <!-- Subject input-->
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="email"><spring:message code="subject"></spring:message> </label>
                                    <div class="col-md-9">
                                        <form:input id="email" path="subject"  type="text" class="form-control"/>
                                    </div>
                                </div>
                                <form:hidden path="uuid"></form:hidden>
                                <!-- Content body -->
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="message"><spring:message code="content"></spring:message> </label>
                                    <div class="col-md-9">
                                        <form:textarea class="form-control" id="message"  path="content" rows="5"/>
                                    </div>
                                </div>

                                <!-- Form actions -->
                                <div class="form-group">
                                    <div class="col-md-12 text-right">
                                        <button type="submit" class="btn btn-primary btn-lg"><spring:message code="submit"></spring:message> </button>
                                    </div>
                                </div>
                            </fieldset>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
</div>
</body>
<c:import url="../template/footer.jsp"></c:import>
</html>
