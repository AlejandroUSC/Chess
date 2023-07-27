import java.util.Timer;

/**
 * Clock Class (Not yet Implemented)
 * Clock will serve as a timer for each player. When the players turn
 * is active, the clock will count down until it hits 0 or the player
 * makes a decision. Once a decision has been made, time will be added
 * to the players clock
 */
public class Clock {
    Timer timer;

    /**
     * Constructor of the Clock Class
     */
    public Clock(){
        timer = new Timer();
    }

    /**
     * When called, the Timer will run down
     */
    public void start(){

    }

    /**
     * Will add 5 seconds to the clock
     */
    public void addTime(){

    }

    /**
     * Pause the timer
     */
    public void pause(){

    }
}
