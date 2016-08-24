package fx;

import common.ConstantsFx;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Presents {@Link ImageView} objects for supported icons,
 * such as info, warning, error, etc.
 * <p>
 *
 * @author gurjyan
 *         Date 8/4/16
 * @version 1.x
 */
public class IconFx {


    // severity icons
    private static Image iError = new Image(IconFx.class.getResourceAsStream("../icons/error.png"));
    private static Image iWarning = new Image(IconFx.class.getResourceAsStream("../icons/warning.png"));
    private static Image iInfo = new Image(IconFx.class.getResourceAsStream("../icons/info.png"));

    // toolbar icons
    private static Image iOpen = new Image(IconFx.class.getResourceAsStream("../icons/open.png"));
    private static Image iSave = new Image(IconFx.class.getResourceAsStream("../icons/save.png"));
    private static Image iSync = new Image(IconFx.class.getResourceAsStream("../icons/sync.png"));
    private static Image iCut = new Image(IconFx.class.getResourceAsStream("../icons/cut.png"));
    private static Image iCopy = new Image(IconFx.class.getResourceAsStream("../icons/copy.png"));
    private static Image iPaste = new Image(IconFx.class.getResourceAsStream("../icons/paste.png"));
    private static Image iConnection = new Image(IconFx.class.getResourceAsStream("../icons/connection.png"));


    // severity image viewers
    private static ImageView _error = new ImageView(iError);
    private static ImageView _warning = new ImageView(iWarning);
    private static ImageView _info = new ImageView(iInfo);

    // toolbar image viewers
    private static ImageView _open = new ImageView(iOpen);
    private static ImageView _save = new ImageView(iSave);
    private static ImageView _sync = new ImageView(iSync);
    private static ImageView _cut = new ImageView(iCut);
    private static ImageView _copy = new ImageView(iCopy);
    private static ImageView _paste = new ImageView(iPaste);
    private static ImageView _connection = new ImageView(iConnection);


    /**
     * @param id integer values defined in {@link ConstantsFx}
     * @return ImageView object
     */
    public static ImageView get(int id) {
        ImageView result;
        switch (id) {
            case ConstantsFx.INFO:
                result = _info;
                break;
            case ConstantsFx.WARNING:
                result = _warning;
                break;
            case ConstantsFx.ERROR:
                result = _error;
                break;
            case ConstantsFx.OPEN:
                result = _open;
                break;
            case ConstantsFx.SAVE:
                result = _save;
                break;
            case ConstantsFx.SYNC:
                result = _sync;
                break;
            case ConstantsFx.CUT:
                result = _cut;
                break;
            case ConstantsFx.COPY:
                result = _copy;
                break;
            case ConstantsFx.PASTE:
                result = _paste;
                break;
            case ConstantsFx.CONNECTION:
                result = _connection;
                break;
            default:
                result = _info;
        }
        return result;
    }

}
