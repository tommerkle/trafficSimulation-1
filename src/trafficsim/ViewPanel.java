/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tyler
 */
public class ViewPanel extends javax.swing.JPanel implements Viewable {

    public Model model = new Model();
    public Road road = model.getRoad(); // added a road to the panel to display
    public int roadLength = road.length; // get the road's length

    private Thread t;

    public ViewPanel() {
        initComponents();
        add(crashLabel);
        setSize(roadLength, 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int index = 0;

        ArrayList<ProtoVehicle> carsArray = model.getCars();
        //System.out.println("Number of cars: " + carsArray.size());
        for (int i = 0; i < carsArray.size(); i++) {
            crashLabel.setText(""); // clear collision test
            if (carsArray.size() > 1 && i > 0) {
                try {
                    checkColisions(g, i);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ViewPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // set color
            Color c = carsArray.get(i).getC();
            drawCar(g, carsArray.get(i), c);
            road.paint(g); // paint the road
            index++;
            if (index > 5) {
                index = 0;
            }
        }

    }

    @Override
    public void Display() {
    }

    public void drawCar(Graphics g, ProtoVehicle car, Color C) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(C);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        double x = car.getPosX() - car.getWidth() / 2; // Modify x,y location from center
        double y = car.getPosY() - car.getHeight() / 2;
        double width = car.getWidth();
        double height = car.getHeight();
        double windowPertX = (width * 0.6);
        double windowPertY = (height * 0.1);
        double halfDiffX = (0.3 * width);
        double halfDiffY = (height - ((height * 0.2)));

        g2.fill(new Rectangle2D.Double(x, y, width, height));

        // draw window, trial and error LOL
        g2.setColor(Color.BLACK);
        g2.fill(new Rectangle2D.Double(x + windowPertX, y + windowPertY, halfDiffX, halfDiffY));
        g2.draw(new Rectangle2D.Double(x + (width / 4), y + (height / 4), width / 2, height / 2));

        drawTires(g, x, y, width, height);
    }

    public void drawTires(Graphics g, double x, double y, double width, double height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        double tireWidth = width / 4.0; // Tires proportianate to car
        double tireHeight = height / 6.0;
        double offsetWidth = tireWidth / 2.0;
        double offsetHeight = tireHeight / 2.0;

        g.setColor(Color.BLACK);
        g2.fill(new Rectangle2D.Double(x, y - offsetHeight, tireWidth, tireHeight)); //draw tire 1     
        g2.fill(new Rectangle2D.Double(x + width - tireWidth, y - offsetHeight, tireWidth, tireHeight)); // tire 2
        g2.fill(new Rectangle2D.Double(x, y + height - offsetHeight, tireWidth, tireHeight)); // etc...
        g2.fill(new Rectangle2D.Double(x + width - tireWidth, y + height - offsetHeight, tireWidth, tireHeight));

        //draw cool details
        g2.draw(new Line2D.Double(x, y, x + (width / 4), y + (height / 4)));
        g2.draw(new Line2D.Double(x, y + height, x + (width / 4), y + (height / 4) + (height / 2)));

    }

    public void checkColisions(Graphics g, int i) throws InterruptedException {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        ProtoVehicle car = model.getCars().get(i);
        ProtoVehicle carFront = model.getCars().get(i - 1);
        double front = car.getPosX() + car.getWidth() / 2;
        double bumper = carFront.getPosX() - carFront.getWidth() / 2;;

        if (Math.abs((front - bumper)) < 5) {
            car.setVelocity(0);
            g.setColor(Color.red);
            g.fillOval((int) bumper, (int) car.getPosY() - ((int) car.getHeight() / 2), (int) car.getWidth() / 5, (int) car.getHeight());
//                crashLabel.setText("BAM!!");
//                System.out.println("Bam!");
            // t.sleep(100);

        }

    }

    public void setModel(Model model) {
        this.model = model;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        crashLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(478, 478, 478)
                .addComponent(crashLabel)
                .addContainerGap(553, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 146, Short.MAX_VALUE)
                .addComponent(crashLabel))
        );
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    private javax.swing.JLabel crashLabel;
    // End of variables declaration                   
}
