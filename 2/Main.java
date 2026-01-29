import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            
            Address address = new Address("Вулиця Шевченка", "15", "42");
            Student originalStudent = new Student(
                "Іван", 
                "Петренко", 
                LocalDate.of(2000, 5, 15), 
                "+380501234567", 
                address
            );
            
            System.out.println("Оригінальний об'єкт:");
            System.out.println(originalStudent);
            
            String jsonString = objectMapper.writeValueAsString(originalStudent);
            System.out.println("\nJSON рядок:");
            System.out.println(jsonString);
            
            Student deserializedStudent = objectMapper.readValue(jsonString, Student.class);
            System.out.println("\nДесеріалізований об'єкт:");
            System.out.println(deserializedStudent);
            
            boolean areEqual = originalStudent.equals(deserializedStudent);
            System.out.println("\nРезультат порівняння (equals): " + areEqual);
            
            System.out.println("\nХеш-коди:");
            System.out.println("Оригінальний: " + originalStudent.hashCode());
            System.out.println("Десеріалізований: " + deserializedStudent.hashCode());
            
        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
