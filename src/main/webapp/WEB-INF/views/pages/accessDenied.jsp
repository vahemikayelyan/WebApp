<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
Dear <strong>${user}</strong>, You are not authorized to access this page
<a href="<c:url value="/logout" />">Logout</a>