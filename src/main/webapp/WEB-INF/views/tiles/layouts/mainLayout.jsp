<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
    <title><tiles:getAsString name="title"/></title>
    <link href="<c:url value="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body class="<tiles:insertAttribute name="class"/>">

<header id="header">
    <tiles:insertAttribute name="header"/>
</header>

<section id="site-content">
    <tiles:insertAttribute name="body"/>
</section>

<footer id="footer">
    <tiles:insertAttribute name="footer"/>
</footer>


<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/webjars/bootstrap/3.3.7/js/bootstrap.min.js" />" type="text/javascript"></script>

</body>
</html>