/*
package se.iths.tt.javafxtt.labb3.model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleTemplate extends ShapeTemplate {

    public CircleTemplate() {
    }

    public CircleTemplate(double xCoordinate, double yCoordinate, double size, Color chosenColor) {
        super(xCoordinate, yCoordinate, size, chosenColor);
    }

    public String drawSVG() {
        String colorToString = "#" + getChosenColor().toString().substring(2, 10);
        return "<<circle cx=\"" + getxCoordinate() + "\" " +
                "cy=\"" + getyCoordinate() + "\" " +
                "r=\"" + getSize() + "\"" +
                "fill=\"" + colorToString + "\" />";
    }

    public void draw(GraphicsContext graphicsContext) {

        graphicsContext.fillOval(centerOfShapeForX(), centerOfShapeForY(), getSize(), getSize());
    }

    public boolean isInsideShape(ShapeTemplate shape, double mouseX, double mouseY) {
        double distX = mouseX - shape.getxCoordinate();
        double distY = mouseY - shape.getyCoordinate();
        double distance = Math.sqrt((distX * distX) + (distY * distY));

        return distance <= shape.getSize();
    }
}
*/
