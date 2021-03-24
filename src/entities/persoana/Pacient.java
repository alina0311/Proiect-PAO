package entities.persoana;

import entities.Afectiune;
import entities.persoana.angajat.Asistent;

import java.util.List;
import java.util.Objects;

public class Pacient extends User{
    private List<Afectiune> afectiuni; //compozitie
    private static int nrPacienti;

    public Pacient(){
    };

    public Pacient(int idPersoana,  String username, String email, String password, String nume, String prenume, String CNP, String dataNasterii, boolean gen, String adresa, String telefon, List<Afectiune> afectiuni) {
        super(idPersoana, username, email, password, nume, prenume, CNP, dataNasterii, gen, adresa, telefon);
        this.afectiuni = afectiuni;
        nrPacienti++;
    }

    public void afiseaza(){}

    public void afiseazaAfectiuni(){
        System.out.println("Pacientul cu numarul fisei " +  idPersoana +  " are urmatoarele afectiuni: ");

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
        return Objects.equals(afectiuni, pacient.afectiuni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), afectiuni);
    }

    public static int getNrPacienti() {
        return nrPacienti;
    }

    public static void setNrPacienti(int nrPacienti) {
        Pacient.nrPacienti = nrPacienti;
    }

    public List<Afectiune> getAfectiuni() {
        return afectiuni;
    }

    public void setAfectiuni(List<Afectiune> afectiuni) {
        this.afectiuni = afectiuni;
    }


}
