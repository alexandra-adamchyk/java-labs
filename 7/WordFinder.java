import java.util.Arrays;
import java.util.Comparator;

/**
 * Консольна програма для знаходження слова з мінімальною кількістю різних символів
 * з використанням Java 8+ Stream API та Lambda expressions.
 */
public class WordFinder {
    
    /**
     * Знаходить слово з мінімальною кількістю різних символів.
     * Використовує Stream API для функціонального підходу.
     * 
     * @param input вхідний рядок для аналізу
     * @return слово з мінімальною кількістю різних символів, або порожній рядок якщо вхід порожній
     */
    public static String findWordWithMinUniqueChars(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        
        return Arrays.stream(input.trim().split("\\s+"))
                .filter(word -> !word.isEmpty())
                .min(Comparator.comparingLong(word -> 
                    word.chars().distinct().count()))
                .orElse("");
    }
    
    /**
     * Допоміжний метод для отримання кількості унікальних символів у слові.
     * 
     * @param word слово для аналізу
     * @return кількість унікальних символів
     */
    public static long getUniqueCharCount(String word) {
        if (word == null || word.isEmpty()) {
            return 0;
        }
        
        return word.chars().distinct().count();
    }
    
    /**
     * Головний метод для тестування програми.
     */
    public static void main(String[] args) {
        // Приклади тестування
        String[] testCases = {
            "hello world programming",
            "aaa bbb ccc ddd",
            "abc def ghi jkl",
            "mississippi california",
            "a bb ccc dddd",
            "",
            "   ",
            "single"
        };
        
        System.out.println("Тестування програми:");
        System.out.println("==================");
        
        for (String testCase : testCases) {
            String result = findWordWithMinUniqueChars(testCase);
            System.out.println("Вхід: \"" + testCase + "\"");
            System.out.println("Результат: \"" + result + "\"");
            if (!result.isEmpty()) {
                System.out.println("Кількість унікальних символів: " + getUniqueCharCount(result));
            }
            System.out.println("------------------");
        }
        
        // Інтерактивний режим
        System.out.println("\nІнтерактивний режим:");
        System.out.println("Введіть рядок для аналізу (або 'exit' для виходу):");
        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            
            String result = findWordWithMinUniqueChars(input);
            System.out.println("Результат: \"" + result + "\"");
        }
        
        scanner.close();
        System.out.println("Програму завершено.");
    }
}
