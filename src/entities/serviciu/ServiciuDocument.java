package entities.serviciu;

import entities.CabinetMedical;
import entities.Programare;
import entities.Serviciu;
import entities.document.*;
import entities.persoana.Pacient;
import entities.persoana.angajat.Angajat;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

public class ServiciuDocument {

    public static ServiciuDocument s;
    static CabinetMedical c = CabinetMedical.getCabinet();

    private ServiciuDocument (){}

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
            System.out.println("SUCCES");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("FAILURE");
        }
    }

    public void afisareDocumenteDB(){
        conexiune();
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
                        csvWriter.append("ID");
                        csvWriter.append(",");
                        csvWriter.append("DataEliberarii");
                        csvWriter.append(",");
                        csvWriter.append("Medic");
                        csvWriter.append(",");
                        csvWriter.append("Pacient");
                        csvWriter.append(",");
                        csvWriter.append("ZileConcediu");
                        csvWriter.append(",");
                        csvWriter.append("DataInceput");
                        csvWriter.append("\n");
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
                        csvWriter.append("ID");
                        csvWriter.append(",");
                        csvWriter.append("DataEliberarii");
                        csvWriter.append(",");
                        csvWriter.append("Medic");
                        csvWriter.append(",");
                        csvWriter.append("Pacient");
                        csvWriter.append(",");
                        csvWriter.append("Scop");
                        csvWriter.append(",");
                        csvWriter.append("Apt");
                        csvWriter.append("\n");
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
                        csvWriter.append("ID");
                        csvWriter.append(",");
                        csvWriter.append("DataEliberarii");
                        csvWriter.append(",");
                        csvWriter.append("Medic");
                        csvWriter.append(",");
                        csvWriter.append("Pacient");
                        csvWriter.append(",");
                        csvWriter.append("Scop");
                        csvWriter.append(",");
                        csvWriter.append("DataValabilitate");
                        csvWriter.append(",");
                        csvWriter.append("Institutie");
                        csvWriter.append("\n");
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
                        csvWriter.append("ID");
                        csvWriter.append(",");
                        csvWriter.append("DataEliberarii");
                        csvWriter.append(",");
                        csvWriter.append("Medic");
                        csvWriter.append(",");
                        csvWriter.append("Pacient");
                        csvWriter.append(",");
                        csvWriter.append("Medicamente");
                        csvWriter.append("\n");
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

                Serviciu.getAudit().actiune("eliberareDocument");
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
            } catch (IOException ioException) { ioException.printStackTrace(); }


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
            } catch (IOException ioException) { ioException.printStackTrace(); }



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
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

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
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }

    public static <T> ArrayList<T> incarcareDocumente(String option, String calea) throws IOException
    {

        ArrayList<T> generaldocs = new ArrayList<T>();
        Path path = Paths.get(calea);
        //List<Document> doc = c.getDocumente();
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
//                        if (doc.contains(ac) == false){
//                            doc.add(ac);
//                        }
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
//                        if (doc.contains(ac) == false){
//                            doc.add(ac);
//                        }
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
//                        if (doc.contains(ac) == false){
//                            doc.add(ac);
//                        }
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
