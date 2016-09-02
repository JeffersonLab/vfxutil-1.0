package fx.components;

import javafx.scene.canvas.Canvas;

/**
 * Class description here....
 * <p>
 *
 * @author gurjyan
 *         Date 8/29/16
 * @version 3.x
 */
public class CanvasFx extends Canvas{
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    public CanvasFx(double w, double h) {
        super(w, h);

//        setOnMousePressed(e -> {
//            orgSceneX = e.getSceneX();
//            orgSceneY = e.getSceneY();
//            orgTranslateX = ((Canvas) e.getSource()).getTranslateX();
//            orgTranslateY = ((Canvas) e.getSource()).getTranslateY();
//            System.out.println(orgSceneX+" "+orgTranslateX);
//            System.out.println(orgSceneY+" "+orgTranslateY);
//        });
//        setOnMouseDragged(e -> {
//            double offsetX = e.getSceneX() - orgSceneX;
//            double offsetY = e.getSceneY() - orgSceneY;
//            double newTranslateX = orgTranslateX + offsetX;
//            double newTranslateY = orgTranslateY + offsetY;
//            System.out.println(offsetX+" "+offsetY);
//            System.out.println(newTranslateX+" "+newTranslateY);
//            System.out.println();
//
//            translate(newTranslateX, newTranslateY);
//
//        });
//
//        setOnMouseDragReleased(e -> {
//            orgSceneX = e.getSceneX();
//            orgSceneY = e.getSceneY();
//        });
    }


    public double getCurrentX(){
        return orgSceneX;
    }

    public double getCurrentY(){
        return orgSceneY;
    }


    public void translate(double x, double y){
        setTranslateX(x);
        setTranslateY(y);
    }

}
