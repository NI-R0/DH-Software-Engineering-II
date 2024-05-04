package de.dhbw.softwareengineering.plugins.persistence.account;

import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.*;

@Entity
@Table(name = "ACCOUNT")
public class AccountJpaEntity {
    @Id
    @Column(name = "ACCOUNTID", nullable = false)
    private UUID id;

    @Size(max = 20)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @Column(name = "INSTITUTION", nullable = true, length = 20)
    private String institution;

    @Size(max = 20)
    @NotNull
    @Column(name = "ACCOUNTNAME", nullable = false, length = 20)
    private String accountname;

    @Size(max = 15)
    @NotNull
    @Column(name = "OWNERFIRSTNAME", nullable = false, length = 15)
    private String ownerfirstname;

    @Size(max = 25)
    @NotNull
    @Column(name = "OWNERLASTNAME", nullable = false, length = 25)
    private String ownerlastname;

    @NotNull
    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account", referencedColumnName = "accountid")
    private List<TransactionJpaEntity> transactions = new ArrayList<>();


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getOwnerfirstname() {
        return ownerfirstname;
    }

    public void setOwnerfirstname(String ownerfirstname) {
        this.ownerfirstname = ownerfirstname;
    }

    public String getOwnerlastname() {
        return ownerlastname;
    }

    public void setOwnerlastname(String ownerlastname) {
        this.ownerlastname = ownerlastname;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<TransactionJpaEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionJpaEntity> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountJpaEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getInstitution(), that.getInstitution()) && Objects.equals(getAccountname(), that.getAccountname()) && Objects.equals(getOwnerfirstname(), that.getOwnerfirstname()) && Objects.equals(getOwnerlastname(), that.getOwnerlastname()) && Objects.equals(getBalance(), that.getBalance()) && Objects.equals(getTransactions(), that.getTransactions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInstitution(), getAccountname(), getOwnerfirstname(), getOwnerlastname(), getBalance(), getTransactions());
    }
}




/*
@Entity
@Table(name = "ACCOUNT")
public class AccountJpaEntity {
    @Id
    @Column(name = "ACCOUNTID", nullable = false)
    private UUID id;
    @NotNull
    @Size(max = 20)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @Column(name = "institution", nullable = false, length = 20)
    private String institutionname;
    @Size(max = 20)
    @NotNull
    @Column(name = "ACCOUNTNAME", nullable = false, length = 20)
    private String accountname;

    @Size(max = 15)
    @NotNull
    @Column(name="OWNERFIRSTNAME", nullable = false, length = 15)
    private String ownerfirstname;

    @Size(max = 25)
    @NotNull
    @Column(name = "OWNERLASTNAME", nullable = false, length = 25)
    private String ownerlastname;

    @NotNull
    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    public UUID getAccountId() {
        return id;
    }

    public void setAccountId(UUID id) {
        this.id = id;
    }

    public String getInstitutionName() {
        return institutionname;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionname = institutionName;
    }

    public String getName() {
        return accountname;
    }

    public void setName(String name) {
        this.accountname = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getOwnerfirstname() {
        return ownerfirstname;
    }

    public void setOwnerfirstname(String ownerfirstname) {
        this.ownerfirstname = ownerfirstname;
    }

    public String getOwnerlastname() {
        return ownerlastname;
    }

    public void setOwnerlastname(String ownerlastname) {
        this.ownerlastname = ownerlastname;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        AccountJpaEntity that = (AccountJpaEntity) o;
        return getAccountId() != null && Objects.equals(getAccountId(), that.getAccountId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}*/
