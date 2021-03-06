package entities;

import entities.document.*;
import entities.persoana.Pacient;
import entities.persoana.angajat.Angajat;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;
import entities.serviciu.Audit;
import entities.serviciu.ServiciuDocument;
import entities.serviciu.ServiciuProgramare;
import entities.serviciu.ServiciuUser;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import static entities.serviciu.ServiciuDocument.incarcareDocumente;

public class Meniu {

    static CabinetMedical c = CabinetMedical.getCabinet();
    static ServiciuProgramare cp = ServiciuProgramare.getCP();
    static ServiciuDocument cd = ServiciuDocument.getCP();
    static ServiciuUser cu = ServiciuUser.getCP();
    static Audit audit = Audit.getCP();

    private String connectionURL = "jdbc:sqlserver://pao-project.database.windows.net:1433;database=PAO-db;user=anca@pao-project;password=qwer1234!@#$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private Connection connection;

    public Meniu(){ }

    public void afisareLogin(){
        cu.incarcarePacienti();
        cu.incarcareAngajati();
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
            cu.adaugaClient();
            loginClient();
        }
    }

    public static Audit getAudit() {
        return audit;
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
                Angajat x = (Angajat) itr.next();
                if (x.getUsername().equals(username) && x.getPassword().equals(parola)){
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

    public static void loginClient(){
        int ok = 0;
        //cu.incarcarePacienti();
        while(ok == 0){

            System.out.println("\t Introdu username-ul: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();

            System.out.println("\t Introdu parola: ");
            String parola = scanner.nextLine();

            Iterator itr = c.pacienti.iterator();
            while (itr.hasNext()) {
                Pacient x = (Pacient) itr.next();
                if (x.getUsername().equals(username) && x.getPassword().equals(parola)){
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

            if(username.equals("admin") && parola.equals("admin1234")){
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

    public static void incarcareToateDoc(){
        ArrayList<AdeverintaMedicala> admed = null;
        try {
            admed = incarcareDocumente("ADEVERINTA MEDICALA", "src/csv_files/AdeverinteMedicale.csv");
        } catch (IOException e) { e.printStackTrace(); }

        ArrayList<AdeverintaConcediu> adcon = null;
        try {
            adcon = incarcareDocumente("ADEVERINTA CONCEDIU", "src/csv_files/AdeverinteConcediu.csv");
        } catch (IOException e) { e.printStackTrace(); }

        ArrayList<Reteta> ret = null;
        try {
            ret = incarcareDocumente("RETETA", "src/csv_files/Retete.csv");
        } catch (IOException e) { e.printStackTrace(); }

        ArrayList<TrimitereMedicala> trim = null;
        try {
            trim = incarcareDocumente("TRIMITERE MEDICALA", "src/csv_files/TrimiteriMedicale.csv");
        } catch (IOException e) { e.printStackTrace(); }

        List<Document> doc = c.getDocumente();
        doc.addAll(admed);
        doc.addAll(adcon);
        doc.addAll(ret);
        doc.addAll(trim);
    }

    public static void incarcareDocDB(){
        ArrayList<AdeverintaMedicala> admed = null;
        try {
            admed = incarcareDocumente("ADEVERINTA MEDICALA", "src/csv_files/AdeverinteMedicale.csv");
        } catch (IOException e) { e.printStackTrace(); }

        ArrayList<AdeverintaConcediu> adcon = null;
        try {
            adcon = incarcareDocumente("ADEVERINTA CONCEDIU", "src/csv_files/AdeverinteConcediu.csv");
        } catch (IOException e) { e.printStackTrace(); }

        ArrayList<Reteta> ret = null;
        try {
            ret = incarcareDocumente("RETETA", "src/csv_files/Retete.csv");
        } catch (IOException e) { e.printStackTrace(); }

        ArrayList<TrimitereMedicala> trim = null;
        try {
            trim = incarcareDocumente("TRIMITERE MEDICALA", "src/csv_files/TrimiteriMedicale.csv");
        } catch (IOException e) { e.printStackTrace(); }

        List<Document> doc = c.getDocumente();
        doc.addAll(admed);
        doc.addAll(adcon);
        doc.addAll(ret);
        doc.addAll(trim);
    }

    public void afisareMeniuAngajat(){
        cp.incarcareProgramari();
        incarcareToateDoc();
        cu.incarcarePacienti();
        cu.incarcareAngajati();

        int opt = 0;
        while(opt != 10) {
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
            System.out.println("\t 9. Editeaza document.");
            System.out.println("\t 10. Exit.\n");

            Scanner scanner = new Scanner(System.in);
            opt = scanner.nextInt();

            if (opt == 1) {
                afisareAngajati();
            }
            if (opt == 2) {
                afisarePacienti();
            }
            if (opt == 3) {
                afisareProgramari();
            }
            if (opt == 4) //afisareDocumente("permis");
            {
                cd.afisareDocumenteDB();
                audit.actiune("afisareDocumente");
            }
            if (opt == 5){
                calculValabilitate("permis");
            }
            if (opt == 6) {
                stergeProgramare("permis");
            }
            if (opt == 7) //cd.elibereazaDocument();
            {
                cd.elibereazaDocument();
                audit.actiune("eliberareDocument");
            }
            if (opt == 8) {
                cd.stergeDocument();
                audit.actiune("stergereDocument");
            }
            if (opt == 9) {
                cd.editareDocument();
                audit.actiune("editareDocument");
            }
        }
    }

    public static void afisareMeniuClient(String username){
        cp.incarcareProgramari();
        incarcareToateDoc();
        cu.incarcarePacienti();
        cu.incarcareAngajati();

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

            if (opt == 1) {
                afisareAngajati();
            }
            if (opt == 2) {
                afisareProgramari();
            }
            if (opt == 3) {
                cd.afisareDocumenteDB();
            }
            if (opt == 4) {
                calculValabilitate(username);
            }
            if (opt == 5) {
                cp.adaugaProgramare(username);
            }
            if (opt == 6) {
                stergeProgramare(username);
            }
        }
    }

    public void afisareServicii(){
        cp.incarcareProgramari();
        incarcareToateDoc();
        cu.incarcarePacienti();
        cu.incarcareAngajati();

        int opt = 0;
        while(opt != 12) {
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
            System.out.println("\t 10. Editare document.");
            System.out.println("\t 11. Adauga angajat.");
            System.out.println("\t 12. Exit.\n");

            Scanner scanner = new Scanner(System.in);
            opt = scanner.nextInt();

            if (opt == 1) {
                afisareAngajati();
            }
            if (opt == 2) {
                stergeAngajat();
            }
            if (opt == 3) {
                afisarePacienti();
            }
            if (opt == 4) {
                stergePacient();
            }
            if (opt == 5) {
                afisareProgramari();
            }
            if (opt == 6) {
                stergeProgramare("permis");
            }
            if (opt == 7) {
                calcuVenit();
            }
            if (opt == 8) {
                cd.afisareDocumenteDB();
                audit.actiune("afisareDocumente");
            }
            if (opt == 9) {
                cd.editareDocument();
                audit.actiune("editareDocument");
            }
            if (opt == 10){
                cd.stergeDocument();
                audit.actiune("stergereDocument");
            }
            if (opt == 11) cu.adaugaAngajat();
        }
    }

    public static void afisareMedici(){
        System.out.println("Cabinetul medical are urmatorii medici: ");

        for (Angajat m : c.angajati){
            if(m instanceof Medic){
                System.out.println(m.getNume() + " " + m.getPrenume());
            }
        }
    }

    public static void afisareAsistenti(){
        System.out.println("\nCabinetul medical are urmatorii asistenti: ");

        for (Angajat m : c.angajati){
            if(m instanceof Asistent){
                System.out.println(m.getNume() + " " + m.getPrenume());
            }
        }
    }

    public static void afisareAngajati(){
        cu.incarcareAngajati();
        System.out.println("\t Introdu:");
        System.out.println("\t 1 pentru a afisa toti asistentii");
        System.out.println("\t 2 pentru a afisa toti medicii");
        System.out.println("\t 3 pentru a afisa toti angajatii.");

        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();
        if (opt == 1) {
            afisareAsistenti();
        }
        if (opt == 2) {
            afisareMedici();
        }
        if (opt == 3) {
            afisareMedici();
            afisareAsistenti();
        }
        else{
            while(opt != 1 && opt != 2 && opt != 3){
                System.out.println("Introduceti o optiune valida! (1, 2, 3).");
            }
        }
        audit.actiune("afisareAngajati");

    }

    public static void afisareProgramari(){
        System.out.println("\t  Introdu data in care vrei sa vezi programarile: ");
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();

        System.out.println("Cabinetul medical are urmatoarele programari pentru data respectiva: ");

        for (Programare p : c.getProgramari()) {
            if (p.getDataProgramare().equals(data)) {
                System.out.println(p.toString());
            }

        }
        audit.actiune("afisareProgramari");
    }

    public void stergeAngajat(){
        int ok = 0;

        System.out.println("\t Indroduceti CNP-ul: ");
        Scanner scanner = new Scanner(System.in);
        String cnp = scanner.nextLine();
        int m = 0;
        int a = 0;

        Iterator itr = c.angajati.iterator();
        while (itr.hasNext()) {
            Angajat x = (Angajat)itr.next();
            if(x instanceof Medic){
                m++;
            }
            if(x instanceof Asistent){
                a++;
            }
            if (x.getCNP().equals(cnp)){
                itr.remove();
                ok = 1;
            }
        }

        if(ok == 0){
            System.out.println("Nu exista angajatul in baza de date!");
        }
        else{
            System.out.println("Angajatul a fost sters cu succes!");
            if(m > 0){
                Medic.setNrMedici(Medic.getNrMedici() - 1);
            }
            if(a > 0){
                Asistent.setNrAsistenti(Asistent.getNrAsistenti() - 1);
            }
        }
        audit.actiune("stergereAngajat");
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
            if (x.getCNP().equals(cnp)){
                itr.remove();
                ok = 1;
            }
        }

        if(ok == 0){
            System.out.println("Nu exista pacientul in baza de date!");
        }
        else{
            System.out.println("Pacientul a fost sters din baza de date!");
            Pacient.setNrPacienti(Pacient.getNrPacienti() - 1);
        }
        audit.actiune("stergerePacient");
    }

    public int getNrProgramari(String data){
        int nr = 0;
        Iterator itr = c.programari.iterator();
        while (itr.hasNext()) {
            Programare x = (Programare) itr.next();
            if(x.getDataProgramare().equals(data)){
                nr++;
            }
        }

        return nr;
    }

    public static void stergeProgramare(String username){
        int ok = 0;

        System.out.println("\t Introduceti CNP-ul pacientului: ");
        Scanner scanner = new Scanner(System.in);
        String cnp = scanner.nextLine();

        System.out.println("\t Introduceti data programarii: ");
        String data = scanner.nextLine();

        Iterator itr = c.programari.iterator();
        while (itr.hasNext()) {
            Programare x = (Programare) itr.next();
            if (x.getPacient().getCNP().equals(cnp) && x.getDataProgramare().equals(data) && (x.getPacient().getUsername().equals(username) || username.equals("permis"))){
                itr.remove();
                ok = 1;
            }
        }

        if(ok == 0){
            System.out.println("Nu exista programarea in baza de date!");
        }
        else{
            System.out.println("Programarea a fost stearsa cu succes!");
            Programare.setNrProg(Programare.getNrProg() - 1);
        }
        audit.actiune("stergereProgramare");
    }

    public static void afisareDocumente(String username){
        if(username.equals("permis")){
            System.out.println("Cabinetul medical are in registrul urmatoarele documente: ");

            for (Document a : c.documente){
                System.out.println(a.toString());
            }
        }
        else{
            System.out.println("Documentele dumneavoastra: ");

            for (Document a : c.documente){
                if(a.getPacient().getUsername().equals(username)) {
                    System.out.println(a.toString());
                    System.out.println("\n");
                }
            }
        }
        audit.actiune("afisareDocumente");
    }

    public void stergeDocument(){
        int ok = 0;

        System.out.println("\t Introduceti id-ul documentului: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        Iterator itr = c.documente.iterator();
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
        else{
            System.out.println("Documentul a fost sters cu succes!");
            Document.setNrDocumente(Document.getNrDocumente() - 1);
        }
        audit.actiune("stergereDocument");
    }

    public void calcuVenit(){
        double v = 0;

        System.out.println("\t Introduceti CNP-ul persoanei: ");
        Scanner scanner = new Scanner(System.in);
        String cnp = scanner.nextLine();

        Iterator itr = c.angajati.iterator();
        while (itr.hasNext()) {
            Angajat x = (Angajat) itr.next();
            if (x.getCNP().equals(cnp)) {
                v = x.calculeazaVenit();
            }
        }

        System.out.println("Venitul este: " + v);
        audit.actiune("calculVenit");
    }

    public static void calculValabilitate(String username){

        System.out.println("\t Introduceti id-ul documentului: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        int d = 0;
        if(username.equals("permis")) {
            Iterator itr = c.documente.iterator();
            while (itr.hasNext()) {
                Document x = (Document) itr.next();
                if (x.getDocId() == id) {
                    TrimitereMedicala tm = (TrimitereMedicala) x;
                    d = tm.zileValabilitate();
                }
            }
        }
        else{
            Iterator itr = c.documente.iterator();
            while (itr.hasNext()) {
                Document x = (Document) itr.next();
                if (x.getDocId() == id && x.getPacient().getUsername().equals(username)) {
                    TrimitereMedicala tm = (TrimitereMedicala) x;
                    d = tm.zileValabilitate();
                }
            }

        }

        System.out.println("\t Adeverinta mai este valabila " + d + " zile.");
        audit.actiune("calculValabilitate");
    }


}
