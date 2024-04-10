package de.dhbw.softwareengineering.valueobjects;

import de.dhbw.softwareengineering.exceptions.InvalidNameException;

import java.util.Objects;

public final class UserName {
    private final String firstName;
    private final String middleName;
    private final String lastName;

    public UserName(String firstName, String middleName, String lastName) {
        if(firstName.isBlank() || lastName.isBlank()){
            throw new InvalidNameException("Invalid username. Name cannot be blank!");
        }
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName(){
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserName username = (UserName) o;
        return Objects.equals(firstName, username.firstName) && Objects.equals(middleName, username.middleName)  && Objects.equals(lastName, username.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }

    @Override
    public String toString() {
        return "UserName{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
