import java.util.Scanner;

public class Q8 {
    public static int PREFERRED_STRING_LENGTH=4;
    public static void main(String[] args) {
        String[] stringsUserInput = getStringsFromUser();
        System.out.println("The most popular subString is : " + getTheMostPopularSubStringInTheArray(stringsUserInput));

    }

    public static String[] getStringsFromUser() {
        Scanner scanner = new Scanner(System.in);
        int size=0;
        String str="";
        String fullString ="";
        do {
            System.out.println("Enter a string :");
            str=scanner.nextLine();
            if (str.length()>=PREFERRED_STRING_LENGTH) {
                size++;
                fullString += str + " ";
            }
        }
        while (str.length()>=PREFERRED_STRING_LENGTH);

        String [] strings = new String[size];
        for (int i=0, j=0; i< fullString.length(); i++){
            for (int k=i; k< fullString.length(); k++)
            if (fullString.charAt(k)== ' '){
                strings[j]=subString(i,k-1,fullString);
                i=k;
                j++;
                break;
            }
        }
        return strings;
        }
        public static int sizeOfArraySubStringsOption (String str){
            int size =0;
            for (int i =1 ;i<str.length();i++){
                size= (i)+size;
            }
            return size;
        }
    public static String [] optionsSubStringWord(String str ){
        int size =sizeOfArraySubStringsOption(str);
        String [] subStringOption=new String[size];
        for (int i =0,k=0; i< str.length(); i++){
            for (int j=i+1; j< str.length(); j++){
                subStringOption[k]=subString(i,j,str);
                k++;
            }
        }
        return subStringOption;
    }
    public static String subString (int start , int finish, String str ){
        String split = "";
        for (int i=start; i<=finish; i++){
            split+=str.charAt(i);
        }
        return split;
    }
    public static void printStringArray (String [] arr){
        for (int i=0; i< arr.length;i++){
            System.out.print(arr[i]+ " ");
        }
    }
    public static boolean isEquals (String str1, String str2){
        boolean isEquals=false;
        if (str1.length()==str2.length()){
            for (int i=0; i< str1.length(); i++){
                if (str1.charAt(i)==str2.charAt(i)){
                    isEquals=true;
                }
                else{
                    isEquals=false;
                    break;
                }
            }
        }
        return isEquals;
    }
    public static String getTheMostPopularSubStringInTheArray(String [] strings){
        String [] split;
        int count = 0;
        for (int i=0; i<strings.length; i++){
            count+=sizeOfArraySubStringsOption(strings[i]);
            }
        String[] splitSubString = new String[count];
            for (int i = 0,k=0; i < strings.length; i++) {
                split = optionsSubStringWord(strings[i]);
                for (int j = 0; j < split.length; j++) {
                    splitSubString[k] = split[j];
                    k++;
                }
            }
        int maxRepeatCounter =0;
        int index =0;
        int sumRepeatOfWord=0;
        for (int i=0; i<splitSubString.length; i++){
            sumRepeatOfWord=0;
            for (int j=i+1; j<splitSubString.length; j++){
                if (isEquals(splitSubString[i],splitSubString[j])){
                    sumRepeatOfWord++;
                    if (sumRepeatOfWord>maxRepeatCounter){
                        maxRepeatCounter=sumRepeatOfWord;
                        index=i;
                    }
                }
            }
        }
        return splitSubString[index];
    }
}
