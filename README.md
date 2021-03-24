# Proiect - Sistem de gestiune a programarilor unui cabinet medical

---

**Tema proiectului**: crearea unui sistem de gestiune a programarilor unui cabinet medical si stocarea informatiilor despre clienti, angajati, documente si programari.

Limbaj: **Java**, IDE: **IntelliJ**.

---
## Partea I:
Cerinte:
- [x] lista cu cel puțin 8 tipuri de obiecte
- [x] lista cu cel puțin 10 acțiuni/interogări care se pot face în cadrul sistemului
- [x] clase simple cu atribute private/protected și metode de acces
- [x] cel puțin 2 colecții diferite capabile să gestioneze obiectele definite anterior dintre care cel puțin una sa fie sortata
- [ ] cel puțin o clasă serviciu care sa expună operațiile sistemului
- [ ] o clasa Main din care sunt făcute apeluri către servicii
 
## Descrierea proiectului
Proiectul cuprinde 17 clase:
- 🟣 clasa **Cabinet Medical** - singleton
- 🟣 clasa **User** - clasa abstracta
    - clasa **Angajat** - sunt mostenite clasele **Medic** si **Asistent**
    - clasa **Pacient**
    
- 🟣 clasa **Document** - clasa abstracta din care sunt mostenite urmatoarele clase:
    - clasa **Adeverinta Concediu**
    - clasa **Adeverinta Medicala** 
    - clasa **Reteta**
    - clasa **Trimitere Medicala**

- 🟣 clasa **Afectiune** 
- 🟣 clasa **Programare** 
- 🟣 clasa **Serviciu** - clasa in care sunt implementate metode sau interogari in functie de rolul userului: angajat, admin sau pacient.
- 🟣 clasa **Main** - din aceasta clasa se fac apeluri catre servicii.
