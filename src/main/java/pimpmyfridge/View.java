package pimpmyfridge;

import pimpmyfridge.controller.AbstractController;
import pimpmyfridge.model.AbstractModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private AbstractController controller;
    private JPanel panel;
    private JLabel label;
    private JButton testButton;
    private ButtonAction buttonAction;

    public View(AbstractController controller) {
        this.controller = controller;
        this.panel = new JPanel();
        this.label = new JLabel("test");
        this.testButton = new JButton("Mon bouton");
        this.setSize(500,500);
        this.setTitle("Pimp My Fridge");
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(this.label);
        this.add(this.testButton);
        this.buttonAction = new ButtonAction(this.controller);
        testButton.addActionListener(buttonAction);
    }

    private class ButtonAction implements ActionListener {

        private AbstractController controller;
        private ButtonAction(AbstractController controller) {
            this.controller = controller;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            this.controller.sendTemp(15);
        }
    }

    public void update(Observable observable, Object o) {
        AbstractModel model = (AbstractModel) observable;
        this.label.setText("" + model.getTemp());
    }
}
