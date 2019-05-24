import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Playboard {
    // dimensiunile nrRow si nrCol pot fi modificte, dar cel putin o dimensiune trebuie sa fie nr. par.
    private int nrRow;
    private int nrCol;
    public void setNrRow(int setRow) {
        this.nrRow = setRow;
    }

    public void setNrCol(int setCol) {
        this.nrCol = setCol;
    }

    public void boardSet() { //todo de verificat de ce nu functioneaza
        char option;
        int row = 4;
        int col = 4;

        Scanner sc = new Scanner(System.in);
        System.out.println("Tabla de joc are in mod implicit dimensiunea de 4 x 4.\n" +
                "Daca doriti configurarea tablei pentru alte dimensiuni, apasati tasta 'C'.");

        option = sc.next().charAt(0);

        if(option == 'c' || option == 'C') {
            System.out.println("Puteti configura tabla de joc pentru maxim 40 carti,  respectiv 20 perechi de semne.\n" +
                    "Produsul numarului de linii si coloane TREBUIE sa fie CEL MULT EGAL cu 40!" +
                    "Cel putin una dintre dimensiuni trebuie sa fie un numar par.\n\n");
            boolean isCorect = false;
            do {
                System.out.println("Introduceti nr de linii ale tablei de joc");
                row = sc.nextInt();

                System.out.println("Introduceti nr de coloane ale tablei de joc");
                col = sc.nextInt();
                isCorect = (((row * col) != 0) && ((row * col) % 2 == 0));
                if (isCorect != true) {
                    System.out.println("Coordonatele introduse sunt incorecte");
                }
            } while (!isCorect);
        }
        setNrRow(row); // nrRow se va seta pe valoarea implicita sau pe cea aleasa
        setNrCol(col);
    }

    private Symbol[][] hiddenMatrix; // dimensiune mare pentru int sau byte, asa ca folosim enum si creem o noua clasa
    private Symbol[][] visibleMatrix; // dimensiune mare pentru int sau byte, asa ca folosim enum si creem o noua clasa

    private int calculatedNrOfPairs = (nrRow * nrCol / 2);

    public int getCalculatedNrOfPairs() {
        return calculatedNrOfPairs;
    }

    Symbol[] symbolList = Symbol.values();

    public Playboard() { // crearea tablei de joc (matricea vizibila si a matricei de gasit (matricea ascunsa)
        hiddenMatrix = new Symbol[nrRow][nrCol]; // Matricea de descoperit
        Random x = new Random();
        int count = 1; // contor pentru nr de perechi de carti introduse
        for (Symbol symbol : symbolList) { // Completarea matricei de verificare cu perechile de carti
            if(count <= (calculatedNrOfPairs)) {
                for (int i = 1; i <= 2; i++) {
                    do {
                        int row = x.nextInt(nrRow); // Generare nr. aleatoriu [0,nrRow)
                        int col = x.nextInt(nrCol); // Generare nr. aleatoriu [0,nrcol
                        if (hiddenMatrix[row][col] == null) {
                            hiddenMatrix[row][col] = symbol; // completarea unei celule null cu o valoare
                            break;
                        }
                    } while (true);
                }
                count++; // incrementare contor nr de perechi de carrti introduse
            } else break;
        }

        visibleMatrix = new Symbol[nrRow][nrCol]; // Matricea afisata - toate casutele au valoare initiana Symbol.X
        for (int row=0; row < nrRow; row++) {
            for (int col=0; col< nrCol; col++) {
                visibleMatrix[row][col] = Symbol.X;
            }
        }
    }

    private boolean isValid(int[] coord) { // verificare daca s-au introdus coordonate valide (in matrice)
        int row = coord[0];
        int col = coord[1];
        return (((row >= 0) && (row < nrRow)) && ((col >= 0) && (col < nrCol)));
    }

    private boolean isVisible(int[] coord) { // verificare daca este goala casuta solicitata
        int row = coord[0];
        int col = coord[1];
        return (visibleMatrix[row][col] != Symbol.X);
    }

    void turnTheCard(int[] coord1) { // Afisare carte selectata
        int row = coord1[0];
        int col = coord1[1];
        visibleMatrix[row][col] = hiddenMatrix[row][col];
    }

    private void returnTheCard(int row, int col) { // Afisare carte selectata
        visibleMatrix[row][col] = Symbol.X; // intoarcerea cartilor
    }

    public boolean isCoincidency(int[]coord1, int[]coord2){ // veriificare egalitate pentru cele 2 carti selectate
        boolean isCoincidency;
        int row1 = coord1[0]; // Coord carte 1
        int col1 = coord1[1];
        int row2 = coord2[0]; // Coord carte 2
        int col2 = coord2[1];
        isCoincidency = visibleMatrix[row1][col1] == visibleMatrix[row2][col2]; // visibleMatrix[row2][col2].toString
        if(!isCoincidency) {
            System.out.println("Nu este coincidenta, cartile se intorc");
            try { // try/catch solicitat de metoda sleep()
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            returnTheCard(row1, col1); // intoarcerea cartilor
            returnTheCard(row2, col2);
        } else {
            System.out.println("Este coincidenta");
        }
        return isCoincidency;
    }

    public boolean checkCoord(int[] coord) { // verificare integrata a coordonatelor (se apeleaza de arbitru)
        boolean verificareCoord = false;
        if (!isValid(coord) || isVisible(coord)) {
            System.out.println("\"Mutare neacceptata - indicatie in afara casutei sau casuta ocupata. " +
                            "Introduceti alte coordonate!");
        } else {
            verificareCoord = true;
        }
        return verificareCoord;
    }

    @Override
    public String toString() {
        String txt = "|   ";
        String line ="";
        for (int row=0; row < nrRow; row++) {
            line = "+---";
            for (int col=0; col< nrCol; col++) {
                txt = txt + visibleMatrix[row][col] + "   ";
//                txt = txt + hiddenMatrix[row][col] + "   "; // pentru afisarea matricei de verificare
                line = line + "----";
                if(col == nrCol - 1 ) {
                    line = line + '+';
                }
            }
            if(row < nrRow-1) { // trasarea semnelor liniilor verticale ale tablei
                txt = txt + "|\n|   ";
            } else {
                txt = txt + "|\n";
            }
        }

        txt = "\n" + line + "\n" + txt + line + "\n";

        return txt;
    }

}