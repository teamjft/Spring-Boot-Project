<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 5/5/17
  Time: 10:03 PM
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
    <style>
        .db-bk-color-one {
            background-color: #f55039;
        }

        .db-bk-color-two {
            background-color: #46A6F7;
        }

        .db-bk-color-three {
            background-color: #47887E;
        }

        .db-bk-color-six {
            background-color: #F59B24;
        }
        .db-padding-btm {
            padding-bottom: 50px;
        }
        .db-button-color-square {
            color: #fff;
            background-color: rgba(0, 0, 0, 0.50);
            border: none;
            border-radius: 0px;
            -webkit-border-radius: 0px;
            -moz-border-radius: 0px;
        }

        .db-button-color-square:hover {
            color: #fff;
            background-color: rgba(0, 0, 0, 0.50);
            border: none;
        }


        .db-pricing-eleven {
            margin-bottom: 30px;
            margin-top: 50px;
            text-align: center;
            box-shadow: 0 0 5px rgba(0, 0, 0, .5);
            color: #fff;
            line-height: 30px;
        }

        .db-pricing-eleven ul {
            list-style: none;
            margin: 0;
            text-align: center;
            padding-left: 0px;
        }

        .db-pricing-eleven ul li {
            padding-top: 20px;
            padding-bottom: 20px;
            cursor: pointer;
        }

        .db-pricing-eleven ul li i {
            margin-right: 5px;
        }


        .db-pricing-eleven .price {
            background-color: rgba(0, 0, 0, 0.5);
            padding: 40px 20px 20px 20px;
            font-size: 60px;
            font-weight: 900;
            color: #FFFFFF;
        }

        .db-pricing-eleven .price small {
            color: #B8B8B8;
            display: block;
            font-size: 12px;
            margin-top: 22px;
        }

        .db-pricing-eleven .type {
            background-color: #52E89E;
            padding: 50px 20px;
            font-weight: 900;
            text-transform: uppercase;
            font-size: 30px;
        }

        .db-pricing-eleven .pricing-footer {
            padding: 20px;
        }

        .db-pricing-eleven.popular {
            margin-top: 10px;
        }

        .db-pricing-eleven.popular .price {
            padding-top: 80px;
        }
    </style>
</head>
<body  class="wellBody">
<div class="col-md-12">
    <div class="row" >
        <c:import url="../template/sidebar.jsp"></c:import>
        <div class="col-md-9">
            <div class="row">
                <div class="col-md-12">
                    <c:forEach items="${plans}" var="item" varStatus="loop">
                    <div class="col-md-3">
                        <div class="db-wrapper">
                            <c:if test="${loop.index%2 != 0}"><div class="db-pricing-eleven db-bk-color-three"></c:if>
                                <c:if test="${loop.index%2 == 0}"><div class="db-pricing-eleven db-bk-color-two"></c:if>
                                    <div class="price">
                                        ${item.price}<small>${item.currency}</small>
                                        <small>per ${item.unit} ${item.periodType}</small>
                                    </div>
                                    <div class="type">
                                            ${item.name}
                                    </div>
                                    <ul>
                                        <li><i class="glyphicon glyphicon-time"></i>Status: Available </li>
                                        <li><i class="glyphicon glyphicon-book"></i><spring:message code="max.book"></spring:message>:${item.maxNumberOfBookAllow} </li>
                                        <li><i class="glyphicon glyphicon-eye-open"></i>
                                            <span class="btn btn-success" data-toggle="modal" onclick="openModalBox(`${item.description}`)"><spring:message code="description"></spring:message> </span>
                                        </li>
                                    </ul>
                                    <div class="pricing-footer">
                                        <a href="<c:url value="/plan/purchase/${item.uuid}"/>" class="btn db-button-color-square btn-lg"><spring:message code="buy"></spring:message></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
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
                <h4 class="modal-title"><spring:message code="description"></spring:message> </h4>
            </div>
            <div class="modal-body">
                <p class="target">
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal"><spring:message code="close"></spring:message></button>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript">
    function openModalBox(description) {
        $("#myModal").find("p.target").html(description);
        $("#myModal").modal('show');
    }
</script>
</body>

<c:import url="../template/footer.jsp"></c:import>
</html>
