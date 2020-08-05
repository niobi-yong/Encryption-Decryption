/**
 * prints out the encrypted/decrypted message in the terminal
 */
public class ConsolePrint implements PrintingMethod {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
