<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 4/5/17
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <c:import url="../template/header.jsp"></c:import>
    <link href="<c:url value="../../../static/custom/css/plan.css" />" rel="stylesheet">
</head>
<body  class="wellBody">
<div class="container col-md-12">
<div class="row" >
    <c:import url="../template/sidebar.jsp"></c:import>

    <div class="col-md-9">
        <div class="jumbotron">
            <div class="container">
                <div class="row">
                    <c:if test="${success ne null}">
                        <h3 class="panel-info">${success} </h3>
                    </c:if>
                    <div class="col-md-6 col-md-offset-2">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><spring:message code="add.plan"></spring:message> </h3>
                            </div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <c:if test="${error ne null}">
                                        <div class="alert alert-danger">
                                            <strong><spring:message code="error"></spring:message> </strong> ${error}.
                                        </div>
                                    </c:if>
                                </div>
                                <spring:message code="add.plan" var="add"></spring:message>
                                <spring:message code="name" var="name"></spring:message>
                                <spring:message code="price" var="price"></spring:message>
                                <spring:message code="unit" var="unit"></spring:message>
                                <spring:message code="period.type" var="periodType"></spring:message>
                                <spring:message code="max.book" var="maxBook"></spring:message>
                                <spring:message code="description" var="description"></spring:message>
                                <spring:message code="max.number.of.book.allow" var="maxNumberOfAllowDays"></spring:message>


                                <form:form  role="form" commandName="plan" method="post" action="/plan/save">
                                    <fieldset>
                                        <div class="form-group">
                                            <form:input path="name" class="form-control" placeholder="${name}" type="text"/>
                                            <form:errors path="name" cssClass="alert-danger"/>
                                        </div>
                                        <div class="form-group">
                                            <form:textarea class="form-control" id="message"  path="description" rows="5" placeholder="${description}"/>
                                            <form:errors path="description" cssClass="alert-danger"/>
                                        </div>

                                        <div class="form-group">
                                            <form:input path="price" class="form-control" placeholder="${price}" type="number"/>
                                            <form:errors path="price" cssClass="alert-danger"/>
                                        </div>
                                        <div class="form-group">
                                        <form:select path="periodType" class="form-control" >
                                            <form:options items="${periodTypes}" />
                                        </form:select>
                                        </div>
                                        <div class="form-group">
                                            <form:input path="unit" class="form-control" placeholder="${unit}" type="number"/>
                                            <form:errors path="unit" cssClass="alert-danger"/>
                                        </div>
                                           <div class="form-group">
                                            <form:input path="maxNumberOfBookAllow" class="form-control" placeholder="${maxBook}" type="number"/>
                                            <form:errors path="maxNumberOfBookAllow" cssClass="alert-danger"/>
                                        </div>
                                           <div class="form-group">
                                            <form:input path="maxNumberOfAllowDays" class="form-control" placeholder="${maxNumberOfAllowDays}" type="number"/>
                                            <form:errors path="maxNumberOfAllowDays" cssClass="alert-danger"/>
                                        </div>

                                        <input class="btn btn-lg btn-success btn-block" type="submit" value="${add}">
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

