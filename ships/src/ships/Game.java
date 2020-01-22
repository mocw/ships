package ships;

/**
 *
 * @author wojmo
 */
public class Game {
    private Player[] players;
    private UserData userData;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        players = new Player[]{
                new Player(1),
                new Player(2) //przeciwnik
        };
        userData = UserData.getInstance();
    }
    /**
     * Start.
     */
    public void start() {
        int i = 0;
        int j = 1;
        int size = players.length;
        Player player = null;
        while(players[0].getLives() > 0 && players[1].getLives() > 0) {
            players[i++ % size].turnToPlay(players[j++ % size]);
            player = (players[0].getLives() < players[1].getLives()) ?
                    players[1] :
                    players[0];
        }
        String winner = player.getId() == 1 ? "Wygrałeś!" : "Przeciwnik zwycięża!";
        userData.saveStats(players[0],player.getId() == 1 ? true : false);
        System.out.printf(winner);
    }
}
