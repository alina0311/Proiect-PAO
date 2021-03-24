package entities.document;

import entities.persoana.Pacient;
import entities.persoana.angajat.Medic;

import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public abstract class Document{
    protected int docId;
    protected String dataEliberarii;
    protected Medic medic;
    protected Pacient pacient;

    public Document(){
        this.dataEliberarii = "";
    }

    public Document(int docId, String dataEliberarii, Medic medic, Pacient pacient) {
        this.docId = docId;
        this.medic = medic;
        this.pacient = pacient;
        if (!dataEliberarii.matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")){
            throw new PatternSyntaxException("Data introdusa incorect!", "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$", -1);
        }
        this.dataEliberarii = dataEliberarii;
    }

    abstract public void afiseaza();

    @Override
    public String toString() {
        return "Data eliberarii: " + dataEliberarii + '\n' +
                "Documentul a fost eliberat de catre: Doctor " + medic.getNume() + " " + medic.getPrenume() +
                " pentru pacientul " + pacient.getNume() + " " + pacient.getPrenume() + ".\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        Document document = (Document) o;
        return Objects.equals(docId, document.docId) && Objects.equals(dataEliberarii, document.dataEliberarii) && Objects.equals(medic, document.medic) && Objects.equals(pacient, document.pacient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataEliberarii, medic, pacient);
    }

    public String getDataEliberarii() {
        return dataEliberarii;
    }

    public void setDataEliberarii(String dataEliberarii) {
        this.dataEliberarii = dataEliberarii;
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

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

}
