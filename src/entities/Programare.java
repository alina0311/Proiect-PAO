package entities;

import entities.persoana.Pacient;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;

public class Programare {
    private int idProgramare;
    private String oraProgramare;
    private String dataProgramare;
    private Medic medic;
    private Asistent asistent;
    private Pacient pacient;
    private static int nrProg;

    public Programare(int idProgramare, String oraProgramare, String dataProgramare, Medic medic, Asistent asistent, Pacient pacient) {
        this.idProgramare = idProgramare;
        this.oraProgramare = oraProgramare;
        this.dataProgramare = dataProgramare;
        this.asistent = asistent;
        this.medic = medic;
        this.pacient = pacient;
        nrProg++;
    }

    public int getIdProgramare() {
        return idProgramare;
    }

    public void setIdProgramare(int idProgramare) {
        this.idProgramare = idProgramare;
    }

    public String getOraProgramare() {
        return oraProgramare;
    }

    public void setOraProgramare(String oraProgramare) {
        this.oraProgramare = oraProgramare;
    }

    public String getDataProgramare() {
        return dataProgramare;
    }

    public void setDataProgramare(String dataProgramare) {
        this.dataProgramare = dataProgramare;
    }


    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Asistent getAsistent() {
        return asistent;
    }

    public void setAsistent(Asistent asistent) {
        this.asistent = asistent;
    }

    public static int getNrProg() {
        return nrProg;
    }

    public static void setNrProg(int nrProg) {
        Programare.nrProg = nrProg;
    }

    @Override
    public String toString() {
        return "id Programare:" + idProgramare + '\n' +
                "ora Programare:" + oraProgramare + '\n' +
                "data Programare:" + dataProgramare + '\n';
    }
}
