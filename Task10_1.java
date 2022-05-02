import java.util.*;
import java.io.*;

public class Task10_1 {
	public static void main(String[] args) throws Exception {

		List<String> list = new ArrayList<>();
		if (args.length != 1) {
			System.out.println("File is not choosen!");
			System.exit(1);
		}

	
		File textFile = new File(args[0]);
		if (!textFile.exists()) {
			PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File("Exercise20_1.txt"), true));
			list.add("black");
	        list.add("funny");
	        list.add("amazing");
	        list.add("start");
	        list.add("finish");
			printWriter.print(list);
			Collections.sort(list);
			System.out.println(list);
		}
			else{
				try (
			
			Scanner input = new Scanner(textFile);
		) {
			while (input.hasNext()) {
				String[] array = input.nextLine().split(" ");
				for (int i = 0; i < array.length; i++) {
					if (array[i].length() > 0 && 
						 Character.isLetter(array[i].charAt(0))) {
						list.add(array[i]);
					}
				}
			}
		}
		Collections.sort(list);
		System.out.println(list);
		}

	}
}