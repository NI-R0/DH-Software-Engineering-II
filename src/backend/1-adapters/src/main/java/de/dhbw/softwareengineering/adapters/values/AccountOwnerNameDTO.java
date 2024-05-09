package de.dhbw.softwareengineering.adapters.values;

import de.dhbw.softwareengineering.annotations.ValidFirstName;
import de.dhbw.softwareengineering.annotations.ValidOwnerLastName;

public class AccountOwnerNameDTO {
    @ValidFirstName
    private final String firstName;
    @ValidOwnerLastName
    private final String lastName;

    public AccountOwnerNameDTO(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
