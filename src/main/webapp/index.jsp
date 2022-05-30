<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="nl">
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>Home pagina</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main class="Homepage">
    <h1>Wie is jou favoriete voetballer?</h1>
    <img class="MessiRonaldo" src="images/messi%20en%20ronaldo.webp">
    <div class="gemiddeldeRating-home">
        <p>Gemiddelde rating: <b>${gemiddeldeRating}</b>.</p>
    </div>
    <p class="front-text">Via de Voeg Toe pagina kan je jou favoriete voetballer doorgeven.</p>
</main>
<jsp:include page="footer.jsp"/>