package de.dhbw.softwareengineering.domain.account;

import de.dhbw.softwareengineering.validation.annotations.ValidAccountName;
import de.dhbw.softwareengineering.validation.annotations.ValidId;
import de.dhbw.softwareengineering.constants.Constants;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @ValidId
    @Column(name = "id", nullable = false)
    private UUID id;

    @Valid
    @ManyToOne
    @JoinColumn(name = "institution_name")
    private Institution institution;

    @ValidAccountName
    @Column(name = "account_name", nullable = false, length = Constants.ACCOUNT_NAME_MAX_LENGTH)
    private String accountName;

    @Valid
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "owner_first_name", length = Constants.OWNER_FIRST_NAME_MAX_LENGTH, nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "owner_last_name", length = Constants.OWNER_LAST_NAME_MAX_LENGTH, nullable = false))
    })
    private AccountOwnerNameValue accountOwner;

    @PositiveOrZero
    @Column(name = "balance", nullable = false)
    private Double balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<@Valid Transaction> transactions;


    protected Account(){}


    public Account(final List<Transaction> transactions){
        this.transactions = transactions;
    }


    public Account(final UUID id,
                   final Institution institution,
                   final String accountName,
                   final AccountOwnerNameValue accountOwner,
                   final Double balance,
                   final List<Transaction> transactions){
        Validate.notNull(id);
        Validate.notNull(institution);
        Validate.notBlank(accountName);
        Validate.notNull(accountOwner);
        Validate.notNaN(balance);
        transactions.forEach(Validate::notNull);

        this.id = id;
        this.institution = institution;
        this.accountName = accountName;
        this.accountOwner = accountOwner;
        this.balance = balance;
        this.transactions = transactions;
    }

    public UUID getId() {
        return id;
    }

    public Institution getInstitution() {
        return institution;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountOwnerNameValue getOwner() {
        return accountOwner;
    }

    public Double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setId(@ValidId UUID id) {
        this.id = id;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public void setAccountName(@ValidAccountName String accountName) {
        this.accountName = accountName;
    }

    public void setAccountOwner(@Valid AccountOwnerNameValue accountOwner) {
        this.accountOwner = accountOwner;
    }

    public void setBalance(@PositiveOrZero Double balance) {
        this.balance = balance;
    }

    public void setTransactions(List<@Valid Transaction> transactions) {
        this.transactions = transactions;
    }

}
