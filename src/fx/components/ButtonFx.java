package fx.components;

import common.CadConstants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

/**
 * JavaFx button with fluid api
 * <p>
 *
 * @author gurjyan
 *         Date 8/9/16
 * @version 1.x
 */
public class ButtonFx extends Button {

    private ButtonFx(Builder builder){
        super(builder.txt);
        if(builder.image!=null) setGraphic(builder.image);
        if(!builder.id.equals(CadConstants.UDF)) setId(builder.id);
        if(!builder.toolTip.equals(CadConstants.UDF)) setTooltip(new Tooltip(builder.toolTip));
        setOnAction(builder.action);
    }

    public static class Builder {
        private String id = CadConstants.UDF;
        private String toolTip = CadConstants.UDF;
        private ImageView image;
        private EventHandler<ActionEvent> action = e-> System.out.println("Empty action");
        private String txt = "";

        public Builder text(String txt){
            this.txt = txt;
            return this;
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder toolTip(String toolTip){
            this.toolTip = toolTip;
            return this;
        }

        public Builder image(ImageView image){
            this.image = image;
            return this;
        }

        public Builder action(EventHandler<ActionEvent> action){
            this.action = action;
            return this;
        }

        public Button build(){
            return new ButtonFx(this);
        }
    }
}
