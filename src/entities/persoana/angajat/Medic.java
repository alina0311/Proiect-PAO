package entities.persoana.angajat;

import entities.persoana.Persoana;

import java.util.Objects;
import java.util.regex.PatternSyntaxException;



public class Medic extends Angajat{
    private int parafa;
    private static int nrMedici;
    private static final double sporuri[] = {5, 10, 15, 20, 25};
    private int treapta;


    public Medic(){
    }

    public Medic(String nume, String prenume, String CNP, String dataNasterii, boolean gen, String adresa, String telefon, String dataAngajarii, float salariu, Specializare specializare, int parafa, int treapta) {
        super(nume, prenume, CNP, dataNasterii, gen, adresa, telefon, dataAngajarii, salariu, specializare);
        this.parafa = parafa;
        if (treapta > 5 || treapta < 1){
            throw new PatternSyntaxException("Treapta este introdusa gresit. Aceasta este de la 1 la 5.", "", -1);
        }
        this.treapta = treapta;
        nrMedici++;
    }

    public int calculeazaVechime(){
        return 0;
    }

    public void afiseaza(){}

    @Override
    public String toString() {
        return super.toString() + "Parafa medicului: " + parafa;
    }

    @Override
    public double calculeazaVenit(){
        return salariu + sporuri[treapta] * salariu / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medic)) return false;
        if (!super.equals(o)) return false;
        Medic medic = (Medic) o;
        return parafa == medic.parafa && treapta == medic.treapta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parafa, treapta);
    }

    public static int getNrMedici() {
        return nrMedici;
    }

    public int getParafa() {
        return parafa;
    }

    public void setParafa(int parafa) {
        this.parafa = parafa;
    }

    public float getOraInceput() {
        return oraInceput;
    }

    public void setOraInceput(float oraInceput) {
        this.oraInceput = oraInceput;
    }

    public float getOraSfarsit() {
        return oraSfarsit;
    }

    public void setOraSfarsit(float oraSfarsit) {
        this.oraSfarsit = oraSfarsit;
    }
}
