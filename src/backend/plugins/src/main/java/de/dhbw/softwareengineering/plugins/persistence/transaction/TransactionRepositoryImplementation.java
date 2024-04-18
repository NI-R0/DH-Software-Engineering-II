package de.dhbw.softwareengineering.plugins.persistence.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryImplementation {
    @Autowired
    TransactionJpaRepository jpaRepository;
}
