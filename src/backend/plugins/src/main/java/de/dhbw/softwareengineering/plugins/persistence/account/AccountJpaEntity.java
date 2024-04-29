package de.dhbw.softwareengineering.plugins.persistence.account;

import de.dhbw.softwareengineering.plugins.persistence.transaction.TransactionJpaEntity;
import de.dhbw.softwareengineering.plugins.persistence.institution.InstitutionJpaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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
}