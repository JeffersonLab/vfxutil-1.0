package fx.components;

import common.CadColor;
import common.CadConstants;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Arch
 * <p>
 *
 * @author gurjyan
 *         Date 8/29/16
 * @version 1.x
 */
public class ArchFx extends Canvas implements Serializable {
    private Builder builder;
    private String name;
    private String startNodeName, endNodeName;
    private transient GraphicsContext gc;
    private double xStart, yStart, xEnd, yEnd;
    private final int ARR_SIZE = 10;

    private ArchFx(Builder builder) {
        super(builder.canvasWidth, builder.canvasHeight);
        gc = getGraphicsContext2D();
        this.builder =builder;
        this.name = builder.name;
        this.xStart = builder.xs;
        this.yStart = builder.ys;
        this.xEnd = builder.xe;
        this.yEnd = builder.ye;
        gc.setStroke(Color.web(builder.color.getRGB()));
        gc.setLineWidth(builder.width);
        gc.strokeLine(xStart, yStart, xEnd, yEnd);
        gc.fillOval(xEnd, yEnd, ARR_SIZE, ARR_SIZE);

//        new Animation().start();

    }

    public Builder getBuilder() {
        return builder;
    }

    public String getName() {
        return name;
    }

    public void setName(String startNodeName, String endNodeName) {
        this.startNodeName = startNodeName;
        this.endNodeName = endNodeName;
        this.name = startNodeName + "`" + endNodeName;
    }

    public double getxStart() {
        return xStart;
    }

    public double getyStart() {
        return yStart;
    }

    public double getxEnd() {
        return xEnd;
    }

    public double getyEnd() {
        return yEnd;
    }

    public void setxStart(double xStart) {
        this.xStart = xStart;
    }

    public void setyStart(double yStart) {
        this.yStart = yStart;
    }

    public void setxEnd(double xEnd) {
        this.xEnd = xEnd;
    }

    public void setyEnd(double yEnd) {
        this.yEnd = yEnd;
    }

    public String getStartNodeName() {
        return startNodeName;
    }

    public void setStartNodeName(String startNodeName) {
        this.startNodeName = startNodeName;
    }

    public String getEndNodeName() {
        return endNodeName;
    }

    public void setEndNodeName(String endNodeName) {
        this.endNodeName = endNodeName;
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

    public void redraw() {
        gc.strokeLine(xStart, yStart, xEnd, yEnd);
        gc.fillOval(xEnd-ARR_SIZE/2, yEnd-ARR_SIZE/2, ARR_SIZE, ARR_SIZE);
    }

    public void draw(double xs,double ys, double xe, double ye, CadColor color) {
        delete();
        xStart = xs;
        yStart = ys;
        xEnd = xe;
        yEnd = ye;
        gc.setStroke(Color.web(color.getRGB()));
        redraw();
        gc.setStroke(Color.web(builder.color.getRGB()));
    }


    public void delete() {
        gc.clearRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArchFx archFx = (ArchFx) o;

        if (Double.compare(archFx.xStart, xStart) != 0) return false;
        if (Double.compare(archFx.yStart, yStart) != 0) return false;
        if (Double.compare(archFx.xEnd, xEnd) != 0) return false;
        if (Double.compare(archFx.yEnd, yEnd) != 0) return false;
        return name.equals(archFx.name);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(xStart);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yStart);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(xEnd);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yEnd);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    @Override
    public boolean isResizable()
    {
        return true;
    }

    @Override
    public void resize(double width, double height)
    {
        super.setWidth(width);
        super.setHeight(height);
    }


    private class Animation extends AnimationTimer implements Serializable {
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

    public static class Builder implements Serializable {
        public double canvasWidth = CadConstants.CANVAS_WIDTH;
        public double canvasHeight = CadConstants.CANVAS_HEIGHT;
        private double xs = 0;
        private double ys = 0;
        private double xe = 0;
        private double ye = 0;
        private CadColor color = CadColor.BLACK;
        private double width = 1;
        private String name = CadConstants.UDF;

        public Builder() {
        }

        public Builder canvasWidth(double canvasWidth) {
            this.canvasWidth = canvasWidth;
            return this;
        }

        public Builder canvasHeight(double canvasHeight) {
            this.canvasHeight = canvasHeight;
            return this;
        }

        public Builder color(CadColor color) {
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

        public ArchFx build() {
            return new ArchFx(this);
        }

    }

}
