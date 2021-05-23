package entities.document;

import entities.persoana.Pacient;
import entities.persoana.angajat.Medic;

import java.util.Objects;

public class AdeverintaConcediu extends Document{
    private int zileConcediu;
    private String dataInceput;

    public AdeverintaConcediu(){
        zileConcediu = 0;
        dataInceput = "";
    }

    public AdeverintaConcediu(int docId, String dataEliberarii, Medic medic, Pacient pacient, int zileConcediu, String dataInceput) {
        super(docId, dataEliberarii, medic, pacient);
        this.zileConcediu = zileConcediu;
        this.dataInceput = dataInceput;
    }

    @Override
    public void afiseaza() {}

    @Override
    public String toString() {
        return super.toString() + "Adeverinta pentru concediu medical a fost eliberata pentru " + zileConcediu + " zile, incepand cu data de: "
                + dataInceput + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof AdeverintaConcediu)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }
        AdeverintaConcediu that = (AdeverintaConcediu) o;
        return zileConcediu == that.zileConcediu && Objects.equals(dataInceput, that.dataInceput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), zileConcediu, dataInceput);
    }

    public int getZileConcediu() {
        return zileConcediu;
    }

    public void setZileConcediu(int zileConcediu) {
        this.zileConcediu = zileConcediu;
    }

    public String getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(String dataInceput) {
        this.dataInceput = dataInceput;
    }
}
