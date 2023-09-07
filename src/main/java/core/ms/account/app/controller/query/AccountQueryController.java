package core.ms.account.app.controller.query;

import core.ms.account.app.dto.response.AccountResponse;
import core.ms.account.app.service.query.AccountQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/domain/account")
public class AccountQueryController {
    @Autowired
    private AccountQueryService accountServiceQuery;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(accountServiceQuery.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountResponse> findByID(@PathVariable String id){
        AccountResponse accountResponse = accountServiceQuery.findByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(accountResponse);
    }
}
