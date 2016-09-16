package appd.view;

import appd.model.CadModel;
import appd.view.appconfig.CadAppConfigMain;
import fx.components.ButtonFx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class CadMain extends Application {

    // Main stage
    Stage window;

    CadMainMenuBar menuBar;
    CadMainToolBar toolBar;
    CadModel model = new CadModel();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        menuBar = new CadMainMenuBar(model);
        toolBar = new CadMainToolBar(model);
        VBox topBox = new VBox(menuBar, toolBar);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topBox);

        Scene scene = new Scene(borderPane, 600, 600);
        scene.getStylesheets().add("/projavafx/starterapp/ui/starterApp.css");
        window.setScene(scene);
        window.setTitle("CLARA Application Designer");
        window.show();
    }





}
