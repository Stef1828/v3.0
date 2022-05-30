<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="nl">
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>Pasaan pagina</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <h1>Hier kan je de gegevens van je favoriete voetballers aanpassen</h1>
    <div class="form-container">
        <c:forEach var="melding" items="${melding}">
            <p>${melding}</p>
        </c:forEach>
        <form method="post" action="VoetballerServlet" novalidate>
            <input type="hidden" name="id" value="${voetballerId}">
            <label for="voornaam_naam"><b>Voornaam en Naam: *</b></label>
            <input type="text" id="voornaam_naam" name="voornaam_naam" placeholder="Voornaam en Naam" value="${voornaam_naam}">
            <label for="competitie">Competitie: *</label>
            <select name="competitie" id="competitie" value="${competitie}">
                <option value="" disabled selected hidden>Kies de competitie...</option>
                <option value="Premier League">Premier League</option>
                <option value="La Liga">La Liga</option>
                <option value="Ligue 1">Ligue 1</option>
                <option value="Bundesliga">Bundesliga</option>
                <option value="Eredivisie">Eredivisie</option>
                <option value="Jupiler Pro League">Jupiler Pro League</option>
            </select>
            <label for="Club">Club: *</label>
            <input type="text" id="club" name="club" placeholder="club" value="${club}">
            <label for="Nummer">Nummer: *</label>
            <input type="number" step="1" id="nummer" name="nummer" placeholder="nummer" min="0" max="100" value="${nummer}">
            <label for="Rating">Rating</label>
            <input type="number" step="0.1" id="rating" name="rating" placeholder="rating" min="0" max="10" value="${rating}">
            <input type="hidden" name="command" value="aanpassen">
            <input type="submit" value="pasAan">
        </form>
    </div>
</main>
<jsp:include page="footer.jsp"/>