public class Producer extends Thread {
    private final RingBuffer<String> buffer;
    private final int producerId;
    private int messageCounter;

    public Producer(RingBuffer<String> buffer, int producerId) {
        this.buffer = buffer;
        this.producerId = producerId;
        this.messageCounter = 0;
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                messageCounter++;
                String message = "Потік " + producerId + " згенерував повідомлення " + messageCounter;
                buffer.put(message);
                Thread.sleep((long) (Math.random() * 100));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
