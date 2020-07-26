public class AlgorithmPicker {

    private Algorithm algorithm;

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * targetOperationBoolean decides if the message should be encrypted or decrypted
     * @param operation the command given to this method for encryption("enc") or decryption("dec")
     * @param message the message given from user
     * @param key this decides how the message should be changed
     * @return the operation to be executed
     */
    public String targetOperation(String operation, String message, int key) {
        if (operation.equals("enc")) {
            return algorithm.encrypt(message, key);

        } else if (operation.equals("dec")) {
            return algorithm.decrypt(message, key);
        }

        return null;
    }
}
