package entities;

import entities.document.Document;
import entities.persoana.Pacient;
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
    List<Medic> medici = new ArrayList<Medic>();
    List<Asistent> asistenti = new ArrayList<Asistent>();
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

    public void adaugaMedic(Medic m){
        medici.add(m);
    }

    public void adaugaAsistent(Asistent a){
        asistenti.add(a);
    }

    public void stergeMedic(Medic m){
        medici.remove(m);
    }

    public void stergeAsistent(Asistent a){
        medici.remove(a);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CabinetMedical)) return false;
        CabinetMedical that = (CabinetMedical) o;
        return Double.compare(that.oraInceput, oraInceput) == 0 && Double.compare(that.oraSfarsit, oraSfarsit) == 0 && Objects.equals(numeCabinet, that.numeCabinet) && Objects.equals(adresa, that.adresa) && Objects.equals(medici, that.medici) && Objects.equals(asistenti, that.asistenti) && Objects.equals(telefon, that.telefon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeCabinet, adresa, telefon, oraInceput, oraSfarsit, medici, asistenti, pacienti, documente, programari);
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

    public List<Medic> getMedici() {
        return medici;
    }

    public void setMedici(List<Medic> medici) {
        this.medici = medici;
    }

    public List<Asistent> getAsistenti() {
        return asistenti;
    }

    public void setAsistenti(List<Asistent> asistenti) {
        this.asistenti = asistenti;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Cabinet Medical: " + numeCabinet + '\'' +
                "Adresa: " + adresa + '\'' +
                "Program: " + oraInceput + "-" + oraSfarsit;
    }
}
