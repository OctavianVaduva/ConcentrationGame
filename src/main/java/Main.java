import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        int row = 4;
//        int col = 4;
//        char option;
        Scanner sc = new Scanner(System.in);
        Referee referee = new Referee(new Playboard(), new Player(sc));

//        Playboard.boardSet();
        referee.start();
    }

}
