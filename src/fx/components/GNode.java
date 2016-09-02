package fx.components;

import common.ConstantsFx;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;

import java.util.HashMap;
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
public class GNode extends Canvas {


    private String name;
    private Map<String, GArch>
            inLinks = new HashMap<>(), outLinks = new HashMap<>();
    private GraphicsContext gc;
    private Builder builder;
    private Animation animation;
    private double currentX, currentY;
    private double initialX, initialY;
    private double lineWidth;

    private GNode(Builder builder) {
        super(builder.canvasWidth, builder.canvasHeight);
        gc = getGraphicsContext2D();
        this.builder = builder;
        this.name = builder.name;
        this.currentX = builder.x;
        this.currentY = builder.y;
        this.initialX = builder.x;
        this.initialY = builder.y;
        this.lineWidth = builder.lineWidth;

        gc.setFill(builder.color);
        gc.setStroke(builder.lineColor);
        gc.setLineWidth(builder.lineWidth);

        switch (builder.shape) {
            case ConstantsFx.RECTANGLE:
                gc.fillRect(builder.x, builder.y, builder.width, builder.height);
                gc.strokeRoundRect(builder.x, builder.y, builder.width, builder.height, 0, 0);
                break;
            case ConstantsFx.OVAL:
                gc.fillOval(builder.x, builder.y, builder.width, builder.height);
                gc.strokeOval(builder.x, builder.y, builder.width, builder.height);

                break;
        }
        gc.setFill(Color.BLACK);
        gc.fillText(name, builder.x,
                builder.x + builder.height + 10 + builder.lineWidth,
                builder.width);

        animation = new Animation();
//        animation.start();
    }


    public String getName() {
        return name;
    }

    public double getNodeWidth(){
        return builder.width;
    }

    public double getNodeHeight(){
        return builder.height;
    }

    public double getCurrentX(){
        return currentX;
    }

    public double getCurrentY(){
        return currentY;
    }

    public double getInitialX() {
        return initialX;
    }

    public double getInitialY() {
        return initialY;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public Map<String, GArch> getInLinks() {
        return inLinks;
    }

    public Map<String, GArch> getOutLinks() {
        return outLinks;
    }

    public void addInLink(GArch link) {
        inLinks.put(link.getName(), link);
    }

    public void addOutLink(GArch link) {
        outLinks.put(link.getName(), link);
    }

    public void removeInLink(String linkName) {
        if (inLinks.containsKey(linkName)) {
            inLinks.get(linkName).delete();
        }
    }

    public void removeOutLink(String linkName) {
        if (outLinks.containsKey(linkName)) {
            outLinks.get(linkName).delete();
        }
    }

    public void clearInLinks() {
        inLinks.forEach((s, gArch) -> gArch.delete());
        inLinks.clear();
    }

    public void clearOutLinks() {
        outLinks.forEach((s, gArch) -> gArch.delete());
        outLinks.clear();
    }

    public void delete() {
        gc.clearRect(builder.x-builder.lineWidth,
                builder.y-builder.lineWidth,
                builder.width+builder.lineWidth,
                builder.height+builder.lineWidth);
        clearInLinks();
        clearOutLinks();
    }

    public void translate(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
        currentX = x;
        currentY = y;
        double _xo =
                x +
                initialX +
                getNodeWidth();
        double _yo = y +
                initialY +
                getNodeHeight()/2;

        double _xi = x+
                initialX;

        double _yi = y+
                initialY+
                getNodeHeight()/2;

        if(!inLinks.isEmpty()) inLinks.forEach((k, v) -> v.translateEnd(_xi, _yi));
        if(!outLinks.isEmpty()) outLinks.forEach((k, v) -> v.translateStart(_xo, _yo));
    }

    public void startAnimation() {
        animation.start();
    }

    public void stopAnimation() {
        animation.stop();
    }

    private class Animation extends AnimationTimer {

        double pOffset = 10;
        double pSize = 5;
        int period = 30; //sec
        int count = 0;

        public Animation() {
            super();
            // 3d  effect
            gc.strokeRoundRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize, 0, 0);
            gc.clearRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
            gc.setFill(builder.color);
            gc.fillRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
        }

        @Override
        public void handle(long now) {
            count++;
            if (count < period) {
                gc.clearRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
                gc.setFill(builder.color);
                gc.fillRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
            } else if (count < period * 2) {
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
            switch (builder.shape) {
                case ConstantsFx.RECTANGLE:
                    gc.fillRect(builder.x, builder.y, builder.width, builder.height);
                    gc.strokeRoundRect(builder.x, builder.y, builder.width, builder.height, 0, 0);
                    break;
                case ConstantsFx.OVAL:
                    gc.fillOval(builder.x, builder.y, builder.width, builder.height);
                    gc.strokeOval(builder.x, builder.y, builder.width, builder.height);

                    break;
            }
            // 3d  effect
            gc.strokeRoundRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize, 0, 0);
            gc.clearRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
            gc.setFill(builder.color);
            gc.fillRect(builder.x + pOffset, builder.y + pOffset, pSize, pSize);
        }
    }

    public static class Builder {

        private double canvasWidth = 500;
        private double canvasHeight = 500;

        private int shape = ConstantsFx.RECTANGLE; // default shape
        private Paint color = Color.YELLOWGREEN; // default color

        // rectangle and oval
        private double x = 10;
        private double y = 10;
        private double width = 50;
        private double height = 50;

        // line around a shape
        private Paint lineColor = Color.BLACK;
        private double lineWidth = 0;

        // text under the shape
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
