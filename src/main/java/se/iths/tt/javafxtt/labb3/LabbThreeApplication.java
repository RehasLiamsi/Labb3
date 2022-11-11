package se.iths.tt.javafxtt.labb3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.iths.tt.javafxtt.labb3.controller.Controller;
//import se.iths.tt.javafxtt.labb3.controller.LabbThreeController;

import java.io.IOException;

public class LabbThreeApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("labb-three.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        //LabbThreeController controller = fxmlLoader.getController();
        Controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("Shapes app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
