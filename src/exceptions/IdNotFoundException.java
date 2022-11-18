package exceptions;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String msg){
        super(msg);
    }
}
