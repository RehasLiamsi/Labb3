package se.iths.tt.javafxtt.labb3.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;


import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class ShapeTemplate {
    private double xCoordinate;
    private double yCoordinate;
    private DoubleProperty size = new SimpleDoubleProperty();
    private ObjectProperty<Color> chosenColor = new SimpleObjectProperty<>();
    private ObservableList<ShapeTemplate> observableListOfShapes = FXCollections.observableArrayList();
    private ArrayDeque<Command> undoDeque = new ArrayDeque<>();

    public ShapeTemplate() {}
    public ShapeTemplate(double xCoordinate, double yCoordinate, double size, Color chosenColor) {
        setxCoordinate(xCoordinate);
        setyCoordinate(yCoordinate);
        setSize(size);
        setChosenColor(chosenColor);
    }

    public ShapeTemplate(ShapeTemplate shape) {
        setxCoordinate(shape.getxCoordinate());
        setyCoordinate(shape.getyCoordinate());
        setSize(shape.getSize());
        setChosenColor(shape.getChosenColor());
    }

    public Color getChosenColor() {
        return chosenColor.get();
    }

    public ObjectProperty<Color> chosenColorProperty() {
        return chosenColor;
    }

    public void setChosenColor(Color chosenColor) {
        this.chosenColor.set(chosenColor);
    }

    public double getSize() {
        return size.get();
    }

    public DoubleProperty sizeProperty() {
        return size;
    }

    public void setSize(double size) {
        this.size.set(size);
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double centerOfShapeForX(){
        double halfSize = getSize() / 2;
        return this.xCoordinate- halfSize;
    }

    public double centerOfShapeForY(){
        double halfSize = getSize() / 2;
        return this.yCoordinate- halfSize;
    }

    public void addToListOfShapes(ShapeTemplate shape) {
        observableListOfShapes.add(shape);
    }

    public ObservableList<ShapeTemplate> getObservableListOfShapes() {
        return observableListOfShapes;
    }

    public void setObservableListOfShapes(ObservableList<ShapeTemplate> observableListOfShapes) {
        this.observableListOfShapes = observableListOfShapes;
    }

    public ArrayDeque<Command> getUndoDeque() {
        return undoDeque;
    }

    public void setUndoDeque(ArrayDeque<Command> undoDeque) {
        this.undoDeque = undoDeque;
    }

    public void draw(GraphicsContext graphicsContext){}

//    public boolean isInsideShape(ShapeTemplate shape, double mouseX, double mouseY){return  false;}

    public void addToUndoStack(ShapeTemplate newShape) {
        Command undo = () -> getObservableListOfShapes().remove(newShape);
        getUndoDeque().push(undo);
    }

    public void undoLastShape() {
        Command undoToExecute = getUndoDeque().pop();
        undoToExecute.execute();
    }


    public FileChooser createFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG", "*.png");
        FileChooser.ExtensionFilter extensionFilterSVG = new FileChooser.ExtensionFilter("SVG", "*.svg");
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
                extensionFilterPNG,extensionFilterSVG);
        return fileChooser;
    }

    public void saveFileToPng(Canvas canvas, Stage stage) {
        File file = createFileChooser().showSaveDialog(stage);

        if(file != null) {
            WritableImage image = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, image);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(image, null);
            try {
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }

    public void saveToSVG(Stage stage) {
        createFileChooser();
        File file = createFileChooser().showSaveDialog(stage);

        List<String> svgString = new ArrayList<>();
        createSVGString(svgString);
    }

    public String drawSVG(){
        return null;
    };

    private void createSVGString(List<String> list) {
        list.add(startSVGString());
        getObservableListOfShapes().forEach(shapeTemplate -> dataToString(shapeTemplate, list));
        list.add(endSVGString());
    }

    private String endSVGString() {
        return "</svg>";
    }

    private void dataToString(ShapeTemplate shape, List<String> list) {
        list.add(shape.drawSVG());
    }

    private String startSVGString() {
        return String.join(" ",
                "<svg" ,
                "xmlns=\"http://www.w3.org/2000/svg\"",
                "version=\"1.1\"",
                "width=\"600.0\"",
                "height=\"400.0\">");
    }
}
