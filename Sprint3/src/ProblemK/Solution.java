package ProblemK;

public class Solution {
    public static int[] merge(int[] arr, int left, int mid, int right){
        return new int[]{};
    }
    public static void merge_sort(int[] arr, int left, int right){
        if (left == right) return;
        if ((right - left)==1) {
            if (arr[left] > arr[right]){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        int mid = (left + right)/2;
        merge_sort(arr, left, mid);
        merge_sort(arr, mid+1, right);
    }
}
