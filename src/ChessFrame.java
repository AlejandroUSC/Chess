import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ChessFrame Class extends the JFrame Class
 * This class will handle the design layout, appearance, and
 * behind the scene mechanics of the chess game. In charge of handling
 * movement between chess pieces.
 * - Implemented
 *      - The creation of the background chessboard
 *      - The calculation of where pieces and components will go
 *      - Retrieve all the chess pieces
 *      - Inner MousePressedListener Class, implements MouseListener
 *      - Inner BlueCircleComponent Class, implements MouseListener
 *      - Removes pieces taken over
 * - Need to Do
 *      - Check for CheckMate
 *      - Implement the Clock Class
 */
public class ChessFrame extends JFrame {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;

    private int playerTurn;
    private char turnColor;
    private Player one;
    private Player two;
    private ChessBoard mainBoard;

    private JLayeredPane layerPanel;
    private JPanel panel;
    private ChessComponent component;
    private BlueCircleComponent bcc;

    /**
     * Default Constructor of the ChessFrame class
     */
    public ChessFrame(){
        setTitle("Alejandro's Chess Game");

        // Set the ChessBoard Game Configurations
        one = new Player(16,true,false);
        two = new Player(16,false,false);
        playerTurn = 1;
        turnColor = 'w';

        mainBoard = new ChessBoard(one,two);
        mainBoard.setPlayerTurn(playerTurn);

        // Set the Panel and LayeredPanel Configurations
        panel = new JPanel();
        layerPanel = new JLayeredPane();
        add(layerPanel);

        // Add Background to the panel
        createBackgroundPanel();

        // Create our Main Component
        component = new ChessComponent(mainBoard, one, two);
        component.setBounds(0,0,800,800);
        MousePressedListener listener = new MousePressedListener();
        component.addMouseListener(listener);

        // Add Panel and Component to the Layered Panel
        layerPanel.add(component, new Integer(1),0);
        layerPanel.add(panel, new Integer(0),0);

        setSize(FRAME_WIDTH,FRAME_HEIGHT+30);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Helper method, creates background panel to be set
     */
    private void createBackgroundPanel(){

        JLabel chessBackgroundLabel = new JLabel(new ImageIcon("src/images/chesswikiboard.png"));
        chessBackgroundLabel.setBounds(0,0,800,800);

        panel.add(chessBackgroundLabel);
        panel.setBounds(0,0,800,800);
        panel.setSize(FRAME_WIDTH,FRAME_WIDTH);
    }

    /**
     * Inner Class MousePressedListener
     * Will activate when a chess piece is selected
     */
    class MousePressedListener implements MouseListener{
        public void mousePressed(MouseEvent event)
        {
            int x = event.getX();
            int y = event.getY();
            int row = computeRow(y);
            char col = computeCol(x);

            Coordinate clickedCoordinate = new Coordinate(col,row);

            HashMap<ChessPiece, ArrayList<Coordinate>> allCoords = new HashMap<>();
            allCoords.putAll(one.getPieces());
            allCoords.putAll(two.getPieces());

            Iterator<Map.Entry<ChessPiece, ArrayList<Coordinate>>> iter = allCoords.entrySet().iterator();

            while (iter.hasNext()) {
                Map.Entry<ChessPiece, ArrayList<Coordinate>> item = iter.next();

                ChessPiece itemPiece;
                if(item.getKey() instanceof King)
                    itemPiece = (King)item.getKey();
                else if(item.getKey() instanceof Queen)
                    itemPiece = (Queen)item.getKey();
                else if(item.getKey() instanceof Rook)
                    itemPiece = (Rook)item.getKey();
                else if(item.getKey() instanceof Bishop)
                    itemPiece = (Bishop)item.getKey();
                else if(item.getKey() instanceof Knight)
                    itemPiece = (Knight)item.getKey();
                else
                    itemPiece = (Pawn)item.getKey();

                // Check if they have the same coordinates, and they are the same color
                // if both are true, then it is an appropriate move
                if(itemPiece.getCoord().equals(clickedCoordinate) && itemPiece.getColor() == getTurnColor()){

                    ArrayList<Coordinate> possibleMoves = itemPiece.possibleMoves(allChessPieces());

                    if(!possibleMoves.isEmpty())
                        drawPossibleMoves(possibleMoves, itemPiece);

                    break;
                }
            }
        }
        public void mouseReleased(MouseEvent event){}
        public void mouseClicked(MouseEvent event){}
        public void mouseEntered(MouseEvent event){}
        public void mouseExited(MouseEvent event) {}
    }

    /**
     * Checks bounds clicked
     * @param y takes frame coords
     * @return board coordinate
     */
    private int computeRow(int y){
        int divY = y / 100;    // Ranges from 0 to 7 : ie: 723 / 100 = 7 (bottom row clicked)
        int boardY = 8 - divY; // 8 - 7 = 1. 1 is the chess cord (bottom row)
        return boardY;
    }

    /**
     * Checks bounds clicked
     * @param x takes frame coords
     * @return board coordinate
     */
    private char computeCol(int x){
        int divX = x / 100;    // Ranges from 0 to 7 : ie: 723 / 100 = 7 (right col 'h' clicked)
        int boardX = divX + 1; // 7 + 1 = 8. 8 is equivalent to H
        char col = (char)(96 + boardX); // 96 + 8 = 104 = 'h'
        return col;
    }

    /**
     * Retrieve which players turn it is
     * @return
     */
    private int getPlayerTurn(){
        if(one.getTurn()){
            one.setTurn(true);
            two.setTurn(false);
            playerTurn = 1;
            turnColor = 'w';
            return playerTurn;
        }
        else{
            one.setTurn(false);
            two.setTurn(true);
            turnColor = 'b';
            playerTurn = 2;
            return playerTurn;
        }
    }

    /**
     * Set the players turn and unset the others
     * Repaints the component once turn has been switched
     */
    private void setPlayerTurn(){
        if(one.getTurn()){
            one.setTurn(false);
            two.setTurn(true);
            playerTurn = 2;
            turnColor = 'b';
        }
        else{
            two.setTurn(false);
            one.setTurn(true);
            playerTurn = 1;
            turnColor = 'w';
        }

        component.repaint();
    }

    /**
     * Return the current color in play motion
     * @return turnColor, whos turn it is
     */
    private char getTurnColor(){
        return turnColor;
    }

    /**
     * Private function that will draw out blue circles for possible movements
     *  and move the piece if action has been done
     * @param posMov possible movements of the piece clicked
     * @param piece the piece being moved itself
     */
    private void drawPossibleMoves(ArrayList<Coordinate> posMov, ChessPiece piece){
        bcc = new BlueCircleComponent(posMov);
        bcc.setBounds(0,0,800,800);

        class BluePressListener implements MouseListener
        {
            public void mousePressed(MouseEvent event)
            {
                int x = event.getX();
                int y = event.getY();
                int row = computeRow(y);
                char col = computeCol(x);

                Coordinate clickedCoordinate = new Coordinate(col,row);

                // If the IF statement is set true, then a selection has been made to move the chess piece there
                for(Coordinate oldItem : posMov){
                    // IF the clickedCoordinate (blue Circle) is the same coordinate as one of the possibleMove coords
                    // Then move the piece
                    if(clickedCoordinate.equals(oldItem)){
                        ChessPiece newPiece;
                        if(piece instanceof King)
                            newPiece = new King(piece.getPieceName(), clickedCoordinate, piece.getColor());
                        else if(piece instanceof Queen)
                            newPiece = new Queen(piece.getPieceName(), clickedCoordinate, piece.getColor());
                        else if(piece instanceof Rook)
                            newPiece = new Rook(piece.getPieceName(), clickedCoordinate, piece.getColor());
                        else if(piece instanceof Bishop)
                            newPiece = new Bishop(piece.getPieceName(), clickedCoordinate, piece.getColor());
                        else if(piece instanceof Knight)
                            newPiece = new Knight(piece.getPieceName(), clickedCoordinate, piece.getColor());
                        else {
                            newPiece = new Pawn(piece.getPieceName(), clickedCoordinate, piece.getColor());
                            if(((Pawn) newPiece).getFirstMove())
                                ((Pawn) newPiece).setFirstMove();
                        }

                        newPiece.setMoveList(newPiece.possibleMoves(allChessPieces()));

                        if(playerTurn == 1) {

                            // Removal of Piece
                            for(ChessPiece twoPiece : two.getPieces().keySet())
                            {
                                if(twoPiece.getCoord().equals(newPiece.getCoord())) {
                                    two.getPieces().remove(twoPiece);
                                    break;
                                }
                            }
                            one.getPieces().remove(piece);
                            one.getPieces().put(newPiece, newPiece.possibleMoves(allChessPieces()));
                        }
                        else {

                            // Removal Of Piece
                            for(ChessPiece onePiece : one.getPieces().keySet())
                            {
                                if(onePiece.getCoord().equals(newPiece.getCoord())) {
                                    one.getPieces().remove(onePiece);
                                    break;
                                }
                            }
                            two.getPieces().remove(piece);
                            two.getPieces().put(newPiece, newPiece.possibleMoves(allChessPieces()));
                        }

                        setPlayerTurn();
                    }
                    layerPanel.remove(bcc);
                    bcc.repaint();
                }

            }
            public void mouseReleased(MouseEvent event){}
            public void mouseClicked(MouseEvent event){}
            public void mouseEntered(MouseEvent event){}
            public void mouseExited(MouseEvent event) {}
        }

        MouseListener bpl = new BluePressListener();
        bcc.addMouseListener(bpl);

        layerPanel.add(bcc, new Integer(2),0);
    }

    /**
     * Creates an Array List of all the Chess pieces on the board
     * @return allChessPie, list of all active pieces
     */
    private ArrayList<ChessPiece> allChessPieces(){
        ArrayList<ChessPiece> allChessPie = new ArrayList<>();
        for(ChessPiece p : one.getPieces().keySet()){
            allChessPie.add(p);
        }
        for(ChessPiece p : two.getPieces().keySet()){
            allChessPie.add(p);
        }
        return allChessPie;
    }
}
