package se.iths.tt.javafxtt.labb3.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {
    public Square(double xCoordinate, double yCoordinate, double size, Color chosenColor) {
        super(xCoordinate, yCoordinate, size, chosenColor);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.fillRect(centerOfShapeForX(), centerOfShapeForY(), getSize(), getSize());
    }

    @Override
    public boolean isInsideShape(double mouseX, double mouseY) {
        double x = getxCoordinate() - getSize() / 2;
        double y = getyCoordinate() - getSize() / 2;

        return mouseX >= x && mouseX <= x + getSize() &&
                mouseY >= y && mouseY <= y + getSize();
    }

    @Override
    public Shape copyShape() {
        return new Square(getxCoordinate(), getyCoordinate(), getSize(), getChosenColor());
    }

    @Override
    public String drawSVG() {
        String color = "#" + getChosenColor().toString().subSequence(2, 10);

        return "<" + "rect" + " x=\"" + getxCoordinate() + "\"" + " y=\"" + getyCoordinate() + "\""
                + " width=\"" + getSize() + "\"" + " height=\"" + getSize() + "\""
                + " fill=\"" + color + "\"" + " />";
    }
}
