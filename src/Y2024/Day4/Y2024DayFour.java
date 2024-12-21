package Y2024.Day4;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import FileReading.FileReader;

public class Y2024DayFour {
    
    private static ArrayList<String> getContent() {
        return FileReader.getFileContentAsList("src/Y2024/Day4/day4.txt");
    }

    private static ArrayList<String> getTranspose(ArrayList<String> matrix) {
        
        int c = 0;
        ArrayList<String> matrixTranspose = new ArrayList<>();
        
        for (String row: matrix) {
            for (int i = 0; i < row.length(); i++) {
                if (c == 0) {
                    matrixTranspose.add(Character.toString(row.charAt(i)));
                } else {
                    matrixTranspose.set(i, matrixTranspose.get(i) + Character.toString(row.charAt(i)));
                } 
            }
            c++;
        }     
        
        return matrixTranspose;
    }

    private static ArrayList<String> getReverseMatrix(ArrayList<String> matrix) {
        
        ArrayList<String> reversedMatrix = new ArrayList<>();

        for (String row: matrix) {
            
            String reversedRow = "";
            
            for (int col = row.length() - 1; col >= 0; col--) {
                reversedRow = reversedRow + Character.toString(row.charAt(col));
            }

            reversedMatrix.add(reversedRow);
        }

        return reversedMatrix;
    }

    private static ArrayList<String> getDiagonals(ArrayList<String> matrix) {
        
        ArrayList<String> diagonals = new ArrayList<>();
        
        for (int i = 0; i < matrix.size(); i++) {
            diagonals.add(getDiagonal(matrix, i, false));
        }

        ArrayList<String> matrixTranspose = getTranspose(matrix);
        
        for (int i = 1; i < matrix.size(); i++) {
            diagonals.add(getDiagonal(matrixTranspose, i, false));
        }

        return diagonals;
    }

    private static String getDiagonal(ArrayList<String> matrix, int startingPoint, boolean flipped) {
        
        String diagonal = "";

        if (!flipped) {
            for (int row = 0; row < matrix.size(); row++) {
                for (int col = startingPoint; col < matrix.size(); col++) {
                    if (startingPoint == col - row) {
                        diagonal = diagonal + Character.toString(matrix.get(row).charAt(col));
                    }
                }
            }            
        }
        
        else {
            for (int row = 0; row < matrix.size(); row++) {
                for (int col = startingPoint; col >= 0; col--) {
                    if (row == startingPoint - col) {
                        diagonal = diagonal + Character.toString(matrix.get(row).charAt(col));
                    }
                }
            }
        }

        return diagonal;

    }

    private static int horizontalSearch(ArrayList<String> matrix) {
        
        int matches = 0;

        Pattern pattern = Pattern.compile("XMAS|SAMX");
        
        for (String row: matrix) {
            Matcher matcher = pattern.matcher(row);
        
            for (int i = 0; i < row.length(); i++) {
                matcher.region(i, row.length()); // Adjust the starting position
                if (matcher.lookingAt()) { // Check if there's a match at the current position
                    matches += 1;
                }
            }
        }

        return matches;
    }

    private static int verticalSearch(ArrayList<String> matrix) {
        
        ArrayList<String> matrixTranspose = getTranspose(matrix);
        return horizontalSearch(matrixTranspose);

    }

    private static int diagonalSearch(ArrayList<String> matrix) {
        
        
        ArrayList<String> diagonals = getDiagonals(matrix);
        
        int matches = 0;

        Pattern pattern = Pattern.compile("XMAS|SAMX");
        
        for (String diagonal: diagonals) {
            if (diagonal.length() >= 4) {
                Matcher matcher = pattern.matcher(diagonal);
        
                for (int i = 0; i < diagonal.length(); i++) {
                    matcher.region(i, diagonal.length());
                    if (matcher.lookingAt()) {
                        matches += 1;
                    }
                }
            }
        }

        return matches;        
    }

    public static void main(String[] args) {
        
        // PART 1
        
        ArrayList<String> matrix = getContent();
        ArrayList<String> reversedMatrix = getReverseMatrix(matrix);

        int matches = verticalSearch(matrix) + horizontalSearch(matrix) + diagonalSearch(matrix) + diagonalSearch(reversedMatrix);
        System.out.println(matches); 

    }
}
