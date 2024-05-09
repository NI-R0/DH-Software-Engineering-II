package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.transaction.mapper.TransactionToReturnDTOMapper;
import de.dhbw.softwareengineering.adapters.transaction.TransactionCreateDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionReturnDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionUpdateDTO;
import de.dhbw.softwareengineering.annotations.ValidAccountName;
import de.dhbw.softwareengineering.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.application.TransactionApplicationService;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import de.dhbw.softwareengineering.exceptions.ObjectNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/transactions")
public class TransactionController {

    private final TransactionApplicationService transactionService;

    private final TransactionToReturnDTOMapper transactionMapper;

    @Autowired
    public TransactionController(TransactionApplicationService transactionService, TransactionToReturnDTOMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping("/getAll/{institutionName}/{accountName}")
    @Operation(
            summary = "Retrieve all transactions",
            description = "Retrieve all transactions of an account.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institutionName", description = "Name of institution"),
            @Parameter(name = "accountName" ,description = "Name of account")
    })
    public ResponseEntity<List<TransactionReturnDTO>> getAllTransactions(@PathVariable String institutionName,
                                                                         @PathVariable String accountName){
        List<TransactionReturnDTO> transactions = this.transactionService.getAllTransactions(institutionName, accountName)
                .stream()
                .map(transactionMapper)
                .collect(Collectors.toList());
        if(!transactions.isEmpty()){
            return ResponseEntity.ok(transactions);
        }
        throw new ObjectNotFoundException("No transactions could be found for Account " + accountName + " at Institution " + institutionName);
    }

    @GetMapping("/get/{institutionName}/{accountName}/id={id}")
    @Operation(
            summary = "Retrieve transaction by ID",
            description = "Retrieves a transaction from the database by its ID.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institutionName", description = "Name of institution"),
            @Parameter(name = "accountName" ,description = "Name of account"),
            @Parameter(name = "id", description = "ID of transaction to retrieve")
    })
    public ResponseEntity<TransactionReturnDTO> getTransactionById(@PathVariable String institutionName,
                                                                   @PathVariable String accountName,
                                                                   @PathVariable("id") UUID transactionId){
        Transaction transaction = this.transactionService.getTransactionById(institutionName, accountName, transactionId).orElseThrow(() -> new ObjectNotFoundException("No transaction could be found for Institution " + institutionName + ", Account " + accountName + " and ID " + transactionId));
        return ResponseEntity.ok(this.transactionMapper.apply(transaction));
    }

    @DeleteMapping("/delete/{institutionName}/{accountName}/id={id}")
    @Operation(
            summary = "Delete transaction by ID",
            description = "Delete a transaction by its ID.",
            tags = {"Transaction Controller"}
    )
    @Parameters({
            @Parameter(name = "institutionName", description = "Name of institution"),
            @Parameter(name = "accountName" ,description = "Name of account"),
            @Parameter(name = "id", description = "ID of transaction to delete")
    })
    public ResponseEntity<Void> deleteTransaction(@PathVariable String institutionName,
                                                  @PathVariable String accountName,
                                                  @PathVariable("id") UUID transactionId) throws Exception{
        this.transactionService.deleteTransaction(institutionName,accountName,transactionId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create new transaction",
            description = "Create a new transaction.",
            tags = {"Transaction Controller"}
    )
    public ResponseEntity<TransactionReturnDTO> createTransaction(@RequestBody TransactionCreateDTO transaction) throws Exception{
        Transaction body = this.transactionService.createTransaction(transaction);
        return ResponseEntity.ok(this.transactionMapper.apply(body));
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update transaction",
            description = "Updates a transaction. ID must match to an existing transaction.",
            tags = {"Transaction Controller"}
    )
    public ResponseEntity<TransactionReturnDTO> updateTransaction(@RequestBody TransactionUpdateDTO transaction) throws Exception{
        Transaction body = this.transactionService.updateTransaction(transaction);
        return ResponseEntity.ok(this.transactionMapper.apply(body));
    }
}
