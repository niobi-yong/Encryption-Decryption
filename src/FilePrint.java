import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * writes into a file
 */
public class FilePrint implements PrintingMethod{

    File file;

    public FilePrint(File file) {
        this.file = file;
    }

    @Override
    public void print(String message) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.print(message);
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }
}
