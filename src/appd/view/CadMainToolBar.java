package appd.view;

import appd.model.CadModel;
import common.CadConstants;
import fx.IconFx;
import fx.components.ButtonFx;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;

/**
 * Clara designer main tool bar
 * <p>
 *
 * @author gurjyan
 *         Date 8/16/16
 * @version 1.x
 */
public class CadMainToolBar extends ToolBar {

    private Button _open, _save, _sync, _cut, _copy, _paste, _run;

    private CadModel model;
    private CadMain owner;

    public CadMainToolBar(CadModel model, CadMain owner) {
        super();
        this.model = model;
        this.owner = owner;

        createButtons();
        getItems().addAll(_open, _save, _sync, new Separator(Orientation.VERTICAL),new Separator(Orientation.VERTICAL),
                _cut, _copy, _paste, new Separator(Orientation.VERTICAL),new Separator(Orientation.VERTICAL),
                _run);
    }


    private void createButtons() {
        _open = new ButtonFx.Builder()
                .id("openId")
                .toolTip("Open")
                .image(IconFx.get(CadConstants.OPEN))
                .action(e -> model.mOpen())
                .build();
        _open.disableProperty().bind(model.editDisableProperty);

        _save = new ButtonFx.Builder()
                .id("saveId")
                .toolTip("Save")
                .image(IconFx.get(CadConstants.SAVE))
                .action(e ->  model.mSave())
                .build();
        _save.disableProperty().bind(model.editDisableProperty);

        _sync = new ButtonFx.Builder()
                .id("syncId")
                .toolTip("Sync")
                .image(IconFx.get(CadConstants.SYNC))
                .action(e -> System.out.println()) // @todo call model appropriate method))
                .build();

        _cut = new ButtonFx.Builder()
                .id("cutId")
                .toolTip("Cut")
                .image(IconFx.get(CadConstants.CUT))
                .action(e -> System.out.println()) // @todo call model appropriate method))
                .build();
        _cut.disableProperty().bind(model.editDisableProperty);

        _copy = new ButtonFx.Builder()
                .id("copyId")
                .toolTip("Copy")
                .image(IconFx.get(CadConstants.COPY))
                .action(e -> System.out.println()) // @todo call model appropriate method))
                .build();
        _copy.disableProperty().bind(model.editDisableProperty);

        _paste = new ButtonFx.Builder()
                .id("pasteId")
                .toolTip("Paste")
                .image(IconFx.get(CadConstants.PASTE))
                .action(e -> System.out.println()) // @todo call model appropriate method))
                .build();
        _paste.disableProperty().bind(model.editDisableProperty);

        _run = new ButtonFx.Builder()
                .id("runId")
                .toolTip("Start Data Processing")
                .image(IconFx.get(CadConstants.PLAY))
                .action(e -> System.out.println()) // @todo call model appropriate method))
                .build();
    }

}
