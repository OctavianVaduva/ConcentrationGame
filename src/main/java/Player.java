import java.util.Scanner;

public class Player {

    private Scanner sc; // butoane apasate

    public Player(Scanner sc) {
        this.sc = sc;
    }

    public int[] getCoord() {
        int row;
        int col;
            System.out.print("Introduceti numarul liniei [1- ... ] ");
            row = sc.nextInt() - 1; //Conversia coordonatelor munerice 1-nrRow in coordonate array 0-(length-1)
            System.out.print("Introduceti numarul coloanei [1- ... ] ");
            col = sc.nextInt() - 1; //Conversia coordonatelor munerice 1-nrCol in coordonate array 0-(length-1
        return new int[]{row,col};
    }

    public int[] getDimension() {
        int newRow = 4;
        int newCol = 4;

        return new int[]{newRow,newCol};
    }

}
