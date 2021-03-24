package entities;

import entities.document.Document;
import entities.document.TrimitereMedicala;
import entities.persoana.Pacient;
import entities.persoana.angajat.Angajat;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Serviciu {
    CabinetMedical c = CabinetMedical.getCabinet();
    private static Serviciu serviciu;

    private Serviciu(){

    }

    public int afisareServicii(){
        System.out.println("\t\t MENIU:\n");
        System.out.println("\n\t Alegeti dintre urmatoarele optiuni:");
        System.out.println("\n\t 1. Afisare angajati.");
        System.out.println("\n\t 2. Adauga angajat.");
        System.out.println("\n\t 3. Sterge angajat.");
        System.out.println("\n\t 4. Afisare pacienti.");
        System.out.println("\n\t 5. Sterge pacient.");
        System.out.println("\n\t 6. Afisare programari.");
        System.out.println("\n\t 7. Adauga programare.");
        System.out.println("\n\t 8. Calculeaza venit angajat.");
        System.out.println("\n\t 9. Obtine valabilitate.");
        System.out.println("\n\t 10. Sterge programare.");
        System.out.println("\n\t 11. Afiseaza documente.");
        System.out.println("\n\t 12. Elibereaza document.");
        System.out.println("\n\t 13. Sterge document.");

        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();

        return opt;



    }

    public void afisareMedici(){

        System.out.println("Cabinetul medical are " + Medic.getNrMedici() + " medici: ");

        for (Medic m : c.medici){
            System.out.println(m.getNume() + " " + m.getPrenume());
        }

    }

    public void afisareAsistenti(){

        System.out.println("Cabinetul medical are " +  Asistent.getNrAsistenti() +  " asistenti: ");

        for (Asistent a : c.asistenti){
            System.out.println(a.getNume() + " " +  a.getPrenume());
        }

    }

    public void afisareAngajati(){

        System.out.println("\t Introdu:");
        System.out.println("\t 1 pentru a afisa toti ansistentii");
        System.out.println("\t 2 pentru a afisa toti medicii");
        System.out.println("\t 3 pentru a afisa toti angajatii.");

        Scanner scanner = new Scanner(System.in);
        int opt = scanner.nextInt();
        if (opt == 1) afisareAsistenti();
        if (opt == 2) afisareMedici();
        if (opt == 3) afisarePacienti();
        else
            while(opt != 1 && opt != 2 && opt != 3)
                System.out.println("Introduceti o optiune valida! (1, 2, 3).");


    }


    public void adaugaAngajat(){
    }
    public void stergeAngajat(String nume, String prenume){
        int ok = 0;

        Iterator itr = c.medici.iterator();
        while (itr.hasNext()) {
            Medic x = (Medic)itr.next();
            if (x.getNume() == nume && x.getPrenume() == prenume){
                itr.remove();
                ok = 1;
            }
        }

        Iterator itr = c.asistenti.iterator();
        while (itr.hasNext()) {
            Asistent x = (Asistent)itr.next();
            if (x.getNume() == nume && x.getPrenume() == prenume){
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
    public void stergePacient(int id){
        int ok = 0;

        Iterator itr = c.pacienti.iterator();
        while (itr.hasNext()) {
            Pacient x = (Pacient)itr.next();
            if (x.getIdPersoana() == id){
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

    public void afisareProgramari(String data){
        System.out.println("Cabinetul medical are " +  getNrProgramari(data) +  " programari pentru data respectiva: ");

        for (Programare a : c.programari){
            System.out.println(a.toString());
        }
    }
    public void adaugaProgramare(){
    }
    public void stergeProgramare(int id){
        int ok = 0;

        Iterator itr = c.pacienti.iterator();
        while (itr.hasNext()) {
            Programare x = (Programare) itr.next();
            if (x.getIdProgramare() == id){
                itr.remove();
                ok = 1;
            }
        }

        if(ok == 0){
            System.out.println("Nu exista programarea in baza de date!");
        }
    }

    public void afisareDocumente(){
        System.out.println("Cabinetul medical are in registrul urmatoarele documente: ");

        for (Programare a : c.programari){
            System.out.println(a.toString());
        }
    }
    public void elibereazaDocument(){

    }
    public void stergeDocument(int id){
        int ok = 0;

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

    public int calcuVenit(int id){
        float v;
        int ok = 0;
        Iterator itr = c.asistenti.iterator();
        while (itr.hasNext()) {
            Asistent x = (Asistent) itr.next();
            if (x.getIdPersoana() == id) {
                v = x.calculeazaVenit();
                ok = 1;
            }
        }
        if(ok == 0){
            Iterator itr = c.medici.iterator();
            while (itr.hasNext()) {
                Medic x = (Medic) itr.next();
                if (x.getIdPersoana() == id) {
                    v = x.calculeazaVenit();
                }
            }
        }

        return v;
    }

    public int calculValabilitate(int id){
        Iterator itr = c.documente.iterator();
        while (itr.hasNext()) {
            TrimitereMedicala x = (TrimitereMedicala) itr.next();
            if (x.getDocId() == id) {
                int d = x.zileValabilitate();
            }
        }

        return d;
    }

    public void meniu(){
        int opt = afisareServicii();

        if(opt == 1) afisareAngajati();
        if(opt == 2) adaugaAngajat();
        if(opt == 3) stergeAngajat(int idPersoana);
        if(opt == 4) afisarePacienti();
        if(opt == 5) stergePacient(int idPersoana);
        if(opt == 6) afisareProgramari();
        if(opt == 7) stergeProgramare();
        if(opt == 8) calcuVenit(int idPacient);
        if(opt == 9) calculValabilitate(int idTrimitere);
        if(opt == 10) stergeProgramare();
        if(opt == 11) afisareDocumente();
        if(opt == 12) elibereazaDocument();
        if(opt == 13) stergeDocument(int idDoc);
    }

}
