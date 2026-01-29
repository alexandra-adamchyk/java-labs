import java.io.*;

public class CaesarCipher {
    
    public static void encrypt(String inputPath, int key) throws IOException {
        String outputPath = getOutputPath(inputPath, "_encrypted");
        
        try (FileInputStream fis = new FileInputStream(inputPath);
             FileOutputStream fos = new FileOutputStream(outputPath);
             CaesarFilterOutputStream cos = new CaesarFilterOutputStream(fos, key)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
        
        System.out.println("Файл зашифровано: " + outputPath);
    }
    
    public static void decrypt(String inputPath, int key) throws IOException {
        String outputPath = getOutputPath(inputPath, "_decrypted");
        
        try (FileInputStream fis = new FileInputStream(inputPath);
             CaesarFilterInputStream cis = new CaesarFilterInputStream(fis, key);
             FileOutputStream fos = new FileOutputStream(outputPath)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        
        System.out.println("Файл розшифровано: " + outputPath);
    }
    
    private static String getOutputPath(String inputPath, String suffix) {
        int dotIndex = inputPath.lastIndexOf('.');
        if (dotIndex == -1) {
            return inputPath + suffix;
        }
        String name = inputPath.substring(0, dotIndex);
        String extension = inputPath.substring(dotIndex);
        return name + suffix + extension;
    }
    
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Використання: java CaesarCipher <encrypt|decrypt> <файл> <ключ>");
            System.out.println("Приклад: java CaesarCipher encrypt input.txt 3");
            System.out.println("Приклад: java CaesarCipher decrypt input_encrypted.txt 3");
            return;
        }
        
        String operation = args[0];
        String filePath = args[1];
        int key;
        
        try {
            key = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Помилка: ключ має бути цілим числом");
            return;
        }
        
        try {
            if ("encrypt".equalsIgnoreCase(operation)) {
                encrypt(filePath, key);
            } else if ("decrypt".equalsIgnoreCase(operation)) {
                decrypt(filePath, key);
            } else {
                System.out.println("Помилка: операція має бути 'encrypt' або 'decrypt'");
            }
        } catch (IOException e) {
            System.out.println("Помилка при роботі з файлами: " + e.getMessage());
        }
    }
}
