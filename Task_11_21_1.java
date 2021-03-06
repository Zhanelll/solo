import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;


public class Task_11_21_1 {

    public static void main(String[] args) {

        String[] array1 = new String[]{"George", "Jim", "John", "Blake", "Kevin", "Michael"};
        String[] array2 = new String[]{"George", "Katie", "Kevin", "Michelle", "Ryan"};

        LinkedHashSet<String> set1 = new LinkedHashSet<>(Arrays.asList(array1));
        LinkedHashSet<String> set2 = new LinkedHashSet<>(Arrays.asList(array2));
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2 + "\n");

        // Union
        LinkedHashSet<String> unionSet = (LinkedHashSet<String>)set1.clone();
        unionSet.addAll(set2);
        System.out.println("Union: " + unionSet + "\n");

        // Difference
        HashSet<String> difference = (LinkedHashSet<String>) set1.clone();
        HashSet<String> dif2Set = (LinkedHashSet<String>) set2.clone();
        difference.removeAll(set2);
        dif2Set.removeAll(set1);
        difference.addAll(dif2Set);
        System.out.println("Difference: " + difference + "\n");

        LinkedHashSet<String> intersection = (LinkedHashSet<String>) set1.clone();
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection + "\n");



    }
}