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
    private JLabel temp;
    private JButton testButton;
    private JLabel humidity;
    private JLabel order;
    private JLabel rosee;
    private ButtonAction buttonAction;

    public View(AbstractController controller) {
        this.controller = controller;
        /**
         * Instantiation
         */
        panel = new JPanel();
        temp = new JLabel("temp");
        humidity = new JLabel("hum");
        order = new JLabel("order");
        rosee = new JLabel("rosee");
        testButton = new JButton("Mon bouton");
        /**
         * Initialize the Frame
         */
        setSize(500,500);
        setTitle("Pimp My Fridge");
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonAction = new ButtonAction(controller);
        testButton.addActionListener(buttonAction);
        /**
         * Add the component in the view
         */
        add(temp);
        add(testButton);
        add(humidity);
        add(order);
        add(rosee);
        setVisible(true);

    }

    private class ButtonAction implements ActionListener {

        private AbstractController controller;
        private ButtonAction(AbstractController controller) {
            this.controller = controller;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            // this.controller.setTemp(15);
            controller.sendData(35,15);
        }
    }

    public void update(Observable observable, Object o) {
        AbstractModel model = (AbstractModel) observable;
        temp.setText("" + model.getTemp());
        humidity.setText("" + model.getHumidity());
        rosee.setText("" + model.getRosee());
    }
}
