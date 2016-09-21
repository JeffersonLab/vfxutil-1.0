package test;

import appd.view.CadCanvas;
import fx.components.ArchFx;
import fx.components.NodeFx;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Class description here....
 * <p>
 *
 * @author gurjyan
 *         Date 9/13/16
 * @version 1.x
 */
public class GridDeTest extends Application {
    private CadCanvas grid;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/grid.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // de-serialize CadCanvas object
            CadCanvas grid_ser = (CadCanvas) in.readObject();
            in.close();
            fileIn.close();

            // recreate a new CadCanvas object
            grid = new CadCanvas(primaryStage, root,0);
            grid.drawGrid();
            root.getChildren().add(grid);

            // temporary list of arches
            Set<ArchFx> tmpArches = new HashSet<>();
            grid_ser.getNodes().values().forEach(e -> {

                // redefine arches
                e.getInLinks().values().forEach(i -> redefineArch(tmpArches, i));
                e.getOutLinks().values().forEach(o -> redefineArch(tmpArches, o));

                // redefine node
                NodeFx n = e.getBuilder().build();
                n.translate(e.getCurrentX(), e.getCurrentY());
                n.setWidth(100);
                n.setHeight(100);
                root.getChildren().add(n);
                grid.addNode(n);

            });

            // add arches to nodes
            tmpArches.forEach(a -> {
                grid.getNodes().get(a.getStartNodeName()).addOutLink(a);
                grid.getNodes().get(a.getEndNodeName()).addInLink(a);
                a.redraw();
                grid.addArch(a);
            });

            grid.toFront();

            primaryStage.setTitle("V Canvas Test");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            // exiting
            primaryStage.setOnCloseRequest(event -> Platform.exit());


            // resizing
//            primaryStage.widthProperty().addListener(observable ->
//                    root.getChildren().forEach(e ->
//                            e.resize(primaryStage.getWidth(), primaryStage.getHeight())));

        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }

    private void redefineArch(Set<ArchFx> tmpArches, ArchFx x) {
        ArchFx a = x.getBuilder().build();
        a.setxStart(x.getxStart());
        a.setyStart(x.getyStart());
        a.setxEnd(x.getxEnd());
        a.setyEnd(x.getyEnd());
        a.setName(x.getStartNodeName(), x.getEndNodeName());
        tmpArches.add(a);
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
