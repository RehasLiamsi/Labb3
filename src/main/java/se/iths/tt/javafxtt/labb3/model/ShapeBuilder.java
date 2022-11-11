package se.iths.tt.javafxtt.labb3.model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ShapeBuilder {
    public Shape createShape(MouseEvent mouseEvent, RadioButton circle, RadioButton square, double size, Color chosenColor) {
        if (circle.isSelected()) {
            return buildCircle(mouseEvent, size, chosenColor);
        } else if (square.isSelected()) {
            return buildSquare(mouseEvent, size, chosenColor);
        }
        return null;
    }
    public Square buildSquare(MouseEvent event, double size, Color color) {
        return new Square(event.getX(), event.getY(), size, color);
    }

    public Circle buildCircle(MouseEvent event, double size, Color color) {
        return new Circle(event.getX(), event.getY(), size, color);
    }
}