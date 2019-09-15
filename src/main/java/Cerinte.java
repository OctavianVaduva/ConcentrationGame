public interface Cerinte {
    /*
    Nume joc "Concentration"

    reguli de joc:
    tabla de joc 4*4 (default) sau definita de jucator
       -  8 simboluri !@#$^& pentru joc (sau altceva) pentru fata cartilor
       -  1 simbol pentru dosul cartilor
       -  cartile sunt amestecate si ascunse
       -  la fiecare tura se intorc cate 2 carti, oricare dintre cele ascunse, succesiv, selectate de jucator
       -  daca la o tura cele 2 carti ai acelsi semn, raman vizibile (nu se mai ascund/reintorc)
       -  jocul se termina cand toate cartile sunt vizibile (cu fata in sus)

    Clasa enum pt simboluri
        - >8 simboluri joc.
        - 1 simbol ascundere carte
    Clasa arbitru
        - da startul jocului
        - are tabla si jucatorul
    Clasa player
        - va avea o metoda getCoord  care citeste de la tastatura doua seturi de coordonate
    Clasa playboard
        - matricea de 4*4 de tip enum
        - metoda initializare tabla - algoritm aleator de amestecare simboluri
            - plasare cate 2 intrari ptr fiecare tip simbol
            -
        - metoda afisare perechi selectate
        - metoda verificare coincidenta
        - metoda scoatere perechi la coincidenta
    Clasa main
        - stabilire numar casute tabla (optional)

     */
}
