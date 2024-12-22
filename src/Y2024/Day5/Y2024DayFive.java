package Y2024.Day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import FileReading.FileReader;

public class Y2024DayFive {

    private static ArrayList<String> getContent() {
        return FileReader.getFileContentAsList("src/Y2024/Day5/day5.txt");
    }

    private static boolean isOrdered(List<String> update, ArrayList<ArrayList<String>> pagePairs) {
        
        for (List<String> pagePair: pagePairs) {
            if (update.contains(pagePair.get(0)) && update.contains(pagePair.get(1))) {
                if (update.indexOf(pagePair.get(0)) > update.indexOf(pagePair.get(1))) {
                    return false;
                }
            }
        }

        return true;
    }

    private static ArrayList<String> sortPages(HashMap<String, Integer> frequencies) {
        
        ArrayList<String> sortedList = new ArrayList<>();
        
        List<Integer> frequenciesList = frequencies.values().stream().collect(Collectors.toList());
        Collections.sort(frequenciesList);

        for (Integer frequencyValue: frequenciesList) {
            for (Entry<String, Integer> entry: frequencies.entrySet()) {
                if (entry.getValue().equals(frequencyValue)) {
                    sortedList.add(entry.getKey());
                }
            }
        }

        return sortedList;
    }

    public static void main(String[] args) {
        
        ArrayList<String> input = getContent();
        ArrayList<String> rules = new ArrayList<>();
        
        for (String str: getContent()) {
            if (str.equals("")) {
                break;
            } else {
                rules.add(str);
            }
        }

        ArrayList<List<String>> updates = new ArrayList<>();
        
        for (int i = input.indexOf("") + 1; i < input.size(); i++) {
            updates.add(Arrays.asList(input.get(i).split(",")));
        }

        ArrayList<ArrayList<String>> pagePairsList = new ArrayList<>();

        for (String pagePairs: rules) {
            
            ArrayList<String> pagePair = new ArrayList<>();
            
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(pagePairs);
            
            // Iterate through all matches
            while (matcher.find()) {
                pagePair.add(matcher.group());
            }

            pagePairsList.add(pagePair);
        }

        int sumOrdered = 0;
        int sumUnordered = 0;

        for (List<String> update: updates) {
            
            if (isOrdered(update, pagePairsList)) {

                // PART 1
                sumOrdered += Integer.parseInt(update.get(update.size()/2));

            } else {

                // PART 2
                List<ArrayList<String>> filteredPagePairs = pagePairsList.stream()
                                        .filter(pair -> update.contains(pair.get(0)) && update.contains(pair.get(1)))
                                        .collect(Collectors.toList());

                ArrayList<String> firstPages = new ArrayList<>(); 
                filteredPagePairs.forEach(pair -> firstPages.add(pair.get(0)));

                HashMap<String, Integer> frequencies = new HashMap<>();
                
                for (String number: update) {
                    int frequency = Collections.frequency(firstPages, number);
                    frequencies.put(number, frequency);
                }

                ArrayList<String> orderedPages = sortPages(frequencies);
                sumUnordered += Integer.parseInt(orderedPages.get(orderedPages.size()/2));
            }
        }

        System.out.println(sumOrdered);
        System.out.println(sumUnordered);
    }
}
