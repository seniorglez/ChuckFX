package hellofx;

import hellofx.services.QuoteService;
import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HelloFX extends Application {

        private Label quoteLabel;
        private ImageView ImageGoyo;
        private Button button;
        QuoteService service;
        private String restURL = "https://api.chucknorris.io/jokes/random";

    public void start(Stage stage) {
        quoteLabel = new Label("Loading...");

        ImageGoyo = new ImageView(new Image(HelloFX.class.getResourceAsStream("/hellofx/chucknorris.png")));
        ImageGoyo.setFitHeight(200);
        ImageGoyo.setPreserveRatio(true);
        button = new Button("New Quote");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!service.isRunning()){
                    service.restart(); // I need to call restart method because javaFX can only start a Service in the
                                        // READY state. When the program reach this line service is in state SUCCEEDED
                } else {
                    System.out.println("Service already running");
                }
            }
        });
        VBox root = new VBox(30, ImageGoyo,quoteLabel,button);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add(HelloFX.class.getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        service = new QuoteService();
        service.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                System.out.println("Service Running");
            }
        });
        quoteLabel.textProperty().bind(service.valueProperty());
        service.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}