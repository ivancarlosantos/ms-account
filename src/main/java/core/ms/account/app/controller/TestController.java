package core.ms.account.app.controller;

import core.ms.account.app.services.TestServices;
import core.ms.account.infra.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class TestController {

    @Autowired
    private TestServices testServices;

    @GetMapping(path = "/test")
    public ResponseEntity<List<Test>> test() throws UnknownHostException {
        return ResponseEntity.status(HttpStatus.OK).body(testServices.test());
    }
}
