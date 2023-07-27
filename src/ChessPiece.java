import java.util.ArrayList;

/**
 * ChessPiece Class
 * The super class of all the chess pieces. With the implementation of inheritance
 * all the subclasses(king,queen,pawn, etc) is-a ChessPiece. This call will provide
 * basic setter and getting methods as well as methods to be Overridden.
 * - Implemented
 *      - The ability to set and get private information/variables
 *      - The ability to construct a list of possible movements
 */
public class ChessPiece {
    private String pieceName;
    private ArrayList<Coordinate> moveList;
    private boolean canMove;
    private char color;
    private Coordinate coord;

    /**
     * Default Constructor of the ChessPiece Class
     */
    public ChessPiece(){
        pieceName = "non";
        moveList = new ArrayList<>();
        canMove = false;
        color = '\0';
        coord = new Coordinate();
    }

    /**
     * Overloaded Constructor to be called by Subclasses
     * @param c
     */
    public ChessPiece(String n, Coordinate c, char color) {
        moveList = new ArrayList<>();
        pieceName = n;
        coord = c;
        this.color = color;
    }

    /**
     * Getter, retrieves pieceName
     * @return pieceName
     */
    public String getPieceName() {
        return pieceName;
    }

    /**
     * Setter method, sets PieceName
     * @param pieceName name of piece
     */
    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    /**
     * Getter, retrieves canMove
     * @return canMove - true/false
     */
    public boolean getCanMove() {
        return canMove;
    }

    /**
     * Setter method, sets canMove
     * @param canMove - true/false
     */
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    /**
     * Getter method, gets color of piece
     * @return color
     */
    public char getColor() {
        return color;
    }

    /**
     * Setter, set color of the piece
     * @param color - char
     */
    public void setColor(char color) {
        this.color = color;
    }

    /**
     * Getter, returns the coordinate of the piece
     * @return coord
     */
    public Coordinate getCoord() {
        return coord;
    }

    /**
     * Setter, return the coord of the piece
     * @param coord coordinate
     */
    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    /**
     * Method to be overloaded by the subclasses
     * @return list of possible movements to be used in higher ups
     */
    public ArrayList<Coordinate> possibleMoves(){
        return moveList;
    }

    /**
     * Method to be overloaded by the subclasses
     * @param pieces list of chess pieces
     * @return list of possible movements to be used in higher ups
     */
    public ArrayList<Coordinate> possibleMoves(ArrayList<ChessPiece> pieces){
        return moveList;
    }

    /**
     * Method that will allow to set moves
     * @param moveList possible moves
     */
    public void setMoveList(ArrayList<Coordinate> moveList) {
        this.moveList = new ArrayList<>(moveList);
    }
}
