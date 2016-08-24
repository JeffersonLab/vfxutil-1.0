package fx.components;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * Radio button toggle group with fluent interface.
 * Builder is an abstract class that requiring implementation
 * of a method that passes toggle group selected radio button name.
 * <p>
 *
 * @author gurjyan
 *         Date 8/19/16
 * @version 1.x
 */
public class RadioToggleGroupFx {

    private HBox radioBox;

    private RadioToggleGroupFx(Builder builder){
        final ToggleGroup radioToggleGroup = new ToggleGroup();
        RadioButton radioButton1 = new RadioButton(builder.name1);
        radioButton1.setToggleGroup(radioToggleGroup);
        RadioButton radioButton2 = new RadioButton(builder.name2);
        radioButton2.setToggleGroup(radioToggleGroup);
        radioBox = new HBox(10, radioButton1, radioButton2);

        radioToggleGroup.selectToggle(radioToggleGroup.getToggles().get(0));
        radioToggleGroup.selectedToggleProperty().addListener((ov, oldValue, newValue) -> {
            RadioButton rb = ((RadioButton) radioToggleGroup.getSelectedToggle());
            if (rb != null) {
                builder.action(rb.getText());
            }
        });

    }

    public HBox get(){
        return radioBox;
    }

    public abstract static class Builder{
        private String name1 = "RadioButton1";
        private String name2 = "RadioButton2";

        protected abstract void action(String radioName);

        public Builder name1(String name1){
            this.name1 = name1;
            return this;
        }

        public Builder name2(String name2){
            this.name2 = name2;
            return this;
        }

        public RadioToggleGroupFx build(){
            return new RadioToggleGroupFx(this);
        }

    }

}
