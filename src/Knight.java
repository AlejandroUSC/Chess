import java.util.ArrayList;
import java.util.Collections;

/**
 * Knight Class
 * Extension of the ChessPiece Class, Inherits several methods, and overrides the
 * possibleMoves() method twice.
 * - Implemented
 *      - The ability to move in L shapes
 *      - Hop over pieces in its way
 */
public class Knight extends ChessPiece{
    private String name;
    private Coordinate coord;
    private int[] movementX = {-2,-2,-1,-1,1, 1, 2,2};
    private int[] movementY = {-1, 1, 2,-2,2,-2,-1,1};

    /**
     * Default Constructor of the Knight Class
     */
    public Knight(){
        name = "knight";
    }

    /**
     * Overloaded Constructor of the Knight Class
     * @param c coordinate
     */
    public Knight(String n, Coordinate c, char color){
        super(n,c, color);
        name = n;
        coord = c;
    }

    /**
     * Gathers an ArrayList of all the possible movements the piece can take
     * Will calculate all of the possible L shape movements that the knight can take
     * @return ArrayList of coordinates that can possibly be moved to legally
     */
    public ArrayList<Coordinate> possibleMoves(){
        ArrayList<Coordinate> canMove = new ArrayList<>();

        char colChar = coord.getxCoord();
        int row = coord.getyCoord();
        int col = (int)colChar - 96;

        for(int i = 0; i < 8; i++)
        {
            int newRow = row + movementX[i];
            int newCol = col + movementY[i];
            if(newRow > 0 && newCol > 0 && newRow <= 8 && newCol <= 8)
                canMove.add(new Coordinate((char)(newCol+96), newRow));
        }

        Collections.sort(canMove);
        return canMove;
    }

    /**
     * Gathers an ArrayList of all the possible movements the piece can take
     * Will calculate all of the possible L shape movements that the knight can take
     * @param pieces list of all the pieces on the board
     * @return ArrayList of coordinates that can possibly be moved to legally
     */
    public ArrayList<Coordinate> possibleMoves(ArrayList<ChessPiece> pieces){
        ArrayList<Coordinate> canMove = new ArrayList<>();

        char colChar = coord.getxCoord();
        int row = coord.getyCoord();
        int col = (int)colChar - 96;

        for(int i = 0; i < 8; i++)
        {
            int newRow = row + movementX[i];
            int newCol = col + movementY[i];
            if(newRow > 0 && newCol > 0 && newRow <= 8 && newCol <= 8)
                canMove.add(new Coordinate((char)(newCol+96), newRow));
        }

        // We will only allow the same occurrence of coordinates IF the pieces have different colors
        // IF they have the same color, remove the coordinate from the 'canMove' list
        for(ChessPiece p : pieces){
            if(canMove.contains(p.getCoord()) && getColor() == p.getColor()){
                canMove.remove(p.getCoord());
            }
        }

        Collections.sort(canMove);
        return canMove;
    }

    /**
     * Sets the coordinate of the piece
     */
    public void setCoord(Coordinate coord) {
        this.coord = new Coordinate(coord);
    }

    /**
     * To string: for displaying purposed
     * @return string format
     */
    @Override
    public String toString() {
        return "'" + name + "' @ " + coord;
    }
}
