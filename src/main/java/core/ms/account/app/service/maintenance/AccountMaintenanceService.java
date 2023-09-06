package core.ms.account.app.service.maintenance;

import core.ms.account.app.dto.request.AccountRequest;
import core.ms.account.app.dto.response.AccountResponse;
import core.ms.account.cross.utils.TokenRequest;
import core.ms.account.cross.utils.ValidationParameter;
import core.ms.account.exceptions.BusinessException;
import core.ms.account.infra.domain.Account;
import core.ms.account.infra.domain.Token;
import core.ms.account.status.AccountStatus;
import core.ms.account.infra.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountMaintenanceService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TokenRequest tokenRequest;

    @Autowired
    private ModelMapper mapper;

    public AccountResponse save(AccountRequest accountRequest) {
        Token token = tokenRequest.generateToken();
        Account account = Account.builder()
                .nome(accountRequest.getNome())
                .conta(accountRequest.getConta())
                .agencia(accountRequest.getAgencia())
                .limite(accountRequest.getLimite())
                .nodeID(token.getToken())
                .status(AccountStatus.ATIVO.toString())
                .build();
        accountRepository.save(account);
        return mapper.map(account, AccountResponse.class);
    }

    public AccountResponse update(String value, AccountRequest accountRequest) {
        Long id = ValidationParameter.validate(value);
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new BusinessException("ID NOT FOUND"));

        account.setNome(accountRequest.getNome());
        account.setConta(accountRequest.getConta());
        account.setAgencia(accountRequest.getAgencia());
        account.setLimite(accountRequest.getLimite());
        account.setStatus(accountRequest.getStatus());

        accountRepository.save(account);

        return mapper.map(account, AccountResponse.class);
    }

    public AccountResponse delete(String value, String status) {
        Long id = ValidationParameter.validate(value);
        Optional<Account> findAccount = Optional
                .ofNullable(accountRepository
                        .findById(id)
                        .orElseThrow(() -> new BusinessException("Account ID: " + id + " NOT FOUND")));

        if (findAccount.get().getStatus().equals(AccountStatus.ATIVO.toString())) {
            findAccount.get().setStatus(status);
            accountRepository.save(findAccount.get());
        }

        if (findAccount.get().getStatus().equals(AccountStatus.INATIVO.toString())) {
            findAccount.get().setStatus(status);
            accountRepository.save(findAccount.get());
        }

        return mapper.map(findAccount.get(), AccountResponse.class);
    }
}