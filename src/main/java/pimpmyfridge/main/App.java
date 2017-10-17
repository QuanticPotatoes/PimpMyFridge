package pimpmyfridge.main;

import pimpmyfridge.View;
import pimpmyfridge.controller.AbstractController;
import pimpmyfridge.controller.SerialController;
import pimpmyfridge.model.AbstractModel;
import pimpmyfridge.model.AppModel;

/**
 * Main app
 *
 */
public class App
{
    public static void main( String[] args )
    {
        // Initialize model
        AbstractModel model = new AppModel();
        // Initialize controller
        AbstractController controller = new SerialController(model);
        // Initialize window
        View view = new View(controller);
        // Add view to model
        model.addObserver(view);
    }
}
