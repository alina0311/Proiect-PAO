package entities.serviciu;

import entities.Afectiune;
import entities.CabinetMedical;
import entities.Programare;
import entities.Serviciu;
import entities.document.*;
import entities.persoana.Pacient;
import entities.persoana.angajat.Angajat;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;
import entities.persoana.angajat.Specializare;

import javax.sound.midi.SysexMessage;
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

public class ServiciuUser {

    public static ServiciuUser s;
    static CabinetMedical c = CabinetMedical.getCabinet();

    private ServiciuUser (){}

    public static ServiciuUser getCP(){
        if (s == null)
            s = new ServiciuUser();
        return s;
    }

    public void adaugaClient(){
        s.incarcarePacienti();
        System.out.println("Indroduceti un username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        System.out.println("Indroduceti adresa de email: ");
        String email = scanner.nextLine();

        System.out.println("Indroduceti o parola: ");
        String password = scanner.nextLine();

        System.out.println("Indroduceti 0 pentru Masculin, 1 pentru Feminin: ");
        int b = scanner.nextInt();
        String newl = scanner.nextLine();

        System.out.println("Indroduceti numele de familie: ");
        String nume = scanner.nextLine();

        System.out.println("Indroduceti prenumele: ");
        String prenume = scanner.nextLine();

        System.out.println("Indroduceti CNP: ");
        String cnp = scanner.nextLine();

        System.out.println("Indroduceti data nasterii: ");
        String data = scanner.nextLine();

        System.out.println("Indroduceti adresa: ");
        String adresa = scanner.nextLine();

        System.out.println("Indroduceti numarul de telefon: ");
        String telefon = scanner.nextLine();



        int id = Pacient.getNrPacienti() + 1;

        boolean gen;
        if(b == 0) gen = false;
        else gen = true;

        Pacient pa = new Pacient(id, username, email, password, nume, prenume, cnp, data, gen, adresa, telefon, null);
        c.getPacienti().add(pa);

        File file = new File("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Pacienti.csv");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter csvWriter = new FileWriter("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Pacienti.csv", true);

            if (file.length() == 0) {
                csvWriter.append("ID");
                csvWriter.append(",");
                csvWriter.append("Username");
                csvWriter.append(",");
                csvWriter.append("Email");
                csvWriter.append(",");
                csvWriter.append("Parola");
                csvWriter.append(",");
                csvWriter.append("Nume");
                csvWriter.append(",");
                csvWriter.append("Prenume");
                csvWriter.append(",");
                csvWriter.append("CNP");
                csvWriter.append(",");
                csvWriter.append("DataNasterii");
                csvWriter.append(",");
                csvWriter.append("Gen");
                csvWriter.append(",");
                csvWriter.append("Adresa");
                csvWriter.append(",");
                csvWriter.append("Telefon");
                csvWriter.append(",");
                csvWriter.append("Afectiuni");
                csvWriter.append("\n");
            }

            String sId = Integer.toString(id);
            String genS = Boolean.toString(gen);
            //1;10/09/2021;Diabet>2;19/03.2020;Raceala
            String afec = "";



            List<String> a = Arrays.asList(sId, username, email, password, nume, prenume, cnp, data, genS, adresa, telefon, "0");
            csvWriter.append(String.join(",", a));
            csvWriter.append("\n");


            csvWriter.flush();
            csvWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }


        System.out.println("Cont creat cu succes!");
        //s.incarcarePacienti();
        //Serviciu.loginClient();

    }

    public void adaugaAngajat(){
        s.incarcareAngajati();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indroduceti 1 pentru medic, 0 pentru asistent: ");
        int opt = scanner.nextInt();
        String u = scanner.nextLine();

        System.out.println("Indroduceti un username: ");
        String username = scanner.nextLine();

        System.out.println("Indroduceti adresa de email: ");
        String email = scanner.nextLine();

        System.out.println("Indroduceti o parola: ");
        String password = scanner.nextLine();

        System.out.println("Indroduceti 0 pentru Masculin, 1 pentru Feminin: ");
        int b = scanner.nextInt();
        String newl = scanner.nextLine();

        System.out.println("Indroduceti numele de familie: ");
        String nume = scanner.nextLine();

        System.out.println("Indroduceti prenumele: ");
        String prenume = scanner.nextLine();

        System.out.println("Indroduceti CNP: ");
        String cnp = scanner.nextLine();

        System.out.println("Indroduceti data nasterii: ");
        String data = scanner.nextLine();

        System.out.println("Indroduceti adresa: ");
        String adresa = scanner.nextLine();

        System.out.println("Indroduceti numarul de telefon: ");
        String telefon = scanner.nextLine();


        int id = Angajat.getNrAngajati() + 1;

        boolean gen;
        if(b == 0) gen = false;
        else gen = true;

        System.out.println("Indroduceti data angajarii: ");
        String dataAngajarii = scanner.nextLine();

        System.out.println("Indroduceti salariul: ");
        float salariu = scanner.nextFloat();

        System.out.println("Indroduceti specializarea: ");
        Specializare value = Specializare.valueOf(scanner.next().toUpperCase());

        if(opt == 1){
            System.out.println("Indroduceti parafa: ");
            int parafa = scanner.nextInt();

            System.out.println("Indroduceti treapta: ");
            int treapta = scanner.nextInt();

            //String thespec = spec;


            Medic m = new Medic(id, username, email, password, nume, prenume, cnp, data, gen, adresa, telefon, dataAngajarii, salariu, value, parafa, treapta);
            c.getAngajati().add(m);

            Path calea = Paths.get("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Medici.csv");
            File file = new File("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Medici.csv");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter csvWriter = new FileWriter("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Medici.csv", true);

                if (file.length() == 0) {
                    csvWriter.append("ID");
                    csvWriter.append(",");
                    csvWriter.append("Username");
                    csvWriter.append(",");
                    csvWriter.append("Email");
                    csvWriter.append(",");
                    csvWriter.append("Parola");
                    csvWriter.append(",");
                    csvWriter.append("Nume");
                    csvWriter.append(",");
                    csvWriter.append("Prenume");
                    csvWriter.append(",");
                    csvWriter.append("CNP");
                    csvWriter.append(",");
                    csvWriter.append("DataNasterii");
                    csvWriter.append(",");
                    csvWriter.append("Gen");
                    csvWriter.append(",");
                    csvWriter.append("Adresa");
                    csvWriter.append(",");
                    csvWriter.append("Telefon");
                    csvWriter.append(",");
                    csvWriter.append("DataAngajarii");
                    csvWriter.append(",");
                    csvWriter.append("Salariu");
                    csvWriter.append(",");
                    csvWriter.append("Specializare");
                    csvWriter.append(",");
                    csvWriter.append("Parafa");
                    csvWriter.append(",");
                    csvWriter.append("Treapta");
                    csvWriter.append("\n");
                }

                String sId = Integer.toString(id);
                String genS = Boolean.toString(gen);
                String sal = Float.toString(salariu);
                String par = Integer.toString(parafa);
                String tre = Integer.toString(treapta);

                List<String> a = Arrays.asList(sId, username, email, password, nume, prenume, cnp, data, genS, adresa, telefon, dataAngajarii, sal, value.toString(), par, tre);
                csvWriter.append(String.join(",", a));
                csvWriter.append("\n");


                csvWriter.flush();
                csvWriter.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }


            System.out.println("Cont creat cu succes!");
            //Serviciu.loginClient();
        }

        if(opt == 0){
            System.out.println("Lucreaza in ture? (1 da, 0 nu): ");
            String t = scanner.nextLine();
            String ture = scanner.nextLine();
            boolean lucruTure = Boolean.parseBoolean(ture);

            Asistent a = new Asistent(id, username, email, password, nume, prenume, cnp, data, gen, adresa, telefon, dataAngajarii, salariu, value, lucruTure);
            c.getAngajati().add(a);

            Path calea = Paths.get("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Asistenti.csv");
            File file = new File("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Asistenti.csv");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter csvWriter = new FileWriter("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Asistenti.csv", true);

                if (file.length() == 0) {
                    csvWriter.append("ID");
                    csvWriter.append(",");
                    csvWriter.append("Username");
                    csvWriter.append(",");
                    csvWriter.append("Email");
                    csvWriter.append(",");
                    csvWriter.append("Parola");
                    csvWriter.append(",");
                    csvWriter.append("Nume");
                    csvWriter.append(",");
                    csvWriter.append("Prenume");
                    csvWriter.append(",");
                    csvWriter.append("CNP");
                    csvWriter.append(",");
                    csvWriter.append("DataNasterii");
                    csvWriter.append(",");
                    csvWriter.append("Gen");
                    csvWriter.append(",");
                    csvWriter.append("Adresa");
                    csvWriter.append(",");
                    csvWriter.append("Telefon");
                    csvWriter.append(",");
                    csvWriter.append("DataAngajarii");
                    csvWriter.append(",");
                    csvWriter.append("Salariu");
                    csvWriter.append(",");
                    csvWriter.append("Specializare");
                    csvWriter.append(",");
                    csvWriter.append("LucruInTure");
                    csvWriter.append("\n");
                }

                String sId = Integer.toString(id);
                String genS = Boolean.toString(gen);
                String sal = Float.toString(salariu);


                List<String> ar = Arrays.asList(sId, username, email, password, nume, prenume, cnp, data, genS, adresa, telefon, dataAngajarii, sal, value.toString(), ture);
                csvWriter.append(String.join(",", ar));
                csvWriter.append("\n");


                csvWriter.flush();
                csvWriter.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }


            System.out.println("Cont creat cu succes!");

            //Serviciu.loginClient();
        }



    }

    public static void incarcareAngajati(){

        List<Angajat> ang = c.getAngajati();
        Path calea = Paths.get("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Medici.csv");
        try (BufferedReader buff = Files.newBufferedReader(calea, StandardCharsets.US_ASCII))
        {
            String rand = buff.readLine();
            rand = buff.readLine();
            while (rand != null)
            {
                int id;
                String[] a = rand.split(","); //pt a lua atributele

                try {
                    id = Integer.parseInt(a[0]);

                }
                catch (NumberFormatException e)
                {
                    id = 0;

                }

                boolean g = Boolean.parseBoolean(a[8]);
                float sal = Float.parseFloat(a[12]);
                Specializare value = Specializare.valueOf(a[13].toUpperCase());

                //System.out.println("meedic");
                int p = Integer.parseInt(a[14]); //parsez parafa in int
                int t = Integer.parseInt(a[15]);

                Medic ac = new Medic(id, a[1], a[2], a[3], a[4], a[5], a[6], a[7], g, a[9], a[10], a[11],  sal, value, p, t);
                if (ang.contains(ac) == false){
                    ang.add(ac);
                }




                rand = buff.readLine();
            }
        } catch (IOException ioe) { ioe.printStackTrace(); }

        c.setAngajati(ang);
//        for(Angajat a: c.getAngajati()){
//            System.out.println(a);
//        }

        calea = Paths.get("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Asistenti.csv");
        try (BufferedReader buff = Files.newBufferedReader(calea, StandardCharsets.US_ASCII))
        {
            String rand = buff.readLine();
            rand = buff.readLine();
            while (rand != null)
            {
                int id;
                String[] a = rand.split(",");

                try {
                    id = Integer.parseInt(a[0]);

                }
                catch (NumberFormatException e)
                {
                    id = 0;

                }


                boolean g = Boolean.parseBoolean(a[8]);
                float sal = Float.parseFloat(a[12]);
                Specializare value = Specializare.valueOf(a[13].toUpperCase());


                boolean t  = Boolean.parseBoolean(a[14]);

                Asistent ac = new Asistent(id, a[1], a[3], a[3], a[4], a[5], a[6], a[7], g, a[9], a[10], a[11], sal, value, t);
                if (ang.contains(ac) == false){
                    ang.add(ac);
                }




                rand = buff.readLine();
            }
        } catch (IOException ioe) { ioe.printStackTrace(); }

    }

    public static void incarcarePacienti(){

        List<Pacient> pac = c.getPacienti();
        Path calea = Paths.get("C:\\Users\\Alina\\IdeaProjects\\proiect\\csv_files\\Pacienti.csv");
        try (BufferedReader buff = Files.newBufferedReader(calea, StandardCharsets.US_ASCII))
        {
            String rand = buff.readLine();
            rand = buff.readLine();
            //System.out.println("daaaand");
            //System.out.println(rand);
            while (rand != null)
            {
                int id;
                String[] a = rand.split(",");
                //System.out.println(a[11]);
                try {
                    id = Integer.parseInt(a[0]);

                }
                catch (NumberFormatException e)
                {
                    id = 0;

                }

                Pacient p = new Pacient();

                for(Pacient pacient : c.getPacienti()){
                    if(pacient.getIdPersoana() == id) {
                        p = (Pacient) pacient;
                    }

                }
                //1;10/09/2021;Diabet>2;19/03.2020;Raceala
                boolean g;
                if(a[8].equals("false")){
                    g = false;
                }
                else{
                    g = true;
                }

                //int p = Integer.parseInt(a[14]);
                if(a[11].equals("0")){
                    //System.out.println("da");

                    Pacient ac = new Pacient(id, a[1], a[3], a[3], a[4], a[5], a[6], a[7], g, a[9], a[10],null);
                    if (pac.contains(ac) == false)
                    {
                        pac.add(ac);
                    }
                }
                else if(a[11].equals("Afectiuni") == false){
                    String[] l = a[11].split(">");
                    List<Afectiune> afec = new ArrayList<Afectiune>();
                    for(String aff : l){ //parcurg lista de iduri ale afectiunilor
                        String[] i = aff.split(";");
                        //System.out.println(i[0]);
                        int idAfec = Integer.parseInt(i[0]);
                        Afectiune af = new Afectiune(idAfec, i[1], i[2]);
                        afec.add(af);

                    }
                    Pacient ac = new Pacient(id, a[1], a[3], a[3], a[4], a[5], a[6], a[7], g, a[9], a[10],afec);
                    if (pac.contains(ac) == false){
                        pac.add(ac);
                    }
                }



                rand = buff.readLine();
            }
        }
        catch (IOException ioe) { ioe.printStackTrace(); }

        c.setPacienti(pac);
//        for(Pacient pp : c.getPacienti()){
//            System.out.println(pp);
//        }

    }


}
