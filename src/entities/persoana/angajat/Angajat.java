package entities.persoana.angajat;

import entities.persoana.User;

import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public abstract class Angajat extends User implements Comparable<User> {
    protected String dataAngajarii;
    protected float salariu;
    private Specializare specializare;

    public Angajat() {
        dataAngajarii = "";
        salariu = 0;
    }

    public Angajat(int idPersoana,  String username, String email, String password, String nume, String prenume, String CNP, String dataNasterii, boolean gen, String adresa, String telefon, String dataAngajarii, float salariu, Specializare specializare) {
        super(idPersoana, username, email, password, nume, prenume, CNP, dataNasterii, gen, adresa, telefon);
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
        return Float.compare(angajat.salariu, salariu) == 0 && Objects.equals(dataAngajarii, angajat.dataAngajarii) && specializare == angajat.specializare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataAngajarii, salariu, specializare);
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
