package Y2024.Day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import FileReading.FileReader;

public class Y2024DayTwo {
    
    private static ArrayList<String> getContent() {
        return FileReader.getFileContentAsList("src/Y2024/Day2/day2.txt");
    }

    private static boolean isSafe(List<String> report) {
          
        boolean increasing = false;
        boolean decreasing = false;

        for (int i = 1; i < report.size(); i++) {
            if (Math.abs(Integer.parseInt(report.get(i)) - Integer.parseInt(report.get(i-1))) < 1 || 
                Math.abs(Integer.parseInt(report.get(i)) - Integer.parseInt(report.get(i-1))) > 3) {
                    return false;
            }

            if (Integer.parseInt(report.get(i)) - Integer.parseInt(report.get(i-1)) < 0) {
                increasing = true;
            } else {
                decreasing = true;
            }

            if (increasing && decreasing) {
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {

        // PART 1
        
        int safeReports = 0;
        ArrayList<List<String>> unsafeReportsList = new ArrayList<>();

        for (String str: getContent()) {
            List<String> report = new ArrayList<>(Arrays.asList(str.split(" ")));
            
            if (isSafe(report)) {
                safeReports += 1;
            } else {
                unsafeReportsList.add(report);
            }
        }

        System.out.println(safeReports);

        // PART 2

        OUTER: for (List<String> unsafeReport: unsafeReportsList) {
            
            for (int i = 0; i < unsafeReport.size(); i++) {
                
                String elementRemoved = unsafeReport.remove(i);
                
                if (isSafe(unsafeReport)) {
                    safeReports += 1;
                    unsafeReport.add(i, elementRemoved);
                    continue OUTER;
                }

                unsafeReport.add(i, elementRemoved);
            }
        }

        System.out.println(safeReports);

    }
}
