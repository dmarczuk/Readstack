<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>${requestScope.category.name} - ReadStack</title>
    <%@include file="../segments/stylesheets.jspf"%>
</head>
<body>
    <div class="container">
        <%@include file="../segments/header.jspf"%>

        <main>
            <h1>${requestScope.category.name}</h1>
            <p>${requestScope.category.description}</p>
            <%@include file="../segments/discovery-list.jspf"%>
        </main>
        <%@include file="../segments/footer.jspf"%>
</div>
</body>
</html>