package entities.serviciu;

import entities.CabinetMedical;
import entities.Meniu;
import entities.document.*;
import entities.persoana.Pacient;
import entities.persoana.angajat.Angajat;
import entities.persoana.angajat.Medic;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.*;

public class ServiciuDocument {

    public static ServiciuDocument s;
    static CabinetMedical c = CabinetMedical.getCabinet();

    private ServiciuDocument (){

        conexiune();
    }

    public static ServiciuDocument getCP(){
        if (s == null)
            s = new ServiciuDocument ();
        return s;
    }


    private String connectionURL = "jdbc:mysql://db1.c54iovni0dyv.us-east-2.rds.amazonaws.com/db1?verifyServerCertificate=false&useSSL=true";
    private Connection connection = null;
    private static String username = "admin";
    private static String password = "Medicalpassword1";

    public void conexiune() {
        try{
            connection = DriverManager.getConnection(connectionURL, username, password);
            //System.out.println("SUCCES");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            //System.out.println("ESEC");
        }
    }

    public void editareDocument(){

        System.out.println("\t Introdu:");
        System.out.println("\t 1 pentru a edita scopul unei adeverinte medicale");
        System.out.println("\t 2 pentru a edita zilele unei adeverinte de concediu");
        System.out.println("\t 3 pentru a edita data valabilitatii unei trimitere medicala");
        System.out.println("\t 4 pentru a edita medicamentele prescrise pe o reteta.");
        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();

        System.out.println("\t Introdu id-ul documentului pe care doresti sa il editezi:");
        int id = scanner.nextInt();
        String ok1 = scanner.nextLine();

        if (opt == 1) {
            System.out.println("\t Dati noul scop:");
            String scop = scanner.nextLine();
            editareAdeverintaMedDB(id, scop);
        }
        if (opt == 2) {
            System.out.println("\t Dati numarul de zile:");
            int zile = scanner.nextInt();
            editareAdeverintaConDB(id, zile);
        }
        if (opt == 3) {
            System.out.println("\t Dati noua data de valabilitate:");
            String data = scanner.nextLine();
            editareTrimitereDB(id, data);
        }
        if (opt == 4) {
            System.out.println("\t Introduceti numarul de medicamente: ");
            int nr = scanner.nextInt();
            String ok2 = scanner.nextLine();
            System.out.println("\t Introduceti pe cate o linie numele medicamentului si de cate ori pe zi trebuie administrat: ");

            String ret;
            String medicam = "";
            while (nr > 0) {
                ret = scanner.nextLine();
                String med = ret.split(" ")[0];
                int n = Integer.parseInt(ret.split(" ")[1]);
                medicam = medicam + med + ":" + n + ">";
                nr--;
            }

            editareRetetaDB(id, medicam);
        }
        else{
            while(opt != 1 && opt != 2 && opt != 3 && opt != 4)
            {
                System.out.println("Introduceti o optiune valida! (1, 2, 3, 4).");
            }
        }

    }

    public void editareAdeverintaMedDB(int id, String scop){
        try {
            String query = "UPDATE AdeverintaMedicala SET scop = ? WHERE doc_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, scop);
            preparedStmt.setInt(2, id);
            preparedStmt.executeUpdate(); //rulez query
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Adeverinta medicala editata cu succes!");
    }

    public void editareAdeverintaConDB(int id, int zile){
        try {
            String query = "UPDATE AdeverintaConcediu SET zile_concediu = ? WHERE doc_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, zile);
            preparedStmt.setInt(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Adeverinta de concediu editata cu succes!");
    }

    public void editareTrimitereDB(int id, String data){
        try {
            String query = "UPDATE TrimitereMedicala SET data_valabil = ? WHERE doc_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, data);
            preparedStmt.setInt(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Trimitere medicala editata cu succes!");
    }

    public void editareRetetaDB(int id, String medicam){
        try {
            String query = "UPDATE Reteta SET medicamente = ? WHERE doc_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, medicam);
            preparedStmt.setInt(2, id);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Reteta editata cu succes!");
    }

    public void stergeDocument(){

        System.out.println("\t Introdu:");
        System.out.println("\t 1 pentru a sterge o adeverinta medicala");
        System.out.println("\t 2 pentru a sterge o adeverinta de concediu");
        System.out.println("\t 3 pentru a sterge o trimitere medicala");
        System.out.println("\t 4 pentru a sterge o reteta.");
        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();
        System.out.println("\t Introdu id-ul documentului pe care doresti sa il stergi:");
        int id = scanner.nextInt();

        if (opt == 1) {
            stergeAdeverintaMedDB(id);
        }
        if (opt == 2) {
            stergeAdeverintaConDB(id);
        }
        if (opt == 3) {
            stergeTrimitereDB(id);
        }
        if (opt == 4) {
            stergeRetetaDB(id);
        }
        else{
            while(opt != 1 && opt != 2 && opt != 3 && opt != 4){
                System.out.println("Introduceti o optiune valida! (1, 2, 3, 4).");
            }
        }
    }

    public void stergeAdeverintaMedDB(int id){
        try {
            String query = "DELETE FROM AdeverintaMedicala WHERE doc_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Reteta stearsa cu succes!");
    }

    public void stergeAdeverintaConDB(int id){
        try {
            String query = "DELETE FROM AdeverintaConcediu WHERE doc_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Reteta stearsa cu succes!");
    }

    public void stergeTrimitereDB(int id){
        try {
            String query = "DELETE FROM TrimitereMedicala WHERE doc_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Reteta stearsa cu succes!");
    }

    public void stergeRetetaDB(int id){
        try {
            String query = "DELETE FROM Reteta WHERE doc_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Reteta stearsa cu succes!");
    }

    public void afisareDocumenteDB(){
        System.out.println("\t Introdu:");
        System.out.println("\t 1 pentru a afisa toate adeverintele medicale");
        System.out.println("\t 2 pentru a afisa toate adeverintele de concediu");
        System.out.println("\t 3 pentru a afisa toate trimiterile medicale");
        System.out.println("\t 4 pentru a afisa toate retetele");
        System.out.println("\t 5 pentru a afisa toate documentele.");

        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();
        if (opt == 1) {
            afisareAdeverinteMedDB();
        }
        if (opt == 2) {
            afisareAdeverinteConDB();
        }
        if (opt == 3) {
            afisareTrimiteriDB();
        }
        if (opt == 4) {
            afisareReteteDB();
        }
        if (opt == 5) {
            afisareAdeverinteConDB();
            afisareAdeverinteMedDB();
            afisareTrimiteriDB();
            afisareReteteDB();
        }
        else{
            while(opt != 1 && opt != 2 && opt != 3 && opt != 4 && opt != 5){
                System.out.println("Introduceti o optiune valida! (1, 2, 3, 4, 5).");
            }
        }
    }

    public void afisareAdeverinteMedDB(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM AdeverintaMedicala");
            while (rs.next()){
                System.out.println("ID: " + rs.getInt("doc_id") + ", " + "Data eliberarii: " + rs.getString("data_eliberare") + ", " + "ID medic: " + rs.getInt("id_medic") + ", " + "ID Pacient: " + rs.getInt("id_pacient") + ", " + "Apt: " + rs.getInt("apt") + ", " + "Scop: " + rs.getString("scop"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void afisareAdeverinteConDB(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM AdeverintaConcediu");
            while (rs.next()){
                System.out.println("ID: " + rs.getInt("doc_id") + ", " + "Data eliberarii: " + rs.getString("data_eliberare") + ", " + "ID medic: " + rs.getInt("id_medic") + ", " + "ID Pacient: " + rs.getInt("id_pacient") + ", " + "Zile concediu: " + rs.getInt("zile_concediu") + ", " + "Data inceput: " + rs.getString("data_inceput"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void afisareTrimiteriDB(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TrimitereMedicala");
            while (rs.next()){
                System.out.println("ID: " + rs.getInt("doc_id") + ", " + "Data eliberarii: " + rs.getString("data_eliberare") + ", " + "ID medic: " + rs.getInt("id_medic") + ", " + "ID Pacient: " + rs.getInt("id_pacient") + ", " + "Scop: " + rs.getString("scop") + ", " + "Data valabilitate: " + rs.getString("data_valabil") + ", " + "Catre: " + rs.getString("catre"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void afisareReteteDB(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Reteta");
            while (rs.next()){
                String medicam = rs.getString("medicamente");
                String m = medicam.replace(">", " ");
                System.out.println("ID: " + rs.getInt("doc_id") + ", " + "Data eliberarii: " + rs.getString("data_eliberare") + ", " + "ID medic: " + rs.getInt("id_medic") + ", " + "ID Pacient: " + rs.getInt("id_pacient") + ", Medicamente:" + m);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void adaugaAdeverintaConDB(AdeverintaConcediu a){
        int zile = a.getZileConcediu();
        String data = a.getDataInceput();
        String data_elib = a.getDataEliberarii();
        int mid = a.getMedic().getIdPersoana();
        int pid = a.getPacient().getIdPersoana();
        int docID = 0;

        try{
            PreparedStatement pstmt = connection.prepareStatement("SELECT MAX(doc_id) + 1 FROM AdeverintaConcediu");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                docID = rs.getInt(1);
            }
            if (docID == 0){
                docID += 1;
            }
            String query = "INSERT INTO AdeverintaConcediu (doc_id, data_eliberare, id_medic, id_pacient, zile_concediu, data_inceput) " + "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, docID);
            stmt.setString(2, data_elib);
            stmt.setInt(3, mid);
            stmt.setInt(4, pid);
            stmt.setInt(5, zile);
            stmt.setString(6, data);
            stmt.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //System.out.println("Adeverinta de concediu eliberata cu succes!");
    }

    public void adaugaTrimitereDB(TrimitereMedicala a){
        String scop = a.getScop();
        String data_exp = a.getDataEliberarii();
        String catre = a.getCatreInstutia();
        String data_elib = a.getDataEliberarii();
        int mid = a.getMedic().getIdPersoana();
        int pid = a.getPacient().getIdPersoana();
        int docID = 0;

        try{
            PreparedStatement pstmt = connection.prepareStatement("SELECT MAX(doc_id) + 1 FROM TrimitereMedicala");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                docID = rs.getInt(1);
            }
            if (docID == 0){
                docID += 1;
            }
            String query = "INSERT INTO TrimitereMedicala (doc_id, data_eliberare, id_medic, id_pacient, scop, data_valabil, catre) " + "values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, docID);
            stmt.setString(2, data_elib);
            stmt.setInt(3, mid);
            stmt.setInt(4, pid);
            stmt.setString(5, scop);
            stmt.setString(6, data_exp);
            stmt.setString(7, catre);
            stmt.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //System.out.println("Trimiterea medicala eliberata cu succes!");
    }

    public void adaugaRetetaDB(Reteta a){
        String data_elib = a.getDataEliberarii();
        int mid = a.getMedic().getIdPersoana();
        int pid = a.getPacient().getIdPersoana();
        int docID = 0;

        try{
            PreparedStatement pstmt = connection.prepareStatement("SELECT MAX(doc_id) + 1 FROM Reteta");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                docID = rs.getInt(1);
            }
            if (docID == 0){
                docID += 1;
            }

            String medicam = "";
            TreeMap<String, Integer> med = a.getMedicamente();
            for (Map.Entry<String, Integer> pereche : med.entrySet()) {
                medicam = medicam + pereche.getKey() + ":" + pereche.getValue() + ">";

            }
            String query = "INSERT INTO Reteta (doc_id, data_eliberare, id_medic, id_pacient, medicamente) " + "values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, docID);
            stmt.setString(2, data_elib);
            stmt.setInt(3, mid);
            stmt.setInt(4, pid);
            stmt.setString(5, medicam);
            stmt.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //System.out.println("Reteta eliberata cu succes!");
    }

    public void adaugaAdeverintaMedDB(AdeverintaMedicala a){
        boolean apt = a.isApt();
        String scop = a.getScop();
        String data_elib = a.getDataEliberarii();
        int mid = a.getMedic().getIdPersoana();
        int pid = a.getPacient().getIdPersoana();
        int docID = 0;

        try{
            PreparedStatement pstmt = connection.prepareStatement("SELECT MAX(doc_id) + 1 FROM AdeverintaMedicala");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                docID = rs.getInt(1);
            }
            if (docID == 0){
                docID += 1;
            }
            String query = "INSERT INTO AdeverintaMedicala (doc_id, data_eliberare, id_medic, id_pacient, apt, scop) " + "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, docID);
            stmt.setString(2, data_elib);
            stmt.setInt(3, mid);
            stmt.setInt(4, pid);
            int apt_int;
            if (apt == true){
                apt_int = 1;
            }
            else{
                apt_int = 0;
            }
            stmt.setInt(5, apt_int);
            stmt.setString(6, scop);
            stmt.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //System.out.println("Adeverinta medicala eliberata cu succes!");
    }

    public static <O> void scrieCSV(O obiect, String path) throws IOException {
        switch (obiect.getClass().getSimpleName()) {
            case "AdeverintaConcediu" -> {
                AdeverintaConcediu ad = (AdeverintaConcediu) obiect;
                File file = new File("src/csv_files/AdeverinteConcediu.csv");
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter csvWriter = new FileWriter("src/csv_files/AdeverinteConcediu.csv", true);
                    if (file.length() == 0) {
                        csvWriter.append("ID,DataEliberarii,Medic,Pacient,ZileConcediu,DataInceput \n");
                    }
                    String idS = Integer.toString(ad.getDocId());
                    String mId = Integer.toString(ad.getMedic().getIdPersoana());
                    String pId = Integer.toString(ad.getPacient().getIdPersoana());
                    String zileS = Integer.toString(ad.getZileConcediu());

                    List<String> a = Arrays.asList(idS, ad.getDataEliberarii(), mId, pId, zileS, zileS, ad.getDataInceput());
                    csvWriter.append(String.join(",", a));
                    csvWriter.append("\n");
                    csvWriter.flush();
                    csvWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Adeverinta de concediu a fost eliberata cu succes!");
            }

            case "AdeverintaMedicala" -> {
                AdeverintaMedicala am = (AdeverintaMedicala) obiect;
                File file = new File("src/csv_files/AdeverinteMedicale.csv");
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter csvWriter = new FileWriter("src/csv_files/AdeverinteMedicale.csv", true);
                    if (file.length() == 0) {
                        csvWriter.append("ID,DataEliberarii,Medic,Pacient,Scop,Apt \n");
                    }

                    String idS = Integer.toString(am.getDocId());
                    String mId = Integer.toString(am.getMedic().getIdPersoana());
                    String pId = Integer.toString(am.getPacient().getIdPersoana());
                    String aptS = Boolean.toString(am.isApt());

                    List<String> a = Arrays.asList(idS, am.getDataEliberarii(), mId, pId, am.getScop(), aptS);
                    csvWriter.append(String.join(",", a));
                    csvWriter.append("\n");
                    csvWriter.flush();
                    csvWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Adeverinta medicala a fost eliberata cu succes!");
            }
            case "TrimitereMedicala" -> {
                TrimitereMedicala t = (TrimitereMedicala) obiect;
                File file = new File("src/csv_files/TrimiteriMedicale.csv");
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter csvWriter = new FileWriter("src/csv_files/TrimiteriMedicale.csv", true);
                    if (file.length() == 0) {
                        csvWriter.append("ID,DataEliberarii,Medic,Pacient,Scop,DataValabilitate,Institutie \n");
                    }
                    String idS = Integer.toString(t.getDocId());
                    String mId = Integer.toString(t.getMedic().getIdPersoana());
                    String pId = Integer.toString(t.getPacient().getIdPersoana());

                    List<String> a = Arrays.asList(idS, t.getDataEliberarii(), mId, pId, t.getScop(), t.getDataExprValabilitate(), t.getCatreInstutia());
                    csvWriter.append(String.join(",", a));
                    csvWriter.append("\n");
                    csvWriter.flush();
                    csvWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Trimiterea medicala a fost eliberata cu succes!");
            }
            case "Reteta" -> {
                Reteta r = (Reteta) obiect;

                File file = new File("src/csv_files/Retete.csv");
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter csvWriter = new FileWriter("src/csv_files/Retete.csv", true);
                    if (file.length() == 0) {
                        csvWriter.append("ID,DataEliberarii,Medic,Pacient,Medicamente \n");
                    }

                    String idS = Integer.toString(r.getDocId());
                    String mId = Integer.toString(r.getMedic().getIdPersoana());
                    String pId = Integer.toString(r.getPacient().getIdPersoana());

                    //mapul e de forma: paracetamol:1>aspirina:4
                    String medicam = "";
                    TreeMap<String, Integer> med = r.getMedicamente();
                    for (Map.Entry<String, Integer> pereche : med.entrySet()) {
                        medicam = medicam + pereche.getKey() + ":" + pereche.getValue() + ">";

                    }

                    List<String> a = Arrays.asList(idS, r.getDataEliberarii(), mId, pId, medicam);
                    csvWriter.append(String.join(",", a));
                    csvWriter.append("\n");
                    csvWriter.flush();
                    csvWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Reteta a fost eliberata cu succes!");
                Meniu.getAudit().actiune("eliberareDocument");
            }

        }

    }

    public void elibereazaDocument(){
        System.out.println("\t Introdu:");
        System.out.println("\t 1 pentru a elibera adeverinta de concediu");
        System.out.println("\t 2 pentru a elibera adeverinta medicala");
        System.out.println("\t 3 pentru a elibera trimitere medicala");
        System.out.println("\t 4 pentru a elibera o reteta");

        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();
        String ok = scanner.nextLine();
        int id = Document.getNrDocumente() + 1;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        String str = dateFormat.format(today);
        System.out.println("\t Introduceti numele medicului: ");
        String nume = scanner.nextLine();
        System.out.println("\t Introduceti prenumele medicului: ");
        String prenume = scanner.nextLine();
        System.out.println("\t Introduceti cnp-ul pacientului: ");
        String cnp = scanner.nextLine();

        Pacient p = new Pacient();
        Medic m = new Medic();

        for(Angajat a : c.getAngajati()){
            if(a.getNume().equals(nume) && a.getPrenume().equals(prenume))
                m = (Medic) a;
        }

        for(Pacient a : c.getPacienti()){
            if(a.getCNP().equals(cnp))
                p = a;
        }

        if(opt == 1){
            System.out.println("\t Introduceti zilele de concediu: ");
            int zile = scanner.nextInt();
            String ok1 = scanner.nextLine();

            System.out.println("\t Introduceti data de inceput: ");
            String data = scanner.nextLine();
            AdeverintaConcediu d = new AdeverintaConcediu(id, str, m, p, zile, data);
            c.getDocumente().add(d);
            try {
                scrieCSV(d, "src/csv_files/AdeverinteConcediu.csv");
                adaugaAdeverintaConDB(d);
            }
            catch (IOException ioException) { ioException.printStackTrace(); }

        }
        if(opt == 2){
            System.out.println("\t Introduceti scopul: ");
            String scop = scanner.nextLine();

            System.out.println("\t Este pacientul apt? Scrieti 1 daca da, sau 0 daca nu. ");
            int aptin = scanner.nextInt();
            boolean apt;
            if(aptin == 0) apt = false;
            else apt = true;

            AdeverintaMedicala d = new AdeverintaMedicala(id, str, m, p, scop, apt);
            c.getDocumente().add(d);
            try {
                scrieCSV(d, "src/csv_files/AdeverinteMedicale.csv");
                adaugaAdeverintaMedDB(d);
            }
            catch (IOException ioException) { ioException.printStackTrace(); }
        }
        if(opt == 3) {
            System.out.println("\t Introduceti scopul: ");
            String scop = scanner.nextLine();

            System.out.println("\t Introduceti data de expirare: ");
            String data = scanner.nextLine();

            System.out.println("\t Introduceti institutia catre care se face trimiterea: ");
            String catre = scanner.nextLine();

            TrimitereMedicala d = new TrimitereMedicala(id, str, m, p, scop, data, catre);
            c.getDocumente().add(d);

            try {
                scrieCSV(d, "src/csv_files/TrimiteriMedicale.csv");
                adaugaTrimitereDB(d);
            }
            catch (IOException ioException) { ioException.printStackTrace(); }
        }
        if(opt == 4) {
            System.out.println("\t Introduceti numarul de medicamente: ");
            int nr = scanner.nextInt();
            String ok2 = scanner.nextLine();

            System.out.println("\t Introduceti pe cate o linie numele medicamentului si de cate ori pe zi trebuie administrat: ");

            TreeMap<String, Integer> r = new TreeMap<String, Integer>();
            String ret;

            while (nr > 0) {
                ret = scanner.nextLine();
                String med = ret.split(" ")[0];
                int n = Integer.parseInt(ret.split(" ")[1]);
                r.put(med, n);
                nr--;
            }
            Reteta d = new Reteta(id, str, m, p, r);
            c.getDocumente().add(d);
            try {
                scrieCSV(d, "src/csv_files/Retete.csv");
                adaugaRetetaDB(d);
            }
            catch (IOException ioException) { ioException.printStackTrace(); }

        }
    }

    public static <T> ArrayList<T> incarcareDocumente(String option, String calea) throws IOException
    {
        ArrayList<T> generaldocs = new ArrayList<T>();
        Path path = Paths.get(calea);
        switch (option.toUpperCase()) {
            case "ADEVERINTA CONCEDIU" -> {
                try (BufferedReader buff = Files.newBufferedReader(path, StandardCharsets.US_ASCII))
                {
                    String rand = buff.readLine();
                    rand = buff.readLine();
                    while (rand != null)
                    {
                        int id, mid, pid;
                        String[] a = rand.split(",");

                        try {
                            id = Integer.parseInt(a[0]);
                            mid = Integer.parseInt(a[2]);
                            pid = Integer.parseInt(a[3]);

                        }
                        catch (NumberFormatException e)
                        {
                            id = 0;
                            mid = 0;
                            pid = 0;

                        }
                        Medic m = new Medic();
                        Pacient p = new Pacient();
                        for(Angajat an : c.getAngajati()){
                            if(an.getIdPersoana() == mid)
                                m = (Medic) an;
                        }

                        for(Pacient pac : c.getPacienti()){
                            if (pac.getIdPersoana() == pid) {
                                p = pac;
                            }

                        }
                        int zile = Integer.parseInt(a[4]);
                        AdeverintaConcediu ac = new AdeverintaConcediu(id, a[1], m, p, zile, a[5]);
                        generaldocs.add((T) ac);

                        rand = buff.readLine();
                    }
                } catch (IOException ioe) { ioe.printStackTrace(); }
            }
            case "ADEVERINTA MEDICALA" -> {
                try (BufferedReader buff = Files.newBufferedReader(path, StandardCharsets.US_ASCII))
                {
                    String rand = buff.readLine();
                    rand = buff.readLine();
                    while (rand != null)
                    {
                        int id, mid, pid;
                        String[] a = rand.split(",");

                        try {
                            id = Integer.parseInt(a[0]);
                            mid = Integer.parseInt(a[2]);
                            pid = Integer.parseInt(a[3]);

                        }
                        catch (NumberFormatException e)
                        {
                            id = 0;
                            mid = 0;
                            pid = 0;

                        }

                        Medic m = new Medic();
                        Pacient p = new Pacient();

                        for(Angajat an : c.getAngajati()){
                            if(an.getIdPersoana() == mid)
                                m = (Medic) an;
                        }

                        for(Pacient pac : c.getPacienti()){
                            if (pac.getIdPersoana() == pid) {
                                p = pac;
                            }

                        }
                        boolean apt = Boolean.parseBoolean(a[5]);
                        AdeverintaMedicala ac = new AdeverintaMedicala(id, a[1], m, p, a[4], apt);
                        generaldocs.add((T) ac);

                        rand = buff.readLine();
                    }
                } catch (IOException ioe) { ioe.printStackTrace(); }
            }

            case "RETETA" -> {
                try (BufferedReader buff = Files.newBufferedReader(path, StandardCharsets.US_ASCII))
                {
                    String rand = buff.readLine();
                    rand = buff.readLine();
                    while (rand != null)
                    {
                        int id, mid, pid;
                        String[] a = rand.split(",");

                        try {
                            id = Integer.parseInt(a[0]);
                            mid = Integer.parseInt(a[2]);
                            pid = Integer.parseInt(a[3]);

                        }
                        catch (NumberFormatException e)
                        {
                            id = 0;
                            mid = 0;
                            pid = 0;

                        }

                        Medic m = new Medic();
                        Pacient p = new Pacient();

                        for(Angajat an : c.getAngajati()){
                            if(an.getIdPersoana() == mid)
                                m = (Medic) an;
                        }
                        for(Pacient pac : c.getPacienti()){
                            if (pac.getIdPersoana() == pid) {
                                p = pac;
                            }

                        }
                        TreeMap<String, Integer> med = new TreeMap<String, Integer>();

                        String s = a[4]; //ultimul string
                        String[] perechi = s.split(">"); //splituiesc dupa > pt a lua perechile

                        for (String pereche : perechi) {
                            String[] pr = pereche.split(":"); //acum pt a lua fiecare string si integer
                            int nr = Integer.parseInt(pr[1]); //parsez nr pt medicamente
                            med.put(pr[0], nr);
                        }
                        Reteta ac = new Reteta(id, a[1], m, p, med);
                        generaldocs.add((T) ac);

                        rand = buff.readLine();
                    }
                } catch (IOException ioe) { ioe.printStackTrace(); }
            }

            case "TRIMITERE MEDICALA" ->{
                try (BufferedReader buff = Files.newBufferedReader(path, StandardCharsets.US_ASCII))
                {
                    String rand = buff.readLine();
                    rand = buff.readLine();
                    while (rand != null)
                    {
                        int id, mid, pid;
                        String[] a = rand.split(",");

                        try {
                            id = Integer.parseInt(a[0]);
                            mid = Integer.parseInt(a[2]);
                            pid = Integer.parseInt(a[3]);

                        }
                        catch (NumberFormatException e)
                        {
                            id = 0;
                            mid = 0;
                            pid = 0;

                        }

                        Medic m = new Medic();
                        Pacient p = new Pacient();

                        for(Angajat an : c.getAngajati()){
                            if(an.getIdPersoana() == mid)
                                m = (Medic) an;
                        }
                        for(Pacient pac : c.getPacienti()){
                            if (pac.getIdPersoana() == pid) {
                                p = pac;
                            }

                        }

                        TrimitereMedicala ac = new TrimitereMedicala (id, a[1], m, p, a[4], a[5], a[6]);
                        generaldocs.add((T) ac);

                        rand = buff.readLine();
                    }
                } catch (IOException ioe) { ioe.printStackTrace(); }
            }
        }
        return generaldocs;

    }


}
