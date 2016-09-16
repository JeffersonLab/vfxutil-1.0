package test;

import fx.components.ArchFx;
import common.CadConstants;
import fx.components.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Test class to test utility classes
 * <p>
 *
 * @author gurjyan
 *         Date 8/3/16
 * @version 1.x
 */
public class Test extends Application {


    Stage window;
    Button button;
    static Group root;

    public static void main(String[] args) {
        String s = "aman`chaman";
        System.out.println(s.split("`")[0]);
        launch(args);
    }

    public static void addArch(ArchFx arch){
        root.getChildren().add(arch);
        arch.toBack();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        window = new Stage();
        window.setTitle("Test window");

        button = new Button("Click");
        button.setOnAction(e -> {
            boolean b = new ConfirmBoxFx.Builder("Are you sure ?")
                    .width(300)
                    .height(150)
                    .title("Delete Service")
                    .severity(CadConstants.WARNING)
                    .build().get();
            System.out.println("DDD = " + b);
        });


        HBox radioBox = new RadioToggleGroupFx.Builder() {
            @Override
            protected void action(String radioName) {
                System.out.println(radioName + " selected");
            }
        }
                .name1("aman")
                .name2("chaman")
                .build()
                .get();

        VBox layout = new VBox();
        layout.getChildren().addAll(button, radioBox);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();

    }

}
