package de.dhbw.softwareengineering.domain.values;

public final class AccountOwnerNameValue {
    private final String firstName;
    private final String lastName;

    public AccountOwnerNameValue(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
