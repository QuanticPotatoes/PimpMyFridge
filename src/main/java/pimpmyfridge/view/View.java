package pimpmyfridge.view;

import pimpmyfridge.controller.AbstractController;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private JPanel container = new JPanel();

    private AbstractController controller;

    public View(AbstractController controller) throws HeadlessException {
        this.controller = controller;

        /**
         * Initialize frame
         */
        this.setSize(500,500);
        this.setTitle("PimpMyFridge");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(container);
        this.setVisible(true);
    }

    public void update(Observable observable, Object o) {

    }
}
