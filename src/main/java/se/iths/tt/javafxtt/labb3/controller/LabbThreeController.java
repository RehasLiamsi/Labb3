package se.iths.tt.javafxtt.labb3.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import se.iths.tt.javafxtt.labb3.model.CircleTemplate;
import se.iths.tt.javafxtt.labb3.model.LabbThreeModel;
import se.iths.tt.javafxtt.labb3.model.ShapeTemplate;
import se.iths.tt.javafxtt.labb3.model.SquareTemplate;

import java.util.List;

public class LabbThreeController {

    LabbThreeModel labbThreeModel = new LabbThreeModel();
    public Menu fileMenu;
    public MenuItem saveButton;
    public MenuItem closeButton;
    public Menu editButton;
    public MenuItem undoButton;
    public Canvas canvas;
    public GraphicsContext graphicsContext;
    public Accordion accordionMenu;
    public TitledPane sizeAccordionButton;
    public Slider sizeSlider;
    public TitledPane colorAccordionButton;
    public ColorPicker colorPicker;
    public RadioButton circleRadioButton;
    public RadioButton squareRadioButton;
    public TitledPane shapeAccordionButton;
    public ToggleGroup shapeGroup;
    public Color chosenColor;
    ShapeTemplate shape = new ShapeTemplate();


    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
    }


    public void saveToFile(ActionEvent actionEvent) {

    }

    public void undoLastMove(ActionEvent actionEvent) {

    }

    public void drawShapeOnClick(MouseEvent mouseEvent) {
        chosenColor = colorPicker.getValue();
        graphicsContext.setFill(chosenColor);

        if (circleRadioButton.isSelected()) {
            drawCircle(mouseEvent);
        } else if (squareRadioButton.isSelected()) {
            drawSquare(mouseEvent);
        }
    }

    private void drawSquare(MouseEvent mouseEvent) {
        SquareTemplate square = new SquareTemplate();
        square.setxCoordinate(mouseEvent.getX());
        square.setyCoordinate(mouseEvent.getY());
        square.setSize(sizeSlider.getValue());

        graphicsContext.fillRect(square.getxCoordinate(), square.getyCoordinate(), square.getSize(), square.getSize());
    }

    private void drawCircle(MouseEvent mouseEvent) {
        CircleTemplate circle = new CircleTemplate();
        circle.setxCoordinate(mouseEvent.getX());
        circle.setyCoordinate(mouseEvent.getY());
        circle.setSize(sizeSlider.getValue());

        graphicsContext.fillOval(circle.getxCoordinate(), circle.getyCoordinate(), circle.getSize(), circle.getSize());
    }

    public void closeApplication(ActionEvent actionEvent) {
        Platform.exit();
    }
}
