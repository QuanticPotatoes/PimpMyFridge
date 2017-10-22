package pimpmyfridge.main;

import javafx.application.Application;
import javafx.stage.Stage;
import pimpmyfridge.controller.AbstractController;
import pimpmyfridge.controller.SerialController;
import pimpmyfridge.model.AbstractModel;
import pimpmyfridge.model.AppModel;
import pimpmyfridge.view.ViewFX;

/**
 * Main app
 *
 */
public class App extends Application
{
    private static ViewFX view;
    private static AbstractController controller;
    public static void main( String[] args )
    {
        // Initialize model
        AbstractModel model = new AppModel();
        // Initialize controller
        controller = new SerialController(model);
        // Initialize window
        view = new ViewFX(controller);
        // Add view to model
        model.addObserver(view);

        //Launch
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        view.start(primaryStage);
        controller.launch();
    }
}
