package fx.components;

import common.CadConstants;
import fx.IconFx;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Set;

/**
 * ListBox with fluent interface
 * <p>
 *
 * @author gurjyan
 *         Date 8/18/16
 * @version 1.x
 */
public class ListBoxFx {
    private static ObservableList<String> result;

    private ListBoxFx(Builder builder){
        // Create a stage
        Stage window = new Stage();
        // This will lock the parent window before this one exists
        window.initModality(builder.modality);
        window.setTitle(builder.title);
        window.setMinWidth(builder.width);
        window.setMinHeight(builder.height);

        // Create a label for the message
        Label label = new Label(builder.message, IconFx.get(CadConstants.INFO));
        label.setContentDisplay(ContentDisplay.LEFT);

        ListView<String> listView = new ListView<>();
        builder.set.forEach(x -> listView.getItems().add(x));
        listView.getSelectionModel().setSelectionMode(builder.select);


        // Create the ok button
        Button button1 = new Button("Ok");
        button1.setOnAction(e -> {
            result = listView.getSelectionModel().getSelectedItems();
            window.close();
        });
        // Create the cancel button
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
        layout.getChildren().addAll(label, listView, separator, buttonLayout);
        layout.setAlignment(Pos.CENTER);

        // Create a scene with components in a layout
        Scene scene = new Scene(layout);

        // Add the scene to the stage and show it
        window.setScene(scene);
        window.showAndWait();

    }

    public ObservableList<String> get(){
        return result;
    }

    public static class Builder {

        // box stage
        private String title = "List Box";
        private String message = "Select...";
        private Modality modality = Modality.APPLICATION_MODAL;
        private int width = 250;
        private int height = 130;

        //listView
        private Set<String> set;
        private boolean editable = false;
        private SelectionMode select = SelectionMode.SINGLE;


        public Builder(Set<String> set){
            this.set = set;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder message(String message){
            this.message = message;
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

        public Builder editable(boolean editable){
            this.editable = editable;
            return this;
        }

        public Builder select(SelectionMode select){
            this.select = select;
            return this;
        }

        public ListBoxFx build(){
            return new ListBoxFx(this);
        }

    }
}
