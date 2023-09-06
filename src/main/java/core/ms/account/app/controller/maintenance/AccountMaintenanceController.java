package core.ms.account.app.controller.maintenance;

import core.ms.account.app.dto.request.AccountRequest;
import core.ms.account.app.dto.response.AccountResponse;
import core.ms.account.app.service.maintenance.AccountMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/domain/account")
public class AccountMaintenanceController {

    @Autowired
    private AccountMaintenanceService accountMaintenanceService;

    @PostMapping
    public ResponseEntity<AccountResponse> save(@RequestBody AccountRequest accountRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountMaintenanceService.save(accountRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable String id, @RequestBody AccountRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountMaintenanceService.update(id, request));
    }

    @PutMapping("/delete/{id}/{status}")
    public ResponseEntity<AccountResponse> delete(@PathVariable String id, @PathVariable String status){
        return ResponseEntity.status(HttpStatus.OK).body(accountMaintenanceService.delete(id, status));
    }
}
