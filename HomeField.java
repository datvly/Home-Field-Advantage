import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

 /**
 * Analysis of results
 *
  mscc06.txt
  **********   College Men's Soccer --- 2006   **********

  HOME FIELD ADVANTAGE RESULTS

  Total number of games: 8,380
  Number of games with a home team: 7,373
  Percentage of games with a home team: 88.0%
  Number of games the home team won: 3,962
  Home team win percentage: 53.7%
  Home team average margin: 0.51

  wscc10.txt
  **********   NCAA Women's Soccer --- 2010   **********

  HOME FIELD ADVANTAGE RESULTS

  Total number of games: 10,593
  Number of games with a home team: 9,941
  Percentage of games with a home team: 93.8%
  Number of games the home team won: 5,392
  Home team win percentage: 54.2%
  Home team average margin: 0.51

  mlb12.txt
  **********   Major League Baseball --- 2012   **********

  HOME FIELD ADVANTAGE RESULTS

  Total number of games: 2,467
  Number of games with a home team: 2,465
  Percentage of games with a home team: 99.9%
  Number of games the home team won: 1,312
  Home team win percentage: 53.2%
  Home team average margin: 0.16

  wbb14.txt
  **********   NCAA Women's Basketball --- 2013 - 2014   **********

  HOME FIELD ADVANTAGE RESULTS

  Total number of games: 15,790
  Number of games with a home team: 14,305
  Percentage of games with a home team: 90.6%
  Number of games the home team won: 8,471
  Home team win percentage: 59.2%
  Home team average margin: 4.24

  ---------------------------------------------------------------------------------------------

  I have organized the data in chronological order for better trend analysis.
  Based on the result of the data from the pass years and different sport,
  I noticed there is are indications that show that home field has an advantage in sport over the visitor team.

  Across the thousands of games, the home team kept a consistence of over 50% win percentage.
  This carries over through 2006 to 2014 including multiple sports.
  Furthermore, the average margins of the score significantly stands out as the ratio is unfairly large.

  Even with the lower ones that are below the margin of 1, the percentage of games with home team are nearly 99%,
  and if not, they are at least 90% or more, which are both very significant percentages.
  It seems like throughout the years,
  the margin get increasingly unfair for the visitor. This is true for all the sports,
  especially in the Women's basketball for 2013 to 2014 as their margin is over 4,
  which is so much more when the margin is supposed to be nearly 0.

 */


 //The program analysis the data file of different sport games.
 //It will then print out the results of each category
 //then let the user determine if there is a home game advantage.
public class HomeField {
     public static final int PERCENTAGE = 100;

    // Ask the user for the name of a data file and process
    // it until they want to quit.
    public static void main(String[] args) throws IOException {
        System.out.println("A program to analyze home field advantage in sports.");
        Scanner keyboard = new Scanner(System.in);
        multipleFileProcessing(keyboard);
        keyboard.close();
    }


     // Ask the user for the file they want to be read.
     public static String getFileName (Scanner keyboard) {
         System.out.println();
         System.out.print("Enter the file name: ");
         String fileName = keyboard.nextLine();
         File file = new File(fileName);
         while (!file.exists()) {
             System.out.println("Sorry, that file does not exist");
             System.out.print("Enter the file name: ");
             fileName = keyboard.nextLine();
             file = new File(fileName);
         }
         return fileName;
     }


     //The core of the program that processes a single file
     //and printing its result from the endResult method.
     public static void singleFileProcessing(Scanner fileScanner) {
         gameLabel (fileScanner);
         int totalGame = 0;
         int numHomeGames = 0;
         int numHomeTeamWon = 0;
         int totalMargin = 0;
         while (fileScanner.hasNextLine()) {   //if it has the first letter then it will add to the total
             String line = fileScanner.nextLine();
             totalGame++;
             if (line.contains("@")) {  // check if there is an @
                 numHomeGames++; //increment the counter
             }
             Scanner lineScanner  = new Scanner(line);
             int margin = numHomeTeamWon(lineScanner);
             if (margin > 0) {   //Determine if the home team won
                 numHomeTeamWon++;
             }
             totalMargin += margin;
         }
         endResult(numHomeGames, totalGame, numHomeTeamWon, totalMargin);
     }


     //Prints out the top label of each file showing what type of games
     //along with the date of the data
     public static void gameLabel (Scanner fileScanner) {
         String typeOfGame = fileScanner.nextLine();
         String date = fileScanner.nextLine();
         System.out.println();
         System.out.println("**********   " + typeOfGame + " --- " + date + "   **********");
         System.out.println();
         System.out.println("HOME FIELD ADVANTAGE RESULTS");
         System.out.println();
     }


    //Calculating the winner of each game within the data
    public static int numHomeTeamWon (Scanner fileScanner) {
        fileScanner.next();   //Skip the date
        String team1Name = getTeam(fileScanner);
        int team1 = fileScanner.nextInt();
        String team2Name = getTeam(fileScanner);
        int team2 = fileScanner.nextInt();
        int winner = 0;
        if (team1Name.contains("@")) {
            winner = team1 - team2;
        } if (team2Name.contains("@")) {
            winner = team2 - team1;
        }
        return winner;
    }


     //Obtain the team's score
     public static String getTeam(Scanner fileScanner) {
        String team = "";
        while (!fileScanner.hasNextInt()) {
             team += fileScanner.next();
        }
        return team;
     }


     //Ask the user if they want to check another data set base on the user input.
     //If the user wants to check another file, the while loop will continue.
     public static void multipleFileProcessing(Scanner keyboard) throws FileNotFoundException {
         boolean anotherDataSet = true;
         while (anotherDataSet) {
             String fileName = getFileName (keyboard);
             Scanner fileScanner = new Scanner (new File (fileName));
             singleFileProcessing(fileScanner);
             System.out.println ();
             System.out.println("Do you want to check another data set?");
             System.out.print("Enter Y or y to analyze another file, anything else to quit: ");
             String endFile = keyboard.nextLine().toLowerCase();  //Error with the keyboard.nextLine()
             anotherDataSet = endFile.equals("y");
         }
     }


     // Prints out the result of each of the methods above with the necessary formatting
     public static void endResult (int numHomeGames, int totalGame, int numHomeTeamWon, int totalMargin) {
         System.out.printf("Total number of games: %,d\n" , totalGame);
         System.out.printf("Number of games with a home team: %,d\n" , numHomeGames);
         System.out.printf("Percentage of games with a home team: %.1f%%\n" , numHomeGames /
                                                                            (double) totalGame * PERCENTAGE);
         System.out.printf("Number of games the home team won: %,d\n" , numHomeTeamWon);
         System.out.printf("Home team win percentage: %.1f%%\n" , numHomeTeamWon / (double) numHomeGames * PERCENTAGE);
         System.out.printf("Home team average margin: %.2f\n", totalMargin / (double) numHomeGames);
     }
}
