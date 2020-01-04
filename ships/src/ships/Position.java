/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import java.awt.Point;

/**
 *
 * @author wojmo
 */
public class Position {
    private Point from;
    private Point to;
    
    public Position(Point from, Point to) {
        if(from.getX() > Constants.BOARD_SIZE || from.getX() < 0
                || from.getY() > Constants.BOARD_SIZE || from.getY() < 0
                || to.getX() > Constants.BOARD_SIZE || to.getX() < 0
                || to.getY() > Constants.BOARD_SIZE || to.getY() < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        this.from = from;
        this.to = to;
    }
    
    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }
    
    
}
