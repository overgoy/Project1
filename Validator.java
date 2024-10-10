import java.io.File;

public class Validator {
    public boolean isValidKey(int key, char[] alphabet) {
        return key >= 0 && key < alphabet.length;
    }
    public boolean isFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}