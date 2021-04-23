package entities.serviciu;

import entities.CabinetMedical;
import entities.Programare;
import entities.document.*;
import entities.persoana.Pacient;
import entities.persoana.angajat.Angajat;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void adaugaProgramare(String username){
        System.out.println("\t Introduceti ora programarii: ");
        Scanner scanner = new Scanner(System.in);
        String ora = scanner.nextLine();

        System.out.println("\t Introduceti data programarii: ");
        String data = scanner.nextLine();

        System.out.println("\t Introduceti numele doctorului: ");
        String numeDoctor = scanner.nextLine();

        System.out.println("\t Introduceti numele asistentului: ");
        String numeAsistent = scanner.nextLine();

        Medic m = new Medic();
        Asistent as = new Asistent();
        Pacient p = new Pacient();

        int id = Programare.getNrProg() + 1;
        for(Angajat a : c.getAngajati()){
            if(a.getNume().equals(numeDoctor))
                m = (Medic) a;
            if(a.getNume().equals(numeAsistent))
                as = (Asistent) a;

        }

        for(Pacient pac : c.getPacienti()){
            if (pac.getUsername().equals(username)) {
                p = pac;
            }

        }

        Programare pr = new Programare(id, ora, data, m, as, p);
        c.getProgramari().add(pr);
        Path calea = Paths.get("Programari.csv");
        File file = new File("Programari.csv");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter csvWriter = new FileWriter("Programari.csv", true);

            if (file.length() == 0) {
                csvWriter.append("ID");
                csvWriter.append(",");
                csvWriter.append("Ora");
                csvWriter.append(",");
                csvWriter.append("Data");
                csvWriter.append(",");
                csvWriter.append("Medic");
                csvWriter.append(",");
                csvWriter.append("Asistent");
                csvWriter.append(",");
                csvWriter.append("Pacient");
                csvWriter.append("\n");
            }

            String sId = Integer.toString(id);
            String mId = Integer.toString(m.getIdPersoana());
            String aId = Integer.toString(as.getIdPersoana());
            String pId = Integer.toString(p.getIdPersoana());

            List<String> a = Arrays.asList(sId, ora, data.toString(), mId, aId, pId);
            csvWriter.append(String.join(",", a));
            csvWriter.append("\n");


            csvWriter.flush();
            csvWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println("Programarea a fost adauga cu succes!");

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

            Path calea = Paths.get("AdeverinteConcediu.csv");
            File file = new File("AdeverinteConcediu.csv");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter csvWriter = new FileWriter("AdeverinteConcediu.csv", true);

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

                String idS = Integer.toString(id);
                String mId = Integer.toString(m.getIdPersoana());
                String pId = Integer.toString(p.getIdPersoana());
                String zileS = Integer.toString(zile);

                List<String> a = Arrays.asList(idS, str, mId, pId, zileS, data);
                csvWriter.append(String.join(",", a));
                csvWriter.append("\n");


                csvWriter.flush();
                csvWriter.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }

            System.out.println("Adeverinta de concediu a fost eliberata cu succes!");
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

            Path calea = Paths.get("AdeverinteMedicale.csv");
            File file = new File("AdeverinteMedicale.csv");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter csvWriter = new FileWriter("AdeverinteMedicale.csv", true);

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

                String idS = Integer.toString(id);
                String mId = Integer.toString(m.getIdPersoana());
                String pId = Integer.toString(p.getIdPersoana());
                String aptS = Boolean.toString(apt);

                List<String> a = Arrays.asList(idS, str, mId, pId, scop, aptS);
                csvWriter.append(String.join(",", a));
                csvWriter.append("\n");


                csvWriter.flush();
                csvWriter.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }

            System.out.println("Adeverinta medicala a fost eliberata cu succes!");

        }
        if(opt == 3){
            System.out.println("\t Introduceti scopul: ");
            String scop = scanner.nextLine();

            System.out.println("\t Introduceti data de expirare: ");
            String data = scanner.nextLine();

            System.out.println("\t Introduceti institutia catre care se face trimiterea: ");
            String catre = scanner.nextLine();

            TrimitereMedicala d = new TrimitereMedicala(id, str, m, p, scop, data, catre);
            c.getDocumente().add(d);

            Path calea = Paths.get("TrimiteriMedicale.csv");
            File file = new File("TrimiteriMedicale.csv");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter csvWriter = new FileWriter("TrimiteriMedicale.csv", true);

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

                String idS = Integer.toString(id);
                String mId = Integer.toString(m.getIdPersoana());
                String pId = Integer.toString(p.getIdPersoana());


                List<String> a = Arrays.asList(idS, str, mId, pId, scop, data, catre);
                csvWriter.append(String.join(",", a));
                csvWriter.append("\n");


                csvWriter.flush();
                csvWriter.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }

            System.out.println("Trimiterea medicala a fost eliberata cu succes!");
        }
        if(opt == 4){
            System.out.println("\t Introduceti numarul de medicamente: ");
            int nr = scanner.nextInt();
            String ok2 = scanner.nextLine();

            System.out.println("\t Introduceti pe cate o linie numele medicamentului si de cate ori pe zi trebuie administrat: ");

            TreeMap<String, Integer> r = new TreeMap<String, Integer>();
            String ret;

            while(nr > 0){
                ret = scanner.nextLine();
                String med = ret.split(" ")[0];
                int n = Integer.parseInt(ret.split(" ")[1]);
                r.put(med, n);
                nr--;
            }
            Reteta d = new Reteta(id, str, m, p, r);
            c.getDocumente().add(d);

            Path calea = Paths.get("Retete.csv");
            File file = new File("Retete.csv");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter csvWriter = new FileWriter("Retete.csv", true);

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

                String idS = Integer.toString(id);
                String mId = Integer.toString(m.getIdPersoana());
                String pId = Integer.toString(p.getIdPersoana());

                //mapul e de forma: paracetamol:1>aspirina:4
                String medicam = "";
                for(Map.Entry<String, Integer> pereche : r.entrySet()) {
                    medicam = medicam + pereche.getKey() + ":" + pereche.getValue() + ">";

                }


                List<String> a = Arrays.asList(idS, str, mId, pId, medicam);
                csvWriter.append(String.join(",", a));
                csvWriter.append("\n");


                csvWriter.flush();
                csvWriter.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }

            System.out.println("Reteta a fost eliberata cu succes!");

        }

    }


    public static void incarcareAdeverinteC(String fisier){

        List<Document> doc = c.getDocumente();
        Path calea = Paths.get(fisier);
        try (BufferedReader buff = Files.newBufferedReader(calea, StandardCharsets.US_ASCII))
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
                if (doc.contains(ac) == false){
                    doc.add(ac);
                }


                rand = buff.readLine();
            }
        } catch (IOException ioe) { ioe.printStackTrace(); }

        c.setDocumente(doc);
    }


}
