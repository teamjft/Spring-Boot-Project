<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 2/5/17
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="something.going.wrong"/> </title>
    <link href="<c:url value="../../static/bootstrap/css/bootstrap.css" />" rel="stylesheet">
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="span12">
            <div class="hero-unit center">
                <h1><spring:message code="sorry"/> ! <small><font face="Tahoma" color="red"><spring:message code="something.going.wrong"/></font></small></h1>
                <br />
                <p><spring:message code="contact.your.webmaster"/> </p>
                <p><b><spring:message code="press.back.button"/> </b></p>
                <a href="javascript:void goBack()" class="btn btn-large btn-info"><i class="icon-home icon-white"></i> <spring:message code="take.me.home"/> </a>
            </div>
            <br/>
        </div>
    </div>
</div>
</body>
</html>
