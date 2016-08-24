package test;

import fx.components.CanvasFx;
import fx.components.ConfirmBoxFx;
import common.ConstantsFx;
import fx.components.RadioToggleGroupFx;
import javafx.animation.StrokeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        Canvas canvas = new CanvasFx.Builder() {
            @Override
            public void drawShape(GraphicsContext gc) {
                gc.setFill(Color.RED);
                gc.fillOval(190, 255, 20, 30);
                gc.strokeLine(12,25,190,250);
                gc.setFill(Color.BLUE);
                gc.fillOval(19, 25, 20, 30);
                StrokeTransition st = new StrokeTransition(Duration.millis(3000), gc.getStroke(), Color.RED, Color.BLUE);
                st.setCycleCount(4);
                st.setAutoReverse(true);

                st.play();
            }
        }
                .width(1000)
                .height(1000)
                .build();


        Group root = new Group();
        root.getChildren().add(canvas);

        primaryStage.setTitle("V Canvas Test");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


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
