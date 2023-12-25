package Y2022.Day1;

import FileReading.FileReader;
import java.util.ArrayList;

public class Y2022DayOne {

    private static ArrayList<String> getContent() {
        return FileReader.getFileContentAsList("src/Y2022/Day1/day1.txt");
    }

    public static void main(String[] args) {
        int caloriesPerElf = 0;
        ArrayList<Integer> listOfCalories = new ArrayList<>();

        // PART 1

        for (Object calorie: getContent().toArray()) {
            if (!calorie.equals("")) {
                caloriesPerElf += Integer.parseInt(calorie.toString());
            }
            else {
                listOfCalories.add(caloriesPerElf);
                caloriesPerElf = 0;
            }
        }

        Integer maxCalories = listOfCalories.stream().max(Integer::compare).get();
        System.out.println(maxCalories);

        // PART TWO

        Integer topThree = maxCalories;

        int i = 0;
        while (i++ != 2) {
            listOfCalories.remove(maxCalories);
            maxCalories = listOfCalories.stream().max(Integer::compare).get();
            topThree += maxCalories;
        }

        System.out.println(topThree);
    }
}
