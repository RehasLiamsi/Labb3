package se.iths.tt.javafxtt.labb3.model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTemplateTest {

    Model model = new Model();
    Shape createNewShapeObject() {
        return new Circle(70, 24, 25, Color.BLUEVIOLET);
    }

    @Test
    void checkToSeeIfCreatingANewObjectAndAddingItToListIncreasesListSize() {
        Shape shape = createNewShapeObject();
        model.addToListOfShapes(shape);

        var actualListSize = model.getObservableListOfShapes().size();
        var expectedSize = 1;

        assertEquals(expectedSize,actualListSize);
    }

    @Test
    void checkToSeeIfCallingUndoMethodReducesTheListSizeByOne() {
        Shape shape = createNewShapeObject();
        model.addToListOfShapes(shape);
        Shape shape2 = createNewShapeObject();
        model.addToListOfShapes(shape2);

        Command undo = () -> model.getObservableListOfShapes().remove(shape2);
        model.getUndoDeque().push(undo);

        model.undoLastShape();

        var actualListSize = model.getObservableListOfShapes().size();
        var expectedSize = 1;

        assertEquals(expectedSize,actualListSize);

    }
}