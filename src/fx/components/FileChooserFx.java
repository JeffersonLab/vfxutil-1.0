package fx.components;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * FileChooser component with the fluent interface
 * <p>
 *
 * @author gurjyan
 *         Date 8/16/16
 * @version 1.x
 */
public class FileChooserFx {

    private FileChooser fc;
    private Stage window = new Stage();

    private FileChooserFx(Builder builder){
        fc = new FileChooser();
        fc.setInitialDirectory(builder.dir);
        fc.setTitle(builder.title);
    }

    public static class Builder {

        private String title = "File Chooser";
        private File dir;


        public Builder(File dir){
            this.dir = dir;
        }

        public Builder title(String  title){
            this.title = title;
            return this;
        }

        public List<File> build(){
           FileChooserFx fcc = new FileChooserFx(this);
               return fcc.fc.showOpenMultipleDialog(fcc.window);
        }
    }
}
