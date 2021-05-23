package entities.document;

import entities.persoana.Pacient;
import entities.persoana.angajat.Medic;

import java.util.Objects;

public class AdeverintaMedicala extends Document{
    private String scop;
    private boolean apt;

    public AdeverintaMedicala(){
        scop = "";
        apt = false;
    }

    public AdeverintaMedicala(int docId, String dataEliberarii, Medic medic, Pacient pacient, String scop, boolean apt) {
        super(docId, dataEliberarii, medic, pacient);
        this.scop = scop;
        this.apt = apt;
    }



    @Override
    public void afiseaza() {}


    @Override
    public String toString() {
        if (apt == false){
            return super.toString() + "Adeverinta medicala a fost eliberata pentru a-i servi la " + scop + ". " +
            "Pacientul nu este apt pentru scopul mentionat.\'";
        }
        return super.toString() + "Adeverinta medicala a fost eliberata pentru a-i servi la " + scop + ". " +
        "Pacientul este apt pentru scopul mentionat.\'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdeverintaMedicala)) return false;
        if (!super.equals(o)) return false;
        AdeverintaMedicala that = (AdeverintaMedicala) o;
        return apt == that.apt && Objects.equals(scop, that.scop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), scop, apt);
    }

    public String getScop() {
        return scop;
    }

    public void setScop(String scop) {
        this.scop = scop;
    }

    public boolean isApt() {
        return apt;
    }

    public void setApt(boolean apt) {
        this.apt = apt;
    }


}
