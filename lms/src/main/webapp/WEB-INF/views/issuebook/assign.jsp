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
                                    <div class="form-group">
                                        <c:if test="${error ne null}">
                                            <div class="alert alert-danger">
                                                <strong><spring:message code="error"/></strong> ${error}.
                                            </div>
                                        </c:if>
                                        </div>
                                    <form:form commandName="issueBook" action="/issuebook/assign">
                                        <fieldset>
                                            <div class="form-group">
                                                <form:input path="membershipId" type="hidden" class="form-control" placeholder="username" />
                                            </div>
                                            <div class="form-group copyholder" >
                                                <form:input class="form-control" path="isbns" placeholder="isbn"/>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                    <button  class="btn btn-info" onclick="myFunction()" type="button"><spring:message code="add.another.book"></spring:message> </button>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                    <button type="submit" id="submit" class="btn btn-primary btn-submit-fix"><spring:message code="assign"/> </button>
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
<script>
        var init;
        var a=0;
        $( document ).ready(function() {
            init =  '<input id="isbns" name="isbns" placeholder="isbn" class="form-control" type="text" value="">';
        });
        function myFunction() {
            a =a+1;
            console.log("fuck hello world"+init+a)
            $(".copyholder").append(init);
        }
</script>

</body>
<c:import url="../template/footer.jsp"></c:import>
</html>

