package pimpmyfridge;

import pimpmyfridge.controller.AbstractController;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private AbstractController controller;
    private JPanel panel;

    public View(AbstractController controller) {
        this.controller = controller;
        this.setSize(500,500);
        this.setTitle("Pimp My Fridge");
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void update(Observable observable, Object o) {

    }
}
