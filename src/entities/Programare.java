package entities;

import entities.persoana.Pacient;
import entities.persoana.angajat.Medic;

public class Programare {
    private int idProgramare;
    private String oraProgramare;
    private String dataProgramare;
    private CabinetMedical cabinet;
    private Medic medic;
    private Pacient pacient;

    public Programare(int idProgramare, String oraProgramare, String dataProgramare, CabinetMedical cabinet, Medic medic, Pacient pacient) {
        this.idProgramare = idProgramare;
        this.oraProgramare = oraProgramare;
        this.dataProgramare = dataProgramare;
        this.cabinet = cabinet;
        this.medic = medic;
        this.pacient = pacient;
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

    public CabinetMedical getCabinet() {
        return cabinet;
    }

    public void setCabinet(CabinetMedical cabinet) {
        this.cabinet = cabinet;
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
}
