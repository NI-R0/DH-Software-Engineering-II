package de.dhbw.softwareengineering.valueobjects;

import de.dhbw.softwareengineering.exceptions.InvalidNameException;

import java.util.Objects;

public final class InstitutionName {
    private final String name;

    public InstitutionName(String name) {
        if(name.isBlank()) throw new InvalidNameException("Institution name cannot be blank!");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitutionName institutionName = (InstitutionName) o;
        return Objects.equals(name, institutionName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "InstitutionName{" +
                "name='" + name + '\'' +
                '}';
    }
}
