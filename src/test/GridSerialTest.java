package test;

import common.CadColor;
import common.CadConstants;
import appd.view.CadCanvas;
import fx.components.NodeFx;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Test the main Grid and serialization
 * <p>
 *
 * @author gurjyan
 *         Date 8/3/16
 * @version 1.x
 */
public class GridSerialTest extends Application {

    private CadCanvas grid;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        grid = new CadCanvas(primaryStage, root,0);
        grid.drawGrid();

        NodeFx node1 = new NodeFx.Builder("aman")
                .color(CadColor.TURQUOISE)
                .build();

        NodeFx node2 = new NodeFx.Builder("chaman")
                .color(CadColor.YELLOW)
                .shape(CadConstants.OVAL)
                .build();
        NodeFx node3 = new NodeFx.Builder("karo")
                .color(CadColor.TURQUOISE)
                .build();

        grid.addNode(node1);
        grid.addNode(node2);
        grid.addNode(node3);


        root.getChildren().addAll(grid, node1, node2, node3);
        grid.toFront();

        primaryStage.setTitle("V Canvas Test");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        node1.startAnimation();
        // exiting
        primaryStage.setOnCloseRequest(event -> Platform.exit());

//        // resizing
//        primaryStage.widthProperty().addListener(observable ->
//                root.getChildren().forEach(e ->
//                        e.resize(primaryStage.getWidth(), primaryStage.getHeight())));
    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("/tmp/grid.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(grid);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/grid.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
