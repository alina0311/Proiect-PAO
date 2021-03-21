package entities.persoana.angajat;

import entities.persoana.Persoana;

import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public abstract class Angajat extends Persoana {
    protected String dataAngajarii;
    protected float salariu;
    protected float oraInceput;
    protected float oraSfarsit;
    private Specializare specializare;

    public Angajat() {
        dataAngajarii = "";
        salariu = 0;
        oraInceput = 0;
        oraSfarsit = 0;
    }

    public Angajat(String nume, String prenume, String CNP, String dataNasterii, boolean gen, String adresa, String telefon, String dataAngajarii, float salariu, Specializare specializare) {
        super(nume, prenume, CNP, dataNasterii, gen, adresa, telefon);
        if (!dataAngajarii.matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")){
            throw new PatternSyntaxException("Data introdusa incorect!", "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$", -1);
        }
        this.dataAngajarii = dataAngajarii;
        this.salariu = salariu;
        this.specializare = specializare;
    }


    @Override
    public String toString() {
        return super.toString() + "Data angajarii: " + dataAngajarii + '\'' +
                "salariu: " + salariu +
                "specializarea: " + specializare + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Angajat)) return false;
        if (!super.equals(o)) return false;
        Angajat angajat = (Angajat) o;
        return Float.compare(angajat.salariu, salariu) == 0 && Float.compare(angajat.oraInceput, oraInceput) == 0 && Float.compare(angajat.oraSfarsit, oraSfarsit) == 0 && Objects.equals(dataAngajarii, angajat.dataAngajarii) && Objects.equals(specializare, angajat.specializare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataAngajarii, salariu, specializare, oraInceput, oraSfarsit);
    }

    public abstract double calculeazaVenit();

    public Specializare getSpecializare() {
        return specializare;
    }

    public void setSpecializare(Specializare specializare) {
        this.specializare = specializare;
    }

    public String getDataAngajarii() {
        return dataAngajarii;
    }

    public void setDataAngajarii(String dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }

    public float getSalariu() {
        return salariu;
    }

    public void setSalariu(float salariu) {
        this.salariu = salariu;
    }
}
