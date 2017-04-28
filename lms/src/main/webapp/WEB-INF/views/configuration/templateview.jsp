<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 24/4/17
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
--%>
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
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="well well-sm">
                            <form class="form-horizontal" action="" method="post">
                                <fieldset>
                                    <c:if test="${success ne null}">
                                        <p class="alert alert-success">${success}</p>
                                    </c:if>
                                    <legend class="text-center">
                                        <a  class="btn bg-primary" href="/configuration/editTemplate/${emailTemplate.uuid}"><spring:message code="edit"></spring:message></a>
                                    </legend>
                                    <!-- Name input-->
                                    <div class="form-group">
                                        <label class="col-md-3 control-label" for="name"><spring:message code="notification.type"></spring:message> </label>
                                        <div class="col-md-9">
                                            <input id="name" readonly type="text" value="${emailTemplate.notificationType}" class="form-control">
                                        </div>
                                    </div>

                                    <!-- Email input-->
                                    <div class="form-group">
                                        <label class="col-md-3 control-label" for="email"><spring:message code="subject"></spring:message> </label>
                                        <div class="col-md-9">
                                            <input id="email" readonly  type="text" value="${emailTemplate.subject}" class="form-control">
                                        </div>
                                    </div>

                                    <!-- Message body -->
                                    <div class="form-group">
                                        <label class="col-md-3 control-label" for="message"><spring:message code="content"></spring:message> </label>
                                        <div class="col-md-9">
                                            <textarea class="form-control" id="message" readonly rows="5">"${emailTemplate.content}" </textarea>
                                        </div>
                                    </div>

                                    <!-- Form actions -->
                                </fieldset>
                            </form>
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
