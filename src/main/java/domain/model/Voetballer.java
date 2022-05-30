package domain.model;

import domain.DomainException;

public class Voetballer {
    private int idVoetballer;
    private String voornaam_naam;
    private String competitie;
    private String club;
    private Integer nummer;
    private Double rating;
    private boolean isValide = false;
    final String[] mogelijk = {"Premier League", "La Liga", "Ligue 1", "Bundesliga", "Eredivisie", "Jupiler Pro League"};

    public Voetballer(String voornaam_naam, String competitie, String club, int nummer, double rating) {
        setVoornaam_naam(voornaam_naam);
        setCompetitie(competitie);
        setClub(club);
        setNummer(nummer);
        setRating(rating);
    }

    public boolean equals (Object o) {
        if (o instanceof Voetballer) {
            return this.getVoornaam_naam().equals(((Voetballer)o).getVoornaam_naam());
        }
        return false;
    }

    public void setIdVoetballer(Integer idVoetballer){
        if (idVoetballer == null){
            throw new DomainException("idvoetballer mag niet leeg zijn.");
        }
        this.idVoetballer = idVoetballer;
    }

    public void setVoornaam_naam(String voornaam_naam){
        if(voornaam_naam==null || voornaam_naam.isEmpty()){
            throw new DomainException("Voornaam en naam moet ingevuld worden.");
        }
        this.voornaam_naam = voornaam_naam;
    }

    public void setCompetitie(String competitie){
        for (String m : mogelijk){
            if (m.equals(competitie)) {
                this.competitie = competitie;
                isValide = true;
                break;
            }
        }
        if (!isValide) {
            throw new DomainException("De aangeduide competitie bestaad niet.");
        }
    }

    public void setClub(String club){
        if(club==null || club.isEmpty()){
            throw new DomainException("Club moet ingevuld worden.");
        }
        this.club = club;
    }

    public void setNummer(Integer nummer){
        if (nummer == null){
            throw new DomainException("Nummer moet ingevuld worden.");
        } else if (nummer < 0){
            throw new DomainException("Nummer kan niet negatief zijn.");
        } else if (nummer > 100){
            throw new DomainException("Nummer kan niet groter als 100 zijn.");
        }
        this.nummer = nummer;
    }

    public void setRating(Double rating){
        if (rating == null) {
            throw new DomainException("Rating moet ingevuld worden.");
        } else if (rating < 0.0) {
            throw new DomainException("Rating kan niet negatief zijn.");
        } else {
            if (rating > 10) {
                throw new DomainException("Rating kan niet groter als 10 zijn.");
            }
        }
        this.rating = rating;
    }

    public int getIdVoetballer() {
        return idVoetballer;
    }
    public String getVoornaam_naam(){return voornaam_naam;}
    public String getCompetitie(){return competitie;}
    public String getClub(){return club;}
    public int getNummer(){return nummer;}
    public Double getRating(){return rating;}

    @Override
    public String toString() {
        return getVoornaam_naam() + getCompetitie() + getClub() + getNummer() + getRating();
    }
}