import entities.Afectiune;
import entities.Serviciu;
import entities.persoana.Pacient;
import entities.persoana.angajat.Asistent;
import entities.persoana.angajat.Medic;
import entities.persoana.angajat.Specializare;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

//        Afectiune af = new Afectiune(1, "03/12/2009", "Diabet");
//        Afectiune al = new Afectiune(2, "03/12/2005", "Raceala");
//
//        List<Afectiune> afec = new ArrayList<Afectiune>();
//        afec.add(af);
//        afec.add(al);
//
//        Pacient p = new Pacient("Popescu", "Maria-Antoaneta", "6001103928412", "03/11/2000", true, "Str Florilor nr 100", "0720039457", 16283, afec);
//        System.out.println(p.toString());
//        System.out.println("Varsta este: " + p.calculeazaVarsta());
//        p.afiseazaAfectiuni();
//
//        Pacient pa = new Pacient("Georgescu", "Ion-Mihai", "9001289311551", "17/05/1999", false, "Str Mirajului nr 23", "0759304875", 65238, null);
//
//        Medic m = new Medic("Iordachescu", "Anca-Mihaela", "5001231350051", "28/02/1998", true, "Str minunilor nr 60", "0768100234", "03/06/2019", 3452, Specializare.CARDIOLOGIE, 234, 1);
//        Asistent a = new Asistent("Badescu", "Madalina", "2352355554212", "07/05/2000", true, "Str primaverii nr 302", "0734678910", "27/08/2020", 1700, Specializare.DIABET, false);
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

        //login pt client
        Serviciu s = new Serviciu();
        s.afisareLogin();

    }
}
