package appd.view;

import appd.model.CadModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Clara application designer
 * <p>
 *
 * @author gurjyan
 *         Date 8/9/16
 * @version 1.x
 */
public class ClaraAppDesigner extends Application {

    // Main stage
    Stage window;

    CadMainMenuBar menuBar;
    CadMainToolBar toolBar;
    CadModel model = new CadModel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        menuBar = new CadMainMenuBar(model);
        toolBar = new CadMainToolBar(model);
        VBox topBox = new VBox(menuBar, toolBar);

        BorderPane borderPane = new BorderPane();
//        borderPane.setCenter(createTabs());
        borderPane.setTop(topBox);
        Scene scene = new Scene(borderPane, 980, 600);
        scene.getStylesheets().add("/projavafx/starterapp/ui/starterApp.css");
        window.setScene(scene);
        window.setTitle("CLARA Application Designer");
        window.show();
    }





}
