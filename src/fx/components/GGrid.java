package fx.components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import test.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Master canvas that reacts mouse clicks and drags.
 * <p>
 *
 * @author gurjyan
 *         Date 8/31/16
 * @version 1.x
 */
public class GGrid extends Canvas {

    private Map<String, GNode> nodes = new HashMap<>();
    private GraphicsContext gc;
    private final static long canvasWidth = 500;
    private final static long canvasHeight = 500;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private GNode sourceNode = null;
    private GArch _arch = null;

    public GGrid() {
        super(canvasWidth, canvasHeight);
        gc = getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(0.5);

        setOnMousePressed(e -> {
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            // find GNode with this coordinates
            sourceNode = findNode(orgSceneX, orgSceneY);
            if (sourceNode != null) {
                orgTranslateX = sourceNode.getTranslateX();
                orgTranslateY = sourceNode.getTranslateY();
                if (e.isControlDown()) {
                    _arch = new GArch.Builder(sourceNode.getName() + "-").build();
                    Test.addArch(_arch);
                }

            }
        });

        setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            if (sourceNode != null) {
                if (e.isControlDown()) {
                    _arch.draw(sourceNode.getCurrentX() + sourceNode.getNodeWidth(),
                            sourceNode.getCurrentY() + (sourceNode.getNodeHeight() / 2),
                            e.getSceneX(),
                            e.getSceneY());
                } else {
                    sourceNode.translate(newTranslateX, newTranslateY);
                }
            }
        });

        setOnMouseReleased(e -> {
            if (e.isControlDown()) {
                // find GNode with this coordinates
                GNode destinationNode = findNode(e.getSceneX(), e.getSceneY());
                if (destinationNode != null) {
                    _arch.setName(_arch.getName() + destinationNode.getName());

                    if(destinationNode.getInLinks().containsKey(_arch.getName())){
                        _arch.delete();
                    } else {

                        double _xs =
                                sourceNode.getCurrentX() +
                                sourceNode.getNodeWidth();
                        double _ys =
                                sourceNode.getCurrentY() +
                                (sourceNode.getNodeHeight() / 2);
                        double _xe =
                                destinationNode.getInitialX()+
                                destinationNode.getCurrentX() +
                                destinationNode.getLineWidth();
                        double _ye =
                                destinationNode.getInitialY() +
                                destinationNode.getCurrentY() +
                                (destinationNode.getNodeHeight() / 2);

                        _arch.draw(_xs, _ys, _xe, _ye);

                        sourceNode.addOutLink(_arch);
                        destinationNode.addInLink(_arch);
                    }
                } else {
                    System.out.println("not_found");
                    _arch.delete();
                }
            }
        });
    }

    public Map<String, GNode> getNodes() {
        return nodes;
    }

    public void addNode(GNode node) {
        nodes.put(node.getName(), node);
    }

    public void removeNode(String nodeName) {
        if (nodes.containsKey(nodeName)) {
            nodes.get(nodeName).delete();
        }
    }

    private GNode findNode(double x, double y) {

        if (nodes.isEmpty()) return null;
        try {
            return nodes.values()
                    .stream()
                    .filter(v -> x >= v.getCurrentX() && x <= (v.getCurrentX() + v.getNodeWidth()) &&
                            y >= v.getCurrentY() && y <= (v.getCurrentY() + v.getNodeHeight()))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void drawGrid(double gridSize) {

        for (double i = 0; i<=canvasHeight; i += gridSize){
            gc.strokeLine(0,i,canvasWidth, i);
        }
        for(double i = 0; i<=canvasWidth; i += gridSize){
        gc.strokeLine(i,0,i,canvasWidth);
        }
    }

    public void removeGrid() {
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
    }
}
