<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="unAuthorized"/> </title>
	<link href="<c:url value="../../static/bootstrap/css/bootstrap.css" />" rel="stylesheet">
	<script>
		function goBack() {
			window.history.back();
		}
	</script>
</head>
<body class="security-app">
		<div class="error">
			<div class="error-code m-b-10 m-t-20">403 <i class="fa fa-warning"></i></div>
			<h3 class="font-bold"><spring:message code="we.coundnt.find.the.page"/> </h3>

			<div class="error-desc">
				<spring:message code="sorry"/> <br/>
				<spring:message code="dont.have.permission"/>
				<div>
					<a href="javascript:void goBack()" class=" login-detail-panel-button btn" href="http://www.vmware.com/">
						<i class="fa fa-arrow-left"></i>
						<spring:message code="please.go.back"/>
					</a>
				</div>
			</div>
		</div>
</body>
</html>
