package com.roconmachine.io.accounts.account;

//import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/accounts/api/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;

    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(accountService.get(id));
    }

    @PostMapping
//    @ApiResponse(responseCode = "201")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid final AccountRequest request) {
        final AccountResponse createdId = accountService.create(request);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateAccount(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final AccountRequest request) {
        accountService.update(id, request);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
//    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAccount(@PathVariable(name = "id") final Long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
