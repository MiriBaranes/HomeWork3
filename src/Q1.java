import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.Arrays;
import java.util.Scanner;

public class Q1 {
    public static final int CHECKING_AN_EVEN_OR_ODD_NUMBER =2;
    public static final int CUT_OUT_THE_NUMBER =10;
    public static final int INDEX_NOT_EXIST =-1;
    public static void main(String[] args) {
        int[] arr = arrUserInput();
        System.out.println("The lowest sum value (index) : "+indexNumberChangeInTheArray(arr));
    }
    public static int [] arrUserInput (){
        Scanner scanner = new Scanner(System.in);
        int size=0;
        int value=0;
        do {
            System.out.println("Enter a size : ");
            size=scanner.nextInt();
        }while (size<=0);
        int [] arr = new int[size];
        for (int i =0; i< arr.length; i++) {
            do {
                System.out.println("Enter a value :");
                value = scanner.nextInt();
            }while (value<0);
            arr[i]=value;
        }
        return arr;
    }

    public static boolean isNumberChanges(int num) {
        boolean isNumberChanges = true;
        while (num % CUT_OUT_THE_NUMBER != 0) {
            if ((num % CUT_OUT_THE_NUMBER) % CHECKING_AN_EVEN_OR_ODD_NUMBER == 0) {
                num = num / CUT_OUT_THE_NUMBER;
                if (num % CUT_OUT_THE_NUMBER % CHECKING_AN_EVEN_OR_ODD_NUMBER == 0 && num!=0 ) {
                    isNumberChanges = false;
                    break;

                }
            }
            else if ((num % CUT_OUT_THE_NUMBER) % CHECKING_AN_EVEN_OR_ODD_NUMBER != 0) {
                    num = num / CUT_OUT_THE_NUMBER;
                    if (num % CUT_OUT_THE_NUMBER % CHECKING_AN_EVEN_OR_ODD_NUMBER != 0 && num!=0) {
                        isNumberChanges = false;
                        break;
                    }
                }
            }
        return isNumberChanges;
    }
    public static int indexNumberChangeInTheArray (int [] arr) {
        int index = INDEX_NOT_EXIST;
        int min = -1;
        int[] arrSumOfValueArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrSumOfValueArray[i] = sumInt(arr[i]);
        }
        for (int i = 0; i < arrSumOfValueArray.length; i++) {
            if (min == -1) {
                if (isNumberChanges(arr[i])) {
                    min = arrSumOfValueArray[i];
                    index = i;
                }
            } else {
                if (isNumberChanges(arr[i])) {
                    if (arrSumOfValueArray[i] < min) {
                        min = arrSumOfValueArray[i];
                        index = i;
                    }
                }
            }
        }
        return index;
    }


        public static int sumInt ( int num){
            int sum = 0;
            while (num > 0) {
                sum += num % CUT_OUT_THE_NUMBER;
                num /= CUT_OUT_THE_NUMBER;
            }
            return sum;
        }
    }

