import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Chess Component Class extension of the JComponent class
 * This class will take charge of drawing the chess pieces at
 * their respective and appropriate location
 */
public class ChessComponent extends JComponent {
    private ChessBoard mainBoard;
    private Player one;
    private Player two;

    private static final int X_Y_DIMENSION = 100;

    /**
     * Default Chess Component values
     */
    public ChessComponent(){
        one = new Player(16,true,false);
        two = new Player(16,false,false);
        mainBoard = new ChessBoard();
        this.repaint();
    }

    /**
     * Creates a new ChessBoard Component with updated player info
     * @param mainBoard main Chessboard
     * @param one Player one
     * @param two Player two
     */
    public ChessComponent(ChessBoard mainBoard, Player one, Player two) {
        this.mainBoard = mainBoard;
        this.one = one;
        this.two = two;
        this.repaint();
    }

    /**
     * PaintComponent
     * @param g graphics device
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        addPiecesHelper(g2, one.getPieces());
        addPiecesHelper(g2, two.getPieces());
    }

    /**
     * This private method will draw all the pieces in their respective position
     * @param g Graphics2D object passed
     * @param map Map of all the players pieces
     */
    private void addPiecesHelper(Graphics2D g, HashMap<ChessPiece, ArrayList<Coordinate>> map){
        ChessPiece itemPiece = null;
        for(ChessPiece item : map.keySet()){
            if(item instanceof King)
                itemPiece = (King)item;
            else if(item instanceof Queen)
                itemPiece = (Queen)item;
            else if(item instanceof Rook)
                itemPiece = (Rook)item;
            else if(item instanceof Bishop)
                itemPiece = (Bishop)item;
            else if(item instanceof Knight)
                itemPiece = (Knight)item;
            else //if(item instanceof Pawn)
                itemPiece = (Pawn)item;

            Coordinate itemCoord = itemPiece.getCoord();

            int x = (int)(itemCoord.getxCoord()-97); // ((0 though 7) - 97) * 100
            int y = 8 - itemCoord.getyCoord(); // 8 - (1 through 8) * 100

            String fileName = "src/images/" + itemPiece.getPieceName() + "_" + itemPiece.getColor() + ".png";

//            System.out.println("\n" +"Color: " + itemPiece.getColor() + " Name: " +
//                    itemPiece.getPieceName() + " Coord: " + itemPiece.getCoord() + " x: " + x + " Y: " + y);

            int topX = x * X_Y_DIMENSION;
            int topY = y * X_Y_DIMENSION;

            Image newImage = new ImageIcon(fileName).getImage();
            g.drawImage(newImage, topX,topY,null);
        }
    }
}
