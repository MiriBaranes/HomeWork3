import java.util.Scanner;

public class Q6 {
    public static final String POSSIBLE_ACTIONS ="^*+-";
    public static final String FORMAT_NUMBERS_AND_ACT = "1234567890+-()^*";
    public static final String FORMAT_OPTION_FOR_THE_FIRST_INDEX ="()-1234567890";
    public static final String FORMAT_OPTION_FOR_THE_LAST_INDEX =")1234567890";
    public static final String PARENTHESIS = "()";
    public static final char PLUS_ACT ='+';
    public static final char MINUS_ACT ='-';
    public static final char POW_ACT ='^';
    public static final char MULTIPLICATION_ACT ='*';
    public static String FIRST_ACT = "^*";
    public static String LAST_ACT = "+-";
    public static String NUMBERS ="1234567890";
    public static void main(String[] args) {
        theResultsOfTheExpression();

    }
    public static void theResultsOfTheExpression(){
        String expressionFromUser=stringFromUserProperExpression();
        expressionFromUser=accountingExpressionAfterCalculatingParentheses(expressionFromUser);
        int calculationSum= getSumExpressionPhrase(expressionFromUser);
        System.out.println("The sum is : "+calculationSum);
    }
    public static String stringFromUserProperExpression (){
        Scanner scanner= new Scanner(System.in);
        String userInput;
            System.out.println("Enter the accounting expression you would like to receive:");
            userInput= scanner.nextLine();
            if (!isProperExpression(userInput)) {
                System.out.println("Improper expression!");
                userInput = stringFromUserProperExpression();
            }
    return userInput;
    }
    public static boolean isProperExpression(String str){
      boolean  properExpression=false;
      if (isInFormatOptions(str)){
          if (isInFormatAct(str)){
              if (isInFormatParenthesis(str)){
                  properExpression=true;
              }
          }
      }
      return properExpression;
    }

    public static boolean isInFormatOptions(String toCheck) {
        boolean format = false;
        int count = 0;
        for (int i = 0; i < toCheck.length(); i++) {
            for (int j = 0; j < FORMAT_NUMBERS_AND_ACT.length(); j++) {
                if (toCheck.charAt(i) == FORMAT_NUMBERS_AND_ACT.charAt(j)) {
                    count++;
                    break;
                }
            }
        }
        if (count == toCheck.length()) {
            format = true;
        }
        return format;
    }
    public static boolean isInFormatAct (String str) {
        boolean formatAct = false;
        for (int i=0; i<FORMAT_OPTION_FOR_THE_FIRST_INDEX.length(); i++){
            if (str.charAt(0)==FORMAT_NUMBERS_AND_ACT.charAt(i)){
                formatAct=true;
                break;
            }
        }
        if (formatAct) {
            for (int k = 0; k < str.length() - 1; k++) {
                if (!formatAct) {
                    break;
                }
                for (int i = 0; i < POSSIBLE_ACTIONS.length(); i++) {
                    if (str.charAt(k) == POSSIBLE_ACTIONS.charAt(i)) {
                        if (str.charAt(k + 1) == POSSIBLE_ACTIONS.charAt(i)) {
                            formatAct = false;
                            break;
                        }
                    }
                }
            }
        }
            if (formatAct){
                for (int i =0; i< FORMAT_OPTION_FOR_THE_FIRST_INDEX.length(); i++){
                if (str.charAt(str.length()-1)==FORMAT_OPTION_FOR_THE_LAST_INDEX.charAt(i)){
                    break;
                }
                if (i==FORMAT_OPTION_FOR_THE_LAST_INDEX.length()-1){
                    formatAct=false;
                    break;
                }
            }
        }
        return formatAct;
    }
    public static boolean isInFormatParenthesis (String str){
        boolean formatParenthesis=false;
        String strSplit="";
        int countForLeftBracket =repetition(str,PARENTHESIS.charAt(0));
        int countForRightBracket= repetition(str,PARENTHESIS.charAt(1));
        if (countForLeftBracket==countForRightBracket && countForLeftBracket!=0){
            while (indexOf(str,PARENTHESIS.charAt(0))!=-1){
                int indexForLeftBracket=indexOf(str,PARENTHESIS.charAt(0));
                int indexForRightBracket=indexOf(str,PARENTHESIS.charAt(1));
                if (indexForLeftBracket<indexForRightBracket){
                    strSplit=subString(indexForLeftBracket+1,indexForRightBracket-1,str);
                    if (!isInFormatAct(strSplit)){
                        formatParenthesis=false;
                        break;
                    }
                    else {
                        if (indexForLeftBracket!=0){
                           if (indexOf(POSSIBLE_ACTIONS,str.charAt(indexForLeftBracket-1))==-1){
                               formatParenthesis=false;
                               break;
                           }
                        }
                        if (indexForRightBracket!=str.length()-1){
                            if (indexOf(POSSIBLE_ACTIONS,str.charAt(indexForRightBracket+1))==-1){
                                formatParenthesis=false;
                                break;
                            }
                        }
                        if (indexForRightBracket==str.length()-1){
                            formatParenthesis=true;
                            break;
                        }
                        formatParenthesis=true;
                        str=subString(indexForRightBracket+1,str.length()-1,str);
                    }
                }
            }
        }
        if (countForLeftBracket==0){
            formatParenthesis=true;
        }
        return formatParenthesis;
    }

    public static int indexOf(String str, char charToCheck) {
        int index = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == charToCheck) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int repetition(String str, char charToCheck) {
        int repetition = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == charToCheck) {
                repetition++;
            }
        }
        return repetition;
    }
    public static String subString(int indexStart, int indexFinish, String str) {
        String newStr = "";
        for (int i = indexStart; i <= indexFinish; i++) {
            newStr += str.charAt(i);
        }
        return newStr;
    }
    public static String  accountingExpressionAfterCalculatingParentheses(String userInput) {
        int repetition = repetition(userInput, '(');
        String[] parenthesis = new String[repetition];
        int index = 0;
        int indexFinish = 0;
        int sum=0;
        int j = 0;
        for (int i = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) == PARENTHESIS.charAt(0)) {
                index = i;
            }
            if (userInput.charAt(i) == PARENTHESIS.charAt(1)) {
                indexFinish = i;
                parenthesis[j] = subString(index+1, indexFinish-1, userInput);
                sum= getSumExpressionPhrase(parenthesis[j]);
                userInput = subString(0, index - 1, userInput) + sum + subString(indexFinish + 1, userInput.length() - 1, userInput);
                j++;
                i = 0;
            }
        }
        return userInput;
    }
    public static double calculation(int firstFactor, int secondFactor, char mathAction) {
        double sum = 0;
        switch (mathAction){
            case PLUS_ACT:
                sum=firstFactor+secondFactor;
                break;
            case MINUS_ACT:
                sum=firstFactor-secondFactor;
                break;
            case MULTIPLICATION_ACT:
                sum=firstFactor*secondFactor;
                break;
            case POW_ACT:
                sum = Math.pow(firstFactor, secondFactor);
                break;
        }
        return sum;
    }
    public static char nextMathematicalOperation(String userInput){
        char chMatAct = ' ';
        for (int j = 0; j < FIRST_ACT.length(); j++) {
            if (chMatAct != ' ') {
                break;
            }
            for (int i = 0; i < userInput.length(); i++) {
                if (userInput.charAt(i) == FIRST_ACT.charAt(j)&& i!=0) {
                    chMatAct = userInput.charAt(i);
                    break;
                }
            }
        }
        if (chMatAct==' '){
            for (int i = 0; i < userInput.length(); i++) {
                if (userInput.charAt(i) == LAST_ACT.charAt(0)|| userInput.charAt(i)==LAST_ACT.charAt(1)) {
                    chMatAct = userInput.charAt(i);
                    break;
                }
            }
        }
        return chMatAct;
    }
    public static int getSumExpressionPhrase(String expressionPhrase) {
        int sum = 0;
        int indexStart = -1;
        int indexEnd = -1;
        boolean thereIsMathematicalOperation = true;
        while (thereIsMathematicalOperation) {
            char ch = nextMathematicalOperation(expressionPhrase);
            int index = indexOf(expressionPhrase, ch);
                if (thereIsNoMoreAction(expressionPhrase)){
                    break;
                }
            String strNumber1 = "";
            String strNumber2 = "";
            for (int i = index - 1; i >= 0; i--) {
                if (indexStart != -1) {
                    break;
                }
                for (int j = 0; j < POSSIBLE_ACTIONS.length(); j++) {
                    if (expressionPhrase.charAt(i) == POSSIBLE_ACTIONS.charAt(j) && i != 0) {
                        indexStart = i + 1;
                        break;
                    } else if (i == 0) {
                        indexStart = 0;
                        break;
                    }
                }
            }
            strNumber1 = subString(indexStart, index - 1, expressionPhrase);
            for (int i = index + 1; i < expressionPhrase.length(); i++) {
                if (indexEnd != -1) {
                    break;
                }

                for (int j = 0; j < POSSIBLE_ACTIONS.length(); j++) {
                    if (expressionPhrase.charAt(i) == POSSIBLE_ACTIONS.charAt(j)) {
                        indexEnd = i;
                        break;
                    } else if (i == expressionPhrase.length() - 1) {
                        indexEnd = expressionPhrase.length();
                        break;
                    }
                }
            }
            strNumber2 = subString(index + 1, indexEnd - 1, expressionPhrase);
            sum += calculation(Integer.parseInt(strNumber1), Integer.parseInt(strNumber2), ch);
            expressionPhrase = subString(0, indexStart - 1, expressionPhrase) + sum + subString(indexEnd, expressionPhrase.length() - 1, expressionPhrase);
            sum = 0;
            indexEnd = -1;
            indexStart = -1;
        }
        sum=Integer.parseInt(expressionPhrase);
        return sum;
    }
    public static boolean thereIsNoMoreAction (String userInput){
        boolean thereIsNoMoreAction=false;
        int count=0;
        for (int i=1; i< userInput.length(); i++){
            for (int j=0; j< NUMBERS.length(); j++){
                if (userInput.charAt(i)==NUMBERS.charAt(j)){
                    count++;
                }
            }
        }
        if (count==userInput.length()-1){
            thereIsNoMoreAction=true;
        }
        return thereIsNoMoreAction;
    }
}
