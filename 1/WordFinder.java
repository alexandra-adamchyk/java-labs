import java.util.HashSet;
import java.util.Set;

/**
 * Консольна програма для знаходження слова з мінімальною кількістю різних символів.
 * 
 * Завдання: Знайти в заданому рядку слово, в якому кількість різних символів мінімальна.
 * Якщо таких слів декілька, визначити перше з них.
 * Словом вважається послідовність символів між пробілами.
 */
public class WordFinder {
    
    /**
     * Головний метод для демонстрації роботи програми.
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        // Приклади тестових рядків
        String testString1 = "hello world programming java";
        String testString2 = "aaa bbb ccc abc def";
        String testString3 = "mississippi california texas";
        
        System.out.println("Тестування програми:");
        System.out.println("Рядок 1: \"" + testString1 + "\"");
        System.out.println("Результат: \"" + findWordWithMinUniqueChars(testString1) + "\"\n");
        
        System.out.println("Рядок 2: \"" + testString2 + "\"");
        System.out.println("Результат: \"" + findWordWithMinUniqueChars(testString2) + "\"\n");
        
        System.out.println("Рядок 3: \"" + testString3 + "\"");
        System.out.println("Результат: \"" + findWordWithMinUniqueChars(testString3) + "\"\n");
    }
    
    /**
     * Знаходить слово з мінімальною кількістю різних символів.
     * 
     * @param inputString вхідний рядок для аналізу
     * @return слово з мінімальною кількістю унікальних символів,
     *         або порожній рядок, якщо вхідний рядок порожній
     */
    public static String findWordWithMinUniqueChars(String inputString) {
        // Перевірка на порожній вхідний рядок
        if (inputString == null || inputString.trim().isEmpty()) {
            return "";
        }
        
        // Розділення рядку на слова за пробілами
        String[] words = inputString.trim().split("\\s+");
        
        // Ініціалізація змінних для відстеження результату
        String resultWord = "";
        int minUniqueCount = Integer.MAX_VALUE;
        
        // Обробка кожного слова
        for (String word : words) {
            if (word.isEmpty()) {
                continue; // Пропускаємо порожні слова
            }
            
            // Підрахунок кількості унікальних символів у поточному слові
            int uniqueCharCount = countUniqueCharacters(word);
            
            // Оновлення результату, якщо знайдено слово з меншою кількістю унікальних символів
            if (uniqueCharCount < minUniqueCount) {
                minUniqueCount = uniqueCharCount;
                resultWord = word;
            }
            
            // Виведення проміжних результатів для налагодження
            System.out.println("Слово: \"" + word + "\", унікальних символів: " + uniqueCharCount);
        }
        
        return resultWord;
    }
    
    /**
     * Підраховує кількість унікальних символів у слові.
     * 
     * @param word слово для аналізу
     * @return кількість унікальних символів
     */
    private static int countUniqueCharacters(String word) {
        // Використання Set для зберігання унікальних символів
        Set<Character> uniqueChars = new HashSet<>();
        
        // Додавання кожного символу до множини
        for (int i = 0; i < word.length(); i++) {
            uniqueChars.add(word.charAt(i));
        }
        
        // Розмір множини дорівнює кількості унікальних символів
        return uniqueChars.size();
    }
}
