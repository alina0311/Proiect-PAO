package entities;

import entities.document.Document;
import entities.persoana.Pacient;
import entities.persoana.angajat.Angajat;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CabinetMedical {
    private String numeCabinet;
    private String adresa;
    private String telefon;
    private double oraInceput;
    private double oraSfarsit;
    List<Angajat> angajati = new ArrayList<Angajat>();
    List<Pacient> pacienti = new ArrayList<Pacient>();
    List<Programare> programari = new ArrayList<Programare>();
    List<Document> documente = new ArrayList<Document>();
    public static CabinetMedical cabinet;

    private CabinetMedical() {
        this.numeCabinet = "Antares";
        this.adresa = "Str Ciresilor nr 45";
        this.telefon = "0233456710";
        this.oraInceput = 10.00;
        this.oraSfarsit = 17.30;

    }

    public static CabinetMedical getCabinet(){
        if (cabinet == null)
            cabinet = new CabinetMedical();

        return cabinet;
    }


    public String getNumeCabinet() {
        return numeCabinet;
    }

    public void setNumeCabinet(String numeCabinet) {
        this.numeCabinet = numeCabinet;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public double getOraInceput() {
        return oraInceput;
    }

    public void setOraInceput(double oraInceput) {
        this.oraInceput = oraInceput;
    }

    public double getOraSfarsit() {
        return oraSfarsit;
    }

    public void setOraSfarsit(double oraSfarsit) {
        this.oraSfarsit = oraSfarsit;
    }


    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public List<Angajat> getAngajati() {
        return angajati;
    }

    public void setAngajati(List<Angajat> angajati) {
        this.angajati = angajati;
    }

    public List<Pacient> getPacienti() {
        return pacienti;
    }

    public void setPacienti(List<Pacient> pacienti) {
        this.pacienti = pacienti;
    }

    public List<Programare> getProgramari() {
        return programari;
    }

    public void setProgramari(List<Programare> programari) {
        this.programari = programari;
    }

    public List<Document> getDocumente() {
        return documente;
    }

    public void setDocumente(List<Document> documente) {
        this.documente = documente;
    }

    @Override
    public String toString() {
        return "Cabinet Medical: " + numeCabinet + '\'' +
                "Adresa: " + adresa + '\'' +
                "Program: " + oraInceput + "-" + oraSfarsit;
    }
}
