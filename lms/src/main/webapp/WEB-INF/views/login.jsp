<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link href="<c:url value="../../static/bootstrap/css/bootstrap.css" />" rel="stylesheet">
<title>Spring Security Example</title>
</head>
<body class="security-app">
<spring:url value="/login" var="login" />
<%--<div class="container">
	<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info" >
			<div class="panel-heading">
				<div class="panel-title">Sign In</div>
				<c:if test="${param.error ne null}">
					${param.error}
					<div class="alert-danger">Invalid username and password.</div>
				</c:if>
				<c:if test="${param.logout ne null}">
					${param.logouts}
					<div class="alert-normal">You have been logged out.</div>
				</c:if>
				<div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a></div>
			</div>

			<div style="padding-top:30px" class="panel-body" >

				<div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
				<form action="/login"  class="form-horizontal"  >
					&lt;%&ndash; <form name='loginForm'
                           class="form-horizontal" role="form"  action="${login}>" method='post'>&ndash;%&gt;
					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input id="login-username" type="text" class="form-control" name="username"  placeholder="username or email">
					</div>

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<input id="login-password" type="password" class="form-control" name="password" placeholder="password">
					</div>



					&lt;%&ndash; <div class="input-group">
                         <div class="checkbox">
                             <label>
                                 <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                             </label>
                         </div>
                     </div>
                     <div style="margin-top:10px" class="form-group">&ndash;%&gt;
					<!-- Button -->

					<div class="col-sm-12 controls">
						<input  type="submit" class="btn btn-success" value="Login"/>
					</div>
					&lt;%&ndash;  </div>&ndash;%&gt;
					<input type="hidden" name="${_csrf.parameterName}"
						   value="${_csrf.token}" />
				</form>

			</div>
		</div>
	</div>
</div>--%>
<div class="container">
	<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info" >
			<div class="panel-heading">
				<c:if test="${param.error ne null}">
					<div class="alert-danger">Invalid username and password.</div>
				</c:if>
				<c:if test="${param.logout ne null}">
					<div class="alert-normal">You have been logged out.</div>
				</c:if>
			</div>
			<div style="padding-top:30px" class="panel-body" >

				<div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
				<form action="/login" method="post">

						<div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
								<input class="form-control" type="text"  name="username"
									   placeholder="User Name" />
							</div>
						</div>
						<div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
								<input class="form-control" type="password" name="password"
									   placeholder="Password" />
							</div>
							<div>
						</div>
							<input type="submit" value="Sign In"  class="btn btn-success"  />
					</div>
						<div class="col-sm-12 controls">
					<input type="hidden" name="${_csrf.parameterName}"
						   value="${_csrf.token}" />
							</div>
				</form>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>
