/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsim;

/**
 *
 * @author Tyler
 */
public class Driver {
    int strategy;
    double speed;

    public Driver(int strategy, double speed) {
        this.strategy = strategy;
        this.speed = speed;
    }

    
    public int getStrategy() {
        return strategy;
    }

    public void setStrategy(int strategy) {
        this.strategy = strategy;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    
    
}
