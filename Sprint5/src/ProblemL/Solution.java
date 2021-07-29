package ProblemL;

import java.util.Arrays;

public class Solution {
    private static void swap(int[] heap, int idxOne, int idxTwo) {
        if (idxOne == idxTwo) return;
        int temp = heap[idxOne];
        heap[idxOne] = heap[idxTwo];
        heap[idxTwo] = temp;
    }

    private static int getLeftChild(int[] heap, int headIdx) {
        int leftChildIdx = 2 * headIdx;
        return (leftChildIdx < heap.length) ? leftChildIdx : -1;
    }

    private static int getRightChild(int[] heap, int headIdx) {
        int rightChildIdx = headIdx * 2 + 1;
        return (rightChildIdx < heap.length) ? rightChildIdx : -1;
    }

    private static int getMaxChildIdx(int[] heap, int headIdx) {
        int leftChildIdx = getLeftChild(heap, headIdx);
        int rightChildIdx = getRightChild(heap, headIdx);

        if (leftChildIdx == -1) return rightChildIdx;
        if (rightChildIdx == -1) return leftChildIdx;
        if ((leftChildIdx == -1) && (rightChildIdx == -1)) return -1;

        return (heap[leftChildIdx] > heap[rightChildIdx]) ? leftChildIdx : rightChildIdx;
    }

    private static int getIdxToSwap(int[] heap, int headIdx) {
        int maxChildIdx = getMaxChildIdx(heap, headIdx);
        if (maxChildIdx == -1) return headIdx;
        return (heap[headIdx] > heap[maxChildIdx]) ? headIdx : maxChildIdx;
    }

    public static int siftDown(int[] heap, int idx) {
        int idxToSwap = getIdxToSwap(heap, idx);
        int res;
        if (idxToSwap == idx)
            return idx;
        else {
            swap(heap, idx, idxToSwap);
            res = siftDown(heap, idxToSwap);
        }
        return res;
    }

    public static void main(String[] args) {
        //---TESTS---
        //   int[] heap = new int[]{-1, 14, 50, 20, 17, 45, 10, 6,5,4};
        //   int[] heap = new int[]{-1, 14};
        //   int[] heap = new int[]{-1, 14, 50};
        //   int[] heap = new int[]{-1, 14, 50, 20};
        //   int[] heap = new int[]{-1, 14, 20, 50};
        //   int[] heap = new int[]{-1, 50, 20, 14};

        int[] heap = new int[]{-1, 50, 14, 20};
        System.out.println(siftDown(heap, 1));
        System.out.println(Arrays.toString(heap));
    }
}
