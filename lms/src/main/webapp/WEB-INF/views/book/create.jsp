<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                                    <h3 class="panel-title"><spring:message code="add.book"></spring:message> </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <c:if test="${error ne null}">
                                            <div class="alert alert-danger">
                                                <strong><strong> <spring:message code="error"></spring:message></strong> ${error}.
                                            </div>
                                        </c:if>
                                        <c:if test="${errors != null}">
                                            <div  class="well">
                                                <c:forEach items="${errors}" var="error">
                                                    <div class="alert alert-danger">
                                                        <strong> <spring:message code="error"></spring:message></strong> ${error.getDefaultMessage()}.
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>
                                    <spring:message code="name" var="name"></spring:message>
                                    <spring:message code="isbn" var="isbn"></spring:message>
                                    <spring:message code="edition" var="edition"></spring:message>
                                    <spring:message code="description" var="description"></spring:message>
                                    <spring:message code="price" var="price"></spring:message>
                                    <spring:message code="publisher" var="publisher"></spring:message>
                                    <spring:message code="author.name" var="authorName"></spring:message>
                                    <spring:message code="publication.year" var="publisherYear"></spring:message>
                                    <spring:message code="total.number.of.copies" var="totalNumberOfCopies"></spring:message>
                                    <spring:message code="add.book" var="addBook"></spring:message>
                                    <form:form  role="form" commandName="book" method="post" action="/book/save" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <input type="file" class="form-control" name="image"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input path="name" class="form-control" placeholder="${name}" type="text"/>
                                            <form:errors path="name" cssClass="alert-danger"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="${isbn}" path="isbn" />
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="${edition}" path="edition" />
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="${description}" path="description"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="${price}" path="price" />
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="${publisher}" path="publisher" />
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="${authorName}" path="authorName" />
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="${publisherYear}" path="publicationYear"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input class="form-control" placeholder="${totalNumberOfCopies}" path="totalNumberOfCopies" type="number"/>
                                        </div>
                                        <form:select class="form-control"  multiple="true"  path="categoryBeens">
                                            <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                                        </form:select>
                                        <input class="btn btn-lg btn-success btn-block" type="submit" value="${addBook}">

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
<c:import url="../template/footer.jsp"></c:import>
</html>
