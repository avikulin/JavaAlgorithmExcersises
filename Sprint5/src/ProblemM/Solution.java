package ProblemM;

import java.util.Arrays;

public class Solution {
    private static int getParentIdx(int idx) {
        return idx / 2;
    }

    private static void swap(int[] heap, int idxOne, int idxTwo) {
        if (idxOne == idxTwo) return;
        int temp = heap[idxOne];
        heap[idxOne] = heap[idxTwo];
        heap[idxTwo] = temp;
    }

    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) return idx;
        int parentNodeIdx = getParentIdx(idx);
        if (heap[parentNodeIdx] < heap[idx]) {
            swap(heap, parentNodeIdx, idx);
            return siftUp(heap, parentNodeIdx);
        }
        return idx;
    }

    public static void main(String[] args) {
        //---TEST---
        int[] heap = new int[]{-1, 50, 45, 20, 17, 14, 10, 6, 5, 4, 33};
        System.out.println(siftUp(heap, 10));
        System.out.println(Arrays.toString(heap));
    }
}
