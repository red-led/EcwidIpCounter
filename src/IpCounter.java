import java.util.Arrays;

public class IpCounter {
    // 1 bit per IP address, 2^32 / 64 = 64 MiB
    private final int STORAGE_SIZE = 67108864;

    long[] storage;

    public IpCounter() {
        this.storage = new long[this.STORAGE_SIZE];
        Arrays.fill(this.storage, 0L);
    }

    public void add(int ip) {
        int element = ip >>> 6;
        int bitNumber = ip & 0b111111;

        this.storage[element] = this.storage[element] | (1L << bitNumber);
    }

    public long count() {
        long count = 0;
        for (int i = 0; i < this.STORAGE_SIZE; i++) {
            count += Long.bitCount(this.storage[i]);
        }

        return count;
    }
}
