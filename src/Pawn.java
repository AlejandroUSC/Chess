import java.util.ArrayList;
import java.util.Collections;

/**
 * Pawn Class
 * Extension of the ChessPiece Class, Inherits several methods, and overrides the
 * possibleMoves() method twice.
 * - Implemented
 *      - Which direction the piece will face depending on its color
 *      - The ability to move forward, and diagonally to capture
 *      - Boolean to check if it is the pieces first movement
 *      - Takes into consideration if other pieces are in its path
 * - Need to do
 *      - En Passant Movement
 */
public class Pawn extends ChessPiece{
    private String name;
    private Coordinate coord;
    private int[] movement;
    private boolean firstMove;

    /**
     * Default Constructor of the Pawn Class
     */
    public Pawn(){
        name = "pawn";
    }

    /**
     * Overloaded Constructor of the Pawn Class
     * @param c coordinate
     */
    public Pawn(String n, Coordinate c, char co){
        super(n,c,co);
        if(Character.toLowerCase(super.getColor()) == 'b')
            movement = new int[] {-1,-2};
        else
            movement = new int[] {1,2};

        firstMove = true;
        name = n;
        coord = c;
    }

    /**
     * Gathers an ArrayList of all the possible movements the piece can take
     * @return ArrayList of coordinates that can possibly be moved to legally
     */
    public ArrayList<Coordinate> possibleMoves(){
        ArrayList<Coordinate> canMove = new ArrayList<>();

        char currentCol = coord.getxCoord();
        int currentRow = coord.getyCoord();

        int newMin = currentRow + movement[0];
        canMove.add(new Coordinate(currentCol, newMin));

        if(firstMove) {
            int newMax = currentRow + movement[1];
            canMove.add(new Coordinate(currentCol, newMax));
        }

        Collections.sort(canMove);
        return canMove;
    }

    /**
     * Gathers an ArrayList of all the possible movements the piece can take
     * @param pieces list of all the pieces on the board
     * @return ArrayList of coordinates that can possibly be moved to legally
     */
    public ArrayList<Coordinate> possibleMoves(ArrayList<ChessPiece> pieces){
        ArrayList<Coordinate> canMove = new ArrayList<>();

        char currentCol = coord.getxCoord();
        int currentRow = coord.getyCoord();

        int newMin = currentRow + movement[0];
        canMove.add(new Coordinate(currentCol, newMin));

        if(firstMove) {
            int newMax = currentRow + movement[1];
            canMove.add(new Coordinate(currentCol, newMax));
        }
        Collections.sort(canMove);


        // Loop will check to see if the spaces ahead of it are permissible to move to
        // Will remove invalid moves
        for(ChessPiece p : pieces){
            if(canMove.contains(p.getCoord())){
                if(canMove.size() == 2 && Math.abs(p.getCoord().getyCoord()-getCoord().getyCoord()) == 1)
                    canMove.clear();
                else
                    canMove.remove(p.getCoord());
            }
        }

        // Checks to see if there are possible pieces it can take over
        for(ChessPiece p : pieces) {
            // Checks if piece is diagonal to it
            if (Math.abs(p.getCoord().getxCoord() - getCoord().getxCoord()) == 1){
                // Checks if piece is ahead of it for black pieces
                if ((p.getCoord().getyCoord() - getCoord().getyCoord()) == -1
                        && getColor() == 'b' && p.getColor() != getColor())
                    canMove.add(p.getCoord());
                // Checks if piece is ahead of it for white pieces
                if ((p.getCoord().getyCoord() - getCoord().getyCoord()) == 1
                    && getColor() == 'w' && p.getColor() != getColor())
                    canMove.add(p.getCoord());
            }
        }

        Collections.sort(canMove);
        return canMove;
    }

    /**
     * Set the firstMove to false
     */
    public void setFirstMove(){
        firstMove = false;
    }

    /**
     * Return whether its the pawns first time moving or not
     * @return
     */
    public boolean getFirstMove(){
        return firstMove;
    }

    /**
     * Sets the coordinate of the piece
     */
    public void setCoord(Coordinate coord) {
        this.coord = coord;
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
