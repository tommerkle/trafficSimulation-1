/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 *
 * @author Tyler
 */
public class Road {
    int numLanes;
    int length;
    int width;
    int throughput = length - 50; // throughput marker 50 px less than full length - so we can see it
    int halfway = throughput/2; // halfway from start to throughput marker

    public Road(int numLanes, int length, int width) {
        this.numLanes = numLanes;
        this.length = length;
        this.width = width;
    }
    
    public void paint(Graphics g) { // paint the road
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        int xIndex = 0;
        while (xIndex < length) {
            g2.drawLine(xIndex, 10, xIndex + 50, 10);
            g2.drawLine(xIndex, 90, xIndex + 50, 90);
            xIndex += 100;
        }
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
