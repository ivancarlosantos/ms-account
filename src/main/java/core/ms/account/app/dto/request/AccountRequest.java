package core.ms.account.app.dto.request;

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
public class AccountRequest implements Serializable {
    private Long id;
    private String nome;
    private String conta;
    private String agencia;
    private String limite;
    private String nodeID;
    private String status;
}
