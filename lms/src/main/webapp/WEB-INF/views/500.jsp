<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 19/4/17
  Time: 12:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
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
                <h1><spring:message code="something.going.wrong"></spring:message> <small><font face="Tahoma" color="red"><spring:message code="error.500"></spring:message> </font></small></h1>
                <br />
                <p><spring:message code="contact.your.webmaster"></spring:message> </p>
                <a href="javascript:void goBack()" class="btn btn-large btn-info"><i class="icon-home icon-white"></i><spring:message code="take.me.home"></spring:message></a>
            </div>
            <br />
        </div>
    </div>
</div>
</body>
<c:import url="template/footer.jsp"></c:import>
</html>
