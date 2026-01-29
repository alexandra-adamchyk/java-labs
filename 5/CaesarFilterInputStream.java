import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CaesarFilterInputStream extends FilterInputStream {
    private final int key;

    protected CaesarFilterInputStream(InputStream in, int key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int data = super.read();
        if (data == -1) {
            return -1;
        }
        return decryptChar((char) data);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = super.read(b, off, len);
        if (result == -1) {
            return -1;
        }
        
        for (int i = off; i < off + result; i++) {
            b[i] = (byte) decryptChar((char) (b[i] & 0xFF));
        }
        return result;
    }

    private char decryptChar(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) ((c - 'a' - key + 26) % 26 + 'a');
        } else if (c >= 'A' && c <= 'Z') {
            return (char) ((c - 'A' - key + 26) % 26 + 'A');
        } else if (c >= 'а' && c <= 'я') {
            return (char) ((c - 'а' - key + 32) % 32 + 'а');
        } else if (c >= 'А' && c <= 'Я') {
            return (char) ((c - 'А' - key + 32) % 32 + 'А');
        }
        return c;
    }
}
