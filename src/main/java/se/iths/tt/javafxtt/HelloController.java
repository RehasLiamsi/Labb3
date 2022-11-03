package se.iths.tt.javafxtt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class HelloController {

    HelloModel helloModel = new HelloModel();
    @FXML
    private Label welcomeText;
    public TextField textField1;
    public Button button1;

    @FXML
    protected void initialize(){
        textField1.textProperty().bindBidirectional(helloModel.textProperty());
        welcomeText.textProperty().bind(helloModel.textProperty());

        System.out.println(Thread.currentThread().getName());
    }

    @FXML
    protected void onHelloButtonClick() {
//       new Thread(()->{
//           Platform.runLater(()->
//           button1.setText("From thread"));
//       }).start();

        helloModel.loadNewQuote();
    }

    public void labelClicked(MouseEvent mouseEvent) {
        if( mouseEvent.isControlDown())
            welcomeText.setBackground(Background.fill(Color.RED));
    }
}