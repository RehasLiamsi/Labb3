package se.iths.tt.javafxtt.labb3.controller;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.iths.tt.javafxtt.labb3.model.*;

public class Controller {
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
    public Stage stage;
    public TitledPane editAccordionButton;
    public CheckBox editCheckBox;

    ShapeBuilder shapeBuilder = new ShapeBuilder();
    Model model = new Model();

    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        graphicsContext.setFill(model.getChosenColor());

        if ((editCheckBox.isSelected())) {
            selectingExistingShape(mouseEvent);
        } else {
            model.addToUndoStack();
            Shape newShape = shapeBuilder.createShape(
                    mouseEvent, circleRadioButton, squareRadioButton,
                    sizeSlider.getValue(), colorPicker.getValue());
            addToList(newShape);
            drawShape();
        }
    }

    private void selectingExistingShape(MouseEvent mouseEvent) {
        for (int i = 0; i < model.getObservableListOfShapes().size(); i++) {
            var thisShape = model.getObservableListOfShapes().get(i);
            if (thisShape.isInsideShape(mouseEvent.getX(), mouseEvent.getY())) {
                selectShape(thisShape);
            }
        }
    }


    private void selectShape(Shape thisShape) {
        model.addToUndoStack();
        if (colorAccordionButton.isExpanded())
            thisShape.setChosenColor(colorPicker.getValue());
        else if (sizeAccordionButton.isExpanded())
            thisShape.setSize(sizeSlider.getValue());

        clearCanvas();
        drawShape();
    }


    private void clearCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawShape() {
        for (int i = 0; i < model.getObservableListOfShapes().size(); i++) {
            graphicsContext.setFill(model.getObservableListOfShapes().get(i).getChosenColor());
            model.getObservableListOfShapes().get(i).draw(graphicsContext);
        }
    }

    private void addToList(Shape newShape) {
        model.addToListOfShapes(newShape);
    }

    public void undoLastMove() {
        model.undoLastShape();
        clearCanvas();
        drawShape();
    }

    public void closeApplication() {
        Platform.exit();
    }

    public void saveToFile() {
        try {
            model.writeToSvg();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
