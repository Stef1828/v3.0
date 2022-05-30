<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.model.Voetballer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="nl">
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>Overzicht pagina</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <h1>Wie is jou favoriete voetballer?</h1>
    <table>
        <thead>
        <tr>
            <th>Voornaam + Naam</th>
            <th>Competitie</th>
            <th>Club</th>
            <th>Nummer</th>
            <th>Rating</th>
            <th>Pas aan</th>
            <th>Verwijder</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="voetballers" items="${voetballers}">
            <tr class="form-links">
                <td>${voetballers.voornaam_naam}</td>
                <td>${voetballers.competitie}</td>
                <td>${voetballers.club}</td>
                <td>${voetballers.nummer}</td>
                <td>${voetballers.rating}</td>
                <td id="pasAan${voetballers.idVoetballer}"><a href="VoetballerServlet?command=pasAan&id=${voetballers.idVoetballer}&voornaam_naam=${voetballers.voornaam_naam}">Pas aan</a></td>
                <td><a href="VoetballerServlet?command=verwijderbevestiging&remove=${voetballers.idVoetballer}" id="verwijderVoetballer">Verwijder</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="gemiddeldeRating-overzicht">
        <a>Gemiddelde rating: <b>${gemiddeldeRating}</b>.</a>
    </div>
</main>
<jsp:include page="footer.jsp"/>
