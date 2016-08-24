package fx.components;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ProgressBar;

/**
 * Simple progress bar that binds to a doubleProperty
 * <p>
 *
 * @author gurjyan
 *         Date 8/22/16
 * @version 1.x
 */
public class ProgressBarFx extends ProgressBar{

    private ProgressBarFx(Builder builder){
        super();
        setPrefWidth(builder.width);
        progressProperty().bind(builder.bindP);
    }

    public static class Builder {
        private int width = 200;
        private DoubleProperty bindP;


        public Builder(DoubleProperty bindP) {
            this.bindP = bindP;
        }

        public Builder width(int width){
            this.width = width;
            return this;
        }

        public ProgressBar build(){
            return new ProgressBarFx(this);
        }
    }
}
