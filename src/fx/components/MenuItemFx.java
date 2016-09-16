package fx.components;

import common.CadConstants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

/**
 * MenuItem with fluent api
 * <p>
 *
 * @author gurjyan
 *         Date 8/9/16
 * @version 1.x
 */
public class MenuItemFx extends MenuItem {


    private MenuItemFx(Builder builder){
      super(builder.title);

      if(builder.imageView!=null) setGraphic(builder.imageView);
        if (!builder.aclKey.equals(CadConstants.UDF) && !builder.aclLetter.equals(CadConstants.UDF)) {
            setAccelerator(KeyCombination.keyCombination(builder.aclKey +"+"+builder.aclLetter));
        }
        setOnAction(builder.action);
    }

    public static class Builder {
        private String title;

        private String aclKey = CadConstants.UDF;
        private String aclLetter = CadConstants.UDF;
        private ImageView imageView;
        private EventHandler<ActionEvent> action = e-> System.out.println("Empty action");

        public Builder(String title){
            this.title = title;
        }

        public Builder aKey(String aclKey){
            this.aclKey = aclKey;
            return this;
        }

        public Builder aLetter(String aclLetter){
            this.aclLetter = aclLetter;
            return this;
        }

        public Builder image(ImageView imageView){
            this.imageView = imageView;
            return this;
        }

        public Builder action(EventHandler<ActionEvent> action){
            this.action = action;
            return this;
        }

        public MenuItem build(){
            return new MenuItemFx(this);
        }
    }

}
