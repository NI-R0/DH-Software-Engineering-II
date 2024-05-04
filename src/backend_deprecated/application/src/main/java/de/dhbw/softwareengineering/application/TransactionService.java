package de.dhbw.softwareengineering.application;

import de.dhbw.softwareengineering.adapters.transaction.TransactionDto;
import de.dhbw.softwareengineering.adapters.transaction.Mapper.TransactionDtoToEntityMapper;
import de.dhbw.softwareengineering.adapters.transaction.Mapper.TransactionEntityToDtoMapper;
import de.dhbw.softwareengineering.domain.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private TransactionEntityToDtoMapper entityToDtoMapper;

    @Autowired
    private TransactionDtoToEntityMapper dtoToEntityMapper;

    public List<TransactionDto> getAllTransactions(String institutionName, String accountName){
        List<TransactionDto> dtos = new ArrayList<>();
        repository.findAllByAccountName(accountName).forEach(entity -> {
            dtos.add(entityToDtoMapper.mapEntityToDto(entity));
        });
        return dtos;
    }


}
