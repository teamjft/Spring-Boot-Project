<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 13/4/17
  Time: 3:21 PM
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

        <div class="col-md-9">
            <div class="jumbotron">


                        <div class="col-xs-3">
                            <div class="project project-default">
                                <div class="shape">
                                    <div class="shape-text">
                                        <spring:message code="top"></spring:message>
                                    </div>
                                </div>
                                <div class="project-content">
                                    <h3 class="lead">
                                        <spring:message code="books"></spring:message>
                                    </h3>
                                    <p>
                                        <spring:message code="total.number.of.book"></spring:message>
                                        <br> <button class="btn btn-block btn-success"> ${dataCount.numberOFBook}</button>
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
                                        <spring:message code="active.member"></spring:message>
                                    </h3>
                                    <p>
                                        <spring:message code="total.number.of.active.member"></spring:message>
                                        <br>  <button class="btn btn-block btn-success"> ${dataCount.numberOFActiveUser}</button>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-3">
                            <div class="project project-radius project-primary">
                                <div class="shape">
                                    <div class="shape-text">
                                        <spring:message code="top"></spring:message>
                                    </div>
                                </div>
                                <div class="project-content">
                                    <h3 class="lead">
                                        <spring:message code="suspended.member"></spring:message>
                                    </h3>
                                    <p>
                                        <spring:message code="total.number.of.suspended.member"></spring:message>
                                        <br>  <button class="btn btn-block btn-danger"> 0</button>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-3">
                            <div class="project project-info">
                                <div class="shape">
                                    <div class="shape-text">
                                        <spring:message code="top"></spring:message>
                                    </div>
                                </div>
                                <div class="project-content">
                                    <h3 class="lead">
                                        <spring:message code="issued.book"></spring:message>
                                    </h3>
                                    <p>
                                        <spring:message code="total.number.of.issued.book"></spring:message>
                                        <br> <button class="btn btn-block"> ${dataCount.numberOFIssuedBook}</button>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-3">
                            <div class="project project-radius project-warning">
                                <div class="shape">
                                    <div class="shape-text">
                                        <spring:message code="top"></spring:message>
                                    </div>
                                </div>
                                <div class="project-content">
                                    <h3 class="lead">
                                        <spring:message code="available.copies"></spring:message>
                                    </h3>
                                    <p>
                                        <spring:message code="total.number.of.available.book.copies"></spring:message>
                                        <br> <button class="btn btn-block"> ${dataCount.totalNumberOfCopies}</button>
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
