package common.validation;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class MailFormatValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_PATTERN = "^[\\w-]+(?:[\\.!#\\'\\*\\+\\-\\/\\=\\?\\^\\_`{\\}~]*[\\-\\w]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9]$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(email)) {
            return true;
        }
        Pattern pat = Pattern.compile(EMAIL_PATTERN);
        return pat.matcher(email).matches();
    }
}
