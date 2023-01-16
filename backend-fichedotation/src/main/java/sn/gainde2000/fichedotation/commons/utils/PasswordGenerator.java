package sn.gainde2000.fichedotation.commons.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author Bassirou THIAM
 */
public class PasswordGenerator {

    private static final int minLength = 6;
    private static final int maxLength = 6;
    private static final int minLCaseCount = 3;
    private static final int minUCaseCount = 1;
    private static final int minNumCount = 1;
    private static final int minSpecialCount = 1;

//  public static void main(String[] args){
//  SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMddHHmmss");
//  SimpleDateFormat formatter1= new SimpleDateFormat("yyMMddHHmmss");
//  SimpleDateFormat formatter2= new SimpleDateFormat("yyyyMMddHHmmss");
//  Date date = new Date(System.currentTimeMillis());
//  String reference = formatter.format(date).toString();
//  String reference1 = formatter1.format(date).toString();
//  String reference2 = formatter2.format(date).toString();
//  System.out.println("AF-" +reference);
//  System.out.println("AF-" +reference1);
//  System.out.println(reference2);
//}

//        public static void main(String[] args) {
//        System.out.println("---> Debut test PasswordGenerator.");
//        //Test with params
//        //System.out.println(PasswordGenerator.GenerateRandomString(8,25,3,1,1,1));
//        //Test without params
//        for (int i = 0; i < 5; i++) {
//            System.out.println(PasswordGenerator.GenerateRandomString());
//        }
//        System.out.println("---> Fin test PasswordGenerator.");
//    }

    //Without params
    public static String GenerateRandomString() {
        char[] randomString;

        String LCaseChars = "abcdefgijkmnpqrstwxyz";
        String UCaseChars = "ABCDEFGHJKLMNPQRSTWXYZ";
        String NumericChars = "23456789";
        //String SpecialChars = "*$-+?_&=!%{}/";
        String SpecialChars = "*-+_!@";

        Map<String, Integer> charGroupsUsed = new HashMap<>();
        charGroupsUsed.put("lcase", minLCaseCount);
        charGroupsUsed.put("ucase", minUCaseCount);
        charGroupsUsed.put("num", minNumCount);
        charGroupsUsed.put("special", minSpecialCount);

        // Because we cannot use the default randomizer, which is based on the
        // current time (it will produce the same "random" number within a
        // second), we will use a random number generator to seed the
        // randomizer.
        // Use a 4-byte array to fill it with random bytes and convert it then
        // to an integer value.
        byte[] randomBytes = new byte[4];

        // Generate 4 random bytes.
        new Random().nextBytes(randomBytes);

        // Convert 4 bytes into a 32-bit integer value.
        int seed = (randomBytes[0] & 0x7f) << 24
                | randomBytes[1] << 16
                | randomBytes[2] << 8
                | randomBytes[3];

        // Create a randomizer from the seed.
        Random random = new Random(seed);

        // Allocate appropriate memory for the password.
        int randomIndex = -1;
        if (minLength < maxLength) {
            randomIndex = random.nextInt((maxLength - minLength) + 1) + minLength;
            randomString = new char[randomIndex];
        } else {
            randomString = new char[minLength];
        }

        int requiredCharactersLeft = minLCaseCount + minUCaseCount + minNumCount + minSpecialCount;

        // Build the password.
        for (int i = 0; i < randomString.length; i++) {
            String selectableChars = "";

            // if we still have plenty of characters left to acheive our minimum requirements.
            if (requiredCharactersLeft < randomString.length - i) {
                // choose from any group at random
                selectableChars = LCaseChars + UCaseChars + NumericChars + SpecialChars;
            } else // we are out of wiggle room, choose from a random group that still needs to have a minimum required.
            {
                // choose only from a group that we need to satisfy a minimum for.
                for (Map.Entry<String, Integer> charGroup : charGroupsUsed.entrySet()) {
                    if (charGroup.getValue() > 0) {
                        if ("lcase".equals(charGroup.getKey())) {
                            selectableChars += LCaseChars;
                        } else if ("ucase".equals(charGroup.getKey())) {
                            selectableChars += UCaseChars;
                        } else if ("num".equals(charGroup.getKey())) {
                            selectableChars += NumericChars;
                        } else if ("special".equals(charGroup.getKey())) {
                            selectableChars += SpecialChars;
                        }
                    }
                }
            }

            // Now that the string is built, get the next random character.
            randomIndex = random.nextInt((selectableChars.length()) - 1);
            char nextChar = selectableChars.charAt(randomIndex);

            // Tac it onto our password.
            randomString[i] = nextChar;

            // Now figure out where it came from, and decrement the appropriate minimum value.
            if (LCaseChars.indexOf(nextChar) > -1) {
                charGroupsUsed.put("lcase", charGroupsUsed.get("lcase") - 1);
                if (charGroupsUsed.get("lcase") >= 0) {
                    requiredCharactersLeft--;
                }
            } else if (UCaseChars.indexOf(nextChar) > -1) {
                charGroupsUsed.put("ucase", charGroupsUsed.get("ucase") - 1);
                if (charGroupsUsed.get("ucase") >= 0) {
                    requiredCharactersLeft--;
                }
            } else if (NumericChars.indexOf(nextChar) > -1) {
                charGroupsUsed.put("num", charGroupsUsed.get("num") - 1);
                if (charGroupsUsed.get("num") >= 0) {
                    requiredCharactersLeft--;
                }
            } else if (SpecialChars.indexOf(nextChar) > -1) {
                charGroupsUsed.put("special", charGroupsUsed.get("special") - 1);
                if (charGroupsUsed.get("special") >= 0) {
                    requiredCharactersLeft--;
                }
            }
        }
        return new String(randomString);
    }

    //With params
    public static String GenerateRandomString(int minLength, int maxLength, int minLCaseCount, int minUCaseCount, int minNumCount, int minSpecialCount) {
        char[] randomString;

        String LCaseChars = "abcdefgijkmnopqrstwxyz";
        String UCaseChars = "ABCDEFGHJKLMNPQRSTWXYZ";
        String NumericChars = "23456789";
        String SpecialChars = "*$-+?_&=!%{}/";

        Map<String, Integer> charGroupsUsed = new HashMap<>();
        charGroupsUsed.put("lcase", minLCaseCount);
        charGroupsUsed.put("ucase", minUCaseCount);
        charGroupsUsed.put("num", minNumCount);
        charGroupsUsed.put("special", minSpecialCount);

        // Because we cannot use the default randomizer, which is based on the
        // current time (it will produce the same "random" number within a
        // second), we will use a random number generator to seed the
        // randomizer.
        // Use a 4-byte array to fill it with random bytes and convert it then
        // to an integer value.
        byte[] randomBytes = new byte[4];

        // Generate 4 random bytes.
        new Random().nextBytes(randomBytes);

        // Convert 4 bytes into a 32-bit integer value.
        int seed = (randomBytes[0] & 0x7f) << 24
                | randomBytes[1] << 16
                | randomBytes[2] << 8
                | randomBytes[3];

        // Create a randomizer from the seed.
        Random random = new Random(seed);

        // Allocate appropriate memory for the password.
        int randomIndex = -1;
        if (minLength < maxLength) {
            randomIndex = random.nextInt((maxLength - minLength) + 1) + minLength;
            randomString = new char[randomIndex];
        } else {
            randomString = new char[minLength];
        }

        int requiredCharactersLeft = minLCaseCount + minUCaseCount + minNumCount + minSpecialCount;

        // Build the password.
        for (int i = 0; i < randomString.length; i++) {
            String selectableChars = "";

            // if we still have plenty of characters left to acheive our minimum requirements.
            if (requiredCharactersLeft < randomString.length - i) {
                // choose from any group at random
                selectableChars = LCaseChars + UCaseChars + NumericChars + SpecialChars;
            } else // we are out of wiggle room, choose from a random group that still needs to have a minimum required.
            {
                // choose only from a group that we need to satisfy a minimum for.
                for (Map.Entry<String, Integer> charGroup : charGroupsUsed.entrySet()) {
                    if (charGroup.getValue() > 0) {
                        if ("lcase".equals(charGroup.getKey())) {
                            selectableChars += LCaseChars;
                        } else if ("ucase".equals(charGroup.getKey())) {
                            selectableChars += UCaseChars;
                        } else if ("num".equals(charGroup.getKey())) {
                            selectableChars += NumericChars;
                        } else if ("special".equals(charGroup.getKey())) {
                            selectableChars += SpecialChars;
                        }
                    }
                }
            }

            // Now that the string is built, get the next random character.
            randomIndex = random.nextInt((selectableChars.length()) - 1);
            char nextChar = selectableChars.charAt(randomIndex);

            // Tac it onto our password.
            randomString[i] = nextChar;

            // Now figure out where it came from, and decrement the appropriate minimum value.
            if (LCaseChars.indexOf(nextChar) > -1) {
                charGroupsUsed.put("lcase", charGroupsUsed.get("lcase") - 1);
                if (charGroupsUsed.get("lcase") >= 0) {
                    requiredCharactersLeft--;
                }
            } else if (UCaseChars.indexOf(nextChar) > -1) {
                charGroupsUsed.put("ucase", charGroupsUsed.get("ucase") - 1);
                if (charGroupsUsed.get("ucase") >= 0) {
                    requiredCharactersLeft--;
                }
            } else if (NumericChars.indexOf(nextChar) > -1) {
                charGroupsUsed.put("num", charGroupsUsed.get("num") - 1);
                if (charGroupsUsed.get("num") >= 0) {
                    requiredCharactersLeft--;
                }
            } else if (SpecialChars.indexOf(nextChar) > -1) {
                charGroupsUsed.put("special", charGroupsUsed.get("special") - 1);
                if (charGroupsUsed.get("special") >= 0) {
                    requiredCharactersLeft--;
                }
            }
        }
        return new String(randomString);
    }

    //Constructor
    private PasswordGenerator() {
    }

    //pour generer les references des operations
    public static String GenerateReferenceString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String reference = formatter.format(date);
        //String reference = "AF-" + formatter.format(date).toString();
        return reference;
    }

    //pour generer le nom des fichiers stockés sur le serveur
    public static String VerifAndGenerateNameOfStoragedFile(String originalFileName) {
        /*
             faut renommer le fichier avec la date courante
             */

        int pos = originalFileName.lastIndexOf(".");
        String extension = "";
        String nomFichier = "";
        if (pos > 0) {
            //	nomFichier = fileName.substring(0, pos);
            extension = originalFileName.substring(pos);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String newFileName = UUID.randomUUID() + "_" + formatter.format(date) + extension;
        return newFileName;
    }

    public static String generateNameOfStoragedFile() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String originalFileName = "B_" + formatter.format(date) + ".pdf";
        return originalFileName;
    }

    //pour generer le nom des pièces jointes à stocker sur le serveur
    public static String GenerateNameOfStoragedFile(String originalFileName) {
        /*
             faut renommer le fichier avec la date courante
             */

        int pos = originalFileName.lastIndexOf(".");
        String extension = "";
        String nomFichier = "";
        if (pos > 0) {
            //	nomFichier = fileName.substring(0, pos);
            extension = originalFileName.substring(pos);
        }
        //SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        //System.out.println(formatter.format(date).toString()+extension);
        System.out.println(originalFileName);
        originalFileName = "P_" + UUID.randomUUID() + extension;
        return originalFileName;
    }
}
