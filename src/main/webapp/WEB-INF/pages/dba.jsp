<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>DBA page</title>
</head>
<body>
Dear <strong>${user}</strong>, Welcome to DBA Page.
<a href="<c:url value="/logout" />">Logout 3333</a>
</body>
</html>