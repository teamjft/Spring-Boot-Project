<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 11/4/17
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.s
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="../../static/bootstrap/css/bootstrap.css" />"  rel="stylesheet">
    <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.3/jquery.js"/>" ></script>
    <script src="<c:url value="../../static/bootstrap/js/bootstrap.js" />" ></script>

    <style>
        .well{
            background-color: #4267b2;
        }
    </style>
    <script>
        $(document).ready(function () {
            $("#button1").click(function () {
                var email = $('#recovery-email').val();
                var forgetPassword="http://localhost:8080/user/forgetPassword/" + email
                $.ajax({
                    url: forgetPassword,
                    contentType : "application/json",
                    success: function (resp) {
                        // we have the response
                        if(resp.type == "SUCCESS" ) {
                        $("#div1").html(' <div class="alert alert-success"> <strong>Success!  </strong> '+resp.message +'. </div>');
                        }else {
                            $("#div1").html(' <div class="alert alert-danger"> <strong>Error!  </strong>'+ resp.message +'. </div>');
                        }
                    },
                    error: function (e) {
                        $("#div1").append('<div class="alert alert-danger"> <strong>Error!  </strong>Some thing went wrong please try again </div>');
                    }
                })

            })
        });
    </script>
<body class="well">
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect(""); %>
</sec:authorize>

<div class="container">
    <spring:message code="user.name" var="username"></spring:message>
    <spring:message code="new.password" var="password"></spring:message>
    <spring:message code="sign.in" var="signin"></spring:message>
    <div class="row">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <c:if test="${param.error ne null}">
                        <div class="alert-danger"><spring:message code="invalid.username.or.password"></spring:message></div>
                    </c:if>
                    <c:if test="${param.logout ne null}">
                        <div class="alert-normal"><spring:message code="logout.info"></spring:message> </div>
                    </c:if>
                </div>
                <div style="padding-top:30px" class="panel-body" >

                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                    <form action="/index" method="post">

                        <div>
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input class="form-control" type="text"  name="username"
                                       placeholder="${username}" />
                            </div>
                        </div>
                        <div>
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input class="form-control" type="password" name="password"
                                       placeholder="${password}" />
                            </div>
                            <div>
                            </div>
                            <input type="submit" value="${signin}"  class="btn btn-success"  />
                        </div>
                        <div class="col-sm-12 controls">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                        </div>
                        <a href="javascript:;" class="forget" data-toggle="modal" data-target=".forget-modal"><spring:message code="forget.password"></spring:message> </a>
                    </form>
                </div>
                <div class="modal fade forget-modal" tabindex="-1" role="dialog" aria-labelledby="myForgetModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">
                                    <span aria-hidden="true">×</span>
                                    <span class="sr-only"><spring:message code="close">s</spring:message> </span>
                                </button>
                                <h4 class="modal-title"<spring:message code="recover.password"></spring:message> </h4>
                            </div>
                            <div class="modal-body">
                                <div id="div1"></div>
                                <p><spring:message code="enter.email"></spring:message> </p>
                                <input type="email" name="recovery-email" id="recovery-email" class="form-control" autocomplete="off">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"></spring:message></button>
                                <button type="button" id="button1" class="btn btn-custom"><spring:message code="recovery"></spring:message></button>
                            </div>
                        </div> <!-- /.modal-content -->
                    </div> <!-- /.modal-dialog -->
                </div> <!-- /.modal -->

            </div>
        </div>
    </div>
</div>
</body>
<c:import url="template/footer.jsp"></c:import>

</html>
