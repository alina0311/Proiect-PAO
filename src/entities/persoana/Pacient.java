package entities.persoana;

import entities.Afectiune;
import entities.persoana.angajat.Asistent;

import java.util.List;
import java.util.Objects;

public class Pacient extends Persoana{
    private int nrFisa;
    private List<Afectiune> afectiuni; //compozitie
    private static int nrPacienti;

    public Pacient(){
        nrFisa = 0;
    };

    public Pacient(String nume, String prenume, String CNP, String dataNasterii, boolean gen, String adresa, String telefon,  int nrFisa, List<Afectiune> afectiuni) {
        super(nume, prenume, CNP, dataNasterii, gen, adresa, telefon);
        this.afectiuni = afectiuni;
        this.nrFisa = nrFisa;
        nrPacienti++;
    }

    public void afiseaza(){}

    public void afiseazaAfectiuni(){
        System.out.println("Pacientul cu numarul fisei " +  nrFisa +  " are urmatoarele afectiuni: ");

        for (Afectiune a : afectiuni){
            System.out.println(a.toString());
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pacient)) return false;
        if (!super.equals(o)) return false;
        Pacient pacient = (Pacient) o;
        return nrFisa == pacient.nrFisa && Objects.equals(afectiuni, pacient.afectiuni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nrFisa, afectiuni);
    }

    public static int getNrPacienti() {
        return nrPacienti;
    }

    public static void setNrPacienti(int nrPacienti) {
        Pacient.nrPacienti = nrPacienti;
    }

    public int getNrFisa() {
        return nrFisa;
    }

    public void setNrFisa(int nrFisa) {
        this.nrFisa = nrFisa;
    }

    public List<Afectiune> getAfectiuni() {
        return afectiuni;
    }

    public void setAfectiuni(List<Afectiune> afectiuni) {
        this.afectiuni = afectiuni;
    }
}
