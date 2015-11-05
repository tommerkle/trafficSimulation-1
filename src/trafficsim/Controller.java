/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficsim;

import java.util.ArrayList;

/**
 *
 * @author Tyler
 */
public class Controller {
    Model model = new Model();
    ViewPanel view = new ViewPanel();
    public Controller() {
    }

    public void step(){
            view.model.step();
            view.Display();
        
    }
    
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public ViewPanel getView() {
        return view;
    }

    public void setView(ViewPanel view) {
        this.view = view;
    }

}
