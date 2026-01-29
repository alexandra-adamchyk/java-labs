import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CaesarFilterOutputStream extends FilterOutputStream {
    private final int key;

    protected CaesarFilterOutputStream(OutputStream out, int key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(encryptChar((char) (b & 0xFF)));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        byte[] encrypted = new byte[len];
        for (int i = 0; i < len; i++) {
            encrypted[i] = (byte) encryptChar((char) (b[off + i] & 0xFF));
        }
        super.write(encrypted, 0, len);
    }

    private char encryptChar(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) ((c - 'a' + key) % 26 + 'a');
        } else if (c >= 'A' && c <= 'Z') {
            return (char) ((c - 'A' + key) % 26 + 'A');
        } else if (c >= 'а' && c <= 'я') {
            return (char) ((c - 'а' + key) % 32 + 'а');
        } else if (c >= 'А' && c <= 'Я') {
            return (char) ((c - 'А' + key) % 32 + 'А');
        }
        return c;
    }
}
