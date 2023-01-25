package Y2022.Day6;

import FileReading.FileReader;

import java.util.*;

public class Y2022DaySix {

    private static ArrayList<String> getContent() {
        return FileReader.getFileContent("src/Y2022/Day6/day6.txt");
    }

    public static void main(String[] args) {

        Set<Character> uniqueLetters = new LinkedHashSet<>();

        int marker = 0;
        int index = 0;

        String characters = getContent().get(0);

        char[] chars = characters.toCharArray();
        System.out.println(Arrays.toString(chars));

        for (char letter: chars) {

            if (uniqueLetters.contains(letter)) {
                int indexOfLetter = uniqueLetters.stream().toList().indexOf(letter);
                List<Character> toRemove = uniqueLetters.stream().toList().subList(0, indexOfLetter + 1);
                uniqueLetters.removeAll(toRemove);
                marker = uniqueLetters.size();
            }

            uniqueLetters.add(letter);
            marker++;

            if (marker == 14) {
                System.out.println(index + 1);
                break;
            }

            index++;
        }

    }
}
