package se.iths.tt.javafxtt.labb3.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import se.iths.tt.javafxtt.labb3.CircleTemplate;
import se.iths.tt.javafxtt.labb3.model.LabbThreeModel;

import java.util.List;

public class LabbThreeController {

    public Menu fileMenu;
    public MenuItem saveButton;
    public MenuItem closeButton;
    public Menu editButton;
    public MenuItem undoButton;
    public Menu shapesMenu;
    public MenuItem circleButton;
    public MenuItem squareButton;
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

    private boolean circle;
    private boolean square;
    List<CircleTemplate> numberOfCircles;
    List<Integer> numberOfSquares;

    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
    }


    public void saveToFile(ActionEvent actionEvent) {

    }

    public void closeApplication(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void undoLastMove(ActionEvent actionEvent) {

    }

    public void drawShapeOnClick(MouseEvent mouseEvent) {
        Color chosenColor = colorPicker.getValue();
        if (circleRadioButton.isSelected()) {
            graphicsContext.setFill(chosenColor);
            graphicsContext.fillOval(mouseEvent.getX()-10, mouseEvent.getY()-10, 50, 50);
        } else if (squareRadioButton.isSelected()) {
            graphicsContext.setFill(chosenColor);
            graphicsContext.fillRect(mouseEvent.getX(), mouseEvent.getY(), 50, 50);
        }
    }

    public void changeSize(){
        sizeSlider.getMin();
    }
}
