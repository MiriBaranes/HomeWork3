public class Q2 {
    public static final int CUT_OUT_THE_NUMBER =10;
    public static final int INDEX_NOT_EXIST =-1;
    public static final int DEFAULT_NUMBER=0;
    public static void main(String[] args) {
        int [] arr = {1,12,15,16,18,22,24};
        int []arr2 = {1,1,1,1,1,1,12,12,21,14,15,16};
        System.out.println("The index of the number from arr 1 , that has the most brothers from arr 2 is: "+ numberIndexCloseness(arr,arr2));
    }

    public static int sumNum(int num) {
        int sum = 0;
        while (num % CUT_OUT_THE_NUMBER != 0) {
            sum= num % CUT_OUT_THE_NUMBER + sum;
            num=num / CUT_OUT_THE_NUMBER;
        }
        return sum;
    }

    public static int[] newArrSumOfNumberArr(int[] arr1) {
        int[] arrSumOfIndexValue = new int[arr1.length];
        int value=0;
        for (int i = 0; i < arr1.length; i++) {
            value= sumNum(arr1[i]);
            arrSumOfIndexValue[i]=value;
        }
        return arrSumOfIndexValue;
    }

    public static void printArray (int []arr ){
        for (int i=0; i< arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
    public static int numberIndexCloseness(int []arr1, int [] arr2){
        int [] arr1Sum = newArrSumOfNumberArr(arr1);
        int [] arr2Sum= newArrSumOfNumberArr(arr2);
        int maxCounter = DEFAULT_NUMBER;
        int index =INDEX_NOT_EXIST;
        for (int i =0; i< arr1Sum.length; i++){
            int counter=DEFAULT_NUMBER;
            for (int j =0; j< arr2Sum.length; j++){
                if (arr1Sum[i]==arr2Sum[j]) {
                    counter++;
                }
                    if (j==arr2Sum.length-1){
                    if (counter>maxCounter){
                        maxCounter=counter;
                        index=i;
                    }
                }
            }
        }
        return index;
    }


}
