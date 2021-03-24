import entities.Afectiune;
import entities.Programare;
import entities.Serviciu;
import entities.persoana.Pacient;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;
import entities.persoana.angajat.Specializare;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {


//        System.out.println("Venitul asistentului " + a.getNumeComplet() + " este de: " + a.calculeazaVenit());
//        System.out.println("Venitul medicului " + m.getNumeComplet() + " este de: " + m.calculeazaVenit());
//
//        List<Medic> l1 = new ArrayList<Medic>();
//        l1.add(m);
//
//        List<Asistent> l2 = new ArrayList<Asistent>();
//        l2.add(a);
//
//        Serviciu s = new Serviciu();
//        s.afisareServicii();

//        c.adaugaMedic(m);
//        c.adaugaAsistent(a);
//
//        c.afiseazaAsistentii();
//        c.afiseazaMedicii();

//        Map<String, Integer> med = new HashMap<String, Integer>();
//        med.put("Furazolidona", 2);
//        med.put("Paracetamol", 3);
//        Reteta re = new Reteta("31/10/2009", m, pa, med);
//        System.out.println(re.toString());

        Serviciu s = new Serviciu();

        Afectiune af = new Afectiune(1, "03/12/2009", "Diabet");
        Afectiune al = new Afectiune(2, "03/12/2005", "Raceala");
        List<Afectiune> afec = new ArrayList<Afectiune>();
        afec.add(af);
        afec.add(al);

        Pacient p1 = new Pacient(20, "maria.antoaneta", "maria@yahoo.com", "123", "Popescu", "Maria-Antoaneta", "6001103928412", "03/11/2000", true, "Str Florilor nr 100", "0720039457", afec);
        Pacient p2 = new Pacient(21, "iorda", "iordachescu@yahoo.com", "123", "Iordachescu", "Anca", "6001103928419", "28/02/1999", true, "Str Pallady nr 90", "0756299018", null);
        Pacient p3 = new Pacient(21, "vlad11", "vlad@yahoo.com", "123", "Moisi", "Vlad", "6001109018419", "28/04/1999", false, "Str Tineretului nr 12", "0757209345", null);
        Pacient p4 = new Pacient(21, "ionel1340", "ionel@yahoo.com", "123", "Grigorescu", "Ionel-Marian", "6001229928419", "13/09/1990", false, "Str Pallady nr 90", "0751203401", null);
        s.adaugaPacient(p1);
        s.adaugaPacient(p2);
        s.adaugaPacient(p3);
        s.adaugaPacient(p4);

        Medic m1 = new Medic(10, "mihailescu", "mihailescu@yahoo.com", "123", "Mihailescu", "Georgeta", "5001231350051", "28/02/1998", true, "Str minunilor nr 60", "0768100234", "03/06/2019", 3452, Specializare.CARDIOLOGIE, 234, 1);
        Asistent a1 = new Asistent(11, "bmadalina", "badescu@yahoo.com", "123", "Badescu", "Madalina", "2352355554212", "07/05/2000", true, "Str primaverii nr 302", "0734678910", "27/08/2020", 1700, Specializare.DIABET, true);
        Medic m2 = new Medic(12, "cristofer", "serafim@yahoo.com", "123", "Serafim", "Cristofer", "5001901350051", "08/12/1991", true, "Str caselor", "0768100290", "03/06/2019", 4500, Specializare.MEDICINAMUNCII, 245, 4);
        Asistent a2 = new Asistent(13, "matei", "mandruleanu@yahoo.com", "123", "Mandruleanu", "Matei", "2352355554212", "16/07/2000", true, "Str iderii", "0734670010", "27/08/2020", 2300, Specializare.GENETICA, false);
        s.adaugaAngajat(m1);
        s.adaugaAngajat(m2);
        s.adaugaAngajat(a1);
        s.adaugaAngajat(a2);

        Programare pro1 = new Programare(30, "10.20", "06/03/2021", m1, a1, p1);
        Programare pro2 = new Programare(31, "11.20", "06/03/2021", m2, a1, p3);
        Programare pro3 = new Programare(32, "10.50", "20/03/2021", m2, a2, p4);
        s.adaugaProg(pro1);
        s.adaugaProg(pro2);
        s.adaugaProg(pro3);



        //s.afisareAngajati();

        //s.afisareLogin();
        s.afisareMeniuAngajat();




    }
}
