import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
public class Task_11_21_6{
	public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Type the list of integers (Input 0 if you want to stop): ");
        ArrayList<Integer> list = new ArrayList<>();
        while(true){
            int x = input.nextInt();
            if (x == 0) break;
            list.add(x);
        }
        Set<Integer> set = new LinkedHashSet<>(list);
        ArrayList<Integer> setArray = new ArrayList<>(set);

        ArrayList<Integer> answer = new ArrayList<>();

        answer = counting(list, setArray);

        System.out.print(answer);
    }

    public static ArrayList<Integer> counting(ArrayList<Integer> list, ArrayList<Integer> setArray){
        if (setArray.size() == 1){
            return setArray;
        }
        else {
            int count1 = 0;
            int count2 = 0;
            for (int j = 0; j < list.size(); j++) {
                if (setArray.get(0) == list.get(j)){
                    count1++;
                }
                if (setArray.get(1) == list.get(j)){
                    count2++;
                }
            }
            if (count1 < count2) {
                setArray.remove(setArray.get(0));
                return counting(list, setArray);
            }
            else {
                setArray.remove(setArray.get(1));
                return counting(list, setArray);
            }
        }
    }
}