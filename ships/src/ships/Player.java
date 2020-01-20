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
/**
 *
 * @author wojmo
 */
public class Player {
    private int id;
    private int lives;
    private Board board;
    private Map<Point, Boolean> targetHistory;
    private Scanner scanner;
    private String date;
    private int shotMissed;
    private int shotHit;
    

    /**
     * Instantiates a new Player.
     *
     * @param id the id
     */
    public Player(int id) {
        //System.out.printf("%n=== Setting up everything for Player %s ====", id);
        this.id = id;
        this.lives = Constants.PLAYER_LIVES;
        this.shotMissed = 0;
        this.shotHit = 0;
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
        return shotMissed;
    }

    public int getShotHit() {
        return shotHit;
    }

    public Board getBoard() {
        return board;
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
        if(id == 1){
        System.out.printf("Wybierz współrzędne do oddania strzału (x y): ");
        Point point = new Point(scanner.nextInt(), scanner.nextInt());

        while(targetHistory.get(point) != null) {
        System.out.print("Juz oddałeś strzał na te współrzędne!");
        point = new Point(scanner.nextInt(), scanner.nextInt());
        }

        attackOpponent(point, opponent);
        } else {
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
                break;
            }
        }
        if(isShipHit) {
            this.shotHit++;
            opponent.decrementLiveByOne();
        } else {
            this.shotMissed++;
        }
        opponent.board.targetOpponentShip(point, isShipHit);
        targetHistory.put(point, isShipHit);
        System.out.printf("Player %d, targets (%d, %d)",
                id,
                (int)point.getX(),
                (int)point.getY());
        System.out.println("...and " + ((isShipHit) ? "HITS!" : "misses..."));
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
            ship.shipWasHit();
            this.shotHit++;
            opponent.decrementLiveByOne();
        } else {
            this.shotMissed++;
        }
        targetHistory.put(point, isShipHit);
        System.out.printf("Player %d, targets (%d, %d)",
                id,
                (int)point.getX(),
                (int)point.getY());
        System.out.println("...and " + ((isShipHit) ? "HITS!" : "misses..."));
    }       
}