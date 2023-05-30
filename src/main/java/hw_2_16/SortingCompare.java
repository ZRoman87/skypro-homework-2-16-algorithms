package hw_2_16;

import java.util.Arrays;
import java.util.Random;

public class SortingCompare {

    public static void sort(){
        int[] arr1 = generateRandomArray();
        int[] arr2 = Arrays.copyOf(arr1,arr1.length);
        int[] arr3 = Arrays.copyOf(arr1,arr1.length);

        // Bubble sort

        System.out.println("Buble");

        long startBubble = System.currentTimeMillis();
        sortBubble(arr1);
        System.out.println(System.currentTimeMillis() - startBubble);

        // Selection sort

        System.out.println("Selection");

        long startSelection = System.currentTimeMillis();
        sortSelection(arr2);
        System.out.println(System.currentTimeMillis() - startSelection);

        // Insertion sort

        System.out.println("Insertion");

        long startInsertion = System.currentTimeMillis();
        sortInsertion(arr3);
        System.out.println(System.currentTimeMillis() - startInsertion);

    }
    private static int[] generateRandomArray() {
        Random random = new Random();
        int[] arr = new int[100_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000);
        }
        return arr;
    }

    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }






}
