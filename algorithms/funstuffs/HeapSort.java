import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: joonhyunglim
 * Date: 11/20/13
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeapSort {

    public HeapSort() {
        System.out.println("----------------------------\n" +
                           " Heap Sort Algorithm\n" +
                           " Efficiency : O( nlog(n) )\n" +
                           "----------------------------\n");
    }

    public void MaxHeapify(int[] A, int i) {
        /* process Max-Heap Property */
        int largest;    // a pointer for largest value
        int l = 2*i;    // left child in the B-tree
        int r = 2*i+1;  // right child in the B-tree
        if (l < A.length && A[l] > A[i]) {
            largest = l;
        } else {
            largest = i;
        }
        if (r < A.length && A[r] > A[largest]) {
            largest = r;
        }
        if (largest != i) {
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            this.MaxHeapify(A,largest);
        }
    }

    public void BuildMaxHeap(int[] A) {
        /*running the MaxHeapify on the remaining node */
        for(int i = (A.length/2); i >= 0; i--) {
            this.MaxHeapify(A, i);
        }

    }

    public int[] sort(int[] A) {
        int[] result = A;
        this.BuildMaxHeap(A);
        for (int i = (A.length-1); i >= 0; i--) {
            // exchange A[1] and A[i]
            int v = A[0];
            A[0] = A[i];
            A[i] = v;
            result[i] = v;
            A = Arrays.copyOfRange(A, 0, i);
            this.MaxHeapify(A, 0);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1,4,2,5,3,7,8,51,11,24};
        HeapSort s = new HeapSort();
        int[] sorted = s.sort(a);
        for (int i=0; i < sorted.length ; i++) {
            System.out.print(sorted[i]+" ");
        }
    }

} // End of the class