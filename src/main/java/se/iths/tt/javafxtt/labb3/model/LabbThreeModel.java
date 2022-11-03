package se.iths.tt.javafxtt.labb3.model;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class LabbThreeModel {

    public RadioButton circleRadioButton;
    public RadioButton squareRadioButton;

    public void toggleRadioButton(){
        ToggleGroup groupButtons = new ToggleGroup();
        circleRadioButton.setToggleGroup(groupButtons);
        squareRadioButton.setToggleGroup(groupButtons);
    }
}
class Slider {
    final double minValue = 10;
    final double maxValue = 150;

    public Slider() {
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slider slider = (Slider) o;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(minValue);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Slider{" +
                "minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}
