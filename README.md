# Proiect - Sistem de gestiune a programarilor unui cabinet medical


**Tema proiectului**: crearea unui sistem de gestiune a programarilor unui cabinet medical si stocarea informatiilor despre clienti, angajati, documente si programari.

Limbaj: **Java**, IDE: **IntelliJ**.

---
## Partea I:
Cerinte:
- [x] lista cu cel puțin 8 tipuri de obiecte
- [x] lista cu cel puțin 10 acțiuni/interogări care se pot face în cadrul sistemului
- [x] clase simple cu atribute private/protected și metode de acces
- [x] cel puțin 2 colecții diferite capabile să gestioneze obiectele definite anterior dintre care cel puțin una sa fie sortata
- [x] cel puțin o clasă serviciu care sa expună operațiile sistemului
- [x] o clasa Main din care sunt făcute apeluri către servicii
 
## Descrierea proiectului
### Proiectul cuprinde 16 clase:

- 🟣 clasa **Afectiune**
- 🟣 clasa **User** - clasa abstracta din care sunt mostenite:
    - clasa **Angajat** - sunt mostenite clasele **Medic** si **Asistent**
    - clasa **Pacient** - utilizat conceptul de *compunere* cu clasa **Afectiune** (retinem o lista de afectiuni)
    
- 🟣 clasa **Document** - utilizat conceptul de *compunere* cu clasa **Medic** si **Pacient**. Este o clasa abstracta din care sunt mostenite urmatoarele clase:
    - clasa **Adeverinta Concediu**
    - clasa **Adeverinta Medicala** 
    - clasa **Reteta** - utilizat *TreeMap* pentru a retine perechi de tipul medicament:nr;
    - clasa **Trimitere Medicala**

- 🟣 enumul **Specializare** care apare in clasele **Medic** si **Asistent**.
- 🟣 clasa **Programare** - *compunere* **Medic**, **Asistent**, **Pacient**.
- 🟣 clasa **Cabinet Medical** - singleton: contine liste de tip *ArrayList* pentru documente, pacienti, programari si angajati;
- 🟣 clasa **Serviciu** - clasa in care sunt implementate metode sau interogari in functie de rolul userului: angajat, admin sau pacient.
- 🟣 clasa **Main** - din aceasta clasa se fac apeluri catre servicii.

### Functionalitati:
- login: in functie de client, angajat, admin sau user neinregistrat;
- register: pentru pacientii neinregistrati in sistem;
- 3 meniuri: in functie de contul asociati: client, angajat sau admin;

#### Functionalitati client:
- 🟡 afisarea angajatilor: doar medicii, doar asistentii sau toti angajatii;
- 🟡 afisarea programarilor pe o zi selectata de el;
- 🟡 afisarea documentelor din sistem care ii apartin;
- 🟡 calculul valabilitatii ramase a unei trimiteri medicale care ii apartine;
- 🟡 adaugarea unei programari;
- 🟡 stergerea unei programari proprii.

#### Functionalitati angajat:
- 🟢  afisarea angajatilor: doar medicii, doar asistentii sau toti angajatii;
- 🟢  afisarea tuturor pacientilor;
- 🟢  afisarea programarilor pe o zi selectata de el;
- 🟢  afisarea tutoror documentelor;
- 🟢  calculul valabilitatii ramase a unei trimiteri medicale oarecare;
- 🟢  eliberarea unui document;
- 🟢  stergerea unui document;
- 🟢  stergerea unei programari.

#### Functionalitati adminului (pe langa cele ale unui angajat):
- 🔵 stergerea unui angajat;
- 🔵 stergerea unui pacient;
- 🔵 calculul venitului unui angajat in functie de rolul sau: pentru medici se ia in considerare treaapta profesionala, iar pentru asistenti daca lucreaza sau nu in ture;


---
## Partea II:
Cerinte:
- [x] realizarea de fișiere de tip CSV pentru cel puțin 4 dintre clasele definite în prima etapa 
- [x] realizarea de servicii singleton generice pentru scrierea și citirea din fișiere
- [x] încărcarea datelor din fișiere utilizând serviciile create
- [x] realizarea unui serviciu care sa scrie într-un fișier de tip CSV de fiecare data când este executată una dintre acțiunile descrise în prima etapa
- [x] cel puțin o clasă serviciu care sa expună operațiile sistemului

Implementare:
- 🟣 am creat fisiere csv pentru fiecare clasa mentionata mai sus
- 🟣 clasa singleton **ServiciuDocument** cu metodele generice *scriecsv* si *incarcareDocumente* pentru a scrie si citi din csv, tratand cele 4 cazuri in functie de cele 4 clase derivate
- 🟣 clasa singleton **ServiciuProgramare** cu metodele *adaugaProgramare* si *incarcareProgramari*
- 🟣 clasa singleton **ServiciuUser** cu metodele *adaugaClient*, *adaugaAngajat*, *incarcareAngajati* si *incarcarePacienti*
- 🟣 clasa singleton **Audit** cu metoda *actiune*


---
## Partea III:
Cerinte:
- [x] realizarea de servicii ce permit operatii de tipul create, read, update si delete pentru cel puțin 4 dintre clasele definite.

Implementare:
- 🔵 am creat baza de date in *Amazon Web Services*
- 🔵 am creat tabelele: **AdeverintaMedicala**, **AdeverintaConcediu**, **TrimitereMedicala**, **Retetata** in *MySQL Workbench*
![](diagram.jpg)
- 🔵 am stabilit conexiunea cu baza de date intr-o metoda numita **conexiune** din clasa **ServiciuDocument**
- 🔵 am realizat 3 metode generale: **editareDocument**, **stergeDocument**, **afisareDocumenteDB** si vechea metoda **elibereazaDocument** care ma ajutau sa apelez functiile descrise mai jos, in functie de tipul documentului
- 🔵 pentru fiecare dintre cele 4 clase, am creat o functie de **stergeTipDocumentDB**, **adaugaTipDocumentDB**, **afisareTipDocumentDB**, **editareTipDocumentDB** unde TipDocument: "AdeverintaMed", "AdeverintaCon", "TrimitereMed", "Reteta".
