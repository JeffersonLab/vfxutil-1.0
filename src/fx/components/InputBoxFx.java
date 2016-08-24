package fx.components;

import common.ConstantsFx;
import fx.IconFx;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Pops up inout dialog box with ok and cancel buttons
 * <p>
 *
 * @author gurjyan
 *         Date 8/15/16
 * @version 1.x
 */
public class InputBoxFx {
    private static String result = ConstantsFx.UDF;

    public static class Builder{
        private String title = "Attention!";
        private String message = "";
        private String prompt = "Enter value...";
        private Modality modality = Modality.APPLICATION_MODAL;
        private int width = 250;
        private int height = 130;


        public Builder(String message){
            this.message = message;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Builder prompt(String prompt){
            this.prompt = prompt;
            return this;
        }

        public Builder modality(Modality modality){
            this.modality = modality;
            return this;
        }

        public Builder width(int width){
            this.width = width;
            return this;
        }

        public Builder height(int hight){
            this.height = hight;
            return this;
        }

        public InputBoxFx build(){
            return new InputBoxFx(this);
        }

    }


    private InputBoxFx(Builder builder){

        // Create a stage
        Stage window = new Stage();
        // This will lock the parent window before this one exists
        window.initModality(builder.modality);
        window.setTitle(builder.title);
        window.setMinWidth(builder.width);
        window.setMinHeight(builder.height);

        // Create a label for the message
        Label label = new Label(builder.message, IconFx.get(ConstantsFx.INFO));
        label.setContentDisplay(ContentDisplay.LEFT);

        TextField inputText = new TextField();
        inputText.setPromptText(builder.prompt);

        // Create the close button
        Button button1 = new Button("Ok");
        button1.setOnAction(e -> {
            if(inputText.getText()!=null && !inputText.getText().isEmpty()) {
                result = inputText.getText();
            }
            window.close();
        });
        // Create the ok button
        Button button2 = new Button("Cancel");
        button2.setOnAction(e -> window.close());

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(button1, button2);
        buttonLayout.setAlignment(Pos.BASELINE_RIGHT);


        // Create a horizontal separator before control buttons
        Separator separator = new Separator();
        separator.setMinWidth(builder.width);
        separator.setOrientation(Orientation.HORIZONTAL);

        // vertical box layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, inputText, separator, buttonLayout);
        layout.setAlignment(Pos.CENTER);

        // Create a scene with components in a layout
        Scene scene = new Scene(layout);

        // Add the scene to the stage and show it
        window.setScene(scene);
        window.showAndWait();
    }

    public String get(){
        return result;
    }

}
