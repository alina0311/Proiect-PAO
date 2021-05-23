package entities.document;

import entities.persoana.Pacient;
import entities.persoana.angajat.Medic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public int zileValabilitate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();

        Date exp = null;
        try {
            exp = new SimpleDateFormat("dd/MM/yyyy").parse(dataExprValabilitate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = Math.abs(today.getTime() - exp.getTime());
        int diffDays = (int) (diff / (1000l * 60 * 60 * 24));

        return diffDays;

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
        if (this == o){
            return true;
        }
        if (!(o instanceof TrimitereMedicala)) {
            return false;
        }
        if (!super.equals(o)){
            return false;
        }
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

    public String getDataExprValabilitate() {
        return dataExprValabilitate;
    }

    public String getCatreInstutia() {
        return catreInstutia;
    }

}
