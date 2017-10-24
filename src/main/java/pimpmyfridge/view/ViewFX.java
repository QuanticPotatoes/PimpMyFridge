package pimpmyfridge.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
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
    @FXML
    public JFXToggleButton power;
    @FXML
    public SVGPath frooze;
    @FXML
    public Label door;
    @FXML
    public SVGPath warning;
    @FXML
    public SVGPath reloadIcon;
    @FXML
    public JFXButton reloadButton;

    private Graph peltier;
    private Graph inside;
    private Graph humidity;
    private Graph rosee;
    private DecimalFormat formatter;
    private RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000),reloadIcon);
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

        formatter = new DecimalFormat("#0.0");
        rotateTransition = new RotateTransition(Duration.millis(1000),reloadIcon);
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
                    reloadButton.setDisable(model.isSerial());
                    FillTransition serialFill = new FillTransition();
                    if(model.isSerial()) {
                        serialFill.setFromValue(Color.web("#aaaaaa"));
                        serialFill.setToValue(Color.web("#1DA18A"));
                    } else {
                        serialFill.setFromValue(Color.web(usbConnect.getFill().toString()));
                        serialFill.setToValue(Color.web("#aaaaaa"));
                    }
                    serialFill.setDelay(Duration.millis(1000));
                    serialFill.setDuration(Duration.millis(1000));
                    serialFill.setShape(usbConnect);
                    serialFill.play();
                    serialFill.setOnFinished(e -> {
                        if(model.isSerial()){
                            connected.setText("CONNECTÉ");
                            connected.setTextFill(Paint.valueOf("#1DA18A"));
                        } else {
                            connected.setText("CONNEXION");
                            connected.setTextFill(Paint.valueOf("#aaaaaa"));
                        }
                        rotateTransition.stop();
                    });
                    break;
                case "frooze":
                    FillTransition froozeFill = new FillTransition();
                    if(model.isFrooze()) {
                            froozeFill.setFromValue(Color.web("#aaaaaa"));
                            froozeFill.setToValue(Color.web("#1DA18A"));
                    } else {
                            froozeFill.setFromValue(Color.web("#1DA18A"));
                            froozeFill.setToValue(Color.web("#aaaaaa"));
                    }
                    froozeFill.setDelay(Duration.millis(1000));
                    froozeFill.setDuration(Duration.millis(200));
                    froozeFill.setShape(frooze);
                    froozeFill.play();
                    break;
                case "door":
                        door.setVisible(model.isDoor());
                        warning.setVisible(model.isDoor());
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
    @FXML
    public void onPowerClick(){
        controller.setPower(power.isSelected());
    }
    @FXML
    public void onReloadClick() {
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.play();
        controller.reloadConnect();
    }
}
