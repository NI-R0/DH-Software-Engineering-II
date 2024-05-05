package de.dhbw.softwareengineering.domain.values;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Embeddable
public final class AccountOwnerNameValue {

    @Size(max = 15, min = 2)
    @NotNull
    @NotBlank
    @NotEmpty
    private String firstName;

    @Size(max = 25)
    @NotNull
    @NotBlank
    private String lastName;

    /*public AccountOwnerNameValue(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }*/

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
