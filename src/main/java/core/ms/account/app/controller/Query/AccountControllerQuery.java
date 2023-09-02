package core.ms.account.app.controller.Query;

import core.ms.account.app.dto.response.AccountResponse;
import core.ms.account.app.service.accountQuery.AccountQueryService;
import core.ms.account.utils.ValidationParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/domain/account")
public class AccountControllerQuery {
    @Autowired
    private AccountQueryService accountQueryService;
    public ResponseEntity<AccountResponse> acharPorId(@PathVariable String id){
        Long valor= ValidationParameter.Validation(id);
        var accountResponse=accountQueryService.encontrarPorId(valor);
        return ResponseEntity.ok(accountResponse);
    }
}
