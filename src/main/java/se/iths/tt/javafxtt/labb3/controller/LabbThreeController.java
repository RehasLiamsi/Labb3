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
import se.iths.tt.javafxtt.labb3.model.CircleTemplate;
import se.iths.tt.javafxtt.labb3.model.ShapeBuilder;
import se.iths.tt.javafxtt.labb3.model.ShapeTemplate;
import se.iths.tt.javafxtt.labb3.model.SquareTemplate;

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
    public Color chosenColor;
    public Stage stage;

    ShapeBuilder shapeBuilder = new ShapeBuilder();
    ShapeTemplate shape = new ShapeTemplate();
    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
        shape.setChosenColor(colorPicker.getValue());

        shape.sizeProperty().bindBidirectional(sizeSlider.valueProperty());
        shape.chosenColorProperty().bindBidirectional(colorPicker.valueProperty());

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        graphicsContext.setFill(shape.getChosenColor());

        if (mouseEvent.isShiftDown()) {
            selectingExistingShape(mouseEvent);
        } else
            drawShape(mouseEvent);
    }

    private void selectingExistingShape(MouseEvent mouseEvent) {
        for (int i = 0; i < shape.getObservableListOfShapes().size(); i++) {
            var thisShape = shape.getObservableListOfShapes().get(i);
            if(new SquareTemplate().isInsideShape(thisShape,mouseEvent.getX(),mouseEvent.getY())
                    || (new CircleTemplate().isInsideShape(thisShape, mouseEvent.getX(), mouseEvent.getY()))) {
                System.out.println("Existing shape");
                selectShape(thisShape);
            }

        }
    }


    private void selectShape(ShapeTemplate shape) {
        graphicsContext.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        shape.getObservableListOfShapes().remove(shape);

        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {

            }
        });

        /*sizeSlider.valueProperty().bindBidirectional(shape.sizeProperty());
        colorPicker.valueProperty().bindBidirectional(shape.chosenColorProperty());*/
    }

    private void drawShape(MouseEvent mouseEvent) {
        if (circleRadioButton.isSelected()) {
            drawCircle(mouseEvent);
        } else if (squareRadioButton.isSelected()) {
            drawSquare(mouseEvent);
        }
    }

    private void drawCircle(MouseEvent mouseEvent) {
        CircleTemplate newCircle = shapeBuilder
                .setxCoordinate(mouseEvent.getX())
                .setyCoordinate(mouseEvent.getY())
                .setSize(sizeSlider.getValue())
                .setChosenColor(chosenColor)
                .buildCircle();
        shape.addToListOfShapes(newCircle);

        newCircle.draw(graphicsContext);
    }

    private void drawSquare(MouseEvent mouseEvent) {
        SquareTemplate square = shapeBuilder
                .setxCoordinate(mouseEvent.getX())
                .setyCoordinate(mouseEvent.getY())
                .setSize(sizeSlider.getValue())
                .setChosenColor(chosenColor)
                .buildSquare();
        shape.addToListOfShapes(square);

        square.draw(graphicsContext);


    }

    public void undoLastMove(ActionEvent actionEvent) {

    }

    public void saveToFile() {
        shape.saveFileToPng(canvas,stage);
    }

    public void closeApplication() {
        Platform.exit();
    }
}
