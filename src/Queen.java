import java.util.ArrayList;
import java.util.Collections;

/**
 * Queen Class
 * Extension of the ChessPiece Class, Inherits several methods, and overrides the
 * possibleMoves() method twice.
 * - Implemented
 *      - The ability to move up,down,left,right,diagonally
 *      - Takes into consideration if other pieces are in its path
 */
public class Queen extends ChessPiece{
    private String name;
    private Coordinate coord;

    /**
     * Default Constructor of the Queen Class
     */
    public Queen(){
        name = "queen";
    }

    /**
     * Overloaded Constructor of the Queen Class
     * @param c coordinate
     */
    public Queen(String n, Coordinate c, char color){
        super(n,c, color);
        name = n;
        coord = c;
    }

    /**
     * Gathers an ArrayList of all the possible movements the piece can take
     * Calculates all of the possible queen movements, takes code from the rook and bishop
     * as they share very similar movement styles
     * @return ArrayList of coordinates that can possibly be moved to legally
     */
    public ArrayList<Coordinate> possibleMoves(){
        ArrayList<Coordinate> canMove = new ArrayList<>();

        // Bishop movements
        char colChar = coord.getxCoord();
        int row = coord.getyCoord();
        int col = (int)colChar - 96;

        // Count top left squares
        int topLeft = Math.min(8-row, col-1);
        for(int i = 1; i <= topLeft; i++)
        {
            char newCol = (char)(colChar - i);
            int newRow = row+i;
            canMove.add(new Coordinate(newCol,newRow));
        }


        // Count top right squares
        int topRight = Math.min(8 - row, 8 - col);
        for(int i = 1; i <= topRight; i++)
        {
            char newCol = (char)(colChar + i);
            int newRow = row+i;
            canMove.add(new Coordinate(newCol,newRow));
        }

        // Count bottom right squares
        int bottomRight = Math.min(row-1, 8-col);
        for(int i = 1; i <= bottomRight; i++)
        {
            char newCol = (char)(colChar + i);
            int newRow = row-i;
            canMove.add(new Coordinate(newCol,newRow));
        }

        // Count bottom left squares
        int bottomLeft = Math.min(row-1,col-1);
        for(int i = 1; i <= bottomLeft; i++)
        {
            char newCol = (char)(colChar - i);
            int newRow = row-i;
            canMove.add(new Coordinate(newCol,newRow));
        }

        // Rook movements

        int intCol = colChar - 97; // 'a' - 97 = 0

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
     * Calculates all of the possible queen movements, takes code from the rook and bishop
     * as they share very similar movement styles
     * @param pieces list of all the pieces on the board
     * @return ArrayList of coordinates that can possibly be moved to legally
     */
    public ArrayList<Coordinate> possibleMoves(ArrayList<ChessPiece> pieces){
        ArrayList<Coordinate> canMove = new ArrayList<>();

        // Rook Movements

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

        // Bishop Movements

        int col = (int)colChar - 96;

        // Count top left squares
        int topLeft = Math.min(8-row, col-1);
        boolean t_l_bool = true;
        for(int i = 1; i <= topLeft && t_l_bool; i++)
        {
            char newCol = (char)(colChar - i);
            int newRow = row+i;
            Coordinate topL = new Coordinate(newCol, newRow);
            for(ChessPiece p : pieces)
            {
                if(p.getCoord().equals(topL)){
                    if(p.getColor() != getColor())
                        canMove.add(topL);
                    t_l_bool = false;
                    break;
                }
            }
            if(t_l_bool)
                canMove.add(topL);
        }

        // Count top right squares
        boolean t_r_bool = true;
        int topRight = Math.min(8 - row, 8 - col);
        for(int i = 1; i <= topRight && t_r_bool; i++)
        {
            char newCol = (char)(colChar + i);
            int newRow = row+i;
            Coordinate topR = new Coordinate(newCol, newRow);
            for(ChessPiece p : pieces)
            {
                if(p.getCoord().equals(topR)){
                    if(p.getColor() != getColor())
                        canMove.add(topR);
                    t_r_bool = false;
                    break;
                }
            }
            if(t_r_bool)
                canMove.add(topR);
        }

        // Count bottom right squares
        boolean b_r_bool = true;
        int bottomRight = Math.min(row-1, 8-col);
        for(int i = 1; i <= bottomRight && b_r_bool; i++)
        {
            char newCol = (char)(colChar + i);
            int newRow = row-i;
            Coordinate bottomR = new Coordinate(newCol, newRow);
            for(ChessPiece p : pieces)
            {
                if(p.getCoord().equals(bottomR)){
                    if(p.getColor() != getColor())
                        canMove.add(bottomR);
                    b_r_bool = false;
                    break;
                }
            }
            if(b_r_bool)
                canMove.add(bottomR);
        }

        // Count bottom left squares
        boolean b_l_bool = true;
        int bottomLeft = Math.min(row-1,col-1);
        for(int i = 1; i <= bottomLeft && b_l_bool; i++)
        {
            char newCol = (char)(colChar - i);
            int newRow = row-i;
            Coordinate bottomL = new Coordinate(newCol, newRow);
            for(ChessPiece p : pieces)
            {
                if(p.getCoord().equals(bottomL)){
                    if(p.getColor() != getColor())
                        canMove.add(bottomL);
                    b_l_bool = false;
                    break;
                }
            }
            if(b_l_bool)
                canMove.add(bottomL);
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
