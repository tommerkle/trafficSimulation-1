/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsim;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Tyler
 */
public class Vehicle {

    double posX, posY;
    int laneNum;
    double width, height;
    double velocity;
    double accRate = 4; // hard coded in for now
    double decRate = 8;
    Driver driver;
    Color c;
    public ArrayList<Color> colorArray = new ArrayList<>();

    public Vehicle() {
    }

    public Vehicle(double posX, double posY, int laneNum, double width, double height,double accRate, double decRate, Driver driver) {
        this.posX = posX;
        this.posY = posY;
        this.laneNum = laneNum;
        this.width = width;
        this.height = height;
        this.driver = driver;
        this.accRate = accRate;
        this.decRate = decRate;
        velocity = driver.getSpeed();
    }

   

    public void deccelerate() {
        velocity -= decRate;
        if (velocity < 0) {
            velocity = 0;
        }
    }

    public void accelerate() {
        velocity += accRate;
        if (velocity > 5) { // speed limit
            velocity = 5;
        }
        else if (velocity < 0.01){ // min speed
            velocity = 0.01;
        }
    }

    public void generateColor() {
        // Set colors for cars
        colorArray.add(Color.red);
        colorArray.add(Color.orange);
        colorArray.add(Color.yellow);
        colorArray.add(Color.green);
        colorArray.add(Color.blue);
        colorArray.add(Color.magenta);

        c = colorArray.get(0 + (int) (Math.random() * 5));
    }

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getLaneNum() {
        return laneNum;
    }

    public void setLaneNum(int laneNum) {
        this.laneNum = laneNum;
    }

    public double getWidth() {
        return width;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
