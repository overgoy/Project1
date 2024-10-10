import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatisticalAnalyzer {
    public int findMostLikelyShift(String encryptedText,
                                   char[] alphabet, String representativeText) {
        // Простая логика: найдем самый частый символ в обоих текстах и определим сдвиг
        char mostFrequentEncrypted = findMostFrequentChar(encryptedText);
        char mostFrequentOriginal = findMostFrequentChar(representativeText);

        int shift = (new Cipher(alphabet)).findCharIndex(mostFrequentEncrypted) - (new Cipher(alphabet)).findCharIndex(mostFrequentOriginal);
        return shift >= 0 ? shift : shift + alphabet.length; // Учитываем цикличность алфавита
    }

    private char findMostFrequentChar(String text) {
        // Логика для нахождения самого частого символа
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return Collections.max(frequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}