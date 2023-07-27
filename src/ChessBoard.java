/**
 * ChessBoard Class
 * This class holds the information of all the pieces on the
 * current board, and any addition checks. Takes in 2 players
 * in order to function.
 */
public class ChessBoard {
    private Clock timerOne;
    private Clock timerTwo;
    private boolean inCheck;
    private int playerTurn;
    private Player white;
    private Player black;

    /**
     * Default Constructor of the ChessBoard Class
     */
    public ChessBoard(){
        timerOne = new Clock();
        timerTwo = new Clock();
        inCheck = false;
        playerTurn = -1;
    }

    /**
     * Overloaded Constructor of the ChessBoard class
     * @param one player one
     * @param two player two
     */
    public ChessBoard(Player one, Player two){
        white = one;
        black = two;
    }

    /**
     * Will check if the king is in Check
     * @return true/false
     */
    public boolean checkForCheck(){
        return false;
    }

    /**
     * Begins the timer of the parameter passed
     * @param c clock to be started
     */
    public void startTimer(Clock c){
        c.start();
    }

    /**
     * Will add time to the Clock passed
     * @param c clock to be incremented
     */
    public void addTime(Clock c){
        c.addTime();
    }

    /**
     * Set the turn of the player
     */
    public void setPlayerTurn(int player){
        playerTurn = player;
    }

    /**
     * Retrieve the players turn info
     * @return playerTurn
     */
    public int getPlayerTurn(){
        return playerTurn;
    }
}
