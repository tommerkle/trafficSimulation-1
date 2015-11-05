/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsim;

/**
 *
 * @author Tyler
 */
public class Road {
    int numLanes;
    int length;
    int width;

    public Road(int numLanes, int length, int width) {
        this.numLanes = numLanes;
        this.length = length;
        this.width = width;
    }

    
    public int getNumLanes() {
        return numLanes;
    }

    public void setNumLanes(int numLanes) {
        this.numLanes = numLanes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    
}
