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
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @Column(name = "INSTITUTIONID", nullable = false)
    private UUID institutionid;
    @Size(max = 20)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 20)
    private String name;
    @NotNull
    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    public UUID getAccountId() {
        return id;
    }

    public void setAccountId(UUID id) {
        this.id = id;
    }

    public UUID getInstitutionId() {
        return institutionid;
    }

    public void setInstitutionId(UUID institutionid) {
        this.institutionid = institutionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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