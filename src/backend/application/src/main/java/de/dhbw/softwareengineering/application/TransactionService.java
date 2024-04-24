package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.transaction.TransactionDtoToEntityMapper;
import de.dhbw.softwareengineering.adapters.transaction.TransactionEntityToDtoMapper;
import de.dhbw.softwareengineering.domain.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private TransactionEntityToDtoMapper entityToDtoMapper;

    @Autowired
    private TransactionDtoToEntityMapper dtoToEntityMapper;




}
