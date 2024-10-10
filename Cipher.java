public class Cipher {
    private char[] alphabet;
    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
    }
    public String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = findCharIndex(c);
            if (index != -1) {
                int newIndex = (index + shift) % alphabet.length;
                result.append(alphabet[newIndex]);
            } else {
                result.append(c); // Оставляем символы, которых нет в алфавите
            }
        }
        return result.toString();
    }
    public String decrypt(String encryptedText, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : encryptedText.toCharArray()) {
            int index = findCharIndex(c);
            if (index != -1) {
                int newIndex = (index + shift) % alphabet.length;
                result.append(alphabet[newIndex]);
            } else {
                result.append(c); // Оставляем символы, которых нет в алфавите
            }
        }
        return result.toString();
    }

    public int findCharIndex(char c) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == c) {
                return i;
            }
        }
        return -1; // Если символ не найден
    }
}
