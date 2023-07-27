import java.util.ArrayList;
import java.util.HashMap;

/**
 * Player class
 * This class will generate the basic information for each player. Such as
 * loading/creating the initial chess pieces, and determining if its the users
 * turn or the game is over.
 * - Implemented
 *      - Generates its set of pieces with all movements
 *      - A HashMap to hold Pieces as the Key and ArrayList of Coordinates to store
 *          movements as the Key values.
 */
public class Player {
    private int piecesRemaining;
    private boolean turn;
    private boolean gameOver;
    private static int number = 0;
    private HashMap<ChessPiece, ArrayList<Coordinate>> pieces;

    /**
     * Default Constructor of the Player Class
     */
    public Player(){
        piecesRemaining = -1;
        turn = number % 2 == 1 ? true : false;
        gameOver = true;
    }

    /**
     * Overloaded Constructor of the Player Class
     * @param piecesRemainin int piecesRemaining
     * @param turn_ boolean Turn
     * @param gameOver_ boolean gameOver
     */
    public Player(int piecesRemainin, boolean turn_, boolean gameOver_){
        number++;
        piecesRemaining = piecesRemainin;
        turn = turn_;
        gameOver = gameOver_;
        pieces = new HashMap<ChessPiece, ArrayList<Coordinate>>();
        this.generateMap();
    }

    /**
     * Return the amount of piecesLeft
     * @return piecedRemaining
     */
    public int getPiecesRemaining() {
        return piecesRemaining;
    }

    /**
     * Return true/false on players turn
     * @return turn - true/false
     */
    public boolean getTurn() {
        return turn;
    }

    /**
     * Returns the state of the game
     * @return gameOver - true/false
     */
    public boolean checkGameOver() {
        return gameOver;
    }

    /**
     * Set the players turn
     * @param turn true/false
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    /**
     * Set the state of the game
     * @param gameOver true/false
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Decrease the piecesRemaining by 1
     */
    public void removePiece(){
        piecesRemaining--;
    }

    /**
     * getter method, returns the hashmap
     * @return hashmap of pieces
     */
    public HashMap<ChessPiece, ArrayList<Coordinate>> getPieces() {
        return pieces;
    }

    /**
     * Setter method, sets pieces
     * @param p updated map
     */
    public void setPieces(HashMap<ChessPiece, ArrayList<Coordinate>> p){
        pieces = p;
    }

    /**
     * Generates the players basic information, such as its pieces and color
     */
    public void generateMap(){
        char color;
        int pawnLevel, fancyPieces;
        int playerNum = number % 2;
        // Odd will represent White
        if(playerNum == 1) {
            color = 'w';
            pawnLevel = 2;
            fancyPieces = 1;
        }
        else {
            color = 'b';
            pawnLevel = 7;
            fancyPieces = 8;
        }

        // Adds the pawns to the players pieces
        for(int i = 1; i <= 8; i++){
            ChessPiece t = new Pawn("pawn",new Coordinate((char)(96+i), pawnLevel), color);
            pieces.put(t, t.possibleMoves());
        }

        // Rooks
        ChessPiece rook1 = new Rook("rook",new Coordinate('a', fancyPieces), color);
        pieces.put(rook1, rook1.possibleMoves());
        ChessPiece rook2 = new Rook("rook",new Coordinate('h', fancyPieces), color);
        pieces.put(rook2, rook2.possibleMoves());

        // Knights
        ChessPiece knight1 = new Knight("knight",new Coordinate('b', fancyPieces), color);
        pieces.put(knight1, knight1.possibleMoves());
        ChessPiece knight2 = new Knight("knight",new Coordinate('g', fancyPieces), color);
        pieces.put(knight2, knight2.possibleMoves());

        //Bishops
        ChessPiece bishop1 = new Bishop("bishop",new Coordinate('c', fancyPieces), color);
        pieces.put(bishop1, bishop1.possibleMoves());
        ChessPiece bishop2 = new Bishop("bishop",new Coordinate('f', fancyPieces), color);
        pieces.put(bishop2, bishop2.possibleMoves());

        // King
        ChessPiece king = new King("king",new Coordinate('e', fancyPieces), color);
        pieces.put(king, king.possibleMoves());

        //Queen
        ChessPiece queen = new Queen("queen",new Coordinate('d', fancyPieces), color);
        pieces.put(queen, queen.possibleMoves());

    }
}
