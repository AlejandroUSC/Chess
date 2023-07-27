import java.util.ArrayList;
import java.util.Collections;

/**
 * Rook Class
 * Extension of the ChessPiece Class, Inherits several methods, and overrides the
 * possibleMoves() method twice.
 * - Implemented
 *      - The ability to move up,down,left,right
 *      - Takes into consideration if other pieces are in its path
 * - Need to do
 *      -Add castling ability
 */
public class Rook extends ChessPiece{
    private String name;
    private Coordinate coord;

    /**
     * Default Constructor of the Rook Class
     */
    public Rook(){
        name = "rook";
    }

    /**
     * Overloaded Constructor of the Rook Class
     * @param c coordinate
     */
    public Rook(String n, Coordinate c, char color){
        super(n,c, color);
        name = n;
        coord = c;
    }

    /**
     * Gathers an ArrayList of all the possible movements the piece can take
     * Rook will scan through all the rows and all the columns
     * @return ArrayList of coordinates that can possibly be moved to legally
     */
    public ArrayList<Coordinate> possibleMoves(){
        ArrayList<Coordinate> canMove = new ArrayList<>();

        char colChar = coord.getxCoord();
        int intCol = colChar - 97; // 'a' - 97 = 0
        int row = coord.getyCoord();

        // Check Left - base on x
        boolean left = true;
        for(int i = intCol-1; i >= 0 && left; i--){
            canMove.add(new Coordinate((char)(i+97), row));
        }

        // Check Right - base on X
        boolean right = true;
        for(int i = intCol+1; i < 8 && right; i++){
            canMove.add(new Coordinate((char)(i+97),row));
        }

        // Check Up - base on Y
        boolean up = true;
        for(int i = row+1; i <= 8 && up; i++){
            canMove.add(new Coordinate(colChar, i));
        }

        // Check Down - base on Y
        boolean down = true;
        for(int i = row-1; i > 0 && down; i--){
            canMove.add(new Coordinate(colChar, i));
        }

        Collections.sort(canMove);
        return canMove;
    }

    /**
     * Gathers an ArrayList of all the possible movements the piece can take
     * Rook will scan through all the rows and all the columns
     * @param pieces list of all the pieces on the board
     * @return ArrayList of coordinates that can possibly be moved to legally
     */
    public ArrayList<Coordinate> possibleMoves(ArrayList<ChessPiece> pieces){
        ArrayList<Coordinate> canMove = new ArrayList<>();

        char colChar = coord.getxCoord();
        int intCol = colChar - 97; // 'a' - 97 = 0
        int row = coord.getyCoord();

        // Check Left - base on x
        boolean left = true;
        for(int i = intCol-1; i >= 0 && left; i--){
            for(ChessPiece p : pieces) {
                if(p.getCoord().equals(new Coordinate((char)(i+97), row))){
                    if(p.getColor() != getColor())
                        canMove.add(new Coordinate((char)(i+97), row));
                    left = false;
                    break;
                }

            }
            if(left)
                canMove.add(new Coordinate((char)(i+97), row));
        }

        // Check Right - base on X
        boolean right = true;
        for(int i = intCol+1; i < 8 && right; i++){
            for(ChessPiece p : pieces) {
                if(p.getCoord().equals(new Coordinate((char)(i+97), row))){
                    if(p.getColor() != getColor())
                        canMove.add(new Coordinate((char)(i+97), row));
                    right = false;
                    break;
                }
            }
            if(right)
                canMove.add(new Coordinate((char)(i+97),row));
        }

        // Check Up - base on Y
        boolean up = true;
        for(int i = row+1; i <= 8 && up; i++) {
            for (ChessPiece p : pieces) {
                if (p.getCoord().equals(new Coordinate(colChar, i))) {
                    if(p.getColor() != getColor())
                        canMove.add(new Coordinate(colChar, i));
                    up = false;
                    break;
                }
            }
            if (up)
                canMove.add(new Coordinate(colChar, i));
        }

        // Check Down - base on Y
        boolean down = true;
        for(int i = row-1; i > 0 && down; i--){
            for(ChessPiece p : pieces) {
                if (p.getCoord().equals(new Coordinate(colChar, i))) {
                    if(p.getColor() != getColor())
                        canMove.add(new Coordinate(colChar, i));
                    down = false;
                    break;
                }
            }
            if(down)
                canMove.add(new Coordinate(colChar, i));
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


















