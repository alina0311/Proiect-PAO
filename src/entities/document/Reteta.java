package entities.document;

import entities.persoana.Pacient;
import entities.persoana.angajat.Medic;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Reteta extends Document{
    private TreeMap<String, Integer> medicamente;

    public Reteta(){

    }


    public Reteta(int docId, String dataEliberarii, Medic medic, Pacient pacient, TreeMap<String, Integer> medicamente) {
        super(docId, dataEliberarii, medic, pacient);
        this.medicamente = medicamente;
    }

    @Override
    public void afiseaza() {}


    @Override
    public String toString() {
        return super.toString() + "Reteta: \n" +
                "Medicamente: \n" + medicamente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reteta)) return false;
        if (!super.equals(o)) return false;
        Reteta reteta = (Reteta) o;
        return Objects.equals(medicamente, reteta.medicamente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), medicamente);
    }

    public TreeMap<String, Integer> getMedicamente() {
        return medicamente;
    }

    public void setMedicamente(TreeMap<String, Integer> medicamente) {
        this.medicamente = medicamente;
    }
}
