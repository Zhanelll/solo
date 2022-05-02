public class Task_12_24_2 {

    public static void main(String[] args) {

        String[] words = new String[10];
        for (int i = 0; i < words.length; i++)
            words[i] = "Name" + i;
        MyList<String> list = new MyLinkedList<>(words);

        System.out.println(list.contains("Name0"));
        list.set(4, "Arantes Sa");
        System.out.println(list.indexOf("Luiz"));
        System.out.println(list.lastIndexOf("Name9"));
        System.out.println(list.get(5));
        System.out.println(list);
    }

}