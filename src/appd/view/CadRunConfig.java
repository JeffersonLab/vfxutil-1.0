package appd.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Application run configuration interface
 * <p>
 *
 * @author gurjyan
 *         Date 9/20/16
 * @version 1.x
 */
public class CadRunConfig {
    private double width = 600;
    private double height = 500;

    private Stage window;

    public CadRunConfig() {
        // Create a stage
        window = new Stage();
        // This will lock the parent window before this one exists
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Run Configuration");
        window.setMinWidth(width);
        window.setMinHeight(height);

        // main vbox
        VBox main = new VBox(10);
        main.getChildren().add(createTabs());
        main.setAlignment(Pos.CENTER);
        Scene scene = new Scene(main);

        // Add the scene to the stage and show it
        window.setScene(scene);
        window.showAndWait();
    }

}
