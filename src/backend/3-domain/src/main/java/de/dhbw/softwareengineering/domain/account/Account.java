package de.dhbw.softwareengineering.domain.account;

import de.dhbw.softwareengineering.domain.institution.Institution;
import de.dhbw.softwareengineering.domain.transaction.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
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
    @Size(max = 20, min = 3)
    @Column(name = "account_name", nullable = false, length = 20)
    private String accountName;

    @Size(max = 15, min = 2)
    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "owner_first_name", length = 15, nullable = false)
    private String ownerFirstName;

    @Size(max = 25)
    @NotNull
    @NotBlank
    @Column(name = "owner_last_name", length = 25, nullable = false)
    private String ownerLastName;

    @NotNull
    @Column(name = "balance", nullable = false)
    private Double balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    protected Account(){}

    public Account(final UUID id,
                   final Institution institution,
                   final String accountName,
                   final String ownerFirstName,
                   final String ownerLastName,
                   final Double balance,
                   final List<Transaction> transactions){
        Validate.notNull(id);
        Validate.notNull(institution);
        Validate.notBlank(accountName);
        Validate.notBlank(ownerFirstName);
        Validate.notBlank(ownerLastName);
        Validate.notNaN(balance);
        transactions.forEach(Validate::notNull);

        this.id = id;
        this.institution = institution;
        this.accountName = accountName;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
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

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public Double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
