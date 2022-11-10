package se.iths.tt.javafxtt.labb3.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import se.iths.tt.javafxtt.labb3.model.*;

import java.util.Iterator;
import java.util.List;

public class LabbThreeController {

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
    public Color color;
    public Stage stage;
    public TitledPane editAccordionButton;
    public CheckBox editCheckBox;

    ShapeBuilder shapeBuilder = new ShapeBuilder();
    ShapeTemplate shape = new ShapeTemplate();

    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
        //shape.setChosenColor(colorPicker.getValue());

        shape.sizeProperty().bindBidirectional(sizeSlider.valueProperty());
        shape.chosenColorProperty().bindBidirectional(colorPicker.valueProperty());

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        graphicsContext.setFill(shape.getChosenColor());

        if ((editCheckBox.isSelected())) {
            selectingExistingShape(mouseEvent);
        } else
            drawShape(mouseEvent);
    }

    private void selectingExistingShape(MouseEvent mouseEvent) {
        for (int i = 0; i < shape.getObservableListOfShapes().size(); i++) {
            var thisShape = shape.getObservableListOfShapes().get(i);
            if (new SquareTemplate().isInsideShape(thisShape, mouseEvent.getX(), mouseEvent.getY())
                    || (new CircleTemplate().isInsideShape(thisShape, mouseEvent.getX(), mouseEvent.getY()))) {
                System.out.println("Existing shape");
                selectShape(thisShape, mouseEvent);
            }
        }
    }


    private void selectShape(ShapeTemplate thisShape, MouseEvent mouseEvent) {
        clearCanvas();
        if(colorAccordionButton.isExpanded())
            thisShape.setChosenColor(colorPicker.getValue());
        else if(sizeAccordionButton.isExpanded())
            thisShape.setSize(sizeSlider.getValue());
        shape.addToUndoStack(thisShape);

        for (int i = 0; i < shape.getObservableListOfShapes().size(); i++) {
            graphicsContext.setFill(shape.getObservableListOfShapes().get(i).getChosenColor());
            shape.getObservableListOfShapes().get(i).draw(graphicsContext);
        }
    }

    private void clearCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawShape(MouseEvent mouseEvent) {
        if (circleRadioButton.isSelected()) {
            drawCircle(createCircleObject(mouseEvent), mouseEvent);
        } else if (squareRadioButton.isSelected()) {
            drawSquare(createSquareObject(mouseEvent), mouseEvent);
        }
    }

    private ShapeTemplate createCircleObject(MouseEvent mouseEvent) {
        ShapeTemplate newCircle = shapeBuilder
                .setxCoordinate(mouseEvent.getX())
                .setyCoordinate(mouseEvent.getY())
                .setSize(sizeSlider.getValue())
                .setChosenColor(colorPicker.getValue())
                .buildCircle();
        addToList(newCircle);
        shape.addToUndoStack(newCircle);
        System.out.println("Before undo is list " + shape.getObservableListOfShapes().size());

        return newCircle;
    }

    private void drawCircle(ShapeTemplate circle, MouseEvent mouseEvent) {
        circle.draw(graphicsContext);
    }

    private void addToList(ShapeTemplate newShape) {
        shape.addToListOfShapes(newShape);
    }

    private ShapeTemplate createSquareObject(MouseEvent mouseEvent) {
        ShapeTemplate newSquare = shapeBuilder
                .setxCoordinate(mouseEvent.getX())
                .setyCoordinate(mouseEvent.getY())
                .setSize(sizeSlider.getValue())
                .setChosenColor(colorPicker.getValue())
                .buildSquare();
        addToList(newSquare);
        shape.addToUndoStack(newSquare);
        return newSquare;
    }

    private void drawSquare(ShapeTemplate square, MouseEvent mouseEvent) {
        square.draw(graphicsContext);
    }

    public void undoLastMove(ActionEvent actionEvent) {
        shape.undoLastShape();
        System.out.println("After undo is list " + shape.getObservableListOfShapes().size());
        clearCanvas();
        for (int i = 0; i < shape.getObservableListOfShapes().size(); i++) {
            graphicsContext.setFill(shape.getObservableListOfShapes().get(i).getChosenColor());
            shape.getObservableListOfShapes().get(i).draw(graphicsContext);
        }
    }


    public void saveToFile() {
        shape.saveFileToPng(canvas, stage);
    }

    public void closeApplication() {
        Platform.exit();
    }
}


