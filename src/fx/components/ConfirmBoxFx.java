package fx.components;

import common.ConstantsFx;
import fx.IconFx;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Pops up confirmation dialog box with ok and cancel buttons
 * <p>
 *
 * @author gurjyan
 *         Date 8/4/16
 * @version 1.x
 */
public class ConfirmBoxFx {
    private static boolean result = false;

    public static class Builder {
        private String title = "Attention!";
        private String message = "Empty message...";
        private Modality modality = Modality.APPLICATION_MODAL;
        private int width = 250;
        private int height = 130;
        private int severity = ConstantsFx.INFO;


        public Builder(String message) {
            this.message = message;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder severity(int severity) {
            this.severity = severity;
            return this;
        }


        public Builder modality(Modality modality) {
            this.modality = modality;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int hight) {
            this.height = hight;
            return this;
        }

        public ConfirmBoxFx build() {
            return new ConfirmBoxFx(this);
        }

    }


    private ConfirmBoxFx(Builder builder) {

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


        // Create the ok button
        Button button1 = new Button("Ok");
        button1.setOnAction(e -> {
            result = true;
            window.close();
        });
        // Create the Cancel button
        Button button2 = new Button("Cancel");
        button2.setOnAction(e -> {
            result = false;
            window.close();
        });
        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(button1, button2);
        buttonLayout.setAlignment(Pos.BASELINE_RIGHT);


        // Create a horizontal separator before control buttons
        Separator separator = new Separator();
        separator.setMinWidth(builder.width);
        separator.setOrientation(Orientation.HORIZONTAL);

        // vertical box layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, separator, buttonLayout);
        layout.setAlignment(Pos.CENTER);

        // Create a scene with components in a layout
        Scene scene = new Scene(layout);

        // Add the scene to the stage and show it
        window.setScene(scene);
        window.showAndWait();
    }

    public boolean get() {
        return result;
    }
}
