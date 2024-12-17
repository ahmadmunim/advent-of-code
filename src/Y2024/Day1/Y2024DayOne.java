package Y2024.Day1;

import java.util.ArrayList;
import java.util.Collections;

import FileReading.FileReader;

public class Y2024DayOne {

    private static ArrayList<String> getContent() {
        return FileReader.getFileContentAsList("src/Y2024/Day1/day1.txt");
    }
    public static void main(String[] args) {

        // PART 1
        
        ArrayList<Integer> leftCol = new ArrayList<>();
        ArrayList<Integer> rightCol = new ArrayList<>();

        for (String str: getContent()) {
            String[] entries = str.split("\\s+");

            leftCol.add(Integer.parseInt(entries[0]));
            rightCol.add(Integer.parseInt(entries[1]));
        }

        Collections.sort(leftCol);
        Collections.sort(rightCol);

        int sum = 0;
        
        for (int i = 0; i < leftCol.size(); i++) {
            sum += Math.abs(leftCol.get(i) - rightCol.get((i)));
        }

        System.out.println(sum);

        // PART 2

        sum = 0;

        for (Integer number: leftCol) {
            int frequency = Collections.frequency(rightCol, number);
            sum += number * frequency;
        }

        System.out.println(sum);
    }
}
