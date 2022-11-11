package se.iths.tt.javafxtt.labb3.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape{
    public Square(double xCoordinate, double yCoordinate, double size, Color chosenColor) {
        super(xCoordinate, yCoordinate, size, chosenColor);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.fillRect(centerOfShapeForX(), centerOfShapeForY(), getSize(), getSize());
    }

    @Override
    public boolean isInsideShape(double mouseX, double mouseY) {
        return false;
    }

    @Override
    public String drawSVG() {
        return null;
    }
}
