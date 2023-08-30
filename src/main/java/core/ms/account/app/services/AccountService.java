package core.ms.account.app.services;

import core.ms.account.infra.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
}
