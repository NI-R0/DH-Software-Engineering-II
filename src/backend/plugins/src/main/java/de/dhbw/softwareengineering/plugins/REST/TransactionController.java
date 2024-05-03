package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.account.AccountDto;
import de.dhbw.softwareengineering.adapters.institution.InstitutionGetDto;
import de.dhbw.softwareengineering.adapters.transaction.TransactionDto;
import de.dhbw.softwareengineering.application.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    //Transaction Methods

    @GetMapping("/api/institution={institution}/account={account}/transactions/getAll")
    @Operation(
            summary = "Retrieve all transactions",
            description = "Retrieve all transactions of an account.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "account" ,description = "Name of account")
    })
    public ResponseEntity<List<TransactionDto>> getAllTransactions(@PathVariable("institution") String institutionName,
                                                                   @PathVariable("account") String accountName)
    {
        return ResponseEntity.ok(transactionService.getAllTransactions(institutionName, accountName));
    }

    @GetMapping("/api/institution={institution}/account={account}/transactions/get/id={id}")
    @Operation(
            summary = "Retrieve transaction by ID",
            description = "Retrieves a transaction from the database by its ID.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "account" ,description = "Name of account"),
            @Parameter(name = "id", description = "ID of transaction to retrieve")
    })
    public ResponseEntity<List<AccountDto>> getTransactionById(@PathVariable("institution") String institutionName,
                                                               @PathVariable("account") String accountName,
                                                               @PathVariable("id") UUID transactionId)
    {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/institution={institution}/account={account}/transactions/create")
    @Operation(
            summary = "Create new transaction",
            description = "Create a new transaction.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "account" ,description = "Name of account")
    })
    public ResponseEntity<InstitutionGetDto> createTransaction(@PathVariable("institution") String institutionName,
                                                               @PathVariable("account") String accountName,
                                                               @RequestBody AccountDto dto)
    {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/institution={institution}/account={account}/transactions/update")
    @Operation(
            summary = "Update transaction",
            description = "Updates a transaction. ID must match to an existing transaction.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "account" ,description = "Name of account")
    })
    public ResponseEntity<InstitutionGetDto> updateTransaction(@PathVariable("institution") String institutionName,
                                                               @PathVariable("account") String accountName,
                                                               @RequestBody AccountDto dto)
    {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/institution={institution}/account={account}/transactions/delete/id={id}")
    @Operation(
            summary = "Delete transaction by ID",
            description = "Delete a transaction by its ID.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institution", description = "Name of institution"),
            @Parameter(name = "account" ,description = "Name of account"),
            @Parameter(name = "id", description = "ID of transaction to delete")
    })
    public ResponseEntity<Void> deleteTransaction(@PathVariable("institution") String institutionName,
                                                  @PathVariable("account") String accountName,
                                                  @PathVariable("id") UUID transactionId)
    {
        return ResponseEntity.ok().build();
    }
}
