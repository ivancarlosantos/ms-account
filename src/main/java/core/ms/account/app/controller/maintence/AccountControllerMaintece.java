package core.ms.account.app.controller.maintence;

import core.ms.account.app.dto.request.AccountRequest;
import core.ms.account.app.service.accountMaintece.AccountMainteceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/domain/account")
public class AccountControllerMaintece {
    @Autowired
    private AccountMainteceService accountMainteceService;

    @PostMapping
    public ResponseEntity<AccountRequest> save(@RequestBody AccountRequest accountRequest){
        accountMainteceService.save(accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountRequest);
    }
@PutMapping("/update/{id}")
    public ResponseEntity<AccountRequest>actualizar(@RequestBody AccountRequest accountRequest, @PathVariable Long id){
       accountRequest= accountMainteceService.editar(id, accountRequest);
        URI uri= ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(accountRequest.getId()).toUri();
        return ResponseEntity.created(uri).body(accountRequest);

    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<AccountRequest>apagar(@PathVariable Long id, @RequestBody AccountRequest accountRequest){
        accountRequest= accountMainteceService.apagar(id, accountRequest);
        URI uri= ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accountRequest.getId())
                .toUri();
        return ResponseEntity.created(uri).body(accountRequest);
    }
}
