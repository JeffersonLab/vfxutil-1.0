package fx.components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Class description here....
 * <p>
 *
 * @author gurjyan
 *         Date 8/22/16
 * @version 1.x
 */
public class CanvasFx extends Canvas {

    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    private CanvasFx(Builder builder) {
        super(builder.width, builder.height);
        GraphicsContext gc = getGraphicsContext2D();
        builder.drawShape(gc);

        setOnMousePressed(e -> {
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = ((Canvas) e.getSource()).getTranslateX();
            orgTranslateY = ((Canvas) e.getSource()).getTranslateY();
            System.out.println(orgSceneX+" "+orgTranslateX);
            System.out.println(orgSceneY+" "+orgTranslateY);
        });
        setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            System.out.println(offsetX+" "+offsetY);
            System.out.println(newTranslateX+" "+newTranslateY);
            System.out.println();

            ((Canvas) (e.getSource())).setTranslateX(newTranslateX);  //transform the object
            ((Canvas) (e.getSource())).setTranslateY(newTranslateY);

        });
    }

    public abstract static class Builder {
        private int width = 300;
        private int height = 250;

        public abstract void drawShape(GraphicsContext gc);

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Canvas build() {
            return new CanvasFx(this);
        }
    }

}
