public class RingBuffer<T> {
    private final Object[] buffer;
    private final int capacity;
    private int head;
    private int tail;
    private int size;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public synchronized void put(T item) throws InterruptedException {
        while (size == capacity) {
            wait();
        }
        buffer[tail] = item;
        tail = (tail + 1) % capacity;
        size++;
        notifyAll();
    }

    @SuppressWarnings("unchecked")
    public synchronized T get() throws InterruptedException {
        while (size == 0) {
            wait();
        }
        T item = (T) buffer[head];
        buffer[head] = null;
        head = (head + 1) % capacity;
        size--;
        notifyAll();
        return item;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized int size() {
        return size;
    }
}
