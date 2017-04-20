<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 19/4/17
  Time: 10:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                <div class="panel-heading">
                                    <h3 class="panel-title">Image Configuration</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="panel-body">
                                       Local Storage Path : ${local}
                                    </div>
                                    <div class="panel-body">
                                        Cloudinary Storage Path : ${cloudinary}
                                    </div>
                                    <div class="panel-body">
                                        Activated Storage Type : ${currentStorageType}
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Update Configuration Storage Type</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <c:if test="${error ne null}">
                                            <div class="alert alert-danger">
                                                <strong>Error!</strong> ${error}.
                                            </div>
                                        </c:if>
                                        <c:if test="${errors != null}">
                                            <div  class="well">
                                                <c:forEach items="${errors}" var="error">
                                                    <div class="alert alert-danger">
                                                        <strong>Error!</strong> ${error.getDefaultMessage()}.
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>
                                    <form  role="form" action="/library/updatestrogetype">
                                        <fieldset>
                                            <div class="form-group">
                                                <c:forEach items="${storageType}" var="value">
                                                    <label class="radio-inline">
                                                        <input type="radio" value="${value}" name="saveImageServiceType">${value}
                                                    </label>
                                                </c:forEach>
                                            </div>
                                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Update Storage">
                                        </fieldset>

                                    </form>
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
</html>
