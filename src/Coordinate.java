/**
 * Coordinate Class
 * This class will implement the interface class Comparable.
 * Used to Override 'compareTo' and 'equals'. The class itself
 * is used to create coordinates to define positioning in the project.
 * And will be used in nearly every file
 * -Implemented
 *      - The ability to get/set X and Y coordinates
 *      - Compare the similarity of two set of coordinates
 */
public class Coordinate implements Comparable{
    private char xCoord;
    private int yCoord;

    /**
     * Default Constructor of the Coordinate Class
     */
    public Coordinate(){
        xCoord = 'X';
        yCoord = -1;
    }

    /**
     * Overloaded Constructor of the Coordinate Class
     * @param c Coordinate to be copied
     */
    public Coordinate(Coordinate c){
        this.xCoord = c.xCoord;
        this.yCoord = c.yCoord;
    }

    /**
     * Overloaded Constructor of the Coordinate Class
     * @param x
     * @param y
     */
    public Coordinate(char x, int y){
        xCoord = x;
        yCoord = y;
    }

    /**
     * Return the X coordinate
     * @return current X coordinate
     */
    public char getxCoord() {
        return xCoord;
    }

    /**
     * Set the X coordinate
     * @param xCoord to be set
     */
    public void setxCoord(char xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Returns the Y coordinate
     * @return the Y coordinate
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * Set the y coordinate
     * @param yCoord to be set
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * Overridden from the Comparable class
     * @param o object to be compared with
     * @return the compare statement true/false/equal
     */
    @Override
    public int compareTo(Object o) {
        Coordinate other = (Coordinate) o;
        if(xCoord < other.xCoord)
            return -1;
        if(xCoord > other.xCoord)
            return 1;

        if(yCoord < other.yCoord)
            return -1;
        if(yCoord > other.yCoord)
            return 1;

        return 0; // Equal
    }

    /**
     * Overridden equals operator
     * @param obj Coordindate operator to be compared
     * @return true/false
     */
    @Override
    public boolean equals(Object obj){
//        System.out.println(this + " " + (Coordinate)obj);
        if(this == obj)
            return true;

        if(obj == null || this.getClass() != obj.getClass())
            return false;

        Coordinate temp = (Coordinate) obj;
        if(xCoord == temp.xCoord && yCoord == temp.yCoord)
            return true;
        else
            return false;
    }

    /**
     * Prints out the chess coordinate
     * @return "e4"
     */
    @Override
    public String toString() {
        return xCoord+ Integer.toString(yCoord);
    }
}
