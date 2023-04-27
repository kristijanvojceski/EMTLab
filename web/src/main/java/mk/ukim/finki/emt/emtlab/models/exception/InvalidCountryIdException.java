package mk.ukim.finki.emt.emtlab.models.exception;

public class InvalidCountryIdException  extends  RuntimeException{

    public InvalidCountryIdException() {
        super("Invalid country id");
    }
}
