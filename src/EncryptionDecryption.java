import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A program to encrypt a given message, it can also decrypt the message with the same logic.
 * @author Zeyong Liu, 27.12.2019
 */
public class EncryptionDecryption {

    public static void main(String[] args) {

        String operation = "enc";
        String message = "";
        String fileInMessage = "";
        int key = 0;
        String fileOutPath = "";
        AlgorithmPicker algorithm = new AlgorithmPicker();

        readArgs(algorithm, args, operation, message, fileInMessage, key, fileOutPath,
                false, false);
    }

    public static void readArgs(AlgorithmPicker algorithm, String[] args, String operation,
                                String message, String fileInMessage, int key, String fileOutPath,
                                boolean outExist, boolean inExist)
    {
        for (int i = 0; i < args.length - 1; i += 2) {
            switch(args[i]) {
                case "-mode":
                    operation = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    message = args[i + 1];
                    break;
                case "-in":
                    if (args[i + 1] != null && !args[i + 1].equals("")) {
                        try {
                            fileInMessage = readFileAsString(args[i + 1]);
                        } catch (IOException e) {
                            System.out.println("Cannot read file: " + e.getMessage());
                        }
                        inExist = true;
                    }
                    break;
                case "-out":
                    if (args[i + 1] != null && !args[i + 1].equals("")) {
                        fileOutPath = args[i + 1];
                        outExist = true;
                    }
                    break;
                case "-alg":
                    if (args[i + 1].equals("unicode")) {
                        algorithm.setAlgorithm(new UnicodeAlgorithm());
                    } else {
                        algorithm.setAlgorithm(new ShiftAlgorithm());
                    }
                    break;
                default:
                    System.out.println("No input");
                    break;
            }
        }

        PrintMethodPicker print = new PrintMethodPicker();

        if (outExist) {
            File fileOut = new File(fileOutPath);
            print.setPrintingMethod(new FilePrint(fileOut));
        } else {
            print.setPrintingMethod(new ConsolePrint());
        }

        if (inExist) {
            print.print(algorithm.targetOperation(operation, fileInMessage, key));
        } else {
            print.print(algorithm.targetOperation(operation, message, key));
        }
    }

    /**
     * reading the file
     */
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}