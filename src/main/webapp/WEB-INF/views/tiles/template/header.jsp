<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-inverse navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Brand</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="<c:url value="/" />">Home</a></li>
                <li><a href="<c:url value="/home" />">Get started</a></li>
                <sec:authorize access="!isAuthenticated()">
                    <li><a href="<c:url value="/login" />">Login</a></li>
                    <li><a href="<c:url value="/signup" />">SignUp</a></li>
                </sec:authorize>
                <li><a href="<c:url value="" />">File Upload</a></li>
                <li><a href="<c:url value="" />">Misc</a></li>
            </ul>
            <ul class="nav navbar-nav pull-right">
                <sec:authorize access="isAuthenticated()">
                    <li><a href="<c:url value="/logout" />">Logout</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</div>