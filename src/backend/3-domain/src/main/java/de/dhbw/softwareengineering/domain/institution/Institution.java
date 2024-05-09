package de.dhbw.softwareengineering.domain.institution;

import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionName;
import de.dhbw.softwareengineering.validation.annotations.ValidInstitutionType;
import de.dhbw.softwareengineering.constants.Constants;
import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.enums.InstitutionType;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.apache.commons.lang3.Validate;

import java.util.List;

@Entity
@Table(name = "institution")
public class Institution {
    @Id
    @ValidInstitutionName
    @Column(name = "name", nullable = false, length = Constants.INSTITUTION_NAME_MAX_LENGTH)
    private String name;

    @ValidInstitutionType
    @Enumerated(EnumType.STRING)
    @Column(name = "institution_type", nullable = false)
    private InstitutionType institutionType;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<@Valid Account> accounts;


    protected Institution(){}

    public Institution(final List<Account> accounts){
        this.accounts = accounts;
    }

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

    public void updateName(@ValidInstitutionName String name) {
        this.name = name;
    }

    public void updateInstitutionType(@ValidInstitutionType InstitutionType institutionType) {
        this.institutionType = institutionType;
    }

    public void updateAccounts(List<@Valid Account> accounts) {
        this.accounts = accounts;
    }

}
