package common;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

/**
 * Utility methods
 * <p>
 *
 * @author gurjyan
 *         Date 8/5/16
 * @version 1.x
 */
public class UtilFx {

    // for JSON parser
    private static ScriptEngine engine =
            new ScriptEngineManager().getEngineByName("javascript");

    // Executes word commands, such as go, move, defined and implemented in
    // {@see} CommandsFx Enum
    public static void execCmd(CommandsFx cmd) {
            cmd.exec();
    }

    /**
     * Simple JSON file parser. No dependencies are required
     * @param f File object
     * @return Map object
     * @throws IOException io exception
     * @throws ScriptException script exception
     */
    public static Map parseJson(File f) throws IOException, ScriptException {
        String json = new String(Files.readAllBytes(f.toPath()));
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = engine.eval(script);
        if(result instanceof Map){
            return (Map) result;
        } else return null;
    }

}
