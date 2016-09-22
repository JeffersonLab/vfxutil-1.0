package appd.view;

import appd.model.CadModel;
import common.CadColor;
import common.CadConstants;
import fx.UtilFx;
import fx.components.NodeFx;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;

/**
 * Clara application designer
 * <p>
 *
 * @author gurjyan
 *         Date 8/9/16
 * @version 1.x
 */
public class CadMain extends Application {

    // Main stage
    Stage window;


    CadMainMenuBar menuBar;
    CadMainToolBar toolBar;
    CadModel model = new CadModel();
    WebView docWebMon = new WebView();
    WebView docWebDoc = new WebView();

    public static void main(String[] args) {
        UtilFx.disableWebCertificates();
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        menuBar = new CadMainMenuBar(model, this);
        toolBar = new CadMainToolBar(model, this);
        VBox topBox = new VBox(menuBar, toolBar);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topBox);
        borderPane.setCenter(createTabs());

        Scene scene = new Scene(borderPane, 800, 600);
        scene.getStylesheets().add("/projavafx/starterapp/ui/starterApp.css");
        window.setScene(scene);
        window.setTitle("CLARA Application Designer");
        window.show();

    }

    private TabPane createTabs() {
        // create tabs
        Tab t_designer = new Tab("Designer");
        t_designer.setClosable(false);
        t_designer.setContent(createDesignerTab());


        Tab t_monitor = new Tab("Monitor");
        t_monitor.setClosable(false);
        t_monitor.setContent(createMonTab());


        Tab t_doc = new Tab("Documentation");
        t_doc.setClosable(false);
        t_doc.setContent(createDocTab());

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(t_designer, t_monitor, t_doc);
        return tabPane;
    }

    public Node createDesignerTab() {

        Group root = new Group();

        CadCanvas grid = new CadCanvas(window, root, 102);
        grid.drawGrid();

        NodeFx node1 = new NodeFx.Builder("aman")
                .color(CadColor.TURQUOISE)
                .build();

        NodeFx node2 = new NodeFx.Builder("chaman")
                .color(CadColor.YELLOW)
                .shape(CadConstants.OVAL)
                .build();
        NodeFx node3 = new NodeFx.Builder("karo")
                .color(CadColor.TURQUOISE)
                .build();

        grid.addNode(node1);
        grid.addNode(node2);
        grid.addNode(node3);

        root.getChildren().addAll(grid, node1, node2, node3);
        grid.toFront();

        return root;
    }

    public Node createMonTab(){
        docWebMon.getEngine().load("http://claraweb.jlab.org:3000");
        return docWebMon;
    }
    public Node createDocTab(){
        docWebDoc.getEngine().load("https://claraweb.jlab.org");
        return docWebDoc;
    }

    public void close(){
        window.close();
    }


}
