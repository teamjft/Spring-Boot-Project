<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 22/5/17
  Time: 11:42 AM
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
                                    <fieldset>
                                        <div class="form-group">
                                            <input id="username" class="form-control" placeholder="Enter User name" type="text"/>
                                            <div id="error">
                                            </div>
                                        </div>
                                        <input id="submit" class="btn btn-lg btn-success btn-block" type="submit" value="Request">
                                    </fieldset>
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
<script>
    $( document ).ready(function() {
        console.log( "ready!" );
        $("#submit").click(function(){
            var username = $("#username").val();
            if (username == null || username == "") {
                return
            }
            var assignBook="http://localhost:8080/issuebook/validateUser/" + username
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
<c:import url="../template/footer.jsp"></c:import>
</html>
