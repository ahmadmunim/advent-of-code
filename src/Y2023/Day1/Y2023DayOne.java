package Y2023.Day1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import FileReading.FileReader;

public class Y2023DayOne {
    
    private static ArrayList<String> getContent() {
        return FileReader.getFileContent("src/Y2023/Day1/day1.txt");
    }
    public static void main(String[] args) {

        // PART 1
        
        int sum = 0;
        ArrayList<Character> digitsInLine = new ArrayList<>();
        String twoDigitNum = "";

        for (String str: getContent()) {
            
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i))) {
                    digitsInLine.add(str.charAt(i));
                }
            }
            
            if (digitsInLine.size() == 1) {
                twoDigitNum = String.valueOf(digitsInLine.get(0));
                twoDigitNum += String.valueOf(digitsInLine.get(0));
            }

            else {
                twoDigitNum = String.valueOf(digitsInLine.get(0));
                twoDigitNum += String.valueOf(digitsInLine.get(digitsInLine.size() -1));
            }

            sum += Integer.parseInt(twoDigitNum);
            digitsInLine.clear();
            twoDigitNum = "";
        }

        System.out.println(sum);

        // PART 2

        sum = 0;

        HashMap<String, Character> wordDigit = new HashMap<>();
        wordDigit.put("one", '1');
        wordDigit.put("two", '2');
        wordDigit.put("three", '3');
        wordDigit.put("four", '4');
        wordDigit.put("five", '5');
        wordDigit.put("six", '6');
        wordDigit.put("seven", '7');
        wordDigit.put("eight", '8');
        wordDigit.put("nine", '9');

        for (String str: getContent()) {
            
            int i = 0;
            boolean foundVal = false;
            while (i < str.length()) {

                // System.out.println(i);

                if (Character.isDigit(str.charAt(i))) {
                    digitsInLine.add(str.charAt(i));
                    i++;
                }

                else if (!Character.isDigit(str.charAt(i))) {
                    
                    if (i+3 <= str.length() && (str.substring(i, i+3).contains("one") | 
                        str.substring(i, i+3).contains("two") |
                        str.substring(i, i+3).contains("six"))) {
                            digitsInLine.add(wordDigit.get(str.substring(i, i+3)));
                            i = i+2;
                            foundVal = true;
                        }
                    
                    else if (i+5 <= str.length() && (str.substring(i, i+5).contains("three") |
                             str.substring(i, i+5).contains("seven") |
                             str.substring(i, i+5).contains("eight"))) {
                                digitsInLine.add(wordDigit.get(str.substring(i, i+5)));
                                i = i+4;
                                foundVal = true;
                             }

                    else if (i+4 <= str.length() && (str.substring(i, i+4).contains("four") |
                             str.substring(i, i+4).contains("five") |
                             str.substring(i, i+4).contains("nine"))) {
                                digitsInLine.add(wordDigit.get(str.substring(i, i+4)));
                                i = i+3;
                                foundVal = true;                                
                             }   

                    if (!foundVal) {
                        i++;
                    }
                }

                foundVal = false;
            }
            
            System.out.println(digitsInLine);

            if (digitsInLine.size() == 1) {
                twoDigitNum = String.valueOf(digitsInLine.get(0));
                twoDigitNum += String.valueOf(digitsInLine.get(0));
            }

            else {
                twoDigitNum = String.valueOf(digitsInLine.get(0));
                twoDigitNum += String.valueOf(digitsInLine.get(digitsInLine.size() -1));
            }

            sum += Integer.parseInt(twoDigitNum);
            digitsInLine.clear();
            twoDigitNum = "";
        }

        System.out.println(sum);
    }
}
