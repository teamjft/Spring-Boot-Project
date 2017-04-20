<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 13/4/17
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="container">
                    <div class="row">

                        <div class="col-xs-3">
                            <div class="project project-default">
                                <div class="shape">
                                    <div class="shape-text">
                                        top
                                    </div>
                                </div>
                                <div class="project-content">
                                    <h3 class="lead">
                                        Books
                                    </h3>
                                    <p>
                                        Total Number of Books.
                                        <br> <button class="btn btn-block btn-success"> ${dataCount.numberOFBook}</button>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-3">
                            <div class="project project-success">
                                <div class="shape">
                                    <div class="shape-text">
                                        top
                                    </div>
                                </div>
                                <div class="project-content">
                                    <h3 class="lead">
                                        Active Member
                                    </h3>
                                    <p>
                                        Total Number Of Active Member.
                                        <br>  <button class="btn btn-block btn-success"> ${dataCount.numberOFActiveUser}</button>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-3">
                            <div class="project project-radius project-primary">
                                <div class="shape">
                                    <div class="shape-text">
                                        top
                                    </div>
                                </div>
                                <div class="project-content">
                                    <h3 class="lead">
                                        Supspended Member
                                    </h3>
                                    <p>
                                        Total Number Of SUSPENDED Member.
                                        <br>  <button class="btn btn-block btn-danger"> 0</button>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-3">
                            <div class="project project-info">
                                <div class="shape">
                                    <div class="shape-text">
                                        top
                                    </div>
                                </div>
                                <div class="project-content">
                                    <h3 class="lead">
                                       Issued Book
                                    </h3>
                                    <p>
                                        Total Number Of Issued Book.
                                        <br> <button class="btn btn-block"> ${dataCount.numberOFIssuedBook}</button>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-3">
                            <div class="project project-radius project-warning">
                                <div class="shape">
                                    <div class="shape-text">
                                        top
                                    </div>
                                </div>
                                <div class="project-content">
                                    <h3 class="lead">
                                        Available Copies
                                    </h3>
                                    <p>
                                        Total Number Of  Available Book Copies.
                                        <br> <button class="btn btn-block"> ${dataCount.totalNumberOfCopies}</button>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-3">
                            <div class="project project-radius project-danger">
                                <div class="shape">
                                    <div class="shape-text">
                                        top
                                    </div>
                                </div>
                                <div class="project-content">
                                    <h3 class="lead">
                                        Project label
                                    </h3>
                                    <p>
                                        And a little description.
                                        <br> and so one
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
</html>
