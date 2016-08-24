package fx.components;

import common.ConstantsFx;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Fluent interface for javafx ComboBox
 * <p>
 *
 * @author gurjyan
 *         Date 8/18/16
 * @version 1.x
 */
public class ComboBoxFx extends ComboBox{


    private ComboBoxFx(Builder builder){
        super();
        if(!builder.prompt.equals(ConstantsFx.UDF)) setPromptText(builder.prompt);
        setEditable(builder.editable);
        builder.add.forEach((s) -> getItems().add(s));
    }

    public static class Builder{
        private String prompt = ConstantsFx.UDF;
        private boolean editable = false;
        private Set<String> add = new TreeSet<>(Collections.singletonList("item1, item2, item3"));

        public Builder prompt(String prompt){
            this.prompt = prompt;
            return this;
        }

        public Builder editable(boolean editable){
            this.editable = editable;
            return this;
        }

        public Builder add(Set<String>  add){
            this.add = add;
            return this;
        }

        public ComboBox build(){
           return new ComboBoxFx(this);
        }
    }
}
