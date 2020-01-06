package ships;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wojmo
 */
public class Ship {
    private String name;
    private int size;
    private int livesLeft;
    private boolean isSunk;
    private Position position;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.livesLeft = size;
        this.isSunk = false;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void setSunk(boolean sunk) {
        isSunk = sunk;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void shipWasHit() {
        if(livesLeft == 0) {
            isSunk = true;
            System.out.println("You sunk the " + name);
            return;
        }
        livesLeft--;
    }
}
