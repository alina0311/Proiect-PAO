package entities;

import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class Afectiune {
    private int afectiuneId;
    private String dataDiagnostic;
    private String numeAfectiune;

    public Afectiune(){
        dataDiagnostic = "";
        numeAfectiune = "";
    };

    public Afectiune(int afectiuneId, String dataDiagnostic, String numeAfectiune) {
        if (!dataDiagnostic.matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$")){
            throw new PatternSyntaxException("Data introdusa incorect!", "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$", -1);
        }
        this.afectiuneId = afectiuneId;
        this.dataDiagnostic = dataDiagnostic;
        this.numeAfectiune = numeAfectiune;
    }

    @Override
    public String toString() {
        return  "Afectiune: " + numeAfectiune + '\n'  +
                "Data diagnostic: " + dataDiagnostic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Afectiune)) return false;
        Afectiune afectiune = (Afectiune) o;
        return Objects.equals(dataDiagnostic, afectiune.dataDiagnostic) && Objects.equals(numeAfectiune, afectiune.numeAfectiune);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataDiagnostic, numeAfectiune);
    }

    public String getDataDiagnostic() {
        return dataDiagnostic;
    }

    public void setDataDiagnostic(String dataDiagnostic) {
        this.dataDiagnostic = dataDiagnostic;
    }

    public String getNumeAfectiune() {
        return numeAfectiune;
    }

    public void setNumeAfectiune(String numeAfectiune) {
        this.numeAfectiune = numeAfectiune;
    }
}
