import java.util.ArrayList;
import java.util.Collections;

/**
 * Bishop Class
 * Extension of the ChessPiece Class, Inherits several methods, and overrides the
 * possibleMoves() method twice.
 * - Implemented
 *      - The ability to move diagonally in all 4 quadrants
 *      - Takes into consideration if other pieces are in its path
 */
public class Bishop extends ChessPiece{
    private String name;
    private Coordinate coord;

    /**
     * Default Constructor of the Bishop Class
     */
    public Bishop(){
        name = "bishop";
    }

    /**
     * Overloaded Constructor of the Bishop Class
     * @param c coordinate
     */
    public Bishop(String n, Coordinate c, char color){
        super(n,c, color);
        name = n;
        coord = c;
    }

    /**
     * Gathers an ArrayList of all the possible movements the piece can take
     * Will calculate all of the possible diagonal moves, left/right, down/up
     * @return ArrayList of coordinates that can possibly be moved to legally
     */
    public ArrayList<Coordinate> possibleMoves(){
        ArrayList<Coordinate> canMove = new ArrayList<>();

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

        Collections.sort(canMove);
        return canMove;
    }

    /**
     * Gathers an ArrayList of all the possible movements the piece can take
     * Will calculate all of the possible diagonal moves, left/right, down/up
     * @param pieces list of all the pieces on the board
     * @return ArrayList of coordinates that can possibly be moved to legally
     */
    public ArrayList<Coordinate> possibleMoves(ArrayList<ChessPiece> pieces){
        ArrayList<Coordinate> canMove = new ArrayList<>();

        char colChar = coord.getxCoord();
        int row = coord.getyCoord();
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
