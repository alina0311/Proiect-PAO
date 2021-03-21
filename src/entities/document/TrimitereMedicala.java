package entities.document;

import entities.persoana.Pacient;
import entities.persoana.angajat.Medic;

import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class TrimitereMedicala extends Document{
    private String scop;
    private String dataExprValabilitate;
    private String catreInstutia;

    public TrimitereMedicala(){
        scop = "";
        dataExprValabilitate = "";
        catreInstutia = "";
    }

    public TrimitereMedicala(int docId, String dataEliberarii, Medic medic, Pacient pacient, String scop, String dataExprValabilitate, String catreInstutia) {
        super(docId, dataEliberarii, medic, pacient);

        if (!dataExprValabilitate.matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")){
            throw new PatternSyntaxException("Data introdusa incorect!", "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$", -1);
        }
        this.scop = scop;
        this.catreInstutia = catreInstutia;
        this.dataExprValabilitate = dataExprValabilitate;

    }

    @Override
    public void afiseaza() {}


    @Override
    public String toString() {
        return super.toString() + "Trimitere Medicala " +
                "pentru a-i servi la : '" + scop + '\'' +
                "valabila pana la data de: " + dataExprValabilitate + '\'' +
                "catre: " + catreInstutia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrimitereMedicala)) return false;
        if (!super.equals(o)) return false;
        TrimitereMedicala that = (TrimitereMedicala) o;
        return Objects.equals(scop, that.scop) && Objects.equals(dataExprValabilitate, that.dataExprValabilitate) && Objects.equals(catreInstutia, that.catreInstutia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), scop, dataExprValabilitate, catreInstutia);
    }

    public String getScop() {
        return scop;
    }

    public void setScop(String scop) {
        this.scop = scop;
    }

    public String getDataExprValabilitate() {
        return dataExprValabilitate;
    }

    public void setDataExprValabilitate(String dataExprValabilitate) {
        this.dataExprValabilitate = dataExprValabilitate;
    }

    public String getCatreInstutia() {
        return catreInstutia;
    }

    public void setCatreInstutia(String catreInstutia) {
        this.catreInstutia = catreInstutia;
    }
}
