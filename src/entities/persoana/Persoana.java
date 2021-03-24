package entities.persoana;

import java.text.ParseException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.PatternSyntaxException;
import java.util.Scanner;
import java.lang.Comparable;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public abstract class Persoana implements Comparable<Persoana>{
    protected String nume;
    protected String prenume;
    protected String CNP;
    protected String dataNasterii;
    protected boolean gen;
    protected String adresa;
    protected String telefon;
    protected int idPersoana;

    public Persoana(){
        nume = "";
        prenume = "";
        CNP = "";
        dataNasterii = "";
        adresa = "";

    }

    public Persoana(int idPersoana, String nume, String prenume, String CNP, String dataNasterii, boolean gen, String adresa, String telefon) {

        if (!telefon.matches("^0[0-9]{9}$")){
            throw new PatternSyntaxException("Numar de telefon incorect!", "^0[0-9]{9}$", -1);
        }
        if (!dataNasterii.matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")){
            throw new PatternSyntaxException("Data introdusa incorect!", "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$", -1);
        }
        if (!CNP.matches("^[0-9]{13}$")){
            throw new PatternSyntaxException("CNP gresit!", "\n" +
                    "^[0-9]{13}$", -1);
        }
        this.idPersoana = idPersoana;
        this.nume = nume;
        this.prenume = prenume;
        this.CNP = CNP;
        this.dataNasterii = dataNasterii;
        this.gen = gen;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public int compareTo(Persoana p){
        return this.CNP.compareTo(p.CNP);
    }


    abstract public void afiseaza();


    @Override
    public String toString() {
        if (gen == false){
            return "Nume: " + nume + '\n' +
                    "Prenume: " + prenume + '\n' +
                    "Sex: masculin " + '\n' +
                    "CNP: " + CNP + '\n' +
                    "Data nasterii: " + dataNasterii + '\n' +
                    "Adresa de domiciliu: " + adresa + '\n' ;
        }
        return "Nume: " +  nume + '\n' +
                "Prenume: " + prenume + '\n' +
                "Sex: feminin " + '\n' +
                "CNP: " + CNP + '\n' +
                "Data nasterii: " + dataNasterii + '\n' +
                "Adresa de domiciliu: " + adresa + '\n' ;
    }

    public int calculeazaVarsta(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();


        Date birth = null;
        try {
            birth = new SimpleDateFormat("dd/MM/yyyy").parse(dataNasterii);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long diff = Math.abs(today.getTime() - birth.getTime());

        //long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.DAYS);
        //System.out.println(diffDays);
        int diffYears = (int) (diff / (1000l * 60 * 60 * 24 * 365));

        return diffYears;

    }

    public String getNumeComplet(){
       return nume + " " + prenume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persoana)) return false;
        Persoana persoana = (Persoana) o;
        return gen == persoana.gen && Objects.equals(nume, persoana.nume) && Objects.equals(prenume, persoana.prenume) && Objects.equals(CNP, persoana.CNP) && Objects.equals(dataNasterii, persoana.dataNasterii) && Objects.equals(adresa, persoana.adresa) && Objects.equals(telefon, persoana.telefon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, CNP, dataNasterii, gen, adresa, telefon);
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(String dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public boolean getGen() {
        return gen;
    }

    public void setGen(boolean gen) {
        this.gen = gen;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getIdPersoana() {
        return idPersoana;
    }

    public void setIdPersoana(int idPersoana) {
        this.idPersoana = idPersoana;
    }
}
