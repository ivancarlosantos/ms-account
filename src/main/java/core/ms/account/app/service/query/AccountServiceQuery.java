package core.ms.account.app.service.query;

import core.ms.account.app.dto.response.AccountResponse;
import core.ms.account.exceptions.BusinessException;
import core.ms.account.infra.domain.Account;
import core.ms.account.infra.repository.AccountRepository;
import core.ms.account.cross.utils.ValidationParameter;
import core.ms.account.status.AccountStatus;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class AccountServiceQuery {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper mapper;

    public List<AccountResponse> listAll(){
        List<Account> accountDomain = accountRepository
                .findAll()
                .stream()
                .filter(c->c.getStatus().equals(AccountStatus.ATIVO.toString()))
                .toList();
        Type listType = new TypeToken<List<AccountResponse>>(){}.getType();
        return mapper.map(accountDomain, listType);
    }

    public AccountResponse findByID(String value){
        Long id = ValidationParameter.validate(value);
        Account account = accountRepository.findById(id).orElseThrow(()-> new BusinessException("ID " + id + " NOT FOUND"));
        return mapper.map(account, AccountResponse.class);
    }
}
