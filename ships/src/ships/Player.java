/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author wojmo
 */
public class Player {
    private int id;
    private int lives;
    private Board board;
    private Map<Point, Boolean> targetHistory;
    private List<Point> playerShots;
    private Scanner scanner;
    private String date;
    private int shotsMissed;
    private int shotsHit;
    private int shots;
    

    /**
     * Instantiates a new Player.
     *
     * @param id the id
     */
    public Player(int id) {
        this.id = id;
        this.lives = Constants.PLAYER_LIVES;
        this.shotsMissed = 0;
        this.shotsHit = 0;
        this.shots = 0;
        this.playerShots = new ArrayList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = format.format(new Date());
        if(id == 1) this.board = new Board(false);
        else this.board = new Board(true);
        this.targetHistory = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives;
    }
    
    public String getDate() {
        return date;
    }

    public int getShotMissed() {
        return shotsMissed;
    }

    public int getShotHit() {
        return shotsHit;
    }

    public Board getBoard() {
        return board;
    }

    public int getShots() {
        return shots;
    }

    public List<Point> getPlayerShots() {
        return playerShots;
    }               

    /**
     * Decrement live by one.
     */
    public void decrementLiveByOne() {
        lives--;
    }

    
     /**
     * Turns player.
     */
    public void turnToPlay(Player opponent){
        if(id == 1){ //JESLI GRACZ
            System.out.printf("Wybierz współrzędne do oddania strzału (x y): ");
            Point point = new Point(scanner.nextInt(), scanner.nextInt());

            while(targetHistory.get(point) != null) {
            System.out.print("Juz oddałeś strzał na te współrzędne!");
            point = new Point(scanner.nextInt(), scanner.nextInt());
            }

            attackOpponent(point, opponent);
        } else { //JESLI PRZECIWNIK
            System.out.printf("Podaj współrzędne strzału przeciwnika (x y) ");
            Point point = new Point(scanner.nextInt(), scanner.nextInt());

            while(targetHistory.get(point) != null) {
                System.out.print("Ta pozycja już została wybrana!");
                point = new Point(scanner.nextInt(), scanner.nextInt());
            }

            attack(point, opponent);
        }
    }
    
     /**
     * Attack opponent.
     * @param point
     * @param opponent
     */
    private void attackOpponent(Point point, Player opponent) {
        boolean isShipHit = false;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Czy oddany strzały jest trafiony? T/N");
        boolean loopctrl = false;
        while(!loopctrl){
       char ch = sc.next().charAt(0);
        switch(ch){
            case 'T':
            case 't':
                isShipHit = true;
                loopctrl = true;
                break;
            case 'N':
            case 'n':
                isShipHit = false;
                loopctrl = true;
                break;
            default:
            System.out.println("Nieprawidłowy wybór!");
            System.out.println("Czy oddany strzały jest trafiony? T/N");
                break;
            }
        }
        this.shots++;
        this.playerShots.add(point);
        if(isShipHit) {
            this.shotsHit++;
            opponent.decrementLiveByOne();
        } else {
            this.shotsMissed++;
        }
        opponent.board.targetOpponentShip(point, isShipHit);
        targetHistory.put(point, isShipHit);
        System.out.printf("Gracz nr %d, celuje w (%d, %d)",
                id,
                (int)point.getX(),
                (int)point.getY());
        System.out.println("...i " + ((isShipHit) ? "TRAFIA!" : "NIE trafia..."));
    }

    /**
     * Attack
     *
     * @param point
     * @param opponent
     */
    private void attack(Point point, Player opponent) {
        Ship ship = opponent.board.targetShip(point);
        boolean isShipHit = (ship != null) ? true : false;

        if(isShipHit) {
            if(ship != null){
                ship.shipWasHit();
            }
            opponent.decrementLiveByOne();
        } 
        targetHistory.put(point, isShipHit);
        System.out.printf("Gracz nr %d, celuje w (%d, %d)",
                id,
                (int)point.getX(),
                (int)point.getY());
        System.out.println("...i " + ((isShipHit) ? "TRAFIA!" : "NIE trafia..."));
    }       
}