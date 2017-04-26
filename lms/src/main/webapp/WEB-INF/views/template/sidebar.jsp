<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bhushan
  Date: 16/4/17
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>

<div class="col-md-3">
    <div id="sidenav1">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sideNavbar"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
        </div>
        <div class="collapse navbar-collapse" id="sideNavbar">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a href="<c:url value="/user/home"/>"> <span class="glyphicon glyphicon-home"></span>Home</a> </h4>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a href="<c:url value="/logout"/>"> <span class="glyphicon glyphicon-off"></span>Logout</a> </h4>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span class="glyphicon glyphicon-book"> </span>Book<span class="caret"></span></a> </h4>
                    </div>
                    <!-- Note: By adding "in" after "collapse", it starts with that particular panel open by default; remove if you want them all collapsed by default -->
                   <%-- in in class--%>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <ul class="list-group">
                            <li><a href="<c:url value="/book/create"/>" class="navlink">Add Book</a></li>
                            <li><a href="<c:url value="/book/index"/>" class="navlink">Books</a></li>
                            <li><a href="<c:url value="/category/index"/>" class="navlink">Categories</a></li>
                            <li><a href="<c:url value="/category/create"/>" class="navlink">Add Category</a></li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"><span class="glyphicon glyphicon-cog"> </span>Member<span class="caret"></span></a> </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <ul class="list-group">
                            <li><a href="<c:url value="/membership/index"></c:url>" class="navlink">Members</a></li>
                            <li><a href="<c:url value="/invite/inviteUser"></c:url>" class="navlink">Add Member</a></li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree"><span class="glyphicon glyphicon-calendar"> </span>Configuration<span class="caret"></span></a> </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <ul class="list-group">
                            <li><a href="<c:url value="/configuration/imageconfiguration"/>" class="navlink">Image Configuration</a></li>
                            <li><a href="<c:url value="/configuration/template"/>" class="navlink">Email Templates</a></li>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour"><span class="glyphicon glyphicon-user"></span> About Us<span class="caret"></span></a></h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <ul class="list-group">
                            <li><a href="" class="navlink">Link 1</a></li>
                            <li><a href="" class="navlink">Link 2</a></li>
                            <li><a href="" class="navlink">Link 3</a></li>
                            <li><a href="" class="navlink">Link 4</a></li>
                            <li><a href="" class="navlink">Link 5</a></li>
                        </ul>
                    </div>
                </div>
                <!-- This is in case you want to add additional links that will only show once the Nav button is engaged; delete if you don't need -->
                <div class="menu-hide">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"><a href=""><span class="glyphicon glyphicon-new-window"></span>External Link</a> </h4>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"><a href=""><span class="glyphicon glyphicon-new-window"></span>External Link</a> </h4>
                        </div>
                    </div>
                </div>
                <!-- end hidden Menu items -->
            </div>
        </div>
    </div>
</div>
