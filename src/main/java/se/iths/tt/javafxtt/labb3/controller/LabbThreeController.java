package se.iths.tt.javafxtt.labb3.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.iths.tt.javafxtt.labb3.model.ShapeBuilder;
import se.iths.tt.javafxtt.labb3.model.ShapeTemplate;
import se.iths.tt.javafxtt.labb3.model.SquareTemplate;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.RenderedImage;

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
    public MenuItem changeSize;
    public MenuItem changeColor;
    public Stage stage;

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
            if (new ShapeTemplate().isInsideShape(thisShape, mouseEvent.getX(), mouseEvent.getY())) {
                System.out.println("Existing shape");
                selectShape(thisShape);
            }

        }
    }

    private void selectShape(ShapeTemplate shape) {
        sizeSlider.valueProperty().bindBidirectional(shape.sizeProperty());
        colorPicker.valueProperty().bindBidirectional(shape.chosenColorProperty());
    }

    private void drawShape(MouseEvent mouseEvent) {
        if (circleRadioButton.isSelected()) {
            drawCircle(mouseEvent);
        } else if (squareRadioButton.isSelected()) {
            drawSquare(mouseEvent);
        }
    }

    private void drawCircle(MouseEvent mouseEvent) {
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        ShapeTemplate circle = shapeBuilder
                .setxCoordinate(mouseEvent.getX())
                .setyCoordinate(mouseEvent.getY())
                .setSize(sizeSlider.getValue())
                .setChosenColor(chosenColor)
                .build();

       /* ShapeTemplate circle = new ShapeTemplate();
        circle.setxCoordinate(mouseEvent.getX());
        circle.setyCoordinate(mouseEvent.getY());
        circle.setSize(sizeSlider.getValue());
        circle.setChosenColor(chosenColor);*/
        shape.addToListOfShapes(circle);

        graphicsContext.fillOval(circle.centerOfShapeForX(), circle.centerOfShapeForY(), circle.getSize(), circle.getSize());
    }

    private void drawSquare(MouseEvent mouseEvent) {
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        ShapeTemplate square = shapeBuilder
                .setxCoordinate(mouseEvent.getX())
                .setyCoordinate(mouseEvent.getY())
                .setSize(sizeSlider.getValue())
                .setChosenColor(chosenColor)
                .build();

        /*ShapeTemplate square = new SquareTemplate();
        square.setxCoordinate(mouseEvent.getX());
        square.setyCoordinate(mouseEvent.getY());
        square.sizeProperty().bind(sizeSlider.valueProperty());
        square.setChosenColor(chosenColor);*/
        shape.addToListOfShapes(square);

        graphicsContext.fillRect(square.centerOfShapeForX(), square.centerOfShapeForY(), square.getSize(), square.getSize());
    }

    public void undoLastMove(ActionEvent actionEvent) {

    }

    public void saveToFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("BMP", "*.bmp"));

        File file = fileChooser.showSaveDialog(stage);
        if(file != null) {
            /*try {
                WritableImage writableImage = new WritableImage(470, 374);
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, ".bmp", file);
            } catch (IOException ex) {
                System.out.println("Error!");
            }*/
        }

    }

    public void closeApplication() {
        Platform.exit();
    }
}
