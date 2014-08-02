package java;

/*************************************************************************
 *  Compilation:  javac java.Comb2.java
 *  Execution:    java java.Comb2 N k
 *
 *  Enumerates all subsets of size k on N elements.
 *
 *  % java java.Comb2 5 3
 *  0 1 2 
 *  0 1 3 
 *  0 1 4 
 *  0 2 3 
 *  0 2 4 
 *  0 3 4 
 *  1 2 3 
 *  1 2 4 
 *  1 3 4 
 *  2 3 4 
 *
 *************************************************************************/

public class Comb2 {

    private static void showCombination(int[] s) {
        for (int i = 0; i < s.length; i++)
            System.out.print(s[i] + " ");
        System.out.println();
    }

    public static void generate(int[] s, int position, int nextInt, int k, int N) {
        if (position == k) {
            showCombination(s);
            return;
        }
        for (int i = nextInt; i < N; i++) {
            s[position] = i;
            generate(s, position + 1, i + 1, k, N);
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        int[] s = new int[k];
        generate(s, 0, 0, k, N);
    }
}
