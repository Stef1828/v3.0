package domain.db;

import domain.model.Voetballer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Locale;

public class VoetballerDB {
    private int sequence;
    private final ArrayList<Voetballer> voetballers = new ArrayList<>();
    private int id;

    public VoetballerDB(){
        id=0;
        this.add(new Voetballer("Lionel Messi", "Ligue 1", "PSG", 30, 10));
        this.add(new Voetballer("Cristiano Ronaldo", "Premier League", "Manchester United", 7, 9));
        this.add(new Voetballer("Kevin de Bruyne", "Premier League", "Manchester City", 17, 9));
        this.add(new Voetballer("Thibaut Courtois", "La Liga", "Real Madrid", 1, 8));
        this.add(new Voetballer("Kylian Mbappé", "Ligue 1", "PSG", 7, 9));
        this.add(new Voetballer("Paul Pogba", "Premier League", "Manchester United", 6, 8));
    }

    public void add(Voetballer voetballer){
        if(voetballer == null){
            throw new IllegalArgumentException("Voer iets in.");
        }
        voetballer.setIdVoetballer(id);
        voetballers.add(voetballer);
        id++;
    }

    public double getGemRating(){
        double gemRating = 0;
        for(Voetballer v: voetballers){
            gemRating += v.getRating();
        }
        gemRating/= voetballers.size();
        BigDecimal bd = new BigDecimal(gemRating).setScale(2, RoundingMode.HALF_UP);
        gemRating = bd.doubleValue();
        return gemRating;
    }

    public ArrayList<Voetballer> zoekVoetballer(String woord){
        ArrayList<Voetballer> gevondenVoetballers = new ArrayList<>();
        String zoekwoord = woord.toLowerCase(Locale.ROOT);
        for (Voetballer voetballer: voetballers) {
            String voetballerNaam = voetballer.getVoornaam_naam().toLowerCase(Locale.ROOT);
            if (voetballerNaam.matches("(.*)"+zoekwoord+"(.*)")) {
                gevondenVoetballers.add(voetballer);
            }
        }
        return gevondenVoetballers;
    }

    public ArrayList getVoetballers(){
        return voetballers;
    }

    public Voetballer getVoetballer(int id){
        for (Voetballer v: voetballers){
            if (v.getIdVoetballer()==id){
                return v;
            }
        }
        return null;
    }

    public void verwijderVoetballer(int id){
        for (Voetballer v: voetballers){
            if(v.getIdVoetballer()==id){
                voetballers.remove(v);
                break;
            }
        }
    }
}
