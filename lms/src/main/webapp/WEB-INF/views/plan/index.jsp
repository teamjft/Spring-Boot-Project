<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 4/5/17
  Time: 11:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib  uri="/WEB-INF/tld/wrap.tld" prefix="w" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <c:import url="../template/header.jsp"></c:import>
    <link href="<c:url value="../../../static/custom/css/plan.css" />" rel="stylesheet">
</head>
<body  class="wellBody">
<div class="container col-md-12">
    <div class="row" >
        <c:import url="../template/sidebar.jsp"></c:import>

        <div class="col-md-9">
            <div class="row">
                <div class="col-md-12">
                    <h1><spring:message code="membership.plan"></spring:message> <a class="btn icon-btn btn-primary pull-right" href="/plan/create">
                        <span class="glyphicon btn-glyphicon glyphicon-plus img-circle"></span> <spring:message code="add.plan"></spring:message></a></h1></div>
${success}
                <c:forEach items="${plans}" var="item">
                    <div class="col-md-4">
                        <div class="thumbnail">
                            <div class="caption">
                                <div class='col-md-12'>
                                    <span class="glyphicon glyphicon-credit-card"></span>
                                    <span class="glyphicon glyphicon-trash pull-right text-primary" data-toggle="modal" onclick="openModalBox('${item.uuid}')"></span>
                                </div>
                                <div class='col-md-12 well well-add-card'>
                                    <h4><c:out value="${item.name}"></c:out></h4>
                                </div>
                                <div class='col-md-12'>
                                    <p><spring:message code="price"></spring:message>: ${item.price}</p>
                                    <p><spring:message code="description"></spring:message>: <w:wrap text="${item.description}" characterLimit="30"></w:wrap></p>
                                    <p class="text-muted"><spring:message code="max.book"></spring:message>: ${item.maxNumberOfBookAllow}</p>
                                </div>
                                <a type="button" class="btn btn-primary btn-xs btn-update btn-add-card" href="/plan/view/${item.uuid}"><spring:message code="view"></spring:message></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</div>
</div>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><spring:message code="confirmation"></spring:message> </h4>
            </div>
            <div class="modal-body">
                <p>
                    <spring:message code="confirmation.delete.alert"></spring:message>
                </p>
            </div>
            <div class="modal-footer">
                <a type="button" href="#test" class="btn btn-danger target" ><spring:message code="confirm"></spring:message> </a>
                <button type="button" class="btn btn-info" data-dismiss="modal"><spring:message code="close"></spring:message></button>
            </div>
        </div>

    </div>
</div>
</body>
<script type="text/javascript">
    function openModalBox(ItemId) {
        $("#myModal").find("a.target").attr('href', "/plan/delete/" + ItemId);
        $("#myModal").modal('show');
    }

</script>

<c:import url="../template/footer.jsp"></c:import>
</html>
