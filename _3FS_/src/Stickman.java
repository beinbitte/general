import java.lang.Math;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Stickman {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String[] words = {"mehr worter","Was", "stepa", "Woche", "Schlecht","hallo","Wort","Decke","Mercedes benz"};
        int rand = (int) (Math.random() * words.length);

        start();

        String betterword = words[rand].toUpperCase();
        List<Character> usedCharacters = new ArrayList<>();
        List<Character> showplace = new ArrayList<>();

        List<Character> alphabet = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));        int tries = 10;

        int correct = 0;

        int rightg = 0;

        for (int i=0;i < betterword.length();i++) {
            if (betterword.charAt(i) == ' ') {
                showplace.add('_');
            }else {
                showplace.add('X');
            }
        }

        for (int i=0;i < betterword.length();i++){
            if (betterword.charAt(i) == ' ') {
                correct++;
            }
        }
        System.out.println("The word has " + (betterword.length() - correct) + " Letters");
        System.out.println(showplace);
        System.out.println("Words available: " + alphabet);
        while (correct != betterword.length()) {

            char input;

            do {
                try {
                    System.out.println("\nRight Guesses: "+rightg+" \n"+ "letters left: "+(betterword.length()-correct));
                    System.out.println("Tries left: " + tries + "\n");
                    System.out.println("Enter a Letter");
                    input = scan.nextLine().toUpperCase().charAt(0);

                    if (usedCharacters.contains(input)) {
                        System.out.println("No you already used it");
                    } else {
                        break;
                    }

                }catch(Exception e){
                    System.out.println("Try again");
                }
            } while (true);

            usedCharacters.add(input);
            for (Character usedCharacter : usedCharacters) {
                alphabet.remove(usedCharacter);
            }


            boolean wrong = false;

            if (betterword.contains(String.valueOf(input))) {
                System.out.println("CORRECT!!!\n");
            }

            for (int i=0;i < betterword.length();i++){
                if (betterword.charAt(i) == input && betterword.charAt(i) != ' ') {
                    System.out.println( "The character was at the spot " + (i+1));
                    showplace.set(i, input);
                    correct++;
                    rightg++;
                    wrong=true;
                }
            }

            if (!wrong) {
                tries--;
                System.out.println("WRONG!!!\n");
            }
            System.out.println(showplace);

            if (tries < 1){
                System.out.println("You Lost");
                System.out.println("The word is: "+words[rand]);
                System.exit(0);
            }else if (betterword.length() <= correct) {
                System.out.println("\nYOU WIN");
                System.out.println("The word is: "+words[rand]);
            }else {
                System.out.println("--------------------------");
                System.out.println("Words available: " + alphabet);
            }
        }
        scan.close();
        }
        public static void start(){
            System.out.println("                  STICKMAN");
            System.out.println("type:");
            System.out.println("Start");
            System.out.println("Quit");
            String start = scan.nextLine();
            if (start.equalsIgnoreCase("quit")){
                System.out.println("Bye");
                System.exit(0);
            } else if (!start.equalsIgnoreCase("start")) {
                start();
            }
        }
    }





