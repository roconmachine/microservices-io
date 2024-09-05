package com.roconmachine.io.accounts.account;


import java.util.List;
import java.util.stream.Collectors;


import com.roconmachine.io.accounts.util.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    public List<AccountResponse> findAll() {
        return accountRepository.findAll(Sort.by("id")).stream()
                .map(this.mapper :: fromAccount)
                .collect(Collectors.toList());
    }

    public AccountResponse get(final Long id) {
        return accountRepository.findById(id)
                .map(this.mapper :: fromAccount)
                .orElseThrow(NotFoundException::new);
    }

    public AccountResponse create(final AccountRequest request) {
        var response = accountRepository.save(mapper.toAccount(request));
        return mapper.fromAccount(response);
    }

    public void update(final Long id, final AccountRequest request) {
        final Account account = accountRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapper.mergeAccount(account, request);
        accountRepository.save(account);
    }

    public void delete(final Long id) {
        accountRepository.deleteById(id);
    }


}
