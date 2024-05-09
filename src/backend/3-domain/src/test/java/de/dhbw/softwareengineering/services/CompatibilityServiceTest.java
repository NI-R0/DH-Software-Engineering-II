package de.dhbw.softwareengineering.services;

import de.dhbw.softwareengineering.domain.services.CompatibilityService;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import de.dhbw.softwareengineering.enums.InstitutionType;
import de.dhbw.softwareengineering.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompatibilityServiceTest {

    private CompatibilityService classUnderTest;

    @BeforeEach
    public void setUp(){
        classUnderTest = new CompatibilityService();
    }

    @Test
    public void test_AreInstitutionTypesCompatibleWithTransactionList() {

        Transaction mockTransaction1 = mock(Transaction.class);
        Transaction mockTransaction2 = mock(Transaction.class);
        List<Transaction> mockTransactionList = Arrays.asList(mockTransaction1, mockTransaction2);

        when(mockTransaction1.getTransactionType()).thenReturn(TransactionType.BUY);
        when(mockTransaction2.getTransactionType()).thenReturn(TransactionType.SELL);


        assertFalse(classUnderTest.isInstitutionTypeCompatibleWithTransactionList(InstitutionType.BANK, mockTransactionList));

        when(mockTransaction1.getTransactionType()).thenReturn(TransactionType.INCOME);
        when(mockTransaction2.getTransactionType()).thenReturn(TransactionType.EXPENSE);

        assertFalse(classUnderTest.isInstitutionTypeCompatibleWithTransactionList(InstitutionType.BROKER, mockTransactionList));
    }

    @Test
    public void test_AreInstitutionTypesCompatibleWithTransactionType() {

        assertFalse(classUnderTest.isInstitutionTypeCompatibleWithTransactionType(InstitutionType.BANK, TransactionType.BUY));
        assertFalse(classUnderTest.isInstitutionTypeCompatibleWithTransactionType(InstitutionType.BANK, TransactionType.SELL));
        assertTrue(classUnderTest.isInstitutionTypeCompatibleWithTransactionType(InstitutionType.BANK, TransactionType.INCOME));
        assertTrue(classUnderTest.isInstitutionTypeCompatibleWithTransactionType(InstitutionType.BANK, TransactionType.EXPENSE));

        assertTrue(classUnderTest.isInstitutionTypeCompatibleWithTransactionType(InstitutionType.BROKER, TransactionType.BUY));
        assertTrue(classUnderTest.isInstitutionTypeCompatibleWithTransactionType(InstitutionType.BROKER, TransactionType.SELL));
        assertFalse(classUnderTest.isInstitutionTypeCompatibleWithTransactionType(InstitutionType.BROKER, TransactionType.INCOME));
        assertFalse(classUnderTest.isInstitutionTypeCompatibleWithTransactionType(InstitutionType.BROKER, TransactionType.EXPENSE));
    }

}
