package de.dhbw.softwareengineering.plugins.REST;

import de.dhbw.softwareengineering.adapters.transaction.Mapper.TransactionToReturnDTOMapper;
import de.dhbw.softwareengineering.adapters.transaction.TransactionCreateDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionReturnDTO;
import de.dhbw.softwareengineering.adapters.transaction.TransactionUpdateDTO;
import de.dhbw.softwareengineering.application.TransactionApplicationService;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
    public ResponseEntity<List<TransactionReturnDTO>> getAllTransactions(@PathVariable String institutionName, @PathVariable String accountName){
        return ResponseEntity.ok(this.transactionService.getAllTransactions(institutionName, accountName)
                .stream()
                .map(transactionMapper)
                .collect(Collectors.toList()));
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
    public ResponseEntity<TransactionReturnDTO> getTransactionById(@PathVariable String institutionName, @PathVariable String accountName, @PathVariable("id") UUID transactionId){
        try{
            Transaction transaction = this.transactionService.getTransactionById(institutionName, accountName, transactionId).orElseThrow(IllegalArgumentException::new);
            return ResponseEntity.ok(this.transactionMapper.apply(transaction));
        }
        catch(Exception e){
            System.out.println("TransactionController: " + e.toString());
            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<Void> deleteTransaction(@PathVariable String institutionName, @PathVariable String accountName, @PathVariable("id") UUID transactionId){
        try{
            this.transactionService.deleteTransaction(institutionName,accountName,transactionId);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            System.out.println("TransactionController: " + e.toString());
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create")
    @Operation(
            summary = "Create new transaction",
            description = "Create a new transaction.",
            tags = {"Transaction Controller"}
    )
    public ResponseEntity<TransactionReturnDTO> createTransaction(@RequestBody TransactionCreateDTO transaction){
        try{
            Transaction body = this.transactionService.createTransaction(transaction);
            return ResponseEntity.ok(this.transactionMapper.apply(body));
        }
        catch(Exception e){
            System.out.println("AccountController: " + e.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update transaction",
            description = "Updates a transaction. ID must match to an existing transaction.",
            tags = {"Transaction Controller"}
    )
    public ResponseEntity<TransactionReturnDTO> updateTransaction(@RequestBody TransactionUpdateDTO transaction){
        try{
            Transaction body = this.transactionService.updateTransaction(transaction);
            return ResponseEntity.ok(this.transactionMapper.apply(body));
        }
        catch(Exception e){
            System.out.println("AccountController: " + e.toString());
            return ResponseEntity.badRequest().build();
        }
    }
}
