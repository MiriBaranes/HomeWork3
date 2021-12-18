import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        int[] arr1 = {1,2,6,8,3,4,7,5};
        int [] arr2 ={8,7,6,5,4,3,2,4};
        System.out.println("Full array ? "+isFullArray(arr1));
        System.out.println("Full array Classified ? "+isFullArrayClassified(arr2));

    }
    public static int [] arrUserInput (){//// If need to be received from the user
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

    public static int maxValueInTheArray(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int minValueInTheArray(int[] arr) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static boolean isFullArray(int[] arr) {
        int max = maxValueInTheArray(arr);
        int min= minValueInTheArray(arr);
        boolean isFullArray = false;
        int size = max - min + 1;
        int count = 2;
        for (int i = min; i < max; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == (i + 1) && (i + 1) < max) {
                    count++;
                    break;
                }
                if (j == arr.length - 1 && arr[j] != i + 1) {
                    isFullArray = false;
                }
            }
        }
        if (count == arr.length && count == size) {
            isFullArray = true;
        }
        return isFullArray;
    }

    public static boolean isFullArrayClassified(int[] arr) {
        boolean isFullArrayClassified = false;
        int min = minValueInTheArray(arr);
        int max = maxValueInTheArray(arr);
        int count = 2;
        int size = max - min + 1;
        if (max == arr[0] && min == arr[arr.length - 1]) {
            for (int i = 1; i <arr.length-1; i++) {
                if (arr[i] == max - 1 ) {
                    count++;
                    max = max - 1;
                } else {
                    break;
                }
            }
            if (count == size && size == arr.length) {
                isFullArrayClassified = true;
            }
        }
        return isFullArrayClassified;
    }
}
