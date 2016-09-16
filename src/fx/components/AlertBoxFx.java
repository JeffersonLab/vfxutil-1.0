package fx.components;

import common.CadConstants;
import fx.IconFx;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Pops up alert/information box
 * <p>
 *
 * @author gurjyan
 *         Date 8/3/16
 * @version 1.x
 */
public class AlertBoxFx {


    public static class Builder{
        private String title = "Attention!";
        private String message = "";
        private int severity = CadConstants.INFO;
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

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder severity(int severity){
            this.severity = severity;
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

        public AlertBoxFx build(){
            return new AlertBoxFx(this);
        }

    }


    private AlertBoxFx(Builder builder){

        // Create a stage
        Stage window = new Stage();
        // This will lock the parent window before this one exists
        window.initModality(builder.modality);
        window.setTitle(builder.title);
        window.setMinWidth(builder.width);
        window.setMinHeight(builder.height);

        // Create a label for the message
        Label label = new Label(builder.message, IconFx.get(builder.severity));
        label.setContentDisplay(ContentDisplay.LEFT);


        // Create the close button
        Button button = new Button("Close");
        button.setOnAction(e -> window.close());

        // Create a horizontal separator before control buttons
        Separator separator = new Separator();
        separator.setMinWidth(builder.width);
        separator.setOrientation(Orientation.HORIZONTAL);

        // vertical box layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, separator, button);
        layout.setAlignment(Pos.CENTER);

        // Create a scene with components in a layout
        Scene scene = new Scene(layout);

        // Add the scene to the stage and show it
        window.setScene(scene);
        window.showAndWait();
    }

}
