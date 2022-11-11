package se.iths.tt.javafxtt.labb3.model;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SVGWriter {
    Stage stage;

    public FileChooser createFileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilterPNG = new FileChooser.ExtensionFilter("PNG", "*.png");
        FileChooser.ExtensionFilter extensionFilterSVG = new FileChooser.ExtensionFilter("SVG", "*.svg");
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
                extensionFilterPNG, extensionFilterSVG);

        return fileChooser;
    }

    private void writeToFile(Path path, List<String> strings) {
        try {
            Files.write(path, strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getSvg(Model model) {
        List<String> listOfSVGStrings = new ArrayList<>();
        listOfSVGStrings.add("<svg " + "xmlns=\"http://www.w3.org/2000/svg\" " +
                "version=\"1.1\"\n " + "width=\"600.0\" " + "height=\"400.0\">");
        model.getObservableListOfShapes().forEach(shape -> listOfSVGStrings.add(shape.drawSVG()));
        listOfSVGStrings.add("</svg>");
        return listOfSVGStrings;
    }

    public void saveFile(Model model) {
        Path path = getPath();
        List<String> svg = getSvg(model);
        writeToFile(path, svg);

    }

    private Path getPath() {
        return Path.of(createFileChooser(stage).showSaveDialog(stage).getPath());
    }
}

