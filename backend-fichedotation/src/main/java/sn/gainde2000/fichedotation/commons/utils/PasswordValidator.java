package sn.gainde2000.fichedotation.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bassirou THIAM
 */
public class PasswordValidator {

    private final Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9*$-+?_&=!%@#\\[\\]\\\"{}/]).{8,20})";

//    public static void main(String[] args) {
//        System.out.println("---> Debut test PasswordValidator.");
//        for (int i = 0; i < 5; i++) {
//            String password = "12345678901234567890";//PasswordGenerator.GenerateRandomString(6,6,3,1,0,0);
//            System.out.println(password);
//            System.out.println("Validation de " + password + " = " + new PasswordValidator().validate(password));
//        }
//        System.out.println("---> Fin test PasswordValidator.");
//    }

    /**
     * Validate password with regular expression
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public boolean validate(String password) {

        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }
}
