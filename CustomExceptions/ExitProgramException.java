package contacts.CustomExceptions;

public class ExitProgramException extends RuntimeException {

    public ExitProgramException(String message) {
        super(message);
    }
}
