package Y2022.Day5;

import FileReading.FileReader;

import java.util.*;

public class Y2022DayFive {

    private static ArrayList<String> getContent() {
        return FileReader.getFileContentAsList("src/Y2022/Day5/day5.txt");
    }

    public static void main(String[] args) {

        // PART 1

        Stack<Character> crate1 = getStack("NCRTMZP");
        Stack<Character> crate2 = getStack("DNTSBZ");
        Stack<Character> crate3 = getStack("MHQRFCTG");
        Stack<Character> crate4 = getStack("GRZ");
        Stack<Character> crate5 = getStack("ZNRH");
        Stack<Character> crate6 = getStack("FHSWPZLD");
        Stack<Character> crate7 = getStack("WDZRCGM");
        Stack<Character> crate8 = getStack("SJFLHWZQ");
        Stack<Character> crate9 = getStack("SQPWN");

        HashMap<Integer, Stack<Character>> crates = new HashMap<>();
        crates.put(1, crate1);
        crates.put(2, crate2);
        crates.put(3, crate3);
        crates.put(4, crate4);
        crates.put(5, crate5);
        crates.put(6, crate6);
        crates.put(7, crate7);
        crates.put(8, crate8);
        crates.put(9, crate9);

        for (String instruction : getContent()) {

            Scanner scanner = new Scanner(instruction);

            scanner.useDelimiter("[^0-9]+");
            ArrayList<Integer> instructionValues = new ArrayList<>();

            while (scanner.hasNextInt()) {
                instructionValues.add(scanner.nextInt());
            }

            for (int i = 0; i < instructionValues.get(0); i++) {
                Character crateToRemove = crates.get(instructionValues.get(1)).pop();
                crates.get(instructionValues.get(2)).add(crateToRemove);
            }
        }

        for (Object value : crates.values()) {
            System.out.println(value);
        }

        // PART 2

        crate1.clear();
        crate2.clear();
        crate3.clear();
        crate4.clear();
        crate5.clear();
        crate6.clear();
        crate7.clear();
        crate8.clear();
        crate9.clear();

        crates.clear();

        crate1 = getStack("NCRTMZP");
        crate2 = getStack("DNTSBZ");
        crate3 = getStack("MHQRFCTG");
        crate4 = getStack("GRZ");
        crate5 = getStack("ZNRH");
        crate6 = getStack("FHSWPZLD");
        crate7 = getStack("WDZRCGM");
        crate8 = getStack("SJFLHWZQ");
        crate9 = getStack("SQPWN");

        crates.put(1, crate1);
        crates.put(2, crate2);
        crates.put(3, crate3);
        crates.put(4, crate4);
        crates.put(5, crate5);
        crates.put(6, crate6);
        crates.put(7, crate7);
        crates.put(8, crate8);
        crates.put(9, crate9);

        for (String instruction : getContent()) {

            Scanner scanner = new Scanner(instruction);

            scanner.useDelimiter("[^0-9]+");
            ArrayList<Integer> instructionValues = new ArrayList<>();

            while (scanner.hasNextInt()) {
                instructionValues.add(scanner.nextInt());
            }

            Stack<Character> temp = new Stack<>();

            for (int i = 0; i < instructionValues.get(0); i++) {
                temp.add(crates.get(instructionValues.get(1)).pop());
            }

            for (int i = 0; i < instructionValues.get(0); i++) {
                Character crateToRemove = temp.pop();
                crates.get(instructionValues.get(2)).add(crateToRemove);
            }
        }

        for (Object value : crates.values()) {
            System.out.println(value);
        }

    }

    public static Stack<Character> getStack(String values) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < values.length(); i++) {
            stack.add(values.charAt(i));
        }

        return stack;
    }
}
