import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Referee {

    private Playboard playBoard; //tabla de joc
    private Player player; //jucatorul

    public Referee(Playboard board, Player player) { //Constructor arbitru (joc)
        this.playBoard = board;
        this.player = player;
    }

    public void start() {
        long startGameHour;
        long endGameHour;
        long gameTime;

        System.out.println("Aveti de descoperit " + playBoard.getCalculatedNrOfPairs() + " perechi de carti." +
                "\nLa fiecare tura vei intoarce cate doua carti." +
                "\nDaca aceste au aceeasi imagine/semn, vor ramane vizibile." +
                "\nDaca aceste difera, se vor intoarce in circa 3 secunde." +
                "\nPe timpul jocului se va pastra pozitia initiala a catilor, chiar daca acestea au fost ascunse.");

        startGameHour = System.currentTimeMillis(); // Inegistrarea orei de incepere a jocului

        move(); // Jocul efectiv

        endGameHour = System.currentTimeMillis();// Inegistrarea orei de terminare a jocului
        gameTime = (endGameHour - startGameHour); // Calcularea timpului de joc

        //Afisarea timpului de rezolvare a jocului
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.ss", Locale.getDefault());
        System.out.println("Durata jocului [HH:mm:ss.sss] a fost: "
                + sdf.format(new Date(gameTime - TimeZone.getDefault().getRawOffset())));
    }

    private void move() {
        int nrOfMove = 0; // numarul de ture de joc parcurse
        int remainingPairs = playBoard.getCalculatedNrOfPairs(); // numarul de perechi de carti ramase ascunse
        int[] coord1; // Coordonatele primei carti din tura de joc
        int[] coord2; // Coordonatele celei de-a carti din tura de joc

        do {
            showThePlayboard();
            System.out.println("Introduceti coordonatele primei carti ce va fi intoarsa");
            coord1 = player.getCoord();
            while (!playBoard.checkCoord(coord1)) {
                coord1 = player.getCoord();
            }
            playBoard.turnTheCard(coord1);
            showThePlayboard();

            System.out.println("Introduceti coordonatele celei de-a doua carti ce va fi intoarsa");
            coord2 = player.getCoord();
            while (!playBoard.checkCoord(coord2)) {
                coord2 = player.getCoord();
            }
            playBoard.turnTheCard(coord2);
            showThePlayboard();

            if (playBoard.isCoincidency(coord1, coord2)) {
                remainingPairs--;
            }

            // ridicarea imaginii afisate pentru crearea impresiei de ecran unic
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ramas de descoperit = " + remainingPairs + " perechi de carti.");
            System.out.println("Mutari efectuate: " + (++nrOfMove));

        } while (remainingPairs > 0);

        showThePlayboard();
        System.out.println("Felicitari!\n" +
                "Cele " + playBoard.getCalculatedNrOfPairs() + " perechi de carti au fost descoperite in " + nrOfMove + " mutari.");
    }

    private void showThePlayboard() {
        System.out.println(playBoard);
    }
}
