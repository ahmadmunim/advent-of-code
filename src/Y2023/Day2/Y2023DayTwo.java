package Y2023.Day2;

import java.util.ArrayList;
import java.util.Collections;

import FileReading.FileReader;

public class Y2023DayTwo {
    private static ArrayList<String> getContent() {
        return FileReader.getFileContent("src/Y2023/Day2/day2.txt");
    }

    public static void main(String[] args) {

        // PART 1
        
        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;

        int sum = 0;

        OUTER: for (String str: getContent()) {
            
            String[] games = str.split(":|;");
            String gameNo = games[0];

            for (int i = 1; i < games.length; i++) {
                
                String[] setCubes = games[i].split(",");
                
                for (String cube: setCubes) {
                    
                    String noOfCube = cube.replaceAll("[^0-9]", "");
                    String cubeColour = cube.replaceAll("[0-9]", "").trim();
                    
                    if (cubeColour.equals("red")) {
                        if (Integer.parseInt(noOfCube) > maxRed) {
                            continue OUTER;
                        }
                    } 

                    else if (cubeColour.equals("green")) {
                        if (Integer.parseInt(noOfCube) > maxGreen) {
                            continue OUTER;
                        }
                    }

                    else {
                        if (Integer.parseInt(noOfCube) > maxBlue) {
                            continue OUTER;
                        }
                    }
                }

            }

            gameNo = gameNo.replaceAll("[^0-9]", "");
            sum += Integer.parseInt(gameNo);

        }

        System.out.println(sum);

        sum = 0;

        // PART 2

        ArrayList<Integer> noOfRedsEachSet = new ArrayList<>();
        ArrayList<Integer> noOfGreensEachSet = new ArrayList<>();
        ArrayList<Integer> noOfBluesEachSet = new ArrayList<>();
        
        for (String str: getContent()) {
            
            String[] games = str.split(":|;");

            for (int i = 1; i < games.length; i++) {
                
                String[] setCubes = games[i].split(",");
                
                for (String cube: setCubes) {
                    
                    String noOfCube = cube.replaceAll("[^0-9]", "");
                    String cubeColour = cube.replaceAll("[0-9]", "").trim();

                    if (cubeColour.equals("red")) {
                        noOfRedsEachSet.add(Integer.parseInt(noOfCube));
                    }

                    else if (cubeColour.equals("green")) {
                        noOfGreensEachSet.add(Integer.parseInt(noOfCube));
                    }

                    else {
                        noOfBluesEachSet.add(Integer.parseInt(noOfCube));
                    }                    
                    
                }

            }

            int maxRedInGame = Collections.max(noOfRedsEachSet);
            int maxBlueInGame = Collections.max(noOfBluesEachSet);
            int maxGreenInGame = Collections.max(noOfGreensEachSet);

            int product = maxRedInGame * maxBlueInGame * maxGreenInGame;
            sum += product;

            noOfRedsEachSet.clear();
            noOfBluesEachSet.clear();
            noOfGreensEachSet.clear();

        }

        System.out.println(sum);

    }
}
