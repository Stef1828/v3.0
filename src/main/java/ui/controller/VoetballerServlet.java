package ui.controller;

import domain.DomainException;
import domain.model.Logboek;
import domain.model.Voetballer;
import domain.db.VoetballerDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet(name = "VoetballerServlet", value = "/VoetballerServlet")
public class VoetballerServlet extends HttpServlet {
    private final VoetballerDB voetballers = new VoetballerDB();
    final String meldingVanForm = "Vul hier de gegevens van je favoriete voetballer in.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            goToHome(request, response);
        }
        switch (command) {
            case "home":
            default:
                goToHome(request, response);
                break;

            case "form":
                fillInForm(request, response);
                break;

            case "zoek":
                goToZoek(request, response);
                break;

            case "zoekconfirmatie":
                goToZoekconfirmatie(request, response);
                break;

            case "zoek-resultaten":
                goToZoekresultaten(request, response);
                break;

            case "voegtoe":
                addAVoetballer(request, response);
                break;

            case "overzicht":
                goToOverzicht(request, response);
                break;

            case "verwijderbevestiging":
                goToVerwijderbevestiging(request, response);
                break;

            case "verwijder":
                doVerwijder(request, response);
                break;

            case "pasAan":
                pasAanForm(request, response);
                break;

            case "aanpassen":
                aanpassen(request, response);
                break;

            case "logboek":
                logboek(request, response);
                break;

            case "deleteLogboek":
                deleteLogboek(request, response);
                break;
        }
    }

    private void goToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("gemiddeldeRating", voetballers.getGemRating());
        logs(request,"Home");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void fillInForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errors = new ArrayList<>();

        String voornaam_naamParam = request.getParameter("voornaam_naam");
        String competitieParam = request.getParameter("competitie");
        String clubParam = request.getParameter("club");
        int nummerParam = Integer.parseInt(request.getParameter("nummer"));
        Double ratingParam = Double.parseDouble(request.getParameter("rating"));

        try {
            Voetballer voetballer = new Voetballer(voornaam_naamParam, competitieParam, clubParam, nummerParam, ratingParam);
            voetballers.add(voetballer);
        } catch (DomainException e) {
            errors.add(e.getMessage());
            Cookie cookie = getCookieWithKey(request, "aantal");
            int aantal = Integer.parseInt(cookie.getValue());
            aantal++;
            response.addCookie(new Cookie("aantal", Integer.toString(aantal)));
            request.setAttribute("melding", errors);
            request.getRequestDispatcher("voegtoe.jsp").forward(request, response);
        }
        ArrayList result = voetballers.getVoetballers();
        request.setAttribute("voetballers", result);
        request.setAttribute("gemiddeldeRating", voetballers.getGemRating());
        logs(request,"Form invullen");
        request.getRequestDispatcher("overzicht.jsp").forward(request, response);
    }

    private void goToZoek(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logs(request,"Zoek");
        request.getRequestDispatcher("zoek.jsp").forward(request, response);
    }

    private void goToZoekconfirmatie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String zoekwoord= request.getParameter("zoekwoord");
        request.setAttribute("zoekwoord", zoekwoord);
        logs(request,"Zoek confirmatie");
        request.getRequestDispatcher("zoekconfirmatie.jsp").forward(request, response);
    }

    private void goToZoekresultaten(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String woord = request.getParameter("woord");
        ArrayList<Voetballer> gevondenVoetballers = voetballers.zoekVoetballer(woord);
        request.setAttribute("voetballers", gevondenVoetballers);
        int aantal= gevondenVoetballers.size();
        request.setAttribute("aantal", aantal);
        logs(request,"Zoek resultaten");
        request.getRequestDispatcher("zoekresultaten.jsp").forward(request, response);
    }

    private void addAVoetballer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getCookieWithKey(request, "aantal")== null) response.addCookie(new Cookie("aantal", "0"));
        request.setAttribute("melding", meldingVanForm);
        logs(request,"Voetballer toevoegen");
        request.getRequestDispatcher("voegtoe.jsp").forward(request, response);
    }

    private void goToOverzicht(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("voetballers", voetballers.getVoetballers());
        request.setAttribute("gemiddeldeRating", voetballers.getGemRating());
        logs(request,"Overzicht");
        request.getRequestDispatcher("overzicht.jsp").forward(request, response);
    }

    private void goToVerwijderbevestiging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int verwijderId = Integer.parseInt(request.getParameter("remove"));
        Voetballer voetballer = voetballers.getVoetballer(verwijderId);
        request.setAttribute("voetballer", voetballer);
        logs(request,"Verwijderbevestiging");
        request.getRequestDispatcher("verwijderbevestiging.jsp").forward(request, response);
    }

    private void doVerwijder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        voetballers.verwijderVoetballer(id);
        request.setAttribute("voetballers", voetballers.getVoetballers());
        request.setAttribute("gemiddeldeRating", voetballers.getGemRating());
        logs(request,"Verwijder");
        request.getRequestDispatcher("overzicht.jsp").forward(request, response);
    }

    private void aanpassen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id= request.getParameter("id");

        String voornaam_naam = request.getParameter("voornaam_naam");
        String competitie = request.getParameter("competitie");
        String club = request.getParameter("club");
        int nummer = Integer.parseInt(request.getParameter("nummer"));
        Double rating = Double.parseDouble(request.getParameter("rating"));
        try {
            Voetballer voetballer = new Voetballer(voornaam_naam, competitie, club, nummer, rating);
            voetballers.add(voetballer);
            goToOverzicht(request, response);
            return;
        }
        catch (DomainException e) {
            ArrayList<String> melding= new ArrayList<>();
            melding.add(e.getMessage());
            request.setAttribute("voornaam_naam", voornaam_naam);
            request.setAttribute("competitie", competitie);
            request.setAttribute("club", club);
            request.setAttribute("nummer", nummer);
            request.setAttribute("rating", rating);
            request.setAttribute("voetballerId", id);
            request.setAttribute("melding", melding);
            request.getRequestDispatcher("pasAan.jsp").forward(request, response);
        }

        request.setAttribute("voetballers", voetballers.getVoetballers());
        request.setAttribute("gemiddeldeRating", voetballers.getGemRating());
        logs(request,"Aanpassen");
        request.getRequestDispatcher("overzicht.jsp").forward(request, response);
    }

    private void pasAanForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ID= request.getParameter("id");
        String naam= request.getParameter("voornaam_naam");
        String competitie= request.getParameter("competitie");
        String club= request.getParameter("club");
        String nummer= request.getParameter("nummer");
        String rating= request.getParameter("rating");
        String melding= request.getParameter("melding");
        request.setAttribute("VoetballersId", ID);
        request.setAttribute("voornaam_naam", naam);
        request.setAttribute("competitie", competitie);
        request.setAttribute("club", club);
        request.setAttribute("nummer", nummer);
        request.setAttribute("rating", rating);
        request.setAttribute("melding", melding);
        logs(request,"Pas aan form");
        request.getRequestDispatcher("pasAan.jsp").forward(request, response);
    }

    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }

    private void logboek(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logs(request,"Logboek navigatie");
        request.getRequestDispatcher("logboek.jsp").forward(request, response);
    }

    private void deleteLogboek(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher("logboek.jsp").forward(request, response);
    }

    private void logs(HttpServletRequest request, String pagina) {
        ArrayList<Logboek> paginas;
        HttpSession session = request.getSession();

        if (session.getAttribute("paginas") == null) {
            paginas = new ArrayList<>();
        } else {
            paginas = (ArrayList<Logboek>) session.getAttribute("paginas");
        }
        paginas.add(new Logboek(pagina));
        session.setAttribute("paginas", paginas);
    }
}

