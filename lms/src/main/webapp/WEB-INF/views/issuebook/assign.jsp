<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 22/5/17
  Time: 12:03 PM
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
                                <div class="panel-body">
                                    <form:form commandName="issueBook" action="/issuebook/assign">
                                        <fieldset>
                                            <div class="form-group">
                                                <form:input path="membershipId" type="hidden" class="form-control" placeholder="username" />
                                            </div>
                                            <div class="form-group" id="isbnholder">
                                                <form:input class="form-control" path="isbn[0]" placeholder="isbn"/>
                                            </div>
                                            <button class="btn btn-info" onclick="addIsbn()" id="addIsbn">Add another book</button>
                                            <div class="form-group">
                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                    <button type="submit" id="submit" class="btn btn-primary btn-submit-fix"><spring:message code="place.order"/> </button>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </form:form>
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

<script>
    $( document ).ready(function() {
        var init= 0;

        var addIsbn = function () {
            $("#isbnholder").append('<form:input class="form-control" path="isbn[${initParam+1}]" placeholder="isbn"/>');
            return
        };

    });
</script>
