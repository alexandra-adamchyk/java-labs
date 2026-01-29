import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class RedBlackTreeDemo {
    private static RedBlackTree tree = new RedBlackTree();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    
    public static void main(String[] args) {
        System.out.println("=== Червоно-чорне дерево - Демонстрація ===\n");
        
        while (true) {
            showMenu();
            int choice = getIntInput("Ваш вибір: ");
            
            switch (choice) {
                case 1:
                    insertNode();
                    break;
                case 2:
                    deleteNode();
                    break;
                case 3:
                    showInorder();
                    break;
                case 4:
                    tree.visualize();
                    break;
                case 5:
                    demonstrateRandomArray();
                    break;
                case 6:
                    demonstrateSortedArray();
                    break;
                case 7:
                    clearTree();
                    break;
                case 8:
                    System.out.println("Програма завершена.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
            }
            System.out.println();
        }
    }
    
    private static void showMenu() {
        System.out.println("Меню:");
        System.out.println("1. Додати вузол");
        System.out.println("2. Видалити вузол");
        System.out.println("3. Показати in-order обхід");
        System.out.println("4. Візуалізувати дерево");
        System.out.println("5. Демонстрація на випадковому масиві");
        System.out.println("6. Демонстрація на впорядкованому масиві");
        System.out.println("7. Очистити дерево");
        System.out.println("8. Вихід");
    }
    
    private static void insertNode() {
        int value = getIntInput("Введіть значення для додавання: ");
        tree.insert(value);
        System.out.println("Вузол " + value + " додано до дерева.");
    }
    
    private static void deleteNode() {
        if (tree.isEmpty()) {
            System.out.println("Дерево порожнє.");
            return;
        }
        int value = getIntInput("Введіть значення для видалення: ");
        tree.delete(value);
    }
    
    private static void showInorder() {
        if (tree.isEmpty()) {
            System.out.println("Дерево порожнє.");
            return;
        }
        tree.inorderTraversal();
    }
    
    private static void demonstrateRandomArray() {
        System.out.println("\n=== Демонстрація на випадковому масиві ===");
        clearTree();
        
        int size = getIntInput("Введіть розмір масиву (рекомендовано 10-20): ");
        int[] randomArray = generateRandomArray(size);
        
        System.out.println("Згенерований випадковий масив:");
        System.out.println(Arrays.toString(randomArray));
        
        System.out.println("\nДодавання елементів до дерева...");
        for (int value : randomArray) {
            tree.insert(value);
            System.out.print(value + " ");
        }
        System.out.println("\n");
        
        tree.visualize();
        tree.inorderTraversal();
    }
    
    private static void demonstrateSortedArray() {
        System.out.println("\n=== Демонстрація на впорядкованому масиві ===");
        clearTree();
        
        int size = getIntInput("Введіть розмір масиву (рекомендовано 10-20): ");
        int[] sortedArray = generateSortedArray(size);
        
        System.out.println("Згенерований впорядкований масив:");
        System.out.println(Arrays.toString(sortedArray));
        
        System.out.println("\nДодавання елементів до дерева...");
        for (int value : sortedArray) {
            tree.insert(value);
            System.out.print(value + " ");
        }
        System.out.println("\n");
        
        tree.visualize();
        tree.inorderTraversal();
    }
    
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100) + 1;
        }
        return array;
    }
    
    private static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (i + 1) * 10;
        }
        return array;
    }
    
    private static void clearTree() {
        tree = new RedBlackTree();
        System.out.println("Дерево очищено.");
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введіть коректне ціле число.");
            }
        }
    }
}
