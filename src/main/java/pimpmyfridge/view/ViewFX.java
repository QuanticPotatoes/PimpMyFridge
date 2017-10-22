package pimpmyfridge.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import pimpmyfridge.controller.AbstractController;
import pimpmyfridge.model.AbstractModel;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;


class Graph {
    private LineChart chart;
    private XYChart.Series series;
    private int count = 10;

    public Graph(LineChart<Number, Number> chart) {
        this.chart = chart;

        series = new XYChart.Series();

        int maxLength = count;
        for (int i = 0; i <= maxLength; i++) {
            series.getData().add(new XYChart.Data("" + i, 0));
        }
        chart.getXAxis().setTickLabelsVisible(false);
        chart.getXAxis().setAnimated(false);
        chart.getYAxis().setAnimated(false);
        chart.setVerticalGridLinesVisible(false);
        chart.setAnimated(false);
        chart.setCreateSymbols(false);
        chart.getData().add(series);

    }

    public void update(double value) {
        series.getData().add(new XYChart.Data("" + count, value));
        count++;
        series.getData().remove(0);
    }

}

public class ViewFX extends Application implements Observer {

    private AbstractController controller;
    @FXML
    public LineChart<Number,Number> graphpeltier;
    @FXML
    public LineChart<Number,Number> graphinterieure;
    @FXML
    public LineChart<Number,Number> graphrosee;
    @FXML
    public LineChart<Number,Number> graphhumidity;
    @FXML
    public Label temp;
    @FXML
    public ProgressIndicator progresshumidity;
    @FXML
    public Label pourcenthumidity;
    private Graph peltier;
    private Graph inside;
    private Graph humidity;
    private Graph rosee;
    private DecimalFormat formatter;

    public ViewFX(AbstractController controller) {
        this.controller = controller;
    }

    public void start(final Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Paths.get("src/main/java/pimpmyfridge/view/ui.fxml").toUri().toURL());
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Pimp my fridge");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                stop();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        peltier = new Graph(graphpeltier);
        humidity = new Graph(graphhumidity);
        rosee = new Graph(graphrosee);
        inside = new Graph(graphinterieure);

        formatter = new DecimalFormat("##.#");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        controller.stop();
    }

    @Override
    public void update(Observable o, Object arg) {
        AbstractModel model = (AbstractModel) o;
        Platform.runLater(() -> {

            temp.setText(formatter.format(model.getTemp()) + "°");
            progresshumidity.setProgress(model.getHumidity() / 100);
            pourcenthumidity.setText(formatter.format(model.getHumidity()) + "%");
            // update line charts
            peltier.update(model.getTemp());
            humidity.update(model.getHumidity());
            rosee.update(model.getRosee());
            inside.update(model.getInside());
        });
    }
}
