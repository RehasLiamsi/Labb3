package se.iths.tt.javafxtt.labb3.model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class ShapeBuilder {
    private double xCoordinate;
    private double yCoordinate;
    private DoubleProperty size = new SimpleDoubleProperty();
    private ObjectProperty<Color> chosenColor = new SimpleObjectProperty<>();

    public ShapeBuilder() {

    }

    public ShapeBuilder setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
        return this;
    }

    public ShapeBuilder setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
        return this;
    }

    public ShapeBuilder setSize(double size) {
        this.size.set(size);
        return this;
    }

    public ShapeBuilder setChosenColor(Color chosenColor) {
        this.chosenColor.set(chosenColor);
        return this;
    }

    public SquareTemplate buildSquare() {
        return new SquareTemplate(xCoordinate, yCoordinate, size, chosenColor);
    }

    public CircleTemplate buildCircle() {return new CircleTemplate(xCoordinate, yCoordinate, size, chosenColor);}
}