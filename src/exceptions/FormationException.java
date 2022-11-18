package exceptions;

//essa classe é serializable, por isso ela precisa de um numero de versão:
public class FormationException extends RuntimeException{
//    public static final long serialVersionUID = 1L;
    public FormationException(String msg){
        super(msg);
    }

}
