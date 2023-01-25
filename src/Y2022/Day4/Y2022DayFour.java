package Y2022.Day4;

import FileReading.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Y2022DayFour {

    private static ArrayList<String> getContent() {
        return FileReader.getFileContent("src/Y2022/Day4/day4.txt");
    }

    public static void main(String[] args) {

        // PART 1

        int duplicates = 0;

        for (String range: getContent()) {

            int commaIndex = range.indexOf(',');

            String range1 = range.substring(0,commaIndex);
            String range2 = range.substring(commaIndex+1);

            int dashIndex1 = range1.indexOf('-');
            int dashIndex2 = range2.indexOf('-');

            Set<String> range1Set = new HashSet<>();

            for (int i = Integer.parseInt(range1.substring(0,dashIndex1)); i <= Integer.parseInt(range1.substring(dashIndex1+1)); i++) {
                range1Set.add(Integer.toString(i));
            }

            Set<String> range2Set = new HashSet<>();

            for (int i = Integer.parseInt(range2.substring(0,dashIndex2)); i <= Integer.parseInt(range2.substring(dashIndex2+1)); i++) {
                range2Set.add(Integer.toString(i));
            }

            if (range1Set.containsAll(range2Set) || range2Set.containsAll(range1Set)) {
                duplicates++;
            }
        }

        System.out.println(duplicates);

        // PART 2

        int overlaps = 0;

        for (String range: getContent()) {

            int commaIndex = range.indexOf(',');

            String range1 = range.substring(0,commaIndex);
            String range2 = range.substring(commaIndex+1);

            int dashIndex1 = range1.indexOf('-');
            int dashIndex2 = range2.indexOf('-');

            Set<String> range1Set = new HashSet<>();

            for (int i = Integer.parseInt(range1.substring(0,dashIndex1)); i <= Integer.parseInt(range1.substring(dashIndex1+1)); i++) {
                range1Set.add(Integer.toString(i));
            }

            Set<String> range2Set = new HashSet<>();

            for (int i = Integer.parseInt(range2.substring(0,dashIndex2)); i <= Integer.parseInt(range2.substring(dashIndex2+1)); i++) {
                range2Set.add(Integer.toString(i));
            }

            range1Set.retainAll(range2Set);

            if (!range1Set.isEmpty()) {
                overlaps++;
            }

        }

        System.out.println(overlaps);
    }
}
