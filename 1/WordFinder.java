import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Лабораторна робота №1
 * Тема: Робота з рядками
 *
 * Мета роботи:
 * Навчитися працювативати зі строками та колекціями в Java
 * та знаходити слово з мінімальною кількістю різних символів.
 */
public class WordFinder {

    // Точка входу в програму
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Зчитуємо один рядок з консолі
        String input = scanner.nextLine();

        // Знаходимо слово з мінімальною кількістю різних символів
        String result = findWordWithMinUniqueChars(input);

        // Виводимо тільки результат
        System.out.println(result);

        scanner.close();
    }

    /**
     * Знаходить слово з мінімальною кількістю різних символів.
     * Якщо таких слів декілька — повертає перше.
     */
    public static String findWordWithMinUniqueChars(String inputString) {
        if (inputString == null || inputString.trim().isEmpty()) {
            return "";
        }

        // Розбиваємо рядок на слова
        String[] words = inputString.trim().split("\\s+");

        String resultWord = "";
        int minUniqueCount = Integer.MAX_VALUE;

        // Аналізуємо кожне слово
        for (String word : words) {
            int uniqueCharCount = countUniqueCharacters(word);

            if (uniqueCharCount < minUniqueCount) {
                minUniqueCount = uniqueCharCount;
                resultWord = word;
            }
        }

        return resultWord;
    }

    /**
     * Рахує кількість різних символів у слові.
     */
    private static int countUniqueCharacters(String word) {
        Set<Character> uniqueChars = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {
            uniqueChars.add(word.charAt(i));
        }

        return uniqueChars.size();
    }
}
