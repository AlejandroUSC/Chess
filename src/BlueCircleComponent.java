import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * BlueCircleComponent Class extends the JComponent class
 * Lays out transparent blue circles based on the possibilities of where
 * the piece can move too
 */
public class BlueCircleComponent extends JComponent {
    private static final int radius = 80;
    private static final int X_Y_DIMENSION = 100;
    private ArrayList<Coordinate> list;

    /**
     * Default Constructor of the BlueCircleComponent Class
     */
    public BlueCircleComponent(){
        list = new ArrayList<>();
        this.repaint();
    }

    /**
     * Overloaded Constructor of the BlueCircleComponent Class
     * @param c list of possible movements
     */
    public BlueCircleComponent(ArrayList<Coordinate> c){
        list = new ArrayList<>(c);
        this.repaint();
    }

    /**
     * Overloaded PaintComponent from the JComponent Class
     * @param g graphics to be painted on X and Y coordinates
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for(Coordinate item : list){
            int x = (int)(item.getxCoord()-97) * X_Y_DIMENSION + 10;
            int y = 800 - (item.getyCoord() - 1) * X_Y_DIMENSION - 85;

            g2.setColor(new Color(0f,.5f,.75f,.5f ));
            g2.fillOval(x,y,radius,radius);
        }
    }
}
