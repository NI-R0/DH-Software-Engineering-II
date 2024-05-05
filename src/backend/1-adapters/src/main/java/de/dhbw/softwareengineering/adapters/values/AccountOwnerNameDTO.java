package de.dhbw.softwareengineering.adapters.values;

public class AccountOwnerNameDTO {
    private final String firstName;

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
