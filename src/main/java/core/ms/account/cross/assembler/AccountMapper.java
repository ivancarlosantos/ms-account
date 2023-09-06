package core.ms.account.cross.assembler;

import core.ms.account.app.dto.request.AccountRequest;
import core.ms.account.app.dto.response.AccountResponse;
import core.ms.account.infra.domain.Account;

public interface AccountMapper {

    Account toEntity(AccountRequest request);

    AccountResponse toResponse(Account account);
}
