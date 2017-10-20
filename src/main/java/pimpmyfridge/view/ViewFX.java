package pimpmyfridge.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pimpmyfridge.controller.AbstractController;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

public class ViewFX extends Application implements Observer {

    private AbstractController controller;
    public ViewFX(AbstractController controller) {
        this.controller = controller;
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Paths.get("src/main/java/pimpmyfridge/view/pmf.fxml").toUri().toURL());
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 275);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onClick() {
        System.out.println("test");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
