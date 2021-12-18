import java.util.Locale;
import java.util.Scanner;

public class Q5 {
    public static void main(String[] args) {
        String strAfterChange = changeChars();
        System.out.println("Information encryption String :  "+ strAfterChange);

    }
    public static char theMostCommonCharInTheString(String str){
        int counter=0;
        int maxRepetitions =0;
        char maxChar=' ';
        for (int i=0; i< str.length(); i++){
            counter= countNumberOfRepetitionsCharInTheString(str,str.charAt(i));
            if (counter>maxRepetitions){
                maxRepetitions=counter;
                maxChar=str.charAt(i);
            }
        }
        return maxChar;
    }
    public static char theSecondMostCommonChar(String str) {
        int theSecondMostCommonCharCount = 0;
        char theSecondMostCommonChar = ' ';
        char maxChar = theMostCommonCharInTheString(str);
        int max = countNumberOfRepetitionsCharInTheString(str, maxChar);
        int count=0;
        for (int i = 0; i < str.length(); i++) {
                count = countNumberOfRepetitionsCharInTheString(str, str.charAt(i));
                if (count == max && str.charAt(i)!=maxChar){
                    theSecondMostCommonChar=str.charAt(i);
                    break;
                }
                if (count<max&& count>theSecondMostCommonCharCount) {
                    theSecondMostCommonCharCount = count;
                    theSecondMostCommonChar=str.charAt(i);
                }
            }
        return theSecondMostCommonChar;
    }
    public static String changeChars (){
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter a string : ");
        String userInput =scanner.nextLine();
        userInput=userInput.toLowerCase(Locale.ROOT);
        String newStr = "";
        char maxChar = theMostCommonCharInTheString(userInput);
        char theSecondMostCommonChar = theSecondMostCommonChar(userInput);
        for (int i= 0; i< userInput.length(); i++){
            if (userInput.charAt(i)!=theSecondMostCommonChar&& userInput.charAt(i)!=maxChar){
                newStr += userInput.charAt(i);
            }
            else if (userInput.charAt(i)==theSecondMostCommonChar){
                newStr+=maxChar;
            }
            else if (userInput.charAt(i)== maxChar){
                newStr+=theSecondMostCommonChar;
            }
        }

    return newStr;
    }
    public static int countNumberOfRepetitionsCharInTheString(String srt, char charToCheck){
        int numberOfRepetitions=0;
        for (int i=0; i< srt.length(); i++){
            if (srt.charAt(i)==charToCheck){
                numberOfRepetitions++;
            }
        }
        return numberOfRepetitions;
    }
}
