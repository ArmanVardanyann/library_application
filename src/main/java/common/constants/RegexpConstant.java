package common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexpConstant {

    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9*&._]+$";
    public static final String FIRSTNAME_PATTERN = "^[\\x00-\\x7F]+$";
    public static final String LASTNAME_AND_MIDNAME_PATTERN = "^[\\x00-\\x7F]+$";
    public static final String ONLY_DIGITS = "\\d+";
    public static final String ALPHANUMERIC = "^[a-zA-Z0-9]+$";
}
