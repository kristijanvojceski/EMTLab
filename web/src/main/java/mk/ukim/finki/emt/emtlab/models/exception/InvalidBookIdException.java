package mk.ukim.finki.emt.emtlab.models.exception;

public class InvalidBookIdException extends RuntimeException{

    public InvalidBookIdException() {
        super("Invalid book id");
    }
}
