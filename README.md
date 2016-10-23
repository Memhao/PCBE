# PCBE

Syslog

Scrieti un server ce permite unor clienti (fire de executie sau procese) sa trimita linii de text in vederea jurnalizarii (logging).
Numele fisierelor "log" si modul de dirijare al cererilor de jurnalizare  sint definite in configuratia sistemului. Dirijarea se poate face functie de urmatoarele criterii:
- denumirea clientului (stabilita de fiecare fir/proces clienti la lansare, numele executabilului sau alt mod oarecare de a stabili o denumire)
- importanta mesajului (DEBUG, INFO, WARNING, ERROR, CRITICAL)

Cererile de jurnalizare vor fi trecute in fisierele corespunzatoare in ordinea sosirii lor. Serverul va avea mai multe fire de executie iar numarul lor va varia in functie de numarul de clienti intre unul si un numar maxim stabilit prin configurare.
Fisierele "log" vor fi "rotite" dupa un numar (stabilit prin configurare) de intrari, adica vor fi redenumite si se va crea un nou fisier "log" curent.
