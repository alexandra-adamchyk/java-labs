public class Main {
    public static void main(String[] args) {
        RingBuffer<String> firstBuffer = new RingBuffer<>(10);
        RingBuffer<String> secondBuffer = new RingBuffer<>(10);

        Producer[] producers = new Producer[5];
        for (int i = 0; i < 5; i++) {
            producers[i] = new Producer(firstBuffer, i + 1);
            producers[i].start();
        }

        Translator[] translators = new Translator[2];
        for (int i = 0; i < 2; i++) {
            translators[i] = new Translator(firstBuffer, secondBuffer, i + 1);
            translators[i].start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Читання 100 повідомлень з другого буфера:");
        for (int i = 0; i < 100; i++) {
            try {
                String message = secondBuffer.get();
                System.out.println((i + 1) + ": " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("Завершено читання 100 повідомлень.");
    }
}
