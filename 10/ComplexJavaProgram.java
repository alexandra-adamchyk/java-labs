import java.util.*;
import java.util.logging.*;
import java.lang.reflect.Field;
import java.io.*;

public class ComplexJavaProgram {
    private static final Logger logger = Logger.getLogger(ComplexJavaProgram.class.getName());
    private static ResourceBundle messages;
    private static Scanner scanner;
    
    static {
        setupLogger();
    }
    
    private static void setupLogger() {
        try {
            // Remove default handlers
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }
            
            // Console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format("[%s] %s: %s%n",
                        record.getLevel(),
                        record.getLoggerName(),
                        record.getMessage());
                }
            });
            logger.addHandler(consoleHandler);
            
            // File handler
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Failed to setup logger: " + e.getMessage());
        }
    }
    
    private static void selectLanguage() {
        System.out.println(messages.getString("language.menu"));
        System.out.println("1. " + messages.getString("language.ukrainian"));
        System.out.println("2. " + messages.getString("language.english"));
        System.out.print(messages.getString("language.choice"));
        
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Locale locale;
        if (choice == 1) {
            locale = new Locale("uk", "UA");
        } else {
            locale = new Locale("en", "US");
        }
        
        messages = ResourceBundle.getBundle("messages", locale);
        logger.info("Language selected: " + locale.toString());
    }
    
    private static void demonstrateReflection() {
        logger.info("Starting reflection demonstration");
        
        try {
            System.out.println("\n" + messages.getString("reflection.title"));
            
            // String created as literal
            String literalString = "Hello World";
            System.out.println(messages.getString("reflection.literal") + " " + literalString);
            System.out.println(messages.getString("reflection.before") + " " + literalString);
            
            // Try to modify private field using reflection
            try {
                Field valueField = String.class.getDeclaredField("value");
                valueField.setAccessible(true);
                
                char[] newValue = "Modified".toCharArray();
                valueField.set(literalString, newValue);
                
                System.out.println(messages.getString("reflection.after") + " " + literalString);
                logger.info("Modified literal string using reflection");
            } catch (NoSuchFieldException e) {
                // For newer Java versions, try alternative approach
                System.out.println("Field 'value' not found in this Java version. Using alternative approach.");
                logger.warning("Field 'value' not found, using alternative reflection approach");
                
                // Try to find the field by checking all declared fields
                Field[] fields = String.class.getDeclaredFields();
                boolean fieldFound = false;
                
                for (Field field : fields) {
                    if (field.getType().equals(char[].class)) {
                        field.setAccessible(true);
                        char[] newValue = "Modified".toCharArray();
                        field.set(literalString, newValue);
                        fieldFound = true;
                        break;
                    }
                }
                
                if (fieldFound) {
                    System.out.println(messages.getString("reflection.after") + " " + literalString);
                    logger.info("Modified literal string using alternative reflection approach");
                } else {
                    System.out.println("Could not modify string in this Java version.");
                    logger.warning("Could not modify string - no suitable field found");
                }
            }
            
            // String created via Scanner
            System.out.print("\n" + messages.getString("reflection.enter"));
            String scannerString = scanner.nextLine();
            System.out.println(messages.getString("reflection.scanner") + " " + scannerString);
            System.out.println(messages.getString("reflection.before") + " " + scannerString);
            
            // Try to modify scanner string
            try {
                Field valueField = String.class.getDeclaredField("value");
                valueField.setAccessible(true);
                
                char[] newScannerValue = "Scanner Modified".toCharArray();
                valueField.set(scannerString, newScannerValue);
                
                System.out.println(messages.getString("reflection.after") + " " + scannerString);
                logger.info("Modified scanner string using reflection");
            } catch (NoSuchFieldException e) {
                System.out.println("Could not modify scanner string in this Java version.");
                logger.warning("Could not modify scanner string");
            }
            
        } catch (Exception e) {
            logger.severe("Reflection error: " + e.getMessage());
            System.err.println("Error during reflection: " + e.getMessage());
        }
    }
    
    private static void demonstrateLogging() {
        logger.info("Starting logging demonstration");
        
        System.out.println("\n" + messages.getString("logging.title"));
        
        // Different log levels
        logger.info(messages.getString("logging.info"));
        logger.warning(messages.getString("logging.warning"));
        logger.severe(messages.getString("logging.severe"));
        
        System.out.println(messages.getString("logging.info"));
        System.out.println(messages.getString("logging.warning"));
        System.out.println(messages.getString("logging.severe"));
        System.out.println(messages.getString("logging.file"));
        
        logger.info("Logging demonstration completed");
    }
    
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n" + messages.getString("menu.main"));
            System.out.println(messages.getString("menu.reflection"));
            System.out.println(messages.getString("menu.logging"));
            System.out.println(messages.getString("menu.exit"));
            System.out.print(messages.getString("menu.choice"));
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    demonstrateReflection();
                    break;
                case 2:
                    demonstrateLogging();
                    break;
                case 3:
                    logger.info("Program terminated by user");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println(messages.getString("invalid.choice"));
                    logger.warning("Invalid menu choice: " + choice);
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            // Initial messages with default locale
            messages = ResourceBundle.getBundle("messages", Locale.getDefault());
            
            // Language selection
            selectLanguage();
            
            // Show program title
            System.out.println("\n" + messages.getString("program.title"));
            logger.info("Program started");
            
            // Show main menu
            showMainMenu();
            
        } catch (Exception e) {
            logger.severe("Program error: " + e.getMessage());
            System.err.println("Program error: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
