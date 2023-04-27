package mk.ukim.finki.emt.emtlab.models.exception;


public class InvalidAuthorIdException extends RuntimeException{

    public InvalidAuthorIdException() {
        super("Invalid author id");
    }
}
