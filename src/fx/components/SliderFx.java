package fx.components;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Slider;

/**
 * Simple Slider with fluent interface
 * <p>
 *
 * @author gurjyan
 *         Date 8/22/16
 * @version 1.x
 */
public class SliderFx extends Slider {

    private SliderFx(Builder builder){
        super(builder.min, builder.max, builder.initValue);
        setPrefWidth(builder.width);
        valueProperty().bindBidirectional(builder.bindP);
    }

    public static class Builder {
        private double min = 0.0, max = 1.0, initValue = 0.0;
        int width = 200;
        DoubleProperty bindP;

        public Builder(DoubleProperty bindP) {
          this.bindP = bindP;
        }

        public Builder width(int width){
            this.width = width;
            return this;
        }

        public Builder min(double min){
            this.min = min;
            return this;
        }

        public Builder max(double max){
            this.max = max;
            return this;
        }

        public Builder initValue(double initValue){
            this.initValue = initValue;
            return this;
        }

        public Slider build(){
            return new SliderFx(this);
        }
    }
}
