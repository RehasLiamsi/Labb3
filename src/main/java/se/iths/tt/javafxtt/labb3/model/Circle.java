package se.iths.tt.javafxtt.labb3.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    public Circle(double xCoordinate, double yCoordinate, double size, Color chosenColor) {
        super(xCoordinate, yCoordinate, size, chosenColor);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.fillOval(centerOfShapeForX(), centerOfShapeForY(), getSize(), getSize());
    }

    @Override
    public boolean isInsideShape(double mouseX, double mouseY) {
        double distX = mouseX - getxCoordinate();
        double distY = mouseY - getyCoordinate();
        double distance = Math.sqrt((distX * distX) + (distY * distY));

        return distance <= getSize() / 2;
    }

    @Override
    public Shape copyShape() {
        return new Circle(getxCoordinate(), getyCoordinate(), getSize(), getChosenColor());
    }

    @Override
    public String drawSVG() {

        String color = "#" + getChosenColor().toString().subSequence(2, 10);

        return "<" + "circle" + " cx=\"" + getxCoordinate() + "\"" + " cy=\"" + getyCoordinate() + "\""
                + " r=\"" + getSize() / 2 + "\"" + " fill=\"" + color + "\"" + " />";
    }
}
