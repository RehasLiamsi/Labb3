package se.iths.tt.javafxtt.labb3.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Shape {
    private double xCoordinate;
    private double yCoordinate;
    private double size;
    private Color chosenColor;

    public Shape(double xCoordinate, double yCoordinate, double size, Color chosenColor) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.size = size;
        this.chosenColor = chosenColor;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Color getChosenColor() {
        return chosenColor;
    }

    public void setChosenColor(Color chosenColor) {
        this.chosenColor = chosenColor;
    }

    public double centerOfShapeForX() {
        double halfSize = getSize() / 2;
        return this.xCoordinate - halfSize;
    }

    public double centerOfShapeForY() {
        double halfSize = getSize() / 2;
        return this.yCoordinate - halfSize;
    }

    abstract public void draw(GraphicsContext graphicsContext);

    abstract public boolean isInsideShape(double mouseX, double mouseY);

    abstract public Shape copyShape();

    abstract public String drawSVG();

}
