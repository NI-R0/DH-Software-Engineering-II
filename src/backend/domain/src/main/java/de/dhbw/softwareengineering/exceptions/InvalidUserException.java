package de.dhbw.softwareengineering.exceptions;

import java.security.InvalidParameterException;

public class InvalidUserException extends InvalidParameterException {
    public InvalidUserException(String message){super(message);}
}
