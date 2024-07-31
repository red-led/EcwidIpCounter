public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please pass file name as an argument");
            System.exit(0);
        }

        var counter = new IpCounter();

        var reader = new IpReader(args[0]);
        while (reader.hasNext()) {
            counter.add(reader.next());
        }

        System.out.println("Parsing finished, counting");
        System.out.println(
            "IP count is: " + String.format("%,d", counter.count())
        );
    }
}