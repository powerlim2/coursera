/******************************************************
 * Date: 2014.06.22
 * Author: Joon Lim
 * Dependencies: QuickFindUF.java
 *
 * Program to answer the exercise question 1
 * Answer: 0 1 6 6 0 5 6 6 6 1
 ******************************************************/
public class question1 {
    public static void main(String[] args) {
        int sz = 10;
        QuickFindUF quickFind = new QuickFindUF(sz);
        quickFind.union(2, 3);  // 1
        quickFind.union(3, 7);  // 2
        quickFind.union(3, 6);  // 3
        quickFind.union(9, 1);  // 4
        quickFind.union(4, 0);  // 5
        quickFind.union(8, 7);  // 6

        int i = 0;
        while(i < sz) {
            System.out.printf("%s ", quickFind.find(i)); // .find(i) is to get the value of id[i]
            i++;
        }
    }
}
