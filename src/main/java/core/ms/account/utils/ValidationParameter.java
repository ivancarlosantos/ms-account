package core.ms.account.utils;

public class ValidationParameter {
    private ValidationParameter(){}



    public static Long Validation(String valor){
        try {
            return Long.parseLong(valor);
        }catch (NumberFormatException o){
            throw new RuntimeException("Numero Invalido");
        }
    }
}
