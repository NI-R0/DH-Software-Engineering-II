package de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionMapper;

import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaRepository;
import de.dhbw.softwareengineering.transaction.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityToJpaMapper {
    @Autowired
    TransactionJpaRepository jpaRepository;

    public TransactionJpaEntity mapEntityToJpa(TransactionEntity entity) throws Exception{
        return null;
    }
}
