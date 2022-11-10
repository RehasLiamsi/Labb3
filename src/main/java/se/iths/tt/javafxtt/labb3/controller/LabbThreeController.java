package se.iths.tt.javafxtt.labb3.controller;

import javafx.embed.swing.SwingFXUtils;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.iths.tt.javafxtt.labb3.model.CircleTemplate;
import se.iths.tt.javafxtt.labb3.model.ShapeBuilder;
import se.iths.tt.javafxtt.labb3.model.ShapeTemplate;
import se.iths.tt.javafxtt.labb3.model.SquareTemplate;


import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOError;
import java.io.IOException;

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
        sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                shape.setSize(sizeSlider.getValue());
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
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        CircleTemplate circle = shapeBuilder
                .setxCoordinate(mouseEvent.getX())
                .setyCoordinate(mouseEvent.getY())
                .setSize(sizeSlider.getValue())
                .setChosenColor(chosenColor)
                .buildCircle();
        shape.addToListOfShapes(circle);

        graphicsContext.fillOval(circle.centerOfShapeForX(), circle.centerOfShapeForY(), circle.getSize(), circle.getSize());
    }

    private void drawSquare(MouseEvent mouseEvent) {
        ShapeBuilder shapeBuilder = new ShapeBuilder();
        SquareTemplate square = shapeBuilder
                .setxCoordinate(mouseEvent.getX())
                .setyCoordinate(mouseEvent.getY())
                .setSize(sizeSlider.getValue())
                .setChosenColor(chosenColor)
                .buildSquare();
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
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));

        File file = fileChooser.showSaveDialog(stage);

        if(file != null) {
            WritableImage image = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, image);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(image, null);
            try{
                ImageIO.write(renderedImage, "png", file);
            }
            catch (IOException e) {
                System.out.println("Error");
            }
        }

    }

    public void closeApplication() {
        Platform.exit();
    }
}
