package ships;


import java.awt.Point;
import java.util.Scanner;

/**
 *
 * @author wojmo
 */
public class Board {
    private static final Ship[] ships;
    private char[][] board;


     static {
        ships = new Ship[]{
                new Ship("Czteromasztowiec1", Constants.FOUR_MASTED_SHIP_SIZE),
               // new Ship("Trojmasztowiec1", Constants.THREE_MASTED_SHIP_SIZE),
              //  new Ship("Trojmasztowiec2", Constants.THREE_MASTED_SHIP_SIZE),
               // new Ship("Dwumasztowiec1", Constants.TWO_MASTED_SHIP_SIZE),
               // new Ship("Dwumasztowiec2", Constants.TWO_MASTED_SHIP_SIZE),
               // new Ship("Dwumasztowiec3", Constants.TWO_MASTED_SHIP_SIZE),
                //new Ship("Jednomasztowiec1", Constants.ONE_MASTED_SHIP_SIZE),
                //new Ship("Jednomasztowiec2", Constants.ONE_MASTED_SHIP_SIZE),
                //new Ship("Jednomasztowiec3", Constants.ONE_MASTED_SHIP_SIZE),
                //new Ship("Jednomasztowiec4", Constants.ONE_MASTED_SHIP_SIZE)
        };
    }


    public Board() {
        board = new char[Constants.BOARD_SIZE][Constants.BOARD_SIZE];

        for(int i = 0; i < Constants.BOARD_SIZE; i++) {
            for(int j = 0; j < Constants.BOARD_SIZE; j++) {
                board[i][j] = Constants.BOARD_ICON;
            }
        }

        placeShipsOnBoard();
    }


    /**
     * Target ship ship.
     *
     * @param point the point
     * @return ship
     */
    public Ship targetShip(Point point) {
        boolean isHit = false;
        Ship hitShip = null;
        for(int i = 0; i < ships.length; i++) {
            Ship ship = ships[i];
            if(ship.getPosition() != null) {
                if(Utils.isPointBetween(point, (Position) ship.getPosition())) {
                    isHit = true;
                    hitShip = ship;
                    break;
                }

            }
        }
        final char result = isHit ? Constants.SHIP_IS_HIT_ICON : Constants.SHOT_MISSED_ICON;
        updateShipOnBoard(point, result);
        printBoard();

        return (isHit) ? hitShip : null;
    }

    /**
     * Place ships on board.
     */
    private void placeShipsOnBoard() {
        System.out.printf("%nAlright - Time to place out your ships%n%n");
        printBoard();
        Scanner s = new Scanner(System.in);

        for(int i = 0; i < ships.length; i++) {
            Ship ship = ships[i];
            boolean isShipPlacementLegal = false;

            System.out.printf("%nEnter position of %s (length  %d): ", ship.getName(), ship.getSize());

            while(!isShipPlacementLegal) {
                try {
                    Point from = new Point(s.nextInt(), s.nextInt());
                    Point to = new Point(s.nextInt(), s.nextInt());

                    while(ship.getSize() != Utils.distanceBetweenPoints(from, to)) {
                        System.out.printf("Okręt, który próbujesz umieścić ma długość: %d. Podaj prawidłowe współrzędne!",
                                ship.getSize());

                        from = new Point(s.nextInt(), s.nextInt());
                        to = new Point(s.nextInt(), s.nextInt());
                    }
                    Position position = new Position(from, to);

                    if(!isPositionOccupied(position)) {
                        if(!isSpaceBetweenShips(position)){
                            System.out.println("W pobliżu znajduje się inny okręt! Spróbuj ponownie");
                        } else {
                            drawShipOnBoard(position);
                            ship.setPosition(position);
                            isShipPlacementLegal = true;
                        }                        
                    }  else {
                            System.out.println("Na tej pozycji znajduje się już inny okręt! Spróbuj ponownie");
                        }                        
                    

                } catch(IndexOutOfBoundsException e) {
                    System.out.println("Invalid coordinates - Outside board");
                }
            }
        }

    }


    private void updateShipOnBoard(Point point, final char result) {
        int x = (int) point.getX() - 1;
        int y = (int) point.getY() - 1;
        board[y][x] = result;
    }

    /**
     *
     * @param position
     * @return
     */
    private boolean isPositionOccupied(Position position) {
        boolean isOccupied = false;
        Point from = position.getFrom();
        Point to = position.getTo();

        outer:
        for(int i = (int) from.getY() - 1; i < to.getY(); i++) {
            for(int j = (int) from.getX() - 1; j < to.getX(); j++) {
                if(board[i][j] == Constants.SHIP_ICON) {
                    isOccupied = true;
                    break outer;
                }
            }
        }


        return isOccupied;
    }
    
    
     /**
     *
     * @param position
     * @return
     */
    private boolean isSpaceBetweenShips(Position position){
        Point from = position.getFrom();
        Point to = position.getTo();
        boolean isSpace = true;
        outer:
           for(int i = (int) from.getY() - 1; i < to.getY(); i++) {
            for(int j = (int) from.getX() - 1; j < to.getX(); j++) {
                         if(board[i+1 >= 10 ? 9 : i+1][j] == Constants.SHIP_ICON ||
                        board[i-1 < 0 ? 0 : i-1][j] == Constants.SHIP_ICON  ||
                        board[i][j+1 >= 10 ? 9 : j+1] == Constants.SHIP_ICON  ||
                        board[i][j-1 < 0 ? 0 : j-1] == Constants.SHIP_ICON) {
                          isSpace=false;
                          break outer;
                        }               
            }
        }
        return isSpace;
    }

    /**
     *
     * @param position
     */
    private void drawShipOnBoard(Position position) {
        Point from = position.getFrom();
        Point to = position.getTo();
        for(int i = (int) from.getY() - 1; i < to.getY(); i++) {
            for(int j = (int) from.getX() - 1; j < to.getX(); j++) {
                board[i][j] = Constants.SHIP_ICON;
            }
        }
        printBoard();
    }

    
    
    private void printBoard() {
        System.out.print("\t");
       
        for(int i = 0; i < Constants.BOARD_SIZE; i++) {            
            System.out.print(Constants.BOARD_LETTERS[i] +"("+(i+1)+")" + "\t");
        }

        System.out.println();

        for(int i = 0; i < Constants.BOARD_SIZE; i++) {
            System.out.print((i+1) + "\t");
            for(int j = 0; j < Constants.BOARD_SIZE; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

}