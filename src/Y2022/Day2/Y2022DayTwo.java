package Y2022.Day2;

import FileReading.FileReader;

import java.util.ArrayList;

public class Y2022DayTwo {

    private static ArrayList<String> getContent() {
        return FileReader.getFileContentAsList("src/Y2022/Day2/day2.txt");
    }

    public static void main(String[] args) {
        // A, X -> rock 1
        // B, Y -> paper 2
        // C, Z -> scissors 3

        // win -> 6
        // draw -> 3
        // lose -> 0

        // PART 1

        Integer totalScore = 0;

        for (Object match: getContent().toArray()) {
            switch(match.toString().charAt(2)) {
                case 'X':
                    totalScore += match.toString().charAt(0) == 'A'? 4: match.toString().charAt(0) == 'B'? 1: 7;
                    break;
                case 'Y':
                    totalScore += match.toString().charAt(0) == 'A'? 8: match.toString().charAt(0) == 'B'? 5: 2;
                    break;
                case 'Z':
                    totalScore += match.toString().charAt(0) == 'A'? 3: match.toString().charAt(0) == 'B'? 9: 6;
                    break;
            }
        }

        System.out.println(totalScore);
        totalScore = 0;

        // PART 2

        // X -> lose
        // Y -> Draw
        // Z -> Win

        for (Object match: getContent().toArray()) {
            switch(match.toString().charAt(2)) {
                case 'X':
                    totalScore += match.toString().charAt(0) == 'A'? 3: match.toString().charAt(0) == 'B'? 1: 2;
                    break;
                case 'Y':
                    totalScore += match.toString().charAt(0) == 'A'? 4: match.toString().charAt(0) == 'B'? 5: 6;
                    break;
                case 'Z':
                    totalScore += match.toString().charAt(0) == 'A'? 8: match.toString().charAt(0) == 'B'? 9: 7;
                    break;
            }
        }

        System.out.println(totalScore);
    }
}
