
package game;

class NoNameException extends RuntimeException {

    /**
     * Creates a new instance of
     * <code>UnknownFileException</code> without detail message.
     */
    public NoNameException() {
    }

    public NoNameException(String msg) {
        super(msg);
    }
}
