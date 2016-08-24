package appd.view;

import appd.model.CadModel;
import common.ConstantsFx;
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
 * @version 3.x
 */
public class CadMainToolBar extends ToolBar {

    private Button _open, _save, _sync, _cut, _copy, _paste, _connect;

    private CadModel model;

    public CadMainToolBar(CadModel model) {
        super();
        this.model = model;

        getChildren().addAll(_open, _save, _sync, new Separator(Orientation.VERTICAL),
                _cut, _copy, _paste, new Separator(Orientation.VERTICAL),
                _connect);
    }


    private void createButtons() {
        _open = new ButtonFx.Builder()
                .id("openId")
                .toolTip("Open")
                .image(IconFx.get(ConstantsFx.OPEN))
                .action(e -> model.mOpen())
                .build();
        _open.disableProperty().bind(model.editDisableProperty);

        _save = new ButtonFx.Builder()
                .id("saveId")
                .toolTip("Save")
                .image(IconFx.get(ConstantsFx.SAVE))
                .action(e ->  model.mSave())
                .build();
        _save.disableProperty().bind(model.editDisableProperty);

        _sync = new ButtonFx.Builder()
                .id("syncId")
                .toolTip("Sync")
                .image(IconFx.get(ConstantsFx.SYNC))
                .action(e -> System.out.println()) // @todo call model appropriate method))
                .build();

        _cut = new ButtonFx.Builder()
                .id("cutId")
                .toolTip("Cut")
                .image(IconFx.get(ConstantsFx.CUT))
                .action(e -> System.out.println()) // @todo call model appropriate method))
                .build();
        _cut.disableProperty().bind(model.editDisableProperty);

        _copy = new ButtonFx.Builder()
                .id("copyId")
                .toolTip("Copy")
                .image(IconFx.get(ConstantsFx.COPY))
                .action(e -> System.out.println()) // @todo call model appropriate method))
                .build();
        _copy.disableProperty().bind(model.editDisableProperty);

        _paste = new ButtonFx.Builder()
                .id("pasteId")
                .toolTip("Paste")
                .image(IconFx.get(ConstantsFx.PASTE))
                .action(e -> System.out.println()) // @todo call model appropriate method))
                .build();
        _paste.disableProperty().bind(model.editDisableProperty);

        _connect = new ButtonFx.Builder()
                .id("connectId")
                .toolTip("Connect")
                .image(IconFx.get(ConstantsFx.CONNECTION))
                .action(e -> System.out.println()) // @todo call model appropriate method))
                .build();
    }

}
