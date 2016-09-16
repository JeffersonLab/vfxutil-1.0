package appd.view.appconfig;

import common.CadConstants;
import fx.IconFx;
import fx.components.ButtonFx;
import fx.components.FileChooserFx;
import fx.components.TreeFx;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * Clara application configuration UI
 * <p>
 *
 * @author gurjyan
 *         Date 9/15/16
 * @version 1.x
 */
public class CadAppConfigMain {

    private double width = 600;
    private double height = 500;

    private Stage window;

    public CadAppConfigMain() {
        // Create a stage
        window = new Stage();
        // This will lock the parent window before this one exists
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Application Structure");
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

    private TabPane createTabs() {
        // create tabs
        Tab t_app = new Tab("Application");
        t_app.setClosable(false);
        t_app.setContent(createAppConfigNode());

        Tab t_lib = new Tab("Service Libraries");
        t_lib.setClosable(false);
        t_lib.setContent(createFcNode("Libraries",IconFx.get(CadConstants.OPEN)));

        Tab t_engine = new Tab("Service Engines");
        t_engine.setClosable(false);
        t_engine.setContent(createFcNode("Service Engines",IconFx.get(CadConstants.PASTE)));

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(t_app, t_lib, t_engine);
        return tabPane;
    }

    /**
     * Application base configuration tab
     * @return VBox node
     */
    private Node createAppConfigNode() {
        //================================ name ==========================
        VBox name = new VBox();
        Label aName = new Label("Application name:");
        aName.setContentDisplay(ContentDisplay.LEFT);

        final TextField aName_tf = new TextField();
        aName_tf.setMaxWidth(width);
        aName_tf.setPromptText("Enter application name");
        aName_tf.textProperty().addListener((ov, oldValue, newValue) -> {
            System.out.println("app name is: " + aName_tf.getText());
        });

        name.getChildren().addAll(aName, aName_tf);

        //================================ description ==========================
        VBox description = new VBox();
        Label aDesc = new Label("Application description:");
        aDesc.setContentDisplay(ContentDisplay.LEFT);

        final TextArea aDesc_ta = new TextArea();
        aDesc_ta.focusedProperty().addListener((ov, oldValue, newValue) -> {
            if (!aDesc_ta.isFocused()) {
                System.out.println("app description is: " + aDesc_ta.getText());
            }
        });
        description.getChildren().addAll(aDesc, aDesc_ta);

        //================================ file chooser ==========================

        VBox fileChooser = new VBox();

        // text filed that holds app data dir
        final TextField tf = new TextField();
        tf.setEditable(false);
//        tf.setPrefColumnCount(42);

        Label aData = new Label("Application data:");
        Label aDataHelp = new Label(
                "\nThis path is used to store application configuration and run-time data. In the selected\n" +
                        "path the new directory, with the application name, will be created (if it is not already\n" +
                        "created) that will contain two subdirectories: config and log for configuration and run\n" +
                        "time log data respectively.");
        aData.setContentDisplay(ContentDisplay.LEFT);

        Button fc = new ButtonFx.Builder()
                .text("...")
                .action(e -> {
                    DirectoryChooser chooser = new DirectoryChooser();
                    chooser.setTitle("Application Data");
                    chooser.setInitialDirectory(new File("./"));
                    File selectedDirectory = chooser.showDialog(new Stage());
                    if(selectedDirectory!=null) {
                        tf.setText(selectedDirectory.getAbsolutePath());
                    }
                }).build();

        HBox aData_fc = new HBox(10);
        HBox.setHgrow(tf, Priority.ALWAYS);
        aData_fc.getChildren().addAll(tf, fc);
        aData_fc.setAlignment(Pos.BASELINE_LEFT);

        fileChooser.getChildren().addAll(aData, aData_fc, aDataHelp);

        //================================ buttons ==========================
        VBox controlButtons = new VBox();
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);

        // Create the ok button
        Button button1 = new Button("Apply");
        button1.setOnAction(e -> {
            //@todo call method from the model
        });
        // Create the Cancel button
        Button button2 = new Button("Cancel");
        button2.setOnAction(e -> window.close());

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(button1, button2);
        buttonLayout.setAlignment(Pos.BASELINE_RIGHT);

        controlButtons.getChildren().addAll(separator, buttonLayout);

        //================================ All ==========================
        VBox all = new VBox(10);
        all.setPadding(new Insets(10));
        all.getChildren().addAll(name, description, fileChooser, controlButtons);
        all.setAlignment(Pos.CENTER);
        return all;
    }

    private Node createFcNode(String id, ImageView icon){

        TreeFx libTree = new TreeFx.Builder("Application "+id)
                .expand(true)
                .rootImage(icon)
                .build();

        //========================== control buttons ==================
        VBox controlButtons = new VBox();
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);

        Button addFile = new ButtonFx.Builder().text("+F").action(e -> {
            List<File> lf = new FileChooserFx.Builder(new File("./"))
                    .title(id+" File")
                    .build();
            if(lf!=null && !lf.isEmpty()) lf.forEach(f -> libTree.addItem(f.getAbsolutePath()));
        }).build();

        Button addDir = new ButtonFx.Builder().text("+D").action(e -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle(id+" Directory");
            chooser.setInitialDirectory(new File("./"));
            File selectedDirectory = chooser.showDialog(new Stage());
            if(selectedDirectory!=null) {
                libTree.addItem(selectedDirectory.getAbsolutePath());
            }

        }).build();

        Button remove = new ButtonFx.Builder().text("-").action(e -> {
            libTree.removeSelected();
        }).build();

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(addDir, addFile, remove);
        buttonLayout.setAlignment(Pos.BASELINE_RIGHT);

        controlButtons.getChildren().addAll(separator, buttonLayout);

        //================================ All ==========================
        VBox all = new VBox(10);
        all.setPadding(new Insets(10));
        all.getChildren().addAll(libTree.show(), controlButtons);
        all.setAlignment(Pos.CENTER);
        return all;
    }

}
