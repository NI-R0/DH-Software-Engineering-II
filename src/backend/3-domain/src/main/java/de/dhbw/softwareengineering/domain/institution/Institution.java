package de.dhbw.softwareengineering.domain.institution;

import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.enums.InstitutionType;
import jakarta.persistence.*;
import org.apache.commons.lang3.Validate;

import java.util.List;

@Entity
@Table(name = "institution")
public class Institution {
    @Id
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "institution_type")
    private InstitutionType institutionType;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private List<Account> accounts;

    protected Institution(){}

    public Institution(final String name, final InstitutionType type, final List<Account> accounts){
        Validate.notBlank(name);
        Validate.notNull(type);
        accounts.forEach(Validate::notNull);

        this.name = name;
        this.institutionType = type;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public InstitutionType getInstitutionType() {
        return institutionType;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
