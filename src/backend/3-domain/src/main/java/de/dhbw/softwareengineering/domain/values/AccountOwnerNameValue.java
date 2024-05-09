package de.dhbw.softwareengineering.domain.values;

import de.dhbw.softwareengineering.validation.annotations.ValidFirstName;
import de.dhbw.softwareengineering.validation.annotations.ValidOwnerLastName;
import jakarta.persistence.*;

@Embeddable
public final class AccountOwnerNameValue {

    @ValidFirstName
    private String firstName;

    @ValidOwnerLastName
    private String lastName;

    public AccountOwnerNameValue(){}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public void setFirstName(@ValidFirstName String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@ValidOwnerLastName String lastName) {
        this.lastName = lastName;
    }
}
