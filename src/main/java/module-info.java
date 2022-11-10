module se.iths.tt.javafxtt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens se.iths.tt.javafxtt to javafx.fxml;
    exports se.iths.tt.javafxtt;
    exports se.iths.tt.javafxtt.labb3;
    exports se.iths.tt.javafxtt.labb3.controller;
    exports se.iths.tt.javafxtt.labb3.model;
}