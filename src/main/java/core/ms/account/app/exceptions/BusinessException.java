package core.ms.account.app.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String mensagem){
        super(mensagem);
    }
}
