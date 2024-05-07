package de.dhbw.softwareengineering.values;

import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AccountOwnerNameValueTest {
    private AccountOwnerNameValue accountOwnerNameValue;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        accountOwnerNameValue = new AccountOwnerNameValue();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void test_CorrectFirstName() {
        String firstName = "John";
        accountOwnerNameValue.setFirstName(firstName);
        Set<ConstraintViolation<AccountOwnerNameValue>> violations = validator.validate(accountOwnerNameValue);
        assertTrue(violations.isEmpty());
        assertEquals(firstName, accountOwnerNameValue.getFirstName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "T", "     ", "SomeWayTooLongFirstName"})
    @NullAndEmptySource
    public void test_IncorrectFirstName(String firstName){
        accountOwnerNameValue.setFirstName(firstName);
        Set<ConstraintViolation<AccountOwnerNameValue>> violations = validator.validate(accountOwnerNameValue);
        assertFalse(violations.isEmpty());
        assertEquals(firstName, accountOwnerNameValue.getFirstName());
    }

    @Test
    public void test_CorrectLastName() {
        String lastName = "Doe";
        accountOwnerNameValue.setLastName(lastName);
        assertEquals(lastName, accountOwnerNameValue.getLastName());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"SomeVeryVeryVeryLongLastName"})
    public void test_IncorrectLastName(String lastName) {
        accountOwnerNameValue.setLastName(lastName);
        Set<ConstraintViolation<AccountOwnerNameValue>> violations = validator.validate(accountOwnerNameValue);
        assertFalse(violations.isEmpty());
        assertEquals(lastName, accountOwnerNameValue.getLastName());
    }

    @Test
    public void test_FullName(){
        String firstName = "John";
        String lastName = "Doe";
        accountOwnerNameValue.setFirstName(firstName);
        accountOwnerNameValue.setLastName(lastName);
        assertEquals(firstName + " " + lastName, accountOwnerNameValue.getFullName());
    }
}
