<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.model.Voetballer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="style/style.css">
    <title>Zoekresultaten pagina</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
    <p class="center">
        aantal resultaten: ${aantal}
    </p>
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
        <c:forEach var="voetballer" items="${voetballers}">
            <tr>
                <td>${voetballer.voornaam_naam}</td>
                <td>${voetballer.competitie}</td>
                <td>${voetballer.club}</td>
                <td>${voetballer.nummer}</td>
                <td>${voetballer.rating}</td>
                <td>Pas aan</td>
                <td><a href="VoetballerServlet?command=verwijderbevestiging&remove=${voetballer.idVoetballer}">Verwijder</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<jsp:include page="footer.jsp"/>