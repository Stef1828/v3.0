<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.model.Voetballer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="nl">
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>Logboek Navigatie pagina</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <h1>Logboek</h1>
    <p>Hieronder zie je een logboek van de bezochte pagina's.</p>
    <article class="logboek-article">
        <a href="VoetballerServlet?command=deleteLogboek">Reset</a>
    </article>
    <table>
        <tr>
            <th>Bezochte pagina</th>
            <th>Tijdstip</th>
        </tr>
        <c:forEach var="pagina" items="${paginas}">
            <tr>
                <td>${pagina.voornaam_naam}</td>
                <td>${pagina.tijdstip}</td>
            </tr>
        </c:forEach>
    </table>
</main>
<jsp:include page="footer.jsp"/>
