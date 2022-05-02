import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Exercise20_6 {
    public static void main(String[] args) {
        List<Integer> integers = generateIntegerList(5000000);

        long timeStart = System.currentTimeMillis();
        for (Integer integer : integers) {
            Integer ref = integer;
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("Time to traverse the list using an iterator:");
        System.out.println(formatTime(timeEnd - timeStart));

        timeStart = System.currentTimeMillis();
        for (int i = 0; i < integers.size(); i++) {
            Integer ref = integers.get(i);
        }
        timeEnd = System.currentTimeMillis();
        System.out.println("Time to traverse the list using the get(index) method:");
        System.out.println(formatTime(timeEnd - timeStart));
    }

    private static String formatTime(long millis) {
        return String.format("%02d:%02d:%02d.%03d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1),
                millis % 1000);
    }

    private static List<Integer> generateIntegerList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add((int) (Math.random() * size));
        }
        return list;
    }
}