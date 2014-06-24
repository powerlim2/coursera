/******************************************************
 * Date: 2014.06.22
 * Author: Joon Lim
 * Dependencies: QuickUnionUF.java
 *
 * Program to answer the exercise question 2
 * Answer: 3 3 3 3 3 1 3 9 4 1
 ******************************************************/
public class question2 {
    public static void main(String[] args) {
        int sz = 10;

        WeightedQuickUnionUF wquickUnion = new WeightedQuickUnionUF(sz);
        wquickUnion.union(4, 8);  // 1
        wquickUnion.union(3, 6);  // 2
        wquickUnion.union(9, 7);  // 3
        wquickUnion.union(1, 5);  // 4
        wquickUnion.union(3, 0);  // 5
        wquickUnion.union(5, 9);  // 6
        wquickUnion.union(4, 3);  // 7
        wquickUnion.union(3, 5);  // 8
        wquickUnion.union(3, 2);  // 9

        int[] result = wquickUnion.getId();
        int i = 0;
        while(i < sz) {
            System.out.printf("%s ", result[i]); // .find(i) is to get the value of id[i]
            i++;
        }
    }
}
