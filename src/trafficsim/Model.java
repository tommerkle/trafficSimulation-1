/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsim;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Tyler
 */
public class Model implements Modelable {

    ArrayList<Vehicle> cars = new ArrayList<>();
    Road road = new Road(1, 1800, 100);
   
    double averageCarWidth = 40;
    double avgAcc = 0.01;
    double avgDecc = 0.02;
    double avgSpeed = 2.0;
    double fov = 40;
    
    int countSteps = 0;
    int spawnRate = 100; // lower means more frequent\
    //double max double  discreetize pixels to convert speed to location.us

    public Model() {
        addNewCar();
    }

    @Override
    public void step() {

        // update car speeds
        stratOne();

        //move traffic
        moveTraffic();


        boolean safeToMerge = false;
        double posLastCar = cars.get(cars.size() - 1).getPosX();
        if (posLastCar > 120) {
            safeToMerge = true;
        }

        // Add a new car once every 10 steps
        if (countSteps % spawnRate == 0 && safeToMerge) { //posLastCar > averageCarWidth*2.0
            addNewCar();
        }
        countSteps++;
    }

    public void moveTraffic() {
        Vehicle car = new Vehicle();

        for (int i = 0; i < cars.size(); i++) {

            car = cars.get(i); // get car
            double speed = car.getVelocity();// get speed

            car.setPosX(car.getPosX() + speed); // get speed

            //Check if car is at end of road
            if (car.getPosX() > road.getLength()) {
                cars.remove(i);
            }
        }
    }

    public void stratOne() {
        Vehicle car = new Vehicle();
        Vehicle carFront = new Vehicle();
        double mySpeed;
        double matchSpeed;
        double speedDiff;

//        if (cars.size() > 0) {
//            cars.get(0).accelerate();
//        }

        if (cars.size() > 1) {
            for (int i = 1; i < cars.size(); i++) {
                car = cars.get(i); // get car 1
                carFront = cars.get(i - 1); // get car 2
                matchSpeed = carFront.getVelocity();
                mySpeed = car.getVelocity();
                speedDiff = mySpeed - matchSpeed;

                double distance = carFront.getPosX() - car.getPosX();


                if (distance < fov && speedDiff > 0) { // 2 car lengths! brake
                    car.deccelerate();
                } else if (distance > fov && speedDiff < 0) { // lots of space
                    car.accelerate();
                } else if (distance > 400) {  // open road, accelerate
                    car.accelerate();
                } else if (car.velocity <= 0) { // stopped cars with nothing in front...
                    car.accelerate();
                }

            }
        }

    }

    public void addNewCar() {
        // vars for pertubations
        double speedVar = 0.1 + (Math.random() * 1.5);
        double variance2 = 0.001 + (Math.random() * 0.005);
        double sizePert = 1.0 + (Math.random() * 15.0) * -1.0; // inverse, small fast, big small
        int laneVariance = (int) (1 + (Math.random() * road.numLanes));

        if (Math.random() > 0.5) {
            speedVar = speedVar * -1.0;
            variance2 = variance2 * -1.0;
            sizePert = sizePert * -1.0;
        }
        // geneerate car with some "perturbation"
        Driver driver = new Driver(1, avgSpeed + speedVar); // strat, speed
        Vehicle newCar = new Vehicle(0, 50.0, road.numLanes, averageCarWidth + sizePert, (averageCarWidth + sizePert) / 2.0, avgAcc + variance2, avgDecc + variance2, driver);

        newCar.generateColor();
        cars.add(newCar);
    }

    @Override
    public void init() {
    }

    public ArrayList<Vehicle> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Vehicle> cars) {
        this.cars = cars;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public int getCountSteps() {
        return countSteps;
    }

    public void setCountSteps(int countSteps) {
        this.countSteps = countSteps;
    }
}
