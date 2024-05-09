package de.dhbw.softwareengineering.plugins.persistence.transaction;

import de.dhbw.softwareengineering.domain.transaction.Transaction;
import de.dhbw.softwareengineering.domain.transaction.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TransactionRepositoryBridge implements TransactionRepository {

    private final TransactionSpringDataRepository springDataRepository;

    public TransactionRepositoryBridge(TransactionSpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Optional<Transaction> findByAccountAndId(UUID accountId, UUID transactionId){
        return this.springDataRepository.findByAccountAndId(accountId, transactionId);
    }

    @Override
    public List<Transaction> findByInstitutionAndAccount(String institutionName, String accountName){
        return this.springDataRepository.findAllByInstitutionAndAccount(institutionName, accountName);
    }

    @Override
    public Optional<Transaction> findByInstitutionAndAccountAndId(String institutionName, String accountName, UUID transactionId) {
        return this.springDataRepository.findByInstitutionAndAccountAndId(institutionName, accountName, transactionId);
    }

}
