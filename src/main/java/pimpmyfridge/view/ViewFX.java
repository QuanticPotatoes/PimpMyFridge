package pimpmyfridge.view;

import com.jfoenix.controls.JFXSlider;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;
import pimpmyfridge.controller.AbstractController;
import pimpmyfridge.model.AbstractModel;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
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
    @FXML
    public JFXSlider regletemp;
    @FXML
    public Label order;
    @FXML
    public ProgressIndicator progresstemp;
    @FXML
    public SVGPath usbConnect;
    @FXML
    public Label connected;

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
        String valueToUpdate = (String) arg;
        Platform.runLater(() -> {
            switch (valueToUpdate) {
                case "temp":
                    temp.setText(formatter.format(model.getTemp()) + "°");
                    peltier.update(model.getTemp());
                    Timeline tempTimeline = new Timeline(
                            new KeyFrame(Duration.ZERO, new KeyValue(progresstemp.progressProperty(),progresstemp.getProgress())),
                            new KeyFrame(Duration.millis(500), new KeyValue(progresstemp.progressProperty(), model.progressGoal()))
                    );
                    tempTimeline.setCycleCount(0);
                    tempTimeline.play();
                    break;
                case "humidity":
                    Timeline humTimeline = new Timeline(
                            new KeyFrame(Duration.ZERO, new KeyValue(progresstemp.progressProperty(),progresshumidity.getProgress())),
                            new KeyFrame(Duration.millis(500), new KeyValue(progresshumidity.progressProperty(), model.getHumidity()/100))
                    );
                    humTimeline.setCycleCount(0);
                    humTimeline.play();
                    pourcenthumidity.setText(formatter.format(model.getHumidity()) + "%");
                    humidity.update(model.getHumidity());
                    break;
                case "rosee":
                    rosee.update(model.getRosee());
                    break;
                case "inside":
                    inside.update(model.getInside());
                    break;
                case "order":
                    order.setText(formatter.format(model.getOrder()) + "°");
                    break;
                case "serial":
                    FillTransition fill = new FillTransition();
                    fill.setFromValue(Color.web("#aaaaaa"));
                    fill.setToValue(Color.web("#1DA18A"));
                    fill.setDelay(Duration.millis(1000));
                    fill.setDuration(Duration.millis(200));
                    fill.setShape(usbConnect);
                    fill.play();
                    fill.setOnFinished(e -> {
                        connected.setText("CONNECTÉ");
                        connected.setTextFill(Paint.valueOf("#1DA18A"));
                    });
                    break;
                default:
                    break;
            }

        });
    }
    @FXML
    public void OnMouseDragged() {
        controller.setOrder(regletemp.getValue());
    }
    @FXML
    public void OnMouseReleased() {
        controller.setGoal(regletemp.getValue());
        controller.sendData("order", String.valueOf(regletemp.getValue()));
    }
}
