package fx.components;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Class description here....
 * <p>
 *
 * @author gurjyan
 *         Date 8/29/16
 * @version 1.x
 */
public class GArch extends Canvas {
    private String name;
    private GraphicsContext gc;
    private double xStart, yStart, xEnd, yEnd;
    private Builder builder;

    private GArch(Builder builder) {
        super(builder.canvasWidth, builder.canvasHeight);
        gc = getGraphicsContext2D();
        this.builder = builder;
        this.name = builder.name;
        this.xStart = builder.xs;
        this.yStart = builder.ys;
        this.xEnd = builder.xe;
        this.yEnd = builder.ye;
        gc.setStroke(builder.color);
        gc.setLineWidth(builder.width);
        gc.strokeLine(xStart, yStart, xEnd, yEnd);
//        new Animation().start();

        toBack();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void translateStart(double x, double y) {
        delete();
        xStart = x;
        yStart = y;
        redraw();
    }

    public void translateEnd(double x, double y) {
        delete();
        xEnd = x;
        yEnd = y;
        redraw();
    }

    private void redraw() {
        gc.strokeLine(xStart, yStart, xEnd, yEnd);
    }

    public void draw(double xs,double ys, double xe, double ye) {
        delete();
        xStart = xs;
        yStart = ys;
        xEnd = xe;
        yEnd = ye;
        redraw();
    }


    public void delete() {
        gc.clearRect(0,0,getWidth(), getWidth());
    }


    private class Animation extends AnimationTimer {
        int count = 0;

        @Override
        public void handle(long now) {
            count++;

            if (count < 50) {
                translateEnd(10, 40);
            } else if (count < 100) {
                translateEnd(40, 10);
            } else {
                count = 0;
            }
        }
    }

    public static class Builder {
        private double canvasWidth = 500;
        private double canvasHeight = 500;
        private double xs = 40;
        private double ys = 10;
        private double xe = 10;
        private double ye = 40;
        private Paint color = Color.BLACK;
        private double width = 1;
        private String name;

        public Builder(String name) {
            this.name = name;
        }

        public Builder canvasWidth(double canvasWidth) {
            this.canvasWidth = canvasWidth;
            return this;
        }

        public Builder canvasHeight(double canvasHeight) {
            this.canvasHeight = canvasHeight;
            return this;
        }

        public Builder color(Paint color) {
            this.color = color;
            return this;
        }

        public Builder xStart(double xs) {
            this.xs = xs;
            return this;
        }

        public Builder yStart(double ys) {
            this.ys = ys;
            return this;
        }

        public Builder xEnd(double xe) {
            this.xe = xe;
            return this;
        }

        public Builder yEnd(double ye) {
            this.ye = ye;
            return this;
        }

        public Builder width(double width) {
            this.width = width;
            return this;
        }

        public GArch build() {
            return new GArch(this);
        }

    }

}
