package Y2024.Day3;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import FileReading.FileReader;

public class Y2024DayThree {
    
    private static ArrayList<String> getContent() {
        return FileReader.getFileContentAsList("src/Y2024/Day3/day3.txt");
    }

    private static void addMatch(String str, String regex, ArrayList<String> list) {
        // Create a pattern and matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        
        // Iterate through all matches
        while (matcher.find()) {
            list.add(matcher.group());
        }    
    }

    public static void main(String[] args) {

        // PART 1

        ArrayList<String> mul = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();

        for (String str: getContent()) {
            addMatch(str, "mul\\(\\d+\\,\\d+\\)", mul);
        }

        int sum = 0;

        for (String fn: mul) {
            addMatch(fn, "\\d+", numbers);
            sum += Integer.parseInt(numbers.get(0)) * Integer.parseInt(numbers.get(1));
            numbers.clear();
        }

        System.out.println(sum);

        // PART 2

        sum = 0;
        mul.clear();
        numbers.clear();
        ArrayList<String> doInstructs = new ArrayList<>();

        String ins = getContent().toString();

        Pattern pattern = Pattern.compile("don't()|do()");
        Matcher matcher = pattern.matcher(ins);

        int prevStart = 0;
        
        while (matcher.find()) {
            
            String instruct = ins.substring(prevStart, matcher.start());  

            if (!instruct.startsWith("don't()")) {
                doInstructs.add(instruct);
            }

            prevStart = matcher.start();

        }
        
        if (!ins.substring(prevStart).startsWith("don't()")) {
            doInstructs.add(ins.substring(prevStart));
        }

        for (String doInstruct: doInstructs) {

            addMatch(doInstruct, "mul\\(\\d+\\,\\d+\\)", mul);
            
            for (String fn: mul) {
                addMatch(fn, "\\d+", numbers);
                sum += Integer.parseInt(numbers.get(0)) * Integer.parseInt(numbers.get(1));
                numbers.clear();
            }

            mul.clear();
        }

        System.out.println(sum);
    }

}
