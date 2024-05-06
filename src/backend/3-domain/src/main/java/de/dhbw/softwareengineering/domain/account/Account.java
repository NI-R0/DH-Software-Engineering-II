package de.dhbw.softwareengineering.domain.account;

import de.dhbw.softwareengineering.constants.Constants;
import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import de.dhbw.softwareengineering.domain.values.AccountOwnerNameValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "institution_name")
    private Institution institution;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = Constants.ACCOUNT_NAME_MAX_LENGTH, min = Constants.ACCOUNT_NAME_MIN_LENGTH)
    @Column(name = "account_name", nullable = false, length = Constants.ACCOUNT_NAME_MAX_LENGTH)
    private String accountName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "owner_first_name", length = Constants.OWNER_FIRST_NAME_MAX_LENGTH, nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "owner_last_name", length = Constants.OWNER_LAST_NAME_MAX_LENGTH, nullable = false))
    })
    private AccountOwnerNameValue accountOwner;

    @NotNull
    @Column(name = "balance", nullable = false)
    private Double balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @SuppressWarnings("unused")
    protected Account(){}

    @SuppressWarnings("unused")
    public Account(final List<Transaction> transactions){
        this.transactions = transactions;
    }

    @SuppressWarnings("unused")
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public void setAccountName(@NotNull @NotBlank @NotEmpty @Size(max = Constants.ACCOUNT_NAME_MAX_LENGTH, min = Constants.ACCOUNT_NAME_MIN_LENGTH) String accountName) {
        this.accountName = accountName;
    }

    public void setAccountOwner(AccountOwnerNameValue accountOwner) {
        this.accountOwner = accountOwner;
    }

    public void setBalance(@NotNull Double balance) {
        this.balance = balance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
