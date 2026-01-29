public class Translator extends Thread {
    private final RingBuffer<String> firstBuffer;
    private final RingBuffer<String> secondBuffer;
    private final int translatorId;
    private int messageCounter;

    public Translator(RingBuffer<String> firstBuffer, RingBuffer<String> secondBuffer, int translatorId) {
        this.firstBuffer = firstBuffer;
        this.secondBuffer = secondBuffer;
        this.translatorId = translatorId;
        this.messageCounter = 0;
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String originalMessage = firstBuffer.get();
                messageCounter++;
                String translatedMessage = "Потік " + translatorId + " переклав повідомлення " + messageCounter + ": " + originalMessage;
                secondBuffer.put(translatedMessage);
                Thread.sleep((long) (Math.random() * 150));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
