package core.ms.account.app.service.accountQuery;

import core.ms.account.app.dto.response.AccountResponse;
import core.ms.account.app.exceptions.BusinessException;
import core.ms.account.infra.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountQueryService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper mapper;
    public AccountResponse encontrarPorId(Long id){
        var accont= accountRepository.findById(id).orElseThrow(()-> new BusinessException("Id not found"));
        return mapper.map(accont, AccountResponse.class);
    }
}
