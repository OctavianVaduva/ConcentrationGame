import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Referee referee = new Referee(new Playboard(), new Player(sc));

//        Playboard.boardSet();
        referee.start();
    }

}
