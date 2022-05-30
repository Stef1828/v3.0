<!doctype html>
<%@ page import="java.util.List" %>
<%@ page import="domain.model.Voetballer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>Verwijderbevestiging pagina</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <p>Bent u zeker dat u de voetballer met naam: ${voetballer.voornaam_naam} wilt verwijderen?</p>
    <div class="bevestiging-links">
        <a href="VoetballerServlet?command=verwijder&id=${voetballer.idVoetballer}">Verwijder</a>
        <a href="VoetballerServlet?command=home">Annuleer</a>
    </div>
</main>
<jsp:include page="footer.jsp"/>