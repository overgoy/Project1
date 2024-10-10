public class BruteForce {
    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
        Cipher cipher = new Cipher(alphabet);
        for (int key = 0; key < alphabet.length; key++) {
            String decryptedText = cipher.decrypt(encryptedText, key);
            System.out.println("Ключ " + key + ": " + decryptedText);
        }
        return encryptedText;
    }
}