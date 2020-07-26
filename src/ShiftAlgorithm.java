public class ShiftAlgorithm implements Algorithm{

    @Override
    public String encrypt(String message, int key) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character >= 'a' && character <= 'z') {
                result.append(lowerCaseAlphabet(character, key, 'a'));
            } else if (character >= 'A' && character <= 'Z') {
                result.append(lowerCaseAlphabet(character, key, 'A'));
            }
            else {
                result.append(character);
            }
        }
        return result.toString();
    }

    private char lowerCaseAlphabet(char character, int key, char alphabetAa) {
        int originalAlphabetPosition = character - alphabetAa;
        int newAlphabetPosition = (originalAlphabetPosition + key) % 26;
        return (char) (alphabetAa + newAlphabetPosition);
    }

    @Override
    public String decrypt(String message, int key) {
        return encrypt(message, 26 - (key % 26));
    }
}
