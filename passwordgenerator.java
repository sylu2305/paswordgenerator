package com.codebind;
import java.util.Scanner; import java.util.Random;
public class passwordgenerator{
public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
    Random rand = new Random();
    System.out.println("Choose an option:");
    System.out.println("1. Generate a password");
    System.out.println("2. Provide your password to check its strength");
    int option = input.nextInt();
    input.nextLine(); 
    if (option == 1) {
    	System.out.println("1. Generate a random password \n2. Desired password");
    	int n = input.nextInt();
        input.nextLine();
        String password;
    	if(n==1) {
    		 System.out.println("Generating a strong password...");
    	         password = generateStrongPassword(rand);
    	}
    	else if(n==2) {
    		System.out.println("How many characters do you want in your password? (Minimum 8)");
    	    int length = input.nextInt();
    	    input.nextLine(); // Consume the remaining newline character
    	    if(length<8) {
    	    	System.out.println("Minimum length of password should be 8");
    	    	return ;
    	    }
    	    // Ensure the password length is at least 8 characters
    	    length = Math.max(length, 8);

    	    System.out.println("Do you want to include uppercase letters? (y/n)");
    	    boolean includeUppercase = input.nextLine().equalsIgnoreCase("y");

    	    System.out.println("Do you want to include lowercase letters? (y/n)");
    	    boolean includeLowercase = input.nextLine().equalsIgnoreCase("y");

    	    System.out.println("Do you want to include numbers? (y/n)");
    	    boolean includeNumbers = input.nextLine().equalsIgnoreCase("y");

    	    System.out.println("Do you want to include special characters? (y/n)");
    	    boolean includeSpecialChars = input.nextLine().equalsIgnoreCase("y");

    	     password = generatePassword(length, includeUppercase, includeLowercase, includeNumbers, includeSpecialChars, rand);
    	}
    	else {
    		 System.out.println("Invalid option. Please choose 1 or 2.");
    		 return;
    	}
        System.out.println("Your generated password is: " + password);
    } 
    else if (option == 2) {
        System.out.println("Enter your password:");
        String password = input.nextLine();
        String strength = checkPasswordStrength(password);
        if (strength.equals("Weak")) {
            String strongerPassword = convertToStrongPassword(password, rand);
            System.out.println("Your password is weak. We have converted it to a stronger password: " + strongerPassword);
        } else {
            System.out.println("Your password is strong.");
        }
    } else {
        System.out.println("Invalid option. Please choose 1 or 2.");
    }
	
	}
private static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase,
        boolean includeNumbers, boolean includeSpecialChars, Random rand) {
String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
String numbers = "0123456789";
String specialChars = "!@#$%^&*()_+{}[]|:;<>,.?/~`";

// Make a StringBuilder to store the generated password
StringBuilder password = new StringBuilder();

// Ensure that the password contains at least one character of each type
if (includeUppercase) {
password.append(uppercaseLetters.charAt(rand.nextInt(uppercaseLetters.length())));
}

if (includeLowercase) {
password.append(lowercaseLetters.charAt(rand.nextInt(lowercaseLetters.length())));
}

if (includeNumbers) {
password.append(numbers.charAt(rand.nextInt(numbers.length())));
}

if (includeSpecialChars) {
password.append(specialChars.charAt(rand.nextInt(specialChars.length())));
}

// Remaining characters to reach the desired length
int remainingLength = length - password.length();

while (remainingLength > 0) {
String chars = "";
if (includeUppercase) {
chars += uppercaseLetters;
}
if (includeLowercase) {
chars += lowercaseLetters;
}
if (includeNumbers) {
chars += numbers;
}
if (includeSpecialChars) {
chars += specialChars;
}

password.append(chars.charAt(rand.nextInt(chars.length())));
remainingLength--;
}

// Shuffle the characters randomly to ensure better password strength
char[] passwordArray = password.toString().toCharArray();
for (int i = passwordArray.length - 1; i > 0; i--) {
int index = rand.nextInt(i + 1);
char temp = passwordArray[index];
passwordArray[index] = passwordArray[i];
passwordArray[i] = temp;
}

return new String(passwordArray);
}

private static String checkPasswordStrength(String password) {
    // Check password length
    if (password.length() < 8) {
        return "Weak";
    }

    // Check if password contains at least one uppercase letter, one lowercase letter,
    // one special character, and one number.
    if (!containsCharacterType(password, CharacterType.UPPERCASE) ||
            !containsCharacterType(password, CharacterType.LOWERCASE) ||
            !containsCharacterType(password, CharacterType.SPECIAL_CHARACTER) ||
            !containsCharacterType(password, CharacterType.NUMBER)) {
        return "Weak";
    }
    return "Strong";
}
private static String generateStrongPassword(Random rand) {
    String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";
    String specialChars = "!@#$%^&*()_+{}[]|:;<>,.?/~`";

    StringBuilder password = new StringBuilder();

    // Add at least one character of each type
    password.append(uppercaseLetters.charAt(rand.nextInt(uppercaseLetters.length())));
    password.append(lowercaseLetters.charAt(rand.nextInt(lowercaseLetters.length())));
    password.append(numbers.charAt(rand.nextInt(numbers.length())));
    password.append(specialChars.charAt(rand.nextInt(specialChars.length())));

    // Remaining characters to reach minimum length (8 characters)
    int remainingLength = 8 - password.length();

    while (remainingLength > 0) {
        String chars = uppercaseLetters + lowercaseLetters + numbers + specialChars;
        password.append(chars.charAt(rand.nextInt(chars.length())));
        remainingLength--;
    }

    // Shuffle the characters randomly to ensure better password strength
    char[] passwordArray = password.toString().toCharArray();
    for (int i = passwordArray.length - 1; i > 0; i--) {
        int index = rand.nextInt(i + 1);
        char temp = passwordArray[index];
        passwordArray[index] = passwordArray[i];
        passwordArray[i] = temp;
    }

    return new String(passwordArray);
}
private static String convertToStrongPassword(String password, Random rand) {
    String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";
    String specialChars = "!@#$%^&*()_+{}[]|:;<>,.?/~`";

    // Make a StringBuilder to store the new strong password
    StringBuilder strongPassword = new StringBuilder(password);

    // Ensure that the strong password contains at least one character of each type
    if (!containsCharacterType(password, CharacterType.UPPERCASE)) {
        strongPassword.append(uppercaseLetters.charAt(rand.nextInt(uppercaseLetters.length())));
    }

    if (!containsCharacterType(password, CharacterType.LOWERCASE)) {
        strongPassword.append(lowercaseLetters.charAt(rand.nextInt(lowercaseLetters.length())));
    }

    if (!containsCharacterType(password, CharacterType.NUMBER)) {
        strongPassword.append(numbers.charAt(rand.nextInt(numbers.length())));
    }

    if (!containsCharacterType(password, CharacterType.SPECIAL_CHARACTER)) {
        strongPassword.append(specialChars.charAt(rand.nextInt(specialChars.length())));
    }
    while (strongPassword.length() < 8) {
        String chars = uppercaseLetters + lowercaseLetters + numbers + specialChars;
        strongPassword.append(chars.charAt(rand.nextInt(chars.length())));
    }

    // Shuffle the characters randomly to ensure better password strength
    char[] strongPasswordArray = strongPassword.toString().toCharArray();
    for (int i = strongPasswordArray.length - 1; i > 0; i--) {
        int index = rand.nextInt(i + 1);
        char temp = strongPasswordArray[index];
        strongPasswordArray[index] = strongPasswordArray[i];
        strongPasswordArray[i] = temp;
    }

    return new String(strongPasswordArray);
}

private static boolean containsCharacterType(String password, CharacterType type) {
    for (char c : password.toCharArray()) {
        switch (type) {
            case UPPERCASE:
                if (Character.isUpperCase(c)) {
                    return true;
                }
                break;
            case LOWERCASE:
                if (Character.isLowerCase(c)) {
                    return true;
                }
                break;
            case SPECIAL_CHARACTER:
                if (!Character.isLetterOrDigit(c)) {
                    return true;
                }
                break;
            case NUMBER:
                if (Character.isDigit(c)) {
                    return true;
                }
                break;
        }
    }
    return false;
}

private enum CharacterType {
    UPPERCASE,
    LOWERCASE,
    SPECIAL_CHARACTER,
    NUMBER
}
	}