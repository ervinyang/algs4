package edu.princeton.cs.algs4;


import java.util.function.Function;

/******************************************************************************
 *  Compilation:  javac KendallTau.java
 *  Execution:    java KendallTau n
 *  Dependencies: StdOut.java Inversions.java
 *
 *  Generate two random permutations of size N and compute their
 *  Kendall tau distance (number of inversions).
 *
 * @link http://shmilyaw-hotmail-com.iteye.com/blog/2275113
 * @link https://algs4.cs.princeton.edu/25applications/KendallTau.java.html
 ******************************************************************************/

public class KendallTau {

    // return Kendall tau distance between two permutations
    public static long distanceByMerge(int[] a, int[] b) {
        return distance(a, b, (Function<Integer[], Long>) integers -> KendallTau.mergeCount(integers));
    }

    // return Kendall tau distance between two permutations
    public static long distanceByInsertion(int[] a, int[] b) {

        return distance(a, b, (Function<Integer[], Long>) integers -> Inversions.count(integers));
    }

    // return Kendall tau distance between two permutations
    public static <Key extends Comparable<Key>> Long distance(int[] a, int[] b, Function<Key[], Long> countFunc) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Array dimensions disagree");
        }
        int n = a.length;

        int[] ainv = new int[n];
        for (int i = 0; i < n; i++)
            ainv[a[i]] = i;

        Integer[] bnew = new Integer[n];
        for (int i = 0; i < n; i++)
            bnew[i] = ainv[b[i]];

        return countFunc.apply((Key[]) bnew);
    }


    // return a random permutation of size n
    public static int[] permutation(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = i;
        StdRandom.shuffle(a);
        return a;
    }


    private static long counter = 0;
    // 使用归并排序方法求逆序数
    private static int[] aux;

    public static long mergeCount(Integer[] a) {
        aux = new int[a.length];
        mergeSort(a, 0, a.length - 1);
        return counter;
    }

    private static void mergeSort(Integer[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, lo, mid);
        mergeSort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(Integer[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
                counter += mid - i + 1;// 每个比前子数组小的后子数组元素，逆序数为前子数组现有的长度
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {

        // two random permutation of size n
        int n = 8;
        int[] a = new int[]{0, 3, 1, 6, 2, 5, 4, 7};
        int[] b = new int[]{1, 0, 3, 6, 7, 4, 2, 5};
        // print initial permutation
        for (int i = 0; i < n; i++)
            StdOut.println(a[i] + " " + b[i]);
        StdOut.println("inversions = " + distanceByInsertion(a, b));
        StdOut.println("+++++++++++++++++++++++++++++++++++++++++++");

        int[] a1 = new int[]{0, 3, 1, 6, 2, 5, 4, 7};
        int[] b1 = new int[]{1, 0, 3, 6, 7, 4, 2, 5};
        for (int i = 0; i < a1.length; i++) {
            System.out.println(a1[i] + " " + b1[i]);
        }
        System.out.println("Inversions:" + distanceByMerge(a1, b1));
    }
}
