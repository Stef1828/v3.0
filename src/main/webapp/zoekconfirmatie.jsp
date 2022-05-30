<!doctype html>
<%@ page import="java.util.List" %>
<%@ page import="domain.model.Voetballer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>Zoekconfirmatie pagina</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <p>We hebben de zoekopdracht: "${zoekwoord}" opgezocht.</p>
    <p><a href="VoetballerServlet?command=zoek-resultaten&woord=${zoekwoord}">Bekijk het resultaat</a></p>
    <p><a href="VoetballerServlet?command=overzicht">Cancel</a> indien je niet meer geïnteresseerd bent.</p>
</main>
<jsp:include page="footer.jsp"/>
