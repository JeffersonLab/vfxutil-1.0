package fx.components;

import common.ConstantsFx;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;

import java.util.Map;

/**
 * Node-and-Ark diagram node component.
 * This node is drawn as a rectangle, circle or oval.
 * It comes with its own canvas and associated text. This will also
 * store a local database of possible in/out GArks (arks).
 * <p>
 *
 * @author gurjyan
 *         Date 8/29/16
 * @version 1.x
 */
public class GNode extends CanvasFx {


    private String name;
    private Map<String, GArch> inLinks, outLinks;
    private GraphicsContext gc;
    private Builder builder;
    private Animation animation;

    private GNode(Builder builder) {
        super(builder.canvasWidth, builder.canvasHeight);
        gc = getGraphicsContext2D();
        this.name = builder.name;

        gc.setFill(builder.color);
        gc.setStroke(builder.lineColor);
        gc.setLineWidth(builder.lineWidth);

        switch (builder.shape){
            case ConstantsFx.RECTANGLE:
                gc.fillRect(builder.x, builder.y, builder.width, builder.height);
                gc.strokeRoundRect(builder.x, builder.y, builder.width, builder.height,0,0);
                break;
            case ConstantsFx.OVAL:
                gc.fillOval(builder.x, builder.y, builder.width, builder.height);
                gc.strokeOval(builder.x, builder.y, builder.width, builder.height);

                break;
        }
        gc.setFill(Color.BLACK);
        gc.fillText(name,builder.x,
                builder.x+builder.height+10+builder.lineWidth,
                builder.width);

        this.builder = builder;

        animation = new Animation();
//        animation.start();

    }


    public String getName() {
        return name;
    }

    public Map<String, GArch> getInLinks() {
        return inLinks;
    }

    public Map<String, GArch> getOutLinks() {
        return outLinks;
    }

    public void addInLink(GArch link){

    }

    public void addOutLink(GArch link){

    }

    public void removeInLink(String linkName){

    }

    public void removeOutLink(String linkName){

    }

    public void clearInLinks(){
        inLinks.clear();
    }

     public void clearOutLinks(){
        outLinks.clear();
    }

    @Override
    public void translate(double x, double y) {
        super.translate(x, y);
        System.out.println("VAI KU ARA");// todo move line from Garch
    }

    public void startAnimation(){
        animation.start();
    }

    public void stopAnimation(){
        animation.stop();
    }

    private class Animation extends AnimationTimer{

        double pOffset = 10;
        double pSize = 5;
        int period = 30; //sec
        int count = 0;

        public Animation(){
            super();
            // 3d  effect
            gc.strokeRoundRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize,0,0);
            gc.clearRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
            gc.setFill(builder.color);
            gc.fillRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
        }

        @Override
        public void handle(long now) {
            count++;
            if(count < period) {
                gc.clearRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
                gc.setFill(builder.color);
                gc.fillRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
            } else if(count < period*2) {
                gc.clearRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
                gc.setFill(Color.RED);
                gc.fillRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
            } else {
                count = 0;
            }
        }

        @Override
        public void start() {
            super.start();
            count = 0;
        }

        @Override
        public void stop() {
            super.stop();
            switch (builder.shape){
                case ConstantsFx.RECTANGLE:
                    gc.fillRect(builder.x, builder.y, builder.width, builder.height);
                    gc.strokeRoundRect(builder.x, builder.y, builder.width, builder.height,0,0);
                    break;
                case ConstantsFx.OVAL:
                    gc.fillOval(builder.x, builder.y, builder.width, builder.height);
                    gc.strokeOval(builder.x, builder.y, builder.width, builder.height);

                    break;
            }
            // 3d  effect
            gc.strokeRoundRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize,0,0);
            gc.clearRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
            gc.setFill(builder.color);
            gc.fillRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
        }
    }

    public static class Builder {

        private double canvasWidth = 500;
        private double canvasHeight = 500;

        private int shape = ConstantsFx.RECTANGLE; // default shape
        private Paint color = Color.DARKRED; // default color

        // rectangle and oval
        private double x = 10;
        private double y = 10;
        private double width = 100;
        private double height = 100;

        // line around a shape
        private Paint lineColor = Color.BLACK;
        private double lineWidth = 0;

        // text under the shape
        private String name;

        public Builder(String name) {
            this.name = name;
        }

        public Builder canvasWidth(double canvasWidth){
            this.canvasWidth = canvasWidth;
            return this;
        }

        public Builder canvasHeight(double canvasHeight){
            this.canvasHeight = canvasHeight;
            return this;
        }

        public Builder shape(int shape) {
            this.shape = shape;
            return this;
        }

        public Builder color(Paint color) {
            this.color = color;
            return this;
        }

        public Builder x(double x) {
            this.x = x;
            return this;
        }

        public Builder y(double y) {
            this.y = y;
            return this;
        }

        public Builder width(double width) {
            this.width = width;
            return this;
        }

        public Builder height(double height) {
            this.height = height;
            return this;
        }


        public Builder lineColor(Paint lineColor) {
            this.lineColor = lineColor;
            return this;
        }

        public Builder lineWidth(double lineWidth) {
            this.lineWidth = lineWidth;
            return this;
        }

        public GNode build() {
            return new GNode(this);
        }

    }
}
