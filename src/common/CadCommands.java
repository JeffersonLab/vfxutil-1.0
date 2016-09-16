package common;

/**
 * Enumeration of command words, such as go, move, save, etc.
 * Actual command execution bodies is accessed as a function references
 * <p>
 *
 * @author gurjyan
 *         Date 8/5/16
 * @version 1.x
 */
public enum CadCommands {
    GO("GO") {
        @Override
        public void exec() {
          // go function reference here
            System.out.println("executing go");
        }
    },
    MOVE("MOVE") {
        @Override
        public void exec() {
            // save function reference here
            System.out.println("executing move");
        }
    },
    SAVE("SAVE") {
        @Override
        public void exec() {
            // save function reference here
            System.out.println("executing save");
        }
    };

    private String cWord;

    CadCommands(String cWord) {
        this.cWord = cWord;
    }

    public String toString(){
        return cWord;
    }

    public abstract void exec();

}
