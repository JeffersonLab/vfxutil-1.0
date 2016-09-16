package common;

import java.io.Serializable;

/**
 * Color enumeration
 * <p>
 *
 * @author gurjyan
 *         Date 9/14/16
 * @version 1.x
 */
public enum CadColor implements Serializable{
    BLACK("#000000"),
    GREY("#808080"),
    RED("#8B0000"),
    GREEN("#008000"),
    PURPLE("#800080"),
    LIME("#00FF00"),
    YELLOW("#FFD700"),
    CYAN("#00FFFF"),
    OLIVE("#808000"),
    TEAL("#008080"),
    SPRING("#00FA9A"),
    TURQUOISE("#40E0D0"),
    LIGHTBLUE ("#87CEEB");

    private CadColor(final String string) {
        this.string = string;
    }

    private final String string;

    public String getRGB() {
        return string;
    }
}
