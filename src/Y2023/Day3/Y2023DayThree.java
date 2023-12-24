package Y2023.Day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

import FileReading.FileReader;

public class Y2023DayThree {
    
    private static ArrayList<String> getContent() {
        return FileReader.getFileContent("Y2023/Day3/day3.txt");
    }

    public static boolean checkAdjacentSymbols(int firstIndex, int lastIndex, String line) {
        if (firstIndex == 0 && lastIndex != line.length() - 1) {
            if (String.valueOf(line.charAt(lastIndex + 1)).matches("[^.]")) {
                return true;
            }
        }

        else if (firstIndex != 0 && lastIndex == line.length() - 1) {
            if (String.valueOf(line.charAt(firstIndex - 1)).matches("[^.]")) {
                return true;
            }
        }

        else {
            if (String.valueOf(line.charAt(firstIndex - 1)).matches("[^.]") | 
                String.valueOf(line.charAt(lastIndex + 1)).matches("[^.]")) {
                    return true;
            }
        }

        return false;
    }

    public static boolean checkTopOrBottomSymbols(int firstIndex, int lastIndex, String line) {
        
        if (firstIndex == 0 && lastIndex != line.length() - 1) {
            if (String.valueOf(line.charAt(firstIndex)).matches("[^.]") |
                String.valueOf(line.charAt(firstIndex + 1)).matches("[^.]") |
                String.valueOf(line.charAt(lastIndex)).matches("[^.]") | 
                String.valueOf(line.charAt(lastIndex - 1)).matches("[^.]") |
                String.valueOf(line.charAt(lastIndex + 1)).matches("[^.]")) {
                    return true;
            }

        }

        else if (firstIndex != 0 && lastIndex == line.length() - 1) {
            if (String.valueOf(line.charAt(firstIndex - 1)).matches("[^.]") | 
                String.valueOf(line.charAt(firstIndex)).matches("[^.]") |
                String.valueOf(line.charAt(firstIndex + 1)).matches("[^.]") |
                String.valueOf(line.charAt(lastIndex)).matches("[^.]") | 
                String.valueOf(line.charAt(lastIndex - 1)).matches("[^.]")) {
                    return true;
            }
        }

        else {
            if (String.valueOf(line.charAt(firstIndex - 1)).matches("[^.]") |
                String.valueOf(line.charAt(firstIndex)).matches("[^.]") | 
                String.valueOf(line.charAt(firstIndex + 1)).matches("[^.]") |
                String.valueOf(line.charAt(lastIndex - 1)).matches("[^.]") | 
                String.valueOf(line.charAt(lastIndex)).matches("[^.]") | 
                String.valueOf(line.charAt(lastIndex + 1)).matches("[^.]")) {
                return true;
            }
        }

        return false;
    }

    public static ArrayList<String> checkAdjacentNumbers(String line, int index) {

        ArrayList<String> numbersFound = new ArrayList<String>();

        String numberRegex = "\\d+";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            if (matcher.start() == index + 1 | matcher.end() - 1 == index - 1) {
                numbersFound.add(line.substring(matcher.start(), matcher.end()));
            }
        }

        return numbersFound;
    }

    public static ArrayList<String> checkTopOrBottomNumbers(String line, int index) {

        ArrayList<String> numbersFound = new ArrayList<String>();

        String numberRegex = "\\d+";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            if (matcher.start() == index + 1 | matcher.end() - 1 == index - 1 | 
                matcher.start() == index | matcher.end() - 1 == index | 
                matcher.start() == index - 1 | matcher.end() - 1 == index + 1) {
                    numbersFound.add(line.substring(matcher.start(), matcher.end()));
            }
        }

        return numbersFound;

    }

    public static void main(String[] args) {

        // PART 1
        
        int sum = 0;

        for (int i = 0; i < getContent().size(); i++) {

            String regex = "\\d+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(getContent().get(i));

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String number = getContent().get(i).substring(start, end);

                if (i == 0) {
                    boolean isSymbolAdjecent = checkAdjacentSymbols(start, end - 1, getContent().get(i));
                    boolean isSymbolBottom  = checkTopOrBottomSymbols(start, end - 1, getContent().get(i + 1));

                    if (isSymbolAdjecent | isSymbolBottom) {
                        sum += Integer.parseInt(number);
                    }
                }

                else if (i == getContent().size() - 1) {
                    boolean isSymbolAdjecent = checkAdjacentSymbols(start, end - 1, getContent().get(i));
                    boolean isSymbolTop  = checkTopOrBottomSymbols(start, end - 1, getContent().get(i - 1));

                    if (isSymbolAdjecent | isSymbolTop) {
                        sum += Integer.parseInt(number);
                    }                    
                }

                else {
                    boolean isSymbolAdjecent = checkAdjacentSymbols(start, end - 1, getContent().get(i));
                    boolean isSymbolTop  = checkTopOrBottomSymbols(start, end - 1, getContent().get(i - 1));
                    boolean isSymbolBottom  = checkTopOrBottomSymbols(start, end - 1, getContent().get(i + 1));

                    if (isSymbolAdjecent | isSymbolTop | isSymbolBottom) {
                        sum += Integer.parseInt(number);
                    }                      
                }
            }
        }

        System.out.println(sum);

        // PART 2

        sum = 0;

        for (int i = 0; i < getContent().size(); i++) {
            String asteriskRegex = "\\*";
            Pattern pattern = Pattern.compile(asteriskRegex);
            Matcher matcher = pattern.matcher(getContent().get(i));

            while (matcher.find()) {
                int start = matcher.start();

                ArrayList<String> adjacentNumbers = checkAdjacentNumbers(getContent().get(i), start);
                ArrayList<String> topAndBottomNumbers = new ArrayList<String>();

                int product = 1;

                
                if (i != 0 & i != getContent().size() - 1) {
                    topAndBottomNumbers = checkTopOrBottomNumbers(getContent().get(i - 1), start);
                    topAndBottomNumbers.addAll(checkTopOrBottomNumbers(getContent().get(i + 1), start));
                }

                if (adjacentNumbers.size() == 2) {
                    for (String num: adjacentNumbers) {
                        product *= Integer.parseInt(num);
                    }
                }

                else if (topAndBottomNumbers.size() == 2) {
                    for (String num: topAndBottomNumbers) {
                        product *= Integer.parseInt(num);
                    }
                }

                else if (adjacentNumbers.size() == 1 & topAndBottomNumbers.size() == 1) {
                    product *= Integer.parseInt(adjacentNumbers.get(0));
                    product *= Integer.parseInt(topAndBottomNumbers.get(0));
                }

                else {
                    product = 0;
                }

                sum += product;
            }
        }

        System.out.println(sum);
        
    }
}
