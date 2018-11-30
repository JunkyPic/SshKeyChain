package keychain.Command;

/**
 * Command interface that should be implemented
 * by various other classes that deal with OS specific commands
 */
public interface ICommand {

    /**
     * Opens a new terminal
     * TODO Figure out how to output to open terminal(if possible)
     */
    void openTerminal();
    void exec(String command);
}
