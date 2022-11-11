/*
package se.iths.tt.javafxtt.labb3.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareTemplate extends ShapeTemplate  {

    public SquareTemplate() {
    }

    public SquareTemplate(double xCoordinate, double yCoordinate, double size, Color chosenColor) {
        super(xCoordinate, yCoordinate, size, chosenColor);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.fillRect(centerOfShapeForX(), centerOfShapeForY(), getSize(), getSize());
    }

    public boolean isInsideShape(ShapeTemplate shape, double mouseX, double mouseY) {
        double x = shape.getxCoordinate() - shape.getSize();
        double y = shape.getyCoordinate() - shape.getSize();

        return  mouseX >= x && mouseX <= x + 2 * shape.getSize() &&
                mouseY >= y && mouseY <= y + 2 * shape.getSize();
    }

    public String drawSVG() {
        String colorToString = "#" + getChosenColor().toString().substring(2,10);

        return "<rect x=\"" + getxCoordinate() + "\" " +
                "y=\"" + getyCoordinate() + "\" " +
                "width=\"" + getSize() + "\" " +
                "height=\"" + getSize() + "\" "+
                "fill=\"" + colorToString + "\" />";
    }

}
*/
