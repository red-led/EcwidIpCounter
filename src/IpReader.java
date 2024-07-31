import java.io.*;

public class IpReader
{
    public static final int BUFFER_SIZE = 1024;

    private final Reader reader;
    private boolean eof;

    private final char[] buffer;
    private int bufferPosition;
    private int bufferSize;

    public IpReader(String fileName) {
        File file = new File(fileName);
        try {
            var in = new FileInputStream(file);
            this.reader = new InputStreamReader(in);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.eof = false;
        this.buffer = new char[BUFFER_SIZE];
        this.bufferPosition = 0;
        this.bufferSize = 0;
    }

    public boolean hasNext() {
        return !(this.eof && this.bufferSize == this.bufferPosition);
    }

    public int next() {
        int ip = 0, ipByte = 0;
        byte read, digit;

        while (true) {
            read = this.nextByte();
            digit = (byte) (read - '0');

            if (digit >= 0 && digit <= 9) {
                ipByte = ipByte * 10 + digit;
            } else if (read == '.') {
                ip = (ip << 8) + ipByte;
                ipByte = 0;
            } else if (read == '\n') {
                ip = (ip << 8) + ipByte;
                return ip;
            }
        }
    }

    private byte nextByte() {
        byte r;

        if (this.bufferPosition == this.bufferSize) {
            this.refreshBuffer();
        }

        r = (byte) this.buffer[this.bufferPosition];
        this.bufferPosition++;

        return r;
    }

    private void refreshBuffer() {
        this.bufferPosition = 0;

        try {
            this.bufferSize = this.reader.read(this.buffer);
            this.eof = this.bufferSize < BUFFER_SIZE;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
