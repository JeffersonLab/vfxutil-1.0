package appd.model;

import common.ConstantsFx;
import fx.components.ConfirmBoxFx;
import fx.components.InputBoxFx;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Class description here....
 * <p>
 *
 * @author gurjyan
 *         Date 8/17/16
 * @version 1.x
 */
public class CadModel {

    // menu item disable properties
    public BooleanProperty appDDisableProperty = new SimpleBooleanProperty(true);
    public BooleanProperty serviceDDisableProperty = new SimpleBooleanProperty(false);
    public BooleanProperty editDisableProperty = new SimpleBooleanProperty(false);

    private int designerState = ConstantsFx.APP_DESIGNER_MODE;


    /**
     * Sets the button states (enable/disable menu items) based on the state of the designer
     * @param designerState Clara designer state
     */
    public void setState(int designerState) {
        this.designerState = designerState;
        switch (designerState) {
            case ConstantsFx.APP_DESIGNER_MODE:
                appDDisableProperty.set(false);
                serviceDDisableProperty.set(true);
                editDisableProperty.set(false);
                break;
            case ConstantsFx.SERVICE_DESIGNER_MODE:
                appDDisableProperty.set(true);
                serviceDDisableProperty.set(false);
                editDisableProperty.set(false);
                break;
            case ConstantsFx.APP_MONITOR_MODE:
                appDDisableProperty.set(true);
                serviceDDisableProperty.set(true);
                editDisableProperty.set(true);
                break;
        }
    }

    public int getState(){
        return designerState;
    }

    public void mNew(){
        {
            String result = ConstantsFx.UDF;
            switch (designerState){
                case ConstantsFx.APP_DESIGNER_MODE:
                    result = new InputBoxFx.Builder("Enter the name of the application")
                            .title("Input Dialog")
                            .width(300)
                            .height(150)
                            .prompt("name")
                            .build().get();

                    if(!result.equals(ConstantsFx.UDF)){
                        //@todo calls model appropriate method
                    }
                    break;
                case ConstantsFx.SERVICE_DESIGNER_MODE:
                    result = new InputBoxFx.Builder("Enter the name of the application")
                            .title("Input Dialog")
                            .width(300)
                            .height(150)
                            .prompt("name")
                            .build().get();

                    if(!result.equals(ConstantsFx.UDF)){
                        //@todo call model appropriate method
                    }
                    break;
            }
        }
    }

    public void mOpen(){
            switch (designerState){
                case ConstantsFx.APP_DESIGNER_MODE:
                    //@todo tree view of applications standard dir
                    break;
                case ConstantsFx.SERVICE_DESIGNER_MODE:
                    //@todo tree view of services standard dir $CLARA_HOME/services
                    break;
            }
            System.out.println("open");
    }

    public void mSave(){
        switch (designerState) {
            case ConstantsFx.APP_DESIGNER_MODE:
                // @todo call model appropriate method
                break;
            case ConstantsFx.SERVICE_DESIGNER_MODE:
                // @todo call model appropriate method
                break;
        }
    }

    public void mSaveAs(){
        String result = new InputBoxFx.Builder("Enter the new file name")
                .title("Input Dialog")
                .width(300)
                .height(150)
                .prompt("name")
                .build().get();

        switch (designerState) {
            case ConstantsFx.APP_DESIGNER_MODE:
                // @todo call model appropriate method
                break;
            case ConstantsFx.SERVICE_DESIGNER_MODE:
                // @todo call model appropriate method
                break;
        }
        System.out.println("save as");
    }

    public void mDeleteService(){
        boolean b = new ConfirmBoxFx.Builder("Are you sure ?")
                .width(300)
                .height(150)
                .title("Delete Service")
                .severity(ConstantsFx.WARNING)
                .build().get();
        //@todo call the method from the model  with this b
    }

    public void mDeleteLink(){
        boolean b = new ConfirmBoxFx.Builder("Are you sure ?")
                .width(300)
                .height(150)
                .title("Delete Service Link")
                .severity(ConstantsFx.WARNING)
                .build().get();
        //@todo call the method from the model  with this b
    }
}
