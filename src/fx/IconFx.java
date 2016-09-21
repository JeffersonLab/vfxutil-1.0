package fx;

import common.CadConstants;
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
    private static Image iPlay = new Image(IconFx.class.getResourceAsStream("../icons/play.png"));
    private static Image iArchive = new Image(IconFx.class.getResourceAsStream("../icons/archive.png"));
    private static Image iFolder = new Image(IconFx.class.getResourceAsStream("../icons/folder.png"));
    private static Image iService = new Image(IconFx.class.getResourceAsStream("../icons/service.png"));


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
    private static ImageView _play = new ImageView(iPlay);
    private static ImageView _archive = new ImageView(iArchive);
    private static ImageView _folder = new ImageView(iFolder);
    private static ImageView _service = new ImageView(iService);


    /**
     * @param id integer values defined in {@link CadConstants}
     * @return ImageView object
     */
    public static ImageView get(int id) {
        ImageView result;
        switch (id) {
            case CadConstants.INFO:
                result = _info;
                break;
            case CadConstants.WARNING:
                result = _warning;
                break;
            case CadConstants.ERROR:
                result = _error;
                break;
            case CadConstants.OPEN:
                result = _open;
                break;
            case CadConstants.SAVE:
                result = _save;
                break;
            case CadConstants.SYNC:
                result = _sync;
                break;
            case CadConstants.CUT:
                result = _cut;
                break;
            case CadConstants.COPY:
                result = _copy;
                break;
            case CadConstants.PASTE:
                result = _paste;
                break;
            case CadConstants.CONNECTION:
                result = _connection;
                break;
            case CadConstants.PLAY:
                result = _play;
                break;
            case CadConstants.LIB:
                result = _archive;
                break;
            case CadConstants.ENGINE:
                result = _folder;
                break;
            case CadConstants.SERVICE:
                result = _service;
                break;
            default:
                result = _info;
        }
        return result;
    }

}
