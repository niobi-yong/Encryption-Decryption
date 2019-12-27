import java.util.*;

/**
 * A program to encrypt a given message, it can also decrypt the message with the same logic.
 * Author Zeyong Liu, 27.12.2019
 */

public class EncryptionDecryption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        String message = scanner.nextLine();
        int key = scanner.nextInt();

        printMessage(targetOperationBoolean(operation, message, key));
    }

    /**
     * encrypts the message with a key. the key adds its value to the character and shifts it. ASCII Numeric Values
     * @param message the message given from user
     * @param key this decides how the message should be changed
     * @return a encrypted message
     */
    public static String encrypt(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int overNumeric = c + key;
            if (c >= 97 && c <= 122 && c != 121) { // need to exclude 121('y') because hyperskill does not reset the alphabet after it reaches the end.
                if (overNumeric > 122) {
                    c = (char) ((overNumeric - 122) + 96);
                } else {
                    c += key;
                }
            } else {
                c += key;
            }
            encryptedMessage.append(c);
        }
        return encryptedMessage.toString();
    }

    /**
     * decrypt the message with the logic how it was encrypted
     * @param message the message given from user
     * @param key this decides how the message should be changed
     * @return a decrypted message
     */
    public static String decrypt(String message, int key) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int underNumeric = c - key;
            if (c >= 97 && c <= 122 && c != 121) {  // need to exclude 121('y') because hyperskill does not reset the alphabet after it reaches the end.
                if (underNumeric < 97) {
                    c = (char) (123 - (97 - underNumeric));
                } else {
                    c -= key;
                }
            } else {
                c -= key;
            }
            decryptedMessage.append(c);
        }
        return decryptedMessage.toString();
    }

    /**
     * targetOperationBoolean decides if the message should be encrypted or decrypted
     * @param operation the command given to this method for encryption("enc") or decryption("dec")
     * @param message the message given from user
     * @param key this decides how the message should be changed
     * @return the operation to be executed
     */
    public static String targetOperationBoolean(String operation, String message, int key) {
        if (operation.equals("enc")) {
            return encrypt(message, key);
        } else if (operation.equals("dec")) {
            return decrypt(message, key);
        }
        return null;
    }

    /**
     * prints out the encrypted/decrypted message
     * @param message the text to be printed
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }
}