import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Playboard.boardSet();

        Scanner sc = new Scanner(System.in);
        Referee referee = new Referee(new Playboard(Playboard.nrRow, Playboard.nrCol), new Player(sc));

        referee.start();
    }

}
