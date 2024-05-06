package de.dhbw.softwareengineering.constants;

import org.springframework.stereotype.Component;

@Component
public final class Constants {
    public static final int INSTITUTION_NAME_MAX_LENGTH = 20;
    public static final int ACCOUNT_NAME_MAX_LENGTH = INSTITUTION_NAME_MAX_LENGTH;
    public static final int UNIT_MAX_LENGTH = 10;
    public static final int OWNER_FIRST_NAME_MAX_LENGTH = 15;
    public static final int OWNER_LAST_NAME_MAX_LENGTH = 25;
    public static final int DESCRIPTION_MAX_LENGTH = 255;

    public static final int INSTITUTION_NAME_MIN_LENGTH = 3;
    public static final int ACCOUNT_NAME_MIN_LENGTH = INSTITUTION_NAME_MIN_LENGTH;
    public static final int UNIT_MIN_LENGTH = INSTITUTION_NAME_MIN_LENGTH;
    public static final int USER_FIRST_NAME_MIN_LENGTH = 2;
}
