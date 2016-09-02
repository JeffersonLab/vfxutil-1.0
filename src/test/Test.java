package test;

import common.ConstantsFx;
import fx.components.GArch;
import fx.components.GGrid;
import fx.components.GNode;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    boolean b;
    static Group root;

    public static void main(String[] args) {
        launch(args);
    }

    public static void addArch(GArch arch){
        root.getChildren().add(arch);
        arch.toBack();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GGrid grid = new GGrid();
        grid.drawGrid(50);

        GNode node1 = new GNode.Builder("aman")
                .build();

        GNode node2 = new GNode.Builder("chaman")
                .shape(ConstantsFx.OVAL)
                .build();

        grid.addNode(node1);
        grid.addNode(node2);

        root = new Group();
        root.getChildren().addAll(grid, node1, node2);
        grid.toFront();

        primaryStage.setTitle("V Canvas Test");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        node1.startAnimation();

//        window = new Stage();
//        window.setTitle("Test window");
//
//        button = new Button("Click");
//        button.setOnAction(e -> {
//            boolean b = new ConfirmBoxFx.Builder("Are you sure ?")
//                    .width(300)
//                    .height(150)
//                    .title("Delete Service")
//                    .severity(ConstantsFx.WARNING)
//                    .build().get();
//            System.out.println("DDD = " + b);
//        });
//
//
//        HBox radioBox = new RadioToggleGroupFx.Builder() {
//            @Override
//            protected void action(String radioName) {
//                System.out.println(radioName + " selected");
//            }
//        }
//                .name1("aman")
//                .name2("chaman")
//                .build()
//                .get();
//
//        VBox layout = new VBox();
//        layout.getChildren().addAll(button, radioBox);
//
//        Scene scene = new Scene(layout, 300, 250);
//        window.setScene(scene);
//        window.show();

    }

}
