package common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsernameRegexMsgConstant {
    public static final String USERNAME_PATTERN_MSG = "Only letters, numbers, '_', '.' , '*', '&' are allowed.";
    public static final String USERNAME_MIN_SIZE_MSG = "Enter 3 or more symbols (letters, numbers, '_', '.' , '*', '&' are allowed)";
    public static final String USERNAME_MAX_SIZE_MSG = "The field username maximum length 30";
}

