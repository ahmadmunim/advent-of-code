package Y2023.Day4;

import java.util.*;

import FileReading.FileReader;

public class Y2023DayFour {

    private static ArrayList<String> getContent() {
        return FileReader.getFileContent("Y2023/Day4/day4.txt");
    }
    public static void main(String[] args) {

        // PART 1
        
        int sum = 0;

        for (String line: getContent()) {
            String[] numberPairs = line.split(":|\\|");
            String[] winningNumbers = numberPairs[1].trim().split("\\s+");
            String[] yourNumbers = numberPairs[2].trim().split("\\s+");

            Set<String> winningNumbersSet = new HashSet<>(Arrays.asList(winningNumbers));
            Set<String> yourNumbersSet = new HashSet<>(Arrays.asList(yourNumbers));

            Set<String> intersection = new HashSet<String>(winningNumbersSet);
            intersection.retainAll(yourNumbersSet);

            int power = intersection.size() - 1;
            double points = Math.pow(2, power);
            sum += Math.floor(points); 

        }

        System.out.println(sum);

        // PART 2

        sum = 0;

        HashMap<Integer, Integer> matches = new HashMap<Integer, Integer>();

        for (int i = 0; i < getContent().size(); i++) {   
            matches.put(i + 1, 1);
        }

        for (int i = 0; i < getContent().size(); i++) {
            String[] numberPairs = getContent().get(i).split(":|\\|");
            String[] winningNumbers = numberPairs[1].trim().split("\\s+");
            String[] yourNumbers = numberPairs[2].trim().split("\\s+"); 

            Set<String> winningNumbersSet = new HashSet<>(Arrays.asList(winningNumbers));
            Set<String> yourNumbersSet = new HashSet<>(Arrays.asList(yourNumbers));

            Set<String> intersection = new HashSet<String>(winningNumbersSet);
            intersection.retainAll(yourNumbersSet);

            for (int j = 0; j < intersection.size(); j++) {
                int copies = matches.get(i + 1);
                matches.put(i + 1 + j + 1, matches.get(i + 1 + j + 1) + copies);
            }
        }

        sum = matches.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
}
