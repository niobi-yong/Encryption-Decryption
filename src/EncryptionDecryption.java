import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A program to encrypt a given message, it can also decrypt the message with the same logic.
 * Author Zeyong Liu, 27.12.2019
 */
public class EncryptionDecryption {

    public static void main(String[] args) {
        String operation = "enc";
        String message = "";
        String fileInMessage = "";
        int key = 0;
        String fileOutPath = "";
        boolean outExist = false;
        boolean inExist = false;

        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-mode")) {
                operation = args[i + 1];

            } else if (args[i].equals("-key")) {
                key = Integer.parseInt(args[i + 1]);

            } else if (args[i].equals("-data")) {
                message = args[i + 1];

            } else if (args[i].equals("-in") && !args[i + 1].equals("")) {
                try {
                    fileInMessage = readFileAsString(args[i + 1]);
                } catch (IOException e) {
                    System.out.println("Cannot read file: " + e.getMessage());
                }
                inExist = true;

            } else if (args[i].equals("-out")) {
                if (!(args[i] + 1).equals("")) {
                    fileOutPath = args[i + 1];
                    outExist = true;
                }
            }
        }

        if (outExist) {
            File fileOut = new File(fileOutPath);

            if (inExist) {
                writeFileAsString(fileOut, operation, fileInMessage, key);
            } else {
                writeFileAsString(fileOut, operation, message, key);
            }

        } else {
            if (inExist) {
                printMessage(operation, message, key);
            } else {
                printMessage(operation, fileInMessage, key);
            }
        }
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
    public static void printMessage(String operation, String message, int key) {
        System.out.println(targetOperationBoolean(operation, message, key));
    }

    /**
     * reads a file
     * @param fileName the file/directory
     * @return the String of the file
     * @throws IOException checked Exception
     */
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    /**
     * writes into a file
     * @param file the file to be written
     * @param operation encryption or decryption
     * @param fileInMessage message to be encrypted or decrypted
     * @param key this decides how the message should be changed
     */
    public static void writeFileAsString(File file, String operation, String fileInMessage, int key) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(targetOperationBoolean(operation, fileInMessage, key));
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }
}