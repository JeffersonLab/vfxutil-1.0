package fx.components;

import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

/**
 * FileChooser component with the fluent interface
 * <p>
 *
 * @author gurjyan
 *         Date 8/16/16
 * @version 1.x
 */
public class FileChooserFx {

    private FileChooser fc = new FileChooser();
    private Stage window = new Stage();

    private FileChooserFx(Builder builder){
        window.setTitle("Clara Designer");
        window.setMinWidth(builder.width);
        window.setMinHeight(builder.height);
        // This will lock the parent window before this one exists
        window.initModality(builder.modality);

        fc.setInitialDirectory(builder.dir);
        fc.setTitle(builder.title);
    }

    public static class Builder {

        private int width = 400, height = 200;
        private String title = "File Chooser";
        private Modality modality = Modality.APPLICATION_MODAL;
        private File dir;


        public Builder(File dir){
            this.dir = dir;
        }

        public Builder width(int minWidth){
            this.width = minWidth;
            return this;
        }

        public Builder height(int minHight){
            this.height = minHight;
            return this;
        }

        public Builder modal(Modality modal){
            this.modality = modal;
            return this;
        }

        public Builder title(String  title){
            this.title = title;
            return this;
        }

        public File build(){
           FileChooserFx fcc = new FileChooserFx(this);
           return fcc.fc.showOpenDialog(fcc.window);
        }
    }
}
