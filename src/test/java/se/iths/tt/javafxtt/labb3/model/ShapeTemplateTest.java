package se.iths.tt.javafxtt.labb3.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTemplateTest {

    ShapeTemplate createNewShapeObject() {
        return new ShapeTemplate();
    }

    @Test
    void checkToSeeIfCreatingANewObjectAndAddingItToListIncreasesListSize() {
        ShapeTemplate shape = createNewShapeObject();
        shape.addToListOfShapes(shape);

        var actualListSize = shape.getObservableListOfShapes().size();
        var expectedSize = 1;

        assertEquals(expectedSize,actualListSize);
    }

    @Test
    void checkToSeeIfCallingUndoMethodReducesTheListSizeByOne() {
        ShapeTemplate shape = createNewShapeObject();
        shape.addToListOfShapes(shape);
        ShapeTemplate shape2 = createNewShapeObject();
        shape.addToListOfShapes(shape2);

        Command undo = () -> shape.getObservableListOfShapes().remove(shape2);
        shape.getUndoDeque().push(undo);

        shape.undoLastShape();

        var actualListSize = shape.getObservableListOfShapes().size();
        var expectedSize = 1;

        assertEquals(expectedSize,actualListSize);

    }
}