package appd.view;

import appd.model.CadModel;
import fx.components.MenuItemFx;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;


/**
 * Clara designer main menu bar
 * <p>
 *
 * @author gurjyan
 *         Date 8/12/16
 * @version 1.x
 */
public class CadMainMenuBar extends MenuBar {


    private CadModel model;

    /**
     * Creates the main menu bar
     */
    public CadMainMenuBar(CadModel model) {
        super();
        this.model = model;
        getMenus().addAll(
                createFileMenu(),
                createEditMenu(),
                createBuildMenu(),
                createRunMenu(),
                createHelpMenu());
    }


    /**
     * Creates File menu
     *
     * @return file menu
     */
    private Menu createFileMenu() {
        // New menu item
        MenuItem _new = new MenuItemFx.Builder("New...")
                .aKey("Ctrl")
                .aLetter("N")
                .action(e -> model.mNew())
                .build();
        _new.disableProperty().bind(model.editDisableProperty);

        // Open menu item
        MenuItem _open = new MenuItemFx.Builder("Open...")
                .aKey("Ctrl")
                .aLetter("O")
                .action(e -> model.mOpen())
                .build();
        _open.disableProperty().bind(model.editDisableProperty);

        // Save menu item
        MenuItem _save = new MenuItemFx.Builder("Save")
                .aKey("Ctrl")
                .aLetter("S")
                .action(e -> model.mSave())
                .build();
        _save.disableProperty().bind(model.editDisableProperty);

        // SaveAs menu item
        MenuItem _saveAs = new MenuItemFx.Builder("Save As...")
                .action(e -> model.mSaveAs())
                .build();
        _saveAs.disableProperty().bind(model.editDisableProperty);

        // Synchronize menu item
        MenuItem _synchronize = new MenuItemFx.Builder("Synchronize")
                .action(e -> System.out.println("synchronize"))
                .build();

        // Exit menu item
        MenuItem _exit = new MenuItemFx.Builder("Exit")
                .action(e -> System.out.println("exit"))
                .build();


        Menu m = new Menu("File");
        m.getItems().addAll(_new, new SeparatorMenuItem(),
                _open, new SeparatorMenuItem(),
                _save, new SeparatorMenuItem(),
                _saveAs, new SeparatorMenuItem(),
                _synchronize, new SeparatorMenuItem(),
                _exit
        );
        return m;
    }

    /**
     * Creates Edit menu
     *
     * @return Edit menu
     */
    private Menu createEditMenu() {
        // Copy menu item
        MenuItem _copy = new MenuItemFx.Builder("Copy")
                .action(e -> System.out.println("copy"))
                .build();
        _copy.disableProperty().bind(model.editDisableProperty);

        // Paste menu item
        MenuItem _paste = new MenuItemFx.Builder("Paste")
                .action(e -> System.out.println("paste"))
                .build();
        _paste.disableProperty().bind(model.editDisableProperty);

        // Delete service menu item
        MenuItem _deleteService = new MenuItemFx.Builder("Delete Service")
                .aKey("Ctrl")
                .aLetter("K")
                .action(e ->  model.mDeleteService())
                .build();
        _deleteService.disableProperty().bind(model.appDDisableProperty);

        // Delete link menu item
        MenuItem _deleteLink = new MenuItemFx.Builder("Delete Link")
                .aKey("Ctrl")
                .aLetter("L")
                .action(e ->  model.mDeleteLink())
                .build();
        _deleteLink.disableProperty().bind(model.appDDisableProperty);


        // Undo menu item
        MenuItem _undo = new MenuItemFx.Builder("Undo")
                .action(e -> System.out.println("undo"))
                .build();

        Menu m = new Menu("Edit");
        m.getItems().addAll(
                _copy, new SeparatorMenuItem(),
                _paste, new SeparatorMenuItem(),
                _deleteLink, new SeparatorMenuItem(),
                _deleteService, new SeparatorMenuItem(),
                _undo
        );
        return m;
    }

    /**
     * Creates Run menu
     *
     * @return Run menu
     */
    private Menu createRunMenu() {
        // Local menu item
        MenuItem _local = new MenuItemFx.Builder("Local...")
                .aKey("Ctrl")
                .aLetter("L")
                .action(e -> System.out.println("local"))
                .build();
        _local.disableProperty().bind(model.appDDisableProperty);

        // cloud menu item
        MenuItem _cloud = new MenuItemFx.Builder("Cloud...")
                .aKey("Ctrl")
                .aLetter("R")
                .action(e -> System.out.println("cloud"))
                .build();
        _cloud.disableProperty().bind(model.appDDisableProperty);


        Menu m = new Menu("Run");
        m.getItems().addAll(
                _local, new SeparatorMenuItem(),
                _cloud
        );
        return m;
    }

    /**
     * Creates Build menu
     *
     * @return Build menu
     */
    private Menu createBuildMenu() {
        // compile a service menu item
        MenuItem _compile = new MenuItemFx.Builder("Compile")
                .action(e -> System.out.println("compile"))
                .build();
        _compile.disableProperty().bind(model.serviceDDisableProperty);

        // generate javadoc of a service menu item
        MenuItem _javaDoc = new MenuItemFx.Builder("Generate JavaDoc")
                .action(e -> System.out.println("generate javadoc"))
                .build();
        _javaDoc.disableProperty().bind(model.serviceDDisableProperty);


        Menu m = new Menu("Build");
        m.getItems().addAll(
                _compile, new SeparatorMenuItem(),
                _javaDoc
        );
        return m;
    }

    /**
     * Creates Help menu
     *
     * @return Help menu
     */
    private Menu createHelpMenu() {
        // About menu item
        MenuItem _about = new MenuItemFx.Builder("About...")
                .action(e -> System.out.println("about"))
                //@todo open html widget pointing to Clara documentation
                //@todo window should not steel control
                .build();

        Menu m = new Menu("Help");
        m.getItems().addAll(_about);
        return m;
    }

}
