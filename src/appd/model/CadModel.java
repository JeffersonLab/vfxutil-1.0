package appd.model;

import appd.view.CadAppConfig;
import common.CadConstants;
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

    private int designerState = CadConstants.APP_DESIGNER_MODE;


    /**
     * Sets the button states (enable/disable menu items) based on the state of the designer
     * @param designerState Clara designer state
     */
    public void setState(int designerState) {
        this.designerState = designerState;
        switch (designerState) {
            case CadConstants.APP_DESIGNER_MODE:
                appDDisableProperty.set(false);
                serviceDDisableProperty.set(true);
                editDisableProperty.set(false);
                break;
            case CadConstants.SERVICE_DESIGNER_MODE:
                appDDisableProperty.set(true);
                serviceDDisableProperty.set(false);
                editDisableProperty.set(false);
                break;
            case CadConstants.APP_MONITOR_MODE:
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
        new CadAppConfig();

//        {
//            String result = CadConstants.UDF;
//            switch (designerState){
//                case CadConstants.APP_DESIGNER_MODE:
//                    result = new InputBoxFx.Builder("Enter the name of the application")
//                            .title("Input Dialog")
//                            .width(300)
//                            .height(150)
//                            .prompt("name")
//                            .build().get();
//
//                    if(!result.equals(CadConstants.UDF)){
//                        //@todo calls model appropriate method
//                    }
//                    break;
//                case CadConstants.SERVICE_DESIGNER_MODE:
//                    result = new InputBoxFx.Builder("Enter the name of the application")
//                            .title("Input Dialog")
//                            .width(300)
//                            .height(150)
//                            .prompt("name")
//                            .build().get();
//
//                    if(!result.equals(CadConstants.UDF)){
//                        //@todo call model appropriate method
//                    }
//                    break;
//            }
//        }
    }

    public void mOpen(){
            switch (designerState){
                case CadConstants.APP_DESIGNER_MODE:
                    //@todo tree view of applications standard dir
                    break;
                case CadConstants.SERVICE_DESIGNER_MODE:
                    //@todo tree view of services standard dir $CLARA_HOME/services
                    break;
            }
            System.out.println("open");
    }

    public void mSave(){
        switch (designerState) {
            case CadConstants.APP_DESIGNER_MODE:
                // @todo call model appropriate method
                break;
            case CadConstants.SERVICE_DESIGNER_MODE:
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
            case CadConstants.APP_DESIGNER_MODE:
                // @todo call model appropriate method
                break;
            case CadConstants.SERVICE_DESIGNER_MODE:
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
                .severity(CadConstants.WARNING)
                .build().get();
        //@todo call the method from the model  with this b
    }

    public void mDeleteLink(){
        boolean b = new ConfirmBoxFx.Builder("Are you sure ?")
                .width(300)
                .height(150)
                .title("Delete Service Link")
                .severity(CadConstants.WARNING)
                .build().get();
        //@todo call the method from the model  with this b
    }
}
