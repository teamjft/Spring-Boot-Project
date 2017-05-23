<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 16/4/17
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<sec:authorize access="hasAnyRole('LIBRARY_ADMIN','LIBRARIAN')" var="isLibraryAdminOrlibrarian"></sec:authorize>
<sec:authorize access="hasAnyRole('LIBRARY_ADMIN')" var="isLibraryAdmin"></sec:authorize>
<sec:authorize access="hasRole('LIBRARY_ADMIN')" var="isSuperAdmin"></sec:authorize>--%>
<sec:authentication property="principal.authorities" var="listOfRole"/>
<c:set var="isLibrarian" value="false" />
<c:set var="isLibraryAdmin" value="false" />
<c:set var="isSuperAdmin" value="false" />
<c:forEach var="item" items="${listOfRole}">
    <c:if test="${item eq 'LIBRARY_ADMIN'}">
        <c:set var="isLibraryAdmin" value="true" />
    </c:if>
    <c:if test="${item eq 'LIBRARIAN'}">
        <c:set var="isLibrarian" value="true" />
    </c:if> <c:if test="${item eq 'LIBRARY_ADMIN'}">
        <c:set var="isSuperAdmin" value="true" />
    </c:if>
</c:forEach>
<div class="col-md-3">
    <div id="sidenav1">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sideNavbar"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
        </div>
        <div class="collapse navbar-collapse" id="sideNavbar">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a href="<c:url value="/user/home"/>"> <span class="glyphicon glyphicon-home"></span><spring:message code="home"></spring:message> </a> </h4>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a href="<c:url value="/logout"/>"> <span class="glyphicon glyphicon-off"></span><spring:message code="logout"></spring:message> </a> </h4>
                    </div>
                </div>
                <c:if test="${isLibraryAdmin == true || isLibrarian == true}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span class="glyphicon glyphicon-book"> </span><spring:message code="book"></spring:message><span class="caret"></span></a> </h4>
                        </div>
                        <!-- Note: By adding "in" after "collapse", it starts with that particular panel open by default; remove if you want them all collapsed by default -->
                            <%-- in in class--%>
                        <div id="collapseOne" class="panel-collapse collapse">
                            <ul class="list-group">
                                <li><a href="<c:url value="/book/create"/>" class="navlink"><spring:message code="add.book"></spring:message></a></li>
                                <li><a href="<c:url value="/book/index"/>" class="navlink"><spring:message code="books"></spring:message></a></li>
                            </ul>
                        </div>
                    </div>
                </c:if>
                <c:if test="${isLibraryAdmin == true}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseX"><span class="glyphicon glyphicon-book"> </span><spring:message code="categories"></spring:message><span class="caret"></span></a> </h4>
                        </div>
                        <!-- Note: By adding "in" after "collapse", it starts with that particular panel open by default; remove if you want them all collapsed by default -->
                            <%-- in in class--%>
                        <div id="collapseX" class="panel-collapse collapse">
                            <ul class="list-group">
                                <li><a href="<c:url value="/category/index"/>" class="navlink"><spring:message code="categories"></spring:message></a></li>
                                <li><a href="<c:url value="/category/create"/>" class="navlink"><spring:message code="add.category"></spring:message></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseR"><span class="glyphicon glyphicon-book"> </span><spring:message code="issued.book"/> <span class="caret"></span></a> </h4>
                        </div>
                        <!-- Note: By adding "in" after "collapse", it starts with that particular panel open by default; remove if you want them all collapsed by default -->
                            <%-- in in class--%>
                        <div id="collapseR" class="panel-collapse collapse">
                            <ul class="list-group">
                                <li><a href="<c:url value="/issuebook/create"/>" class="navlink"><spring:message code="issue.book"/> </a></li>
                                <%--<li><a href="<c:url value="/category/create"/>" class="navlink">Issued Book</a></li>--%>
                            </ul>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseZ"><span class="glyphicon glyphicon-book"> </span><spring:message code="membership.plan"></spring:message><span class="caret"></span></a> </h4>
                        </div>
                        <!-- Note: By adding "in" after "collapse", it starts with that particular panel open by default; remove if you want them all collapsed by default -->
                            <%-- in in class--%>
                        <div id="collapseZ" class="panel-collapse collapse">
                            <ul class="list-group">
                                <li><a href="<c:url value="/plan/index"/>" class="navlink"><spring:message code="membership.plan"></spring:message></a></li>
                                <li><a href="<c:url value="/plan/create"/>" class="navlink"><spring:message code="add.plan"></spring:message></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"><span class="glyphicon glyphicon-cog"> </span><spring:message code="membership"></spring:message><span class="caret"></span></a> </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse">
                            <ul class="list-group">
                                <li><a href="<c:url value="/membership/index"></c:url>" class="navlink"><spring:message code="members"></spring:message></a></li>
                                <li><a href="<c:url value="/invite/inviteUser"></c:url>" class="navlink"><spring:message code="add.member"></spring:message></a></li>
                            </ul>
                        </div>
                    </div>
                </c:if>
                <c:if test="${isSuperAdmin ne false}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree"><span class="glyphicon glyphicon-calendar"> </span><spring:message code="configuration"></spring:message><span class="caret"></span></a> </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse">
                            <ul class="list-group">
                                <li><a href="<c:url value="/configuration/imageconfiguration"/>" class="navlink"><spring:message code="image.configuration"></spring:message></a></li>
                                <li><a href="<c:url value="/configuration/template"/>" class="navlink"><spring:message code="email.templates.config"></spring:message></a></li>
                            </ul>
                        </div>
                    </div>
                </c:if>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour"><span class="glyphicon glyphicon-user"></span> <spring:message code="about.us"></spring:message><span class="caret"></span></a></h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <ul class="list-group">
                            <li><a href="https://www.jellyfishtechnologies.com" class="navlink">Our web side</a></li>
                            <li><a href="https://github.com/teamjft/Spring-Boot-Project" class="navlink">Source code</a></li>
                        </ul>
                    </div>
                </div>
                <!-- This is in case you want to add additional links that will only show once the Nav button is engaged; delete if you don't need -->
            </div>
        </div>
    </div>
</div>
