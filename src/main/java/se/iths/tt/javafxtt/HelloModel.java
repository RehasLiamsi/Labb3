package se.iths.tt.javafxtt;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HelloModel {
    private StringProperty text;
    public HelloModel() {
        text = new SimpleStringProperty();
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public void loadNewQuote() {

    }
}

