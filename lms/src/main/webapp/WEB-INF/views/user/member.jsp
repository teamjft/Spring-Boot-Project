<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 23/5/17
  Time: 8:37 PM
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

</head>
<body  class="wellBody">
<div class="container col-md-12">
    <div class="row" >
        <c:import url="../template/sidebar.jsp"></c:import>

        <div class="col-md-9 jumbotron">
            <div >
                <div class="col-xs-3">
                    <div class="project project-default">
                        <div class="shape">
                            <div class="shape-text">
                                <spring:message code="top"></spring:message>
                            </div>
                        </div>
                        <div class="project-content">
                            <h3 class="lead">
                                <spring:message code="assign"></spring:message>
                            </h3>
                            <p>
                                <spring:message code="total.number.assign"></spring:message>
                                <br> <a href="/issuebook/assigned"> <button class="btn btn-block btn-success">${issueDataCount.numberOFIssuedBook}</button></a>
                            </p>
                        </div>
                    </div>
                </div>

                <div class="col-xs-3">
                    <div class="project project-success">
                        <div class="shape">
                            <div class="shape-text">
                                <spring:message code="top"></spring:message>
                            </div>
                        </div>
                        <div class="project-content">
                            <h3 class="lead">
                                <spring:message code="returned"></spring:message>
                            </h3>
                            <p>
                                <spring:message code="total.number.returned"></spring:message>
                                <br>   <a href="/issuebook/returned"><button class="btn btn-block btn-success">${issueDataCount.numberOFReturnedBook}</button></a>
                            </p>
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
