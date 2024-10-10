import java.util.Scanner;

public class MainApp {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cipher cipher = new Cipher(ALPHABET);
        FileManager fileManager = new FileManager();
        Validator validator = new Validator();
        BruteForce bruteForce = new BruteForce();
        StatisticalAnalyzer statisticalAnalyzer = new StatisticalAnalyzer();

        while (true) {
            System.out.println("Выберите режим:");
            System.out.println("1. Шифрование");
            System.out.println("2. Расшифровка с ключом");
            System.out.println("3. Brute Force");
            System.out.println("4. Статистический анализ");
            System.out.println("0. Выход");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Введите путь к файлу для шифрования:");
                    String inputFile = scanner.next();
                    System.out.println("Введите путь к выходному файлу:");
                    String outputFile = scanner.next();
                    System.out.println("Введите ключ шифрования:");
                    int key = scanner.nextInt();

                    if (validator.isFileExists(inputFile) && validator.isValidKey(key, ALPHABET)) {
                        String text = fileManager.readFile(inputFile);
                        String encryptedText = cipher.encrypt(text, key);
                        System.out.println("Шифрование завершено.");
                    } else {
                        System.out.println("Ошибка валидации входных данных.");
                    }
                    break;
                case 2:
                    System.out.println("Введите путь к файлу для шифрования:");
                    String inputFileDecrypt = scanner.next();
                    System.out.println("Введите путь к выходному файлу:");
                    String outputFileDecrypt = scanner.next();
                    System.out.println("Введите ключ шифрования:");
                    int decryptKey = scanner.nextInt();

                    if (validator.isFileExists(inputFileDecrypt) && validator.isValidKey(decryptKey, ALPHABET)) {
                        String encryptedText = fileManager.readFile(inputFileDecrypt);
                        String decryptedText = cipher.decrypt(encryptedText, decryptKey);
                        fileManager.writeFile(decryptedText, outputFileDecrypt);
                        System.out.println("Расшифровка завершена.");
                    } else {
                        System.out.println("Ошибка валидации входных данных.");
                    }
                    break;
                case 3:
                    System.out.println("Введите путь к файлу для brute force:");
                    String inputFileBruteForce = scanner.next();
                    System.out.println("Введите путь к выходному файлу:");
                    String outputFileBruteForce = scanner.next();

                    if (validator.isFileExists(inputFileBruteForce)) {
                        String encryptedText = fileManager.readFile(inputFileBruteForce);
                        String bruteForcedText = bruteForce.decryptByBruteForce(encryptedText, ALPHABET);
                        fileManager.writeFile(bruteForcedText, outputFileBruteForce);
                        System.out.println("Расшифровка методом brute force завершена.");
                    } else {
                        System.out.println("Ошибка валидации входных данных.");
                    }
                    break;
                case 4:
                    System.out.println("Введите путь к зашифрованному файлу для статистического анализа:");
                    String inputFileStatAnalysis = scanner.next();
                    System.out.println("Введите путь к файлу для анализа на основе языка:");
                    String languageFile = scanner.next();
                    System.out.println("Введите путь к выходному файлу:");
                    String outputFileStatAnalysis = scanner.next();

                    if (validator.isFileExists(inputFileStatAnalysis) && validator.isFileExists(languageFile)) {
                        String encryptedText = fileManager.readFile(inputFileStatAnalysis);
                        String languageSampleText = fileManager.readFile(languageFile);
                        String decryptedText = String.valueOf(statisticalAnalyzer.findMostLikelyShift(encryptedText, ALPHABET, languageSampleText));
                        fileManager.writeFile(decryptedText, outputFileStatAnalysis);
                        System.out.println("Расшифровка с помощью статистического анализа завершена.");
                    } else {
                        System.out.println("Ошибка валидации входных данных.");
                    }
                    break;
                case 0:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный ввод.");
            }
        }
    }

}