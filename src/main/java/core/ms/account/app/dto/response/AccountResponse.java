package core.ms.account.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountResponse implements Serializable {
    private Long id;
    private String nome;
    private String conta;
    private String agencia;
    private String limite;
    private String status;
}
