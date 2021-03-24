package entities;

import entities.document.AdeverintaConcediu;
import entities.document.AdeverintaMedicala;
import entities.document.Document;
import entities.document.TrimitereMedicala;
import entities.persoana.Pacient;
import entities.persoana.angajat.Angajat;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;
import entities.persoana.User;

import javax.print.Doc;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Serviciu {
    CabinetMedical c = CabinetMedical.getCabinet();

    public Serviciu(){

    }

    public void afisareLogin(){
        System.out.println("\t\t ------------- LOGIN -------------");
        System.out.println("\t\t Alegeti cu ce cont va conectati:");
        System.out.println("\t\t 1. Angajat.");
        System.out.println("\t\t 2. Client.");
        System.out.println("\t\t 3. Admin.");
        System.out.println("\t\t 4. Nu am cont. Doresc sa ma inregistrez.");

        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();

        if(opt == 1){
            loginAngajat();
        }
        if(opt == 2){
            loginClient();
        }
        if(opt == 3){
            loginAdmin();
        }

        if(opt == 4){
            adaugaClient();
        }

    }

    public void adaugaPacient(Pacient p){
        c.pacienti.add(p);
    }

    public void adaugaAngajat(Angajat a){
        c.angajati.add(a);
    }
    public void adaugaProg(Programare p){
        c.programari.add(p);
    }

    public void adaugaClient(){
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
        c.pacienti.add(pa);
        for(Pacient a : c.pacienti){
            System.out.println(a.toString());
        }

        loginClient();

    }

    public void loginAngajat(){
        int ok = 0;
        while(ok == 0){
            System.out.println("\t Introdu username-ul: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();

            System.out.println("\t Introdu parola: ");
            String parola = scanner.nextLine();

            Iterator itr = c.angajati.iterator();
            while (itr.hasNext()) {
                Medic x = (Medic)itr.next();
                if (x.getUsername() == username && x.getPassword() == parola){
                    ok = 1;
                }
            }


            if(ok == 1){
                afisareMeniuAngajat();
                break;
            }
            else{
                System.out.println("Username sau parola gresita!!!");
            }
        }

    }

    public void loginClient(){
        int ok = 0;
        while(ok == 0){

            System.out.println("\t Introdu username-ul: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();

            System.out.println("\t Introdu parola: ");
            String parola = scanner.nextLine();

            Iterator itr = c.pacienti.iterator();
            while (itr.hasNext()) {
                Pacient x = (Pacient) itr.next();
                if (x.getUsername() == username && x.getPassword() == parola){
                    ok = 1;
                }
            }

            if(ok == 1){
                afisareMeniuClient(username);
                break;
            }
            else{
                System.out.println("\tUsername sau parola gresita!!!");
            }
        }
    }

    public void loginAdmin(){
        int ok = 0;
        while(ok == 0){

            System.out.println("\t Introdu username-ul: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();

            System.out.println("\t Introdu parola: ");
            String parola = scanner.nextLine();

            if(username == "admin" && parola == "admin1234"){
                ok = 1;
            }

            if(ok == 1){
                afisareServicii();
                break;
            }
            else{
                System.out.println("Username sau parola gresita!!!");
            }
        }
    }

    public void afisareMeniuAngajat(){
        int opt = 0;
        while(opt != 9) {
            System.out.println("\n\t-------------------------------------------\n");
            System.out.println("\t\t MENIU:\n");
            System.out.println("\t Alegeti dintre urmatoarele optiuni:");
            System.out.println("\t 1. Afisare angajati.");
            System.out.println("\t 2. Afisare pacienti.");
            System.out.println("\t 3. Afisare programari.");
            System.out.println("\t 4. Afiseaza documente.");
            System.out.println("\t 5. Obtine valabilitate trimitere medicala.");
            System.out.println("\t 6. Sterge programare.");
            System.out.println("\t 7. Elibereaza document.");
            System.out.println("\t 8. Sterge document.");
            System.out.println("\t 9. Exit.\n");

            Scanner scanner = new Scanner(System.in);
            opt = scanner.nextInt();

            if (opt == 1) afisareAngajati();
            if (opt == 2) afisarePacienti();
            if (opt == 3) afisareProgramari();
            if (opt == 4) afisareDocumente("permis");
            if (opt == 5) calculValabilitate("permis");
            if (opt == 6) stergeProgramare();
            //if(opt == 7) elibereazaDocument();
            if (opt == 8) stergeDocument();
        }
    }

    public void afisareMeniuClient(String username){
        int opt = 0;
        while(opt != 7) {
            System.out.println("\n\t-------------------------------------------\n");
            System.out.println("\t\t MENIU:\n");
            System.out.println("\t Alegeti dintre urmatoarele optiuni:");
            System.out.println("\t 1. Afisare angajati.");
            System.out.println("\t 2. Afisare programari.");
            System.out.println("\t 3. Afiseaza documente personale.");
            System.out.println("\t 4. Obtine valabilitate trimitere medicala personala.");
            System.out.println("\t 5. Adauga programare.");
            System.out.println("\t 6. Sterge programare.");
            System.out.println("\t 7. Exit.\n");


            Scanner scanner = new Scanner(System.in);
            opt = scanner.nextInt();

            if (opt == 1) afisareAngajati();
            if (opt == 2) afisareProgramari();
            if (opt == 3) afisareDocumente("permis");
            if (opt == 4) calculValabilitate("permis");
            //if(opt == 5) adaugaProgramare(username);
            if (opt == 6) stergeProgramare();
        }
    }

    public void afisareServicii(){
        int opt = 0;
        while(opt != 10) {
            System.out.println("\n\t-------------------------------------------\n");
            System.out.println("\t\t MENIU:\n");
            System.out.println("\t Alegeti dintre urmatoarele optiuni:");
            System.out.println("\t 1. Afisare angajati.");
            System.out.println("\t 2. Sterge angajat.");
            System.out.println("\t 3. Afisare pacienti.");
            System.out.println("\t 4. Sterge pacient.");
            System.out.println("\t 5. Afisare programari.");
            System.out.println("\t 6. Sterge programare.");
            System.out.println("\t 7. Calculeaza venit angajat.");
            System.out.println("\t 8. Afiseaza documente.");
            System.out.println("\t 9. Sterge document.");
            System.out.println("\t 7. Exit.\n");

            Scanner scanner = new Scanner(System.in);
            opt = scanner.nextInt();

            if (opt == 1) afisareAngajati();
            if (opt == 2) stergeAngajat();
            if (opt == 3) afisarePacienti();
            if (opt == 4) stergePacient();
            if (opt == 5) afisareProgramari();
            if (opt == 6) stergeProgramare();
            if (opt == 7) calcuVenit();
            if (opt == 8) afisareDocumente("permis");
            if (opt == 9) stergeDocument();
        }
    }

    public void afisareMedici(){
        System.out.println("Cabinetul medical are " + Medic.getNrMedici() + " medici: ");

        for (Angajat m : c.angajati){
            if(m instanceof Medic){
                System.out.println(m.getNume() + " " + m.getPrenume());
            }
        }
    }

    public void afisareAsistenti(){
        System.out.println("\nCabinetul medical are " + Asistent.getNrAsistenti() + " asistenti: ");

        for (Angajat m : c.angajati){
            if(m instanceof Asistent){
                System.out.println(m.getNume() + " " + m.getPrenume());
            }

        }
    }

    public void afisareAngajati(){

        System.out.println("\t Introdu:");
        System.out.println("\t 1 pentru a afisa toti asistentii");
        System.out.println("\t 2 pentru a afisa toti medicii");
        System.out.println("\t 3 pentru a afisa toti angajatii.");

        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();
        if (opt == 1) afisareAsistenti();
        if (opt == 2) afisareMedici();
        if (opt == 3) {
            afisareMedici();
            afisareAsistenti();
        }
        else
            while(opt != 1 && opt != 2 && opt != 3)
                System.out.println("Introduceti o optiune valida! (1, 2, 3).");


    }


    public void adaugaMedic(Medic m){
        Angajat a = m;
        c.angajati.add(a);
    }

    public void adaugaAsistent(Asistent m){
        Angajat a = m;
        c.angajati.add(a);
    }

    public void stergeAngajat(){
        int ok = 0;

        System.out.println("\t Indroduceti CNP-ul: ");
        Scanner scanner = new Scanner(System.in);
        String cnp = scanner.nextLine();

        Iterator itr = c.angajati.iterator();
        while (itr.hasNext()) {
            Medic x = (Medic)itr.next();
            if (x.getCNP() == cnp){
                itr.remove();
                ok = 1;
            }
        }

        if(ok == 0){
            System.out.println("Nu exista angajatul in baza de date!");
        }

    }
    public void afisarePacienti(){

        System.out.println("Cabinetul medical are " +  Pacient.getNrPacienti() +  " pacienti: ");

        for (Pacient a : c.pacienti){
            System.out.println(a.getNume() + " " +  a.getPrenume());
        }
    }
    public void stergePacient(){
        int ok = 0;
        System.out.println("\t 3 Introdu CNP-ul pacientului: ");
        Scanner scanner = new Scanner(System.in);
        String cnp = scanner.nextLine();

        Iterator itr = c.pacienti.iterator();
        while (itr.hasNext()) {
            Pacient x = (Pacient)itr.next();
            if (x.getCNP() == cnp){
                itr.remove();
                ok = 1;
            }
        }

        if(ok == 0){
            System.out.println("Nu exista pacientul in baza de date!");
        }

    }

    public int getNrProgramari(String data){
        int nr = 0;
        Iterator itr = c.programari.iterator();
        while (itr.hasNext()) {
            Programare x = (Programare) itr.next();
            if (x.getDataProgramare() == data){
                nr++;
            }
        }

        return nr;
    }

    public void afisareProgramari(){
        System.out.println("\t  Introdu data programarii: ");
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();

        System.out.println("Cabinetul medical are " +  getNrProgramari(data) +  " programari pentru data respectiva: ");

        for (Programare a : c.programari){
            if(a.getDataProgramare() == data)
                System.out.println(a.toString());
        }
    }
//
//    public void adaugaProgramare(String username){
//        System.out.println("\t Introduceti ora programarii: ");
//        Scanner scanner = new Scanner(System.in);
//        String ora = scanner.nextLine();
//
//        System.out.println("\t Introduceti data programarii: ");
//        String data = scanner.nextLine();
//
//        System.out.println("\t Introduceti numele doctorului: ");
//        String numeDoctor = scanner.nextLine();
//
//        System.out.println("\t Introduceti numele asistentului: ");
//        String numeAsistent = scanner.nextLine();
//
//        Medic m = new Medic();
//        Angajat as = new Asistent();
//
//
//        int id = Programare.getNrProg() + 1;
//        for(Angajat a : c.angajati){
//            if(a.getNume() == numeDoctor)
//                m = a;
//            if(a.getNume() == numeAsistent)
//                as = a;
//
//        }
//
//        for(Pacient pac : c.pacienti){
//            if (pac.getUsername() == username) {
//                Pacient p = new Pacient();
//                p = pac;
//            }
//
//        }
//
//        Programare pr = new Programare(id, ora, data, m, as, p);
//        c.programari.add(pr);
//
//    }

    public void stergeProgramare(){
        int ok = 0;

        System.out.println("\t Introduceti CNP-ul pacientului: ");
        Scanner scanner = new Scanner(System.in);
        String cnp = scanner.nextLine();

        System.out.println("\t Introduceti data programarii: ");
        String data = scanner.nextLine();

        Iterator itr = c.programari.iterator();
        while (itr.hasNext()) {
            Programare x = (Programare) itr.next();
            if (x.getPacient().getCNP() == cnp && x.getDataProgramare() == data){
                itr.remove();
                ok = 1;
            }
        }

        if(ok == 0){
            System.out.println("Nu exista programarea in baza de date!");
        }
    }

    public void afisareDocumente(String username){
        if(username == "permis"){
            System.out.println("Cabinetul medical are in registrul urmatoarele documente: ");

            for (Programare a : c.programari){
                System.out.println(a.toString());
            }
        }
        else{
            System.out.println("Documentele dumneavoastra: ");

            for (Programare a : c.programari){
                if(a.getPacient().getUsername() == username)
                    System.out.println(a.toString());
            }
        }
    }
//    public void elibereazaDocument(){
//        System.out.println("\t Ce tip de document doresti sa eliberezi? ");
//        System.out.println("\t Apasa 1 pentru adeverinta de concediu, 2 pentru adeverinta medicala");
//        System.out.println("\t 3 pentru trimitere medicala, 4 pentru reteta");
//        Scanner scanner = new Scanner(System.in);
//        int opt = scanner.nextInt();
//
//        int id = Document.getNrDocumente() + 1;
//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date today = new Date();
//        String str = dateFormat.format(today);
//
//        System.out.println("\t Introduceti numele medicului: ");
//        String nume = scanner.nextLine();
//
//        System.out.println("\t Introduceti prenumele medicului: ");
//        String prenume = scanner.nextLine();
//
//        System.out.println("\t Introduceti cnp-ul pacientului: ");
//        String cnp = scanner.nextLine();
//
//        Medic m = new Medic();
//        Pacient p = new Pacient();
//
//        for(Angajat a : c.angajati){
//            if(a.getNume() == nume && a.getPrenume() == prenume)
//                m = a;
//        }
//
//        for(Pacient a : c.pacienti){
//            if(a.getCNP() == cnp)
//                p = a;
//        }
//
//        if(opt == 1){
//            System.out.println("\t Introduceti zilele de concediu: ");
//            String zile = scanner.nextInt();
//
//            System.out.println("\t Introduceti data de inceput: ");
//            String data = scanner.nextLine();
//            AdeverintaConcediu d = new AdeverintaConcediu(id, str, m, p, zile, data);
//            c.documente.add(d);
//        }
//        if(opt == 2){
//            System.out.println("\t Introduceti scopul: ");
//            String scop = scanner.nextLine();
//
//            System.out.println("\t Este pacientul apt? Scrieti 1 daca da, sau 0 daca nu. ");
//            int aptin = scanner.nextInt();
//            boolean apt;
//            if(aptin == 0) apt = false;
//            else apt = true;
//
//            AdeverintaMedicala d = new AdeverintaMedicala(id, str, m, p, scop, apt);
//            c.documente.add(d);
//
//        }
//        if(opt == 3){
//            System.out.println("\t Introduceti scopul: ");
//            String scop = scanner.nextLine();
//
//            System.out.println("\t Introduceti data de expirare: ");
//            String data = scanner.nextLine();
//
//            System.out.println("\t Introduceti institutia catre care se face trimiterea: ");
//            String catre = scanner.nextLine();
//
//            AdeverintaMedicala d = new AdeverintaMedicala(id, str, m, p, scop, data, catre);
//            c.documente.add(d);
//        }
//        if(opt == 4){
//            System.out.println("\t Introduceti numarul de medicamente: ");
//            int nr = scanner.nextInt();
//
//            System.out.println("\t Introduceti pe cate o linie numele medicamentului si de cate ori pe zi trebuie administrat: ");
//
//            Map<String, Integer> r = new TreeMap<>(String, Integer);
//            String ret = scanner.nextLine();
//
//            while(nr){
//                String med = ret.split()[0];
//                int n = (int) ret.split()[1];
//                r.put(med, n);
//                nr--;
//                String ret = scanner.nextLine();
//            }
//
//
//        }
//
//    }
    public void stergeDocument(){
        int ok = 0;

        System.out.println("\t Introduceti id-ul documentului: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        Iterator itr = c.pacienti.iterator();
        while (itr.hasNext()) {
            Document x = (Document) itr.next();
            if (x.getDocId() == id){
                itr.remove();
                ok = 1;
            }
        }

        if(ok == 0){
            System.out.println("Nu exista programarea in baza de date!");
        }
    }

    public double calcuVenit(){
        double v = 0;

        System.out.println("\t Introduceti id-ul persoanei: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        Iterator itr = c.angajati.iterator();
        while (itr.hasNext()) {
            Asistent x = (Asistent) itr.next();
            if (x.getIdPersoana() == id) {
                v = x.calculeazaVenit();
            }
        }


        return v;
    }

    public int calculValabilitate(String username){

        System.out.println("\t Introduceti id-ul documentului: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        int d = 0;
        if(username == "permis") {
            Iterator itr = c.documente.iterator();
            while (itr.hasNext()) {
                TrimitereMedicala x = (TrimitereMedicala) itr.next();
                if (x.getDocId() == id) {
                    d = x.zileValabilitate();
                }
            }
        }
        else{
            Iterator itr = c.documente.iterator();
            while (itr.hasNext()) {
                TrimitereMedicala x = (TrimitereMedicala) itr.next();
                if (x.getDocId() == id && x.getPacient().getUsername() == username) {
                    d = x.zileValabilitate();
                }
            }

        }

        return d;
    }


}
