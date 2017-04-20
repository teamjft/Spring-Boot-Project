<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 17/4/17
  Time: 1:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <div class="col-md-6 col-md-offset-2">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Add Book</h3>
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
                                                        <strong>Errror!</strong> ${error.getDefaultMessage()}.
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>
                                    <form:form  role="form" commandName="book" method="post" action="/book/save" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <input type="file" class="form-control" name="image"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input path="name" class="form-control" placeholder="Name" type="text"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="isbn" path="isbn" />
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="Edition" path="edition" />
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="Description" path="description"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="Price" path="price" />
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="publisher" path="publisher" />
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="Author Name" path="authorName" />
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="Publication Year" path="publicationYear"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="Total Number Of Copies" path="totalNumberOfCopies" type="number"/>
                                        </div>
                                        <form:select class="form-control"  multiple="true"  path="categoryBeens">
                                            <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                                        </form:select>
                                        <input class="btn btn-lg btn-success btn-block" type="submit" value="Add Book">

                                        </fieldset>

                                    </form:form>
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
