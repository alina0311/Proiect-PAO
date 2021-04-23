package entities.serviciu;

import entities.CabinetMedical;
import entities.Programare;
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
import java.util.Scanner;
import java.util.*;

public class ServiciuProgramare {

    public static ServiciuProgramare prog;
    static CabinetMedical c = CabinetMedical.getCabinet();

    private ServiciuProgramare(){}

    public static ServiciuProgramare getCP(){
        if (prog == null)
            prog = new ServiciuProgramare();
        return prog;
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


    public static void incarcareProgramari(String fisier){
        List<Programare> prog = c.getProgramari();
        Path calea = Paths.get(fisier);
        try (BufferedReader buff = Files.newBufferedReader(calea, StandardCharsets.US_ASCII))
        {
            String rand = buff.readLine();
            rand = buff.readLine();
            while (rand != null)
            {
                int id, mid, aid, pid;
                String[] a = rand.split(",");

                try {
                    id = Integer.parseInt(a[0]);
                    mid = Integer.parseInt(a[3]);
                    aid = Integer.parseInt(a[4]);
                    pid = Integer.parseInt(a[5]);
                }
                catch (NumberFormatException e)
                {
                    id = 0;
                    mid = 0;
                    pid = 0;
                    aid = 0;
                }

                Medic m = new Medic();
                Asistent as = new Asistent();
                Pacient p = new Pacient();


                for(Angajat an : c.getAngajati()){
                    if(an.getIdPersoana() == mid)
                        m = (Medic) an;
                    if(an.getIdPersoana() == aid)
                        as = (Asistent) an;

                }

                for(Pacient pac : c.getPacienti()){
                    if (pac.getIdPersoana() == pid) {
                        p = pac;
                    }

                }

                Programare pr = new Programare(id, a[1], a[2], m, as, p);
                if (prog.contains(pr) == false){
                    prog.add(pr);
                }


                rand = buff.readLine();
            }
        } catch (IOException ioe) { ioe.printStackTrace(); }

        c.setProgramari(prog);
    }


}
