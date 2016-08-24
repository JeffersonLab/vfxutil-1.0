package appd.view;

import appd.model.CadModel;
import fx.components.CanvasFx;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebView;

/**
 * Class description here....
 * <p>
 *
 * @author gurjyan
 *         Date 8/22/16
 * @version 3.x
 */
public class CadTabPane extends TabPane{

//    private CadModel model;
//    private Canvas canvas = new CanvasFx();
//
//    public CadTabPane(CadModel model){
//        super();
//        this.model = model;
//        getTabs().addAll(
//          createAppDesignTab(),
//          createServiceDesignTab(),
//          createAppMonitorTab());
//
//    }
//
//    Tab createAppDesignTab(){
//        Tab t = new Tab("Application Design");
//        t.setContent();
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//    TabPane createTabs() {
//        final WebView webView = new WebView();
//        Tab tableTab = new Tab("TableView");
//        tableTab.setContent(createTableDemoNode());
//        tableTab.setClosable(false);
//        Tab accordionTab = new Tab("Accordion/TitledPane");
//        accordionTab.setContent(createAccordionTitledDemoNode());
//        accordionTab.setClosable(false);
//        Tab splitTab = new Tab("SplitPane/TreeView/ListView");
//        splitTab.setContent(createSplitTreeListDemoNode());
//        splitTab.setClosable(false);
//        Tab treeTableTab = new Tab("TreeTableView");
//        treeTableTab.setContent(createTreeTableDemoNode());
//        treeTableTab.setClosable(false);
//        Tab scrollTab = new Tab("ScrollPane/Miscellaneous");
//        scrollTab.setContent(createScrollMiscDemoNode());
//        scrollTab.setClosable(false);
//        Tab htmlTab = new Tab("HTMLEditor");
//        htmlTab.setContent(createHtmlEditorDemoNode());
//        htmlTab.setClosable(false);
//        Tab webViewTab = new Tab("WebView");
//        webViewTab.setContent(webView);
//        webViewTab.setClosable(false);
//        webViewTab.setOnSelectionChanged(e -> {
//            String randomWebSite = model.getRandomWebSite();
//            if (webViewTab.isSelected()) {
//                webView.getEngine().load(randomWebSite);
//                System.out.println("WebView tab is selected, loading: "
//                        + randomWebSite);
//            }
//        });
//        TabPane tabPane = new TabPane();
//        tabPane.getTabs().addAll(
//                tableTab,
//                accordionTab,
//                splitTab,
//                treeTableTab,
//                scrollTab,
//                htmlTab,
//                webViewTab
//        );
//
//        return tabPane;
//    }

}
