package CW.view;

import CW.controller.AbstractController;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractView extends JFrame implements IView{
    protected AbstractController controller;

    protected void initialize(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();

        this.setVisible(true);
    }

    public void setController(AbstractController abstractController){
        this.controller = controller;
    }

    public abstract void update();


}
