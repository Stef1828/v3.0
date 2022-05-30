package domain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logboek {
    private String naam;
    private String tijdstip;

    public Logboek(String naam) {
        setNaam(naam);
        this.tijdstip = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public String getVoornaam_naam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getTijdstip() {
        return tijdstip;
    }
}
