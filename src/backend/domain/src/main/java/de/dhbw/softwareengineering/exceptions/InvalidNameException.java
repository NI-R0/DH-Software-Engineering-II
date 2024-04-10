package de.dhbw.softwareengineering.exceptions;

import java.security.InvalidParameterException;

public class InvalidNameException extends InvalidParameterException {
    public InvalidNameException(String message){super(message);}
}
