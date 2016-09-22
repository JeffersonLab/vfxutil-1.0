package appd.view;

import common.CadColor;
import common.CadConstants;
import fx.components.ArchFx;
import fx.components.NodeFx;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.Serializable;
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
public class CadCanvas extends Canvas implements Serializable {

    private Map<String, NodeFx> nodes = new HashMap<>();
    private transient GraphicsContext gc;
    private transient Group root;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private double gridSize = 50;
    private NodeFx sourceNode = null;
    private ArchFx _arch = null;

    public CadCanvas(Stage primaryStage, Group root, int yOffset) {
        super(CadConstants.CANVAS_WIDTH, CadConstants.CANVAS_HEIGHT);
        this.root = root;

        widthProperty().bind(primaryStage.widthProperty());
        heightProperty().bind(primaryStage.heightProperty());

        gc = getGraphicsContext2D();
        CadColor black = CadColor.BLACK;
        gc.setStroke(Color.web(black.getRGB()));
        gc.setLineWidth(0.5);

        setOnMouseClicked(e -> {
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY() - yOffset;
            // find NodeFx with this coordinates
            sourceNode = findNode(orgSceneX, orgSceneY);
            if (sourceNode != null) {
                orgTranslateX = sourceNode.getTranslateX();
                orgTranslateY = sourceNode.getTranslateY();
                if (e.isShiftDown()) {
                    if (e.getClickCount() == 1) {
                        sourceNode.getOutLinks().values().forEach(a -> a.draw(a.getxStart(),
                                a.getyStart(),
                                a.getxEnd(),
                                a.getyEnd(),
                                CadColor.RED));
                    } else if (e.getClickCount() == 2) {
                        sourceNode.getOutLinks().values().forEach(a -> a.draw(a.getxStart(),
                                a.getyStart(),
                                a.getxEnd(),
                                a.getyEnd(),
                                CadColor.BLACK));
                    }
                }
            }
        });

        setOnMousePressed(e -> {
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY() - yOffset;
            // find NodeFx with this coordinates
            sourceNode = findNode(orgSceneX, orgSceneY);
            if (sourceNode != null) {
                orgTranslateX = sourceNode.getTranslateX();
                orgTranslateY = sourceNode.getTranslateY();
                if (e.isControlDown()) {
                    _arch = new ArchFx.Builder(primaryStage).build();
                    addArch(_arch);
                }

            }
        });

        setOnMouseDragged(e -> {
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = (e.getSceneY() - yOffset) - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            // this is for not going out of dedicated canvas space
            if(newTranslateX < 0) newTranslateX = 0;
            if(newTranslateY < 0) newTranslateY = 0;
                if (sourceNode != null) {
                    if (e.isControlDown()) {
                        _arch.draw(sourceNode.getCurrentX() + sourceNode.getNodeWidth(),
                                sourceNode.getCurrentY() + (sourceNode.getNodeHeight() / 2),
                                e.getSceneX(),
                                e.getSceneY() - yOffset, CadColor.BLACK);
                    } else {
                        sourceNode.translate(newTranslateX, newTranslateY);
                    }
                }
        });

        setOnMouseReleased(e -> {
            if (e.isControlDown()) {
                // find NodeFx with this coordinates
                NodeFx destinationNode = findNode(e.getSceneX(), e.getSceneY() - yOffset);
                if (destinationNode != null && sourceNode != null) {
                    _arch.setName(sourceNode.getName(), destinationNode.getName());

                    if (destinationNode.getInLinks().containsKey(_arch.getName())) {
                        _arch.delete();
                    } else {

                        double _xs =
                                sourceNode.getCurrentX() +
                                        sourceNode.getNodeWidth();
                        double _ys =
                                sourceNode.getCurrentY() +
                                        (sourceNode.getNodeHeight() / 2);
                        double _xe =
                                destinationNode.getInitialX() +
                                        destinationNode.getCurrentX() +
                                        destinationNode.getLineWidth();
                        double _ye =
                                destinationNode.getInitialY() +
                                        destinationNode.getCurrentY() +
                                        (destinationNode.getNodeHeight() / 2);


                        _arch.draw(_xs, _ys, _xe, _ye, CadColor.BLACK);

                        sourceNode.addOutLink(_arch);
                        destinationNode.addInLink(_arch);
                    }
                } else {
                    System.out.println("not_found");
                    if (_arch != null) _arch.delete();
                }
            }
        });
    }

    public Map<String, NodeFx> getNodes() {
        return nodes;
    }

    public void addNode(NodeFx node) {
        nodes.put(node.getName(), node);
    }

    public void removeNode(String nodeName) {
        if (nodes.containsKey(nodeName)) {
            nodes.get(nodeName).delete();
        }
    }

    private NodeFx findNode(double x, double y) {
        if (nodes.isEmpty()) return null;
        try {

//            nodes.values().forEach(v -> {
//                System.out.println(v.getCurrentX() + " x " + x + " x " + (v.getCurrentX() + v.getNodeWidth()));
//                System.out.println(v.getCurrentY() + " y " + y + " y " + (v.getCurrentY() + v.getNodeHeight()));
//                System.out.println("\n");
//            });


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

    public void setGridSize(double gs) {
        gridSize = gs;
    }

    public void drawGrid() {

        for (double i = 0; i <= getHeight(); i += gridSize) {
            gc.strokeLine(0, i, getWidth(), i);
        }
        for (double i = 0; i <= getWidth(); i += gridSize) {
            gc.strokeLine(i, 0, i, getWidth());
        }
    }

    public void addArch(ArchFx arch) {
        root.getChildren().add(arch);
        arch.toBack();
    }

    public void removeGrid() {
        gc.clearRect(0, 0, getWidth(), getHeight());
    }


    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        removeGrid();
        drawGrid();
    }

}
