public class UnicodeAlgorithm implements Algorithm{

    /**
     * encrypts the message with a key. the key adds its value to the character and shifts it. ASCII Numeric Values
     */
    @Override
    public String encrypt(String message, int key) {
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
     */
    @Override
    public String decrypt(String message, int key) {
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
}
