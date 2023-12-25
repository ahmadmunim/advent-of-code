package Y2022.Day3;

import FileReading.FileReader;

import java.util.*;

public class Y2022DayThree {
    private static ArrayList<String> getContent() {
        return FileReader.getFileContentAsList("src/Y2022/Day3/day3.txt");
    }

    public static void main(String[] args) {

        // PART 1

        HashMap<Character, Integer> itemValue = new HashMap<>();
        Character item = 'a';

        for (int i = 1; i <= 26; i++) {
            itemValue.put(item++, i);
        }

        item = 'A';
        for (int i = 27; i <= 52 ; i++) {
            itemValue.put(item++, i);
        }

        int sum = 0;

        for (Object itemLists: getContent()) {

            String firstHalf = itemLists.toString().substring(0, itemLists.toString().length() / 2);
            String secondHalf = itemLists.toString().substring(itemLists.toString().length() / 2);

            for (Character i: firstHalf.toCharArray()) {
                if (secondHalf.contains(i.toString())) {
                    sum += itemValue.get(i);
                    break;
                }
            }
        }

        System.out.println(sum);

        // PART 2

        sum = 0;

        for (int i = 0; i < getContent().size(); i += 3) {

            Set<Character> charSet1 = new HashSet<>();
            Set<Character> charSet2 = new HashSet<>();
            Set<Character> charSet3 = new HashSet<>();


            for (char c: getContent().get(i).toCharArray()) {
                charSet1.add(c);
            }

            for (char c: getContent().get(i+1).toCharArray()) {
                charSet2.add(c);
            }

            for (char c: getContent().get(i+2).toCharArray()) {
                charSet3.add(c);
            }

            charSet1.retainAll(charSet2);
            charSet1.retainAll(charSet3);

            sum += itemValue.get(charSet1.toArray()[0]);
        }

        System.out.println(sum);
    }
}
