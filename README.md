This is solution of [IP Addr Counter](https://github.com/Ecwid/new-job/blob/master/IP-Addr-Counter.md) test assignment.

Build:
```bash
javac -d build/class src/*.java
jar cfm build/ip_count.jar manifest/MANIFEST.MF -C build/class .
```

Run:
```bash
java -jar build/ip_count.jar <path/to/file>
```

Result of run on example file, measured with `time` utility:
```
IP count is: 1,000,000,000

real    5m15,076s
user    4m43,477s
sys     0m31,540s
```