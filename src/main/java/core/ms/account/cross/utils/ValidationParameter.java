package core.ms.account.cross.utils;

import core.ms.account.exceptions.BusinessException;

public class ValidationParameter {
    private ValidationParameter(){}

    public static Long validate(String valor){
        try {
            return Long.parseLong(valor);
        }catch (NumberFormatException o){
            throw new BusinessException("Parâmetro Inválido");
        }
    }
}
