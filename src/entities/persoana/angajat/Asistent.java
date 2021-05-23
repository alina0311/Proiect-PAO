package entities.persoana.angajat;

import java.util.Objects;

public class Asistent extends Angajat{
    private boolean lucruInTure;
    private static int nrAsistenti;
    private int treapta;

    public Asistent(){
        lucruInTure = false;
    }

    public Asistent(int idPersoana, String username, String email, String password, String nume, String prenume, String CNP, String dataNasterii, boolean gen, String adresa, String telefon, String dataAngajarii, float salariu, Specializare specializare, boolean lucruInTure) {
        super(idPersoana, username, email, password, nume, prenume, CNP, dataNasterii, gen, adresa, telefon, dataAngajarii, salariu, specializare);
        this.lucruInTure = lucruInTure;
        this.treapta = treapta;
        nrAsistenti++;
    }

    public void afiseaza(){}

    @Override
    public String toString() {
        if(lucruInTure == false) {
            return super.toString() + "Asistentul nu lucreaza in ture.";
        }
        return super.toString() + "Asistentul lucreaza in ture.";
    }

    @Override
    public double calculeazaVenit(){
        if (lucruInTure == true) {
            return salariu +  5 * salariu / 100;
        }
        return salariu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Asistent)) {
            return false;
        }
        if (!super.equals(o)){
            return false;
        }
        Asistent asistent = (Asistent) o;
        return lucruInTure == asistent.lucruInTure && treapta == asistent.treapta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lucruInTure, treapta);
    }

    public static int getNrAsistenti() {
        return nrAsistenti;
    }

    public boolean isLucruInTure() {
        return lucruInTure;
    }

    public void setLucruInTure(boolean lucruInTure) {
        this.lucruInTure = lucruInTure;
    }

    public static void setNrAsistenti(int nrAsistenti) {
        Asistent.nrAsistenti = nrAsistenti;
    }
}
