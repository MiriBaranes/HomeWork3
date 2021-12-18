import java.util.Random;
import java.util.Scanner;

public class Q9 {
    public static final int EASY_ROUTE=1;
    public static final int EASY_TRACK_TORN =20;
    public static final int MEDIUM_ROUTE=2;
    public static final int MEDIUM_TRACK_TORN =15;
    public static final int HARD_ROUTE=3;
    public static final int HARD_TRACK_TORN =10;
    public static final int SURPRISING_ROUTE=4;
    public static final int THE_BIGGEST_NUMBER_OPTION=6;
    public static final int THE_SMALLER_NUMBER_OPTION=1;
    public static final int PASSWORD_LENGTH=4;
    public static final String WINN_STRING ="You won!";

    public static void main(String[] args) {
        play();

        }
    public static void play (){
        printInstructionsGame();
        int selectedRoute=menuTurnOption();
        int counterTorn = getTornCount(selectedRoute);
        int [] numberDrew = lotterySecretNumber();
        int [] arrUserGuss;
            while (counterTorn>0){
                arrUserGuss=userChoice();
                String guessingMessage= correctAndPartialGuessesMessage(numberDrew,arrUserGuss);
                System.out.println(guessingMessage);
                if (isEquals(guessingMessage,WINN_STRING)){
                    break;
                }
                int duplicates = duplicatesOfTheUserChoice(arrUserGuss);
                counterTorn=turnNumberLeft(selectedRoute,duplicates,counterTorn);
            }
            if (counterTorn==0){
                System.out.println("Dear user, you did not succeed in the task\n"+
                        "The secret number is: ");
                printArr(numberDrew);
            }
        }
        public static void printArr (int[] arr){
        for (int i=0; i< arr.length; i++){
            System.out.print(arr[i]);
        }
        }
        public static int [] userChoice (){
        Scanner scanner= new Scanner(System.in);
            System.out.println("Enter your guess :");
            int guessUser=scanner.nextInt();
            int []arrUserGuss= fromIntToArr(guessUser);
            if (!validityNumberFromUser(arrUserGuss)){
                System.out.println("The number that the arc does not meet the specified conditions");
                arrUserGuss= userChoice();
            }
        return arrUserGuss;
        }
        public static int turnNumberLeft(int selectedRoute, int duplicates,int counterTorn){
            if (duplicates>0){
                System.out.println("You have chosen a number twice - meaning you have broken a law, so you get 2 turns off the turn quota");
                counterTorn-=3;
            }
            else if (duplicates==0){
                counterTorn--;
            }
            if (selectedRoute!=4){
                System.out.println(" You have "+counterTorn+ " turns left");
            }
            else {
                System.out.println("continued: ");
            }
        return counterTorn;
        }

    public static boolean validityNumberFromUser(int [] userChoice){
        boolean inputValidity =false;
        int count=0;
        if (userChoice.length==PASSWORD_LENGTH){
            for (int i=0; i< userChoice.length; i++){
                if (userChoice[i]<=THE_BIGGEST_NUMBER_OPTION && userChoice[i]>=THE_SMALLER_NUMBER_OPTION){
                    count++;
                }
            }
        }
        if (count==userChoice.length && userChoice.length==PASSWORD_LENGTH){
            inputValidity=true;
        }
        return inputValidity;
    }

    public static int menuTurnOption(){
        Scanner scanner= new Scanner(System.in);
        int choiceRoute;
        System.out.println("\n 1- Easy Track - 20 Attempts Press 1\n"+
        "2- Intermediate track - 15 attempts to press 2\n"+
        "3- Hard track - 10 attempts to press 3\n"+
        "4- Surprise track - between 5 and 25 attempts to press 4");
        System.out.println("Enter your choice: ");
        choiceRoute=scanner.nextInt();
        if (choiceRoute>4 || choiceRoute<1){
            System.out.println("Tap a valid choice between 1 and 4!");
            choiceRoute=menuTurnOption();
        }
        return choiceRoute;
    }
    public static int getTornCount(int routeChoice){
        int turnCount =0;
        switch (routeChoice){
            case EASY_ROUTE: turnCount=EASY_TRACK_TORN;
                break;
            case MEDIUM_ROUTE: turnCount=MEDIUM_TRACK_TORN;
                break;
            case HARD_ROUTE: turnCount=HARD_TRACK_TORN;
                break;
            case SURPRISING_ROUTE:
                Random random=new Random();
                turnCount= random.nextInt(21)+5;
                break;
        }
        return turnCount;
    }
    public static void printInstructionsGame (){ ////הסבר על המשחק
        System.out.println("Hello - This is a game of lottery secret number. You have to guess the secret number that was drawn."+
                "\nThe secret number contains 4 digits. The rules :\n The lottery numbers will be between digits 1 to 6 for your information\n" +
                "2. No story appears twice\n" +
                "3.In accordance with section 1, if you pressed the same attempt the same number of times, you lost 2 of the allowed attempts \n" +
                "4.After each round you will be notified if the guesses you made were correct.\n" +
                "If you guessed a correct number but not in the correct position - you will receive a message that the guess is partial\n" +
                "If you guessed correctly and in the exact location you will get that you guessed an accurate guess \n" +
                "5.The next line will have a menu with options of the number of attempts in which you can guess the secret number. You must choose one of the four options.");
    }
    public static int [] lotterySecretNumber() {
        Random random = new Random();
        int[] secretNumber = new int[PASSWORD_LENGTH];
        for (int i = 0; i < secretNumber.length; i++) {
            secretNumber[i] = random.nextInt(THE_BIGGEST_NUMBER_OPTION) + THE_SMALLER_NUMBER_OPTION;
        }
        for (int i = 0; i < secretNumber.length; i++) {
            for (int j = i + 1; j < secretNumber.length; j++) {
                if (secretNumber[i] == secretNumber[j]) {
                    secretNumber = lotterySecretNumber();
                    break;
                }
            }
        }
        return secretNumber;
    }
    public static String correctAndPartialGuessesMessage(int [] arrCodeFromTheComputer, int[] guessFromUser) {
        int countOfCorrectGuesses=0;
        int countPartialGuesses=0;
        String messageToUser ="";
        for (int i=0; i< arrCodeFromTheComputer.length; i++){
            for (int j=0; j< guessFromUser.length; j++){
                if (arrCodeFromTheComputer[i]==guessFromUser[j] && i==j){
                    countOfCorrectGuesses++;
                    break;
                }
                if (arrCodeFromTheComputer[i]==guessFromUser[j]&& i!=j){
                    countPartialGuesses++;
                    break;
                }
            }
        }
        if (countOfCorrectGuesses!=0){
            messageToUser="The number of accurate guesses is :  "+countOfCorrectGuesses;
            if (countOfCorrectGuesses==PASSWORD_LENGTH){
                messageToUser= WINN_STRING;
            }
        }
        if (countPartialGuesses!=0 && countOfCorrectGuesses!=4){
            messageToUser=messageToUser+ "\n"+   "The number of partial guesses is : " +countPartialGuesses;
        }
        else if (countOfCorrectGuesses==0 && countPartialGuesses==0){
            messageToUser= "You have not guessed any accurate or partial guess";
        }
        return messageToUser;
    }
    public static int duplicatesOfTheUserChoice(int []userChoice){
        int duplicatesOfTheUser=0;
        for (int i=0; i< userChoice.length; i++){
            for(int j=i+1; j< userChoice.length-1; j++){
                if (userChoice[i]==userChoice[j]){
                    duplicatesOfTheUser++;
                    break;
                }
            }
        }
        return duplicatesOfTheUser;
    }

    public static int [] fromIntToArr (int num) {
        int count =0;
        int numberCopy = num;
        while (numberCopy%10!=0){
            numberCopy=numberCopy/10;
            count++;
        }
        int[] arr = new int[count];
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = (num % 10);
            num = num / 10;
        }
        return arr;
    }
    public static boolean isEquals (String str1, String str2){
        boolean isEquals = false;
        if (str1.length()==str2.length()){
            for (int i=0; i< str1.length(); i++){
                if (str1.charAt(i) == str2.charAt(i)) {
                    isEquals=true;
                }
                else {
                    isEquals=false;
                    break;
                }
            }
        }
        return isEquals;
    }
}
