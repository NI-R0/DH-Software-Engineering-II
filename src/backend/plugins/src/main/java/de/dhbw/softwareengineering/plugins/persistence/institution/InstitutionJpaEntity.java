package de.dhbw.softwareengineering.plugins.persistence.institution;

import de.dhbw.softwareengineering.enums.InstitutionType;
import de.dhbw.softwareengineering.plugins.persistence.account.AccountJpaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Entity
@Table(name = "INSTITUTION")
public class InstitutionJpaEntity {
    @Id
    @Size(max = 20)
    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private InstitutionType institutionType;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "institution", referencedColumnName = "name")*/
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "institution", referencedColumnName = "name")
    private List<AccountJpaEntity> accounts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstitutionType getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(InstitutionType institutionType) {
        this.institutionType = institutionType;
    }

    public List<AccountJpaEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountJpaEntity> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstitutionJpaEntity that)) return false;
        return Objects.equals(getName(), that.getName()) && getInstitutionType() == that.getInstitutionType() && Objects.equals(getAccounts(), that.getAccounts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getInstitutionType(), getAccounts());
    }
}





/*
@Entity
@Table(name = "INSTITUTION")
public class InstitutionJpaEntity {
    @Id
    @Column(name = "INSTITUTIONID", nullable = false)
    private UUID id;

    @Size(max = 20)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 20)
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private InstitutionType institutionType;

    public InstitutionType getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(InstitutionType institutionType) {
        this.institutionType = institutionType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        InstitutionJpaEntity that = (InstitutionJpaEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}*/
