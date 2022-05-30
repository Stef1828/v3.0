<!doctype html>
<%@ page import="java.util.List" %>
<%@ page import="domain.model.Voetballer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>Zoek pagina</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <div class="center">
        <form method="get" action="VoetballerServlet">
            <label for="zoekwoord">Vul hier de naam van de spelers die je wilt opzoeken in:</label>
            <input type="text" id="zoekwoord" name="zoekwoord" value="">
            <input type="hidden" name="command" value="zoekconfirmatie">
            <input type="submit" value="Submit">
        </form>
    </div>
</main>
<jsp:include page="footer.jsp"/>
