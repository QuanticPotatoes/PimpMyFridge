package pimpmyfridge.main;

import pimpmyfridge.controller.AbstractController;
import pimpmyfridge.controller.AppController;
import pimpmyfridge.model.AbstractModel;
import pimpmyfridge.model.AppModel;
import pimpmyfridge.view.View;

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
        AbstractController controller = new AppController(model);
        // Initialize window
        View view = new View(controller);
        // Add view to model
        model.addObserver(view);
    }
}
