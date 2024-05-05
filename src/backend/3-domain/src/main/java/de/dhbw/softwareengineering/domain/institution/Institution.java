package de.dhbw.softwareengineering.domain.institution;

import de.dhbw.softwareengineering.domain.account.Account;
import de.dhbw.softwareengineering.enums.InstitutionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.Validate;

import java.util.List;

@Entity
@Table(name = "institution")
public class Institution {
    @Id
    @Column(name = "name", nullable = false, length = 20)
    @Size(max = 20, min = 3)
    private String name;

    @NotNull
    @NotBlank
    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "institution_type", nullable = false)
    private InstitutionType institutionType;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

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

    public void updateName(@Size(max = 20, min = 3) String name) {
        this.name = name;
    }

    public void updateInstitutionType(@NotNull @NotBlank @NotEmpty InstitutionType institutionType) {
        this.institutionType = institutionType;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
