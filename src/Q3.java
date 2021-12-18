public class Q3 {

    public static void main(String[] args) {
        int[] arr = arrFactorization(100);
        printArr(arr);

    }

    public static boolean remainingDivision(int num1, int num2) {
        boolean dividedWithResidue;

        if (num1 % num2 != 0) {
            dividedWithResidue = false;
        } else {
            dividedWithResidue = true;
        }
        return dividedWithResidue;

    }

    public static boolean checkForPrimeNumber(int numberForCheck) {

        boolean isPrime = true;

        if (numberForCheck==1){
            isPrime = false;
        }

        for (int i = 2; i < numberForCheck; i++) {
            if (remainingDivision(numberForCheck, i)) {
                isPrime = false;
            }

        }

        return isPrime;
    }

    public static int [] arrFactorization(int num){
        int counter =0;
        int k =0;
        int numForCounter =num;
        for (int i =2; i<=numForCounter; i++){
            if (numForCounter%i==0){
                if (checkForPrimeNumber(i)){
                    counter++;
                    numForCounter= numForCounter/i;
                    i=1;
                }
            }
        }
        int [] arr = new int[counter];
        for (int i =2; i<=num; i++) {
            if (num % i == 0) {
                if (checkForPrimeNumber(i)) {
                    arr[k] = i;
                    k++;
                    num = num / i;
                    i = 1;
                }
            }
        }
        return arr;
    }
    public static void printArr (int[] arr){
        for (int i =0; i<arr.length; i++){
            System.out.print(arr[i] + " * ");
        }
    }
}
