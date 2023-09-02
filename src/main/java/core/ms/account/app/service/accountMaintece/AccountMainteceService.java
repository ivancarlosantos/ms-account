package core.ms.account.app.service.accountMaintece;

import core.ms.account.app.dto.request.AccountRequest;
import core.ms.account.app.exceptions.BusinessException;
import core.ms.account.infra.domain.Account;
import core.ms.account.infra.domain.status.AccountStatus;
import core.ms.account.infra.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountMainteceService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper mapper;
    public AccountRequest save(AccountRequest accountRequest){
        Account account=  Account.builder().
                nome(accountRequest.getNome()).
                conta(accountRequest.getConta()).
                agencia(accountRequest.getAgencia()).
                limite(accountRequest.getLimite()).
                nodeID(accountRequest.getNodeID()).
                status(AccountStatus.ATIVO)
                .build();

accountRepository.save(account);
return mapper.map(account, AccountRequest.class);
    }

    public AccountRequest editar(Long id, AccountRequest accountRequest){
        Account account= accountRepository.findById(id).
                orElseThrow(()-> new BusinessException("Id not found"));
        return mapper.map(account, AccountRequest.class);
    }



    public AccountRequest apagar(Long id, AccountRequest accountRequest){{
        Account account = accountRepository.findById(id).
                orElseThrow(()->new BusinessException("Account not foud"));
        account.setStatus(AccountStatus.DESATIVO);
        return mapper.map(account, AccountRequest.class);
    }

    }


}
