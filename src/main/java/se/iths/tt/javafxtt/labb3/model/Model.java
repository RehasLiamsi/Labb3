package se.iths.tt.javafxtt.labb3.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;

public class Model {
    private final DoubleProperty size = new SimpleDoubleProperty();
    private final ObjectProperty<Color> chosenColor = new SimpleObjectProperty<>();
    private final ObservableList<Shape> observableListOfShapes = FXCollections.observableArrayList();
    private final ArrayDeque<Command> undoDeque = new ArrayDeque<>();
    private final SVGWriter svgWriter;

    public Model() {
        svgWriter = new SVGWriter();
    }
    public Color getChosenColor() {
        return chosenColor.get();
    }
    public ObjectProperty<Color> chosenColorProperty() {
        return chosenColor;
    }
    public DoubleProperty sizeProperty() {
        return size;
    }
    public void addToListOfShapes(Shape shape) {
        observableListOfShapes.add(shape);
    }
    public ObservableList<Shape> getObservableListOfShapes() {
        return observableListOfShapes;
    }
    public ArrayDeque<Command> getUndoDeque() {
        return undoDeque;
    }
    public void addToUndoStack() {
        ObservableList<Shape> tempList = copyOfObservableListOfShapes();
        Command undo = () -> {
            getObservableListOfShapes().clear();
            getObservableListOfShapes().addAll(tempList);
        };
        getUndoDeque().push(undo);
    }
    public ObservableList<Shape> copyOfObservableListOfShapes() {
        ObservableList<Shape> tempList = FXCollections.observableArrayList();

        for (Shape shape : getObservableListOfShapes()) {
            tempList.add(shape.copyShape());
        }
        return tempList;
    }
    public void undoLastShape() {
        Command undoToExecute = getUndoDeque().pop();
        undoToExecute.execute();
    }

    public void writeToSvg() {
        svgWriter.saveFile(this);
    }
}
