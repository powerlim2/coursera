/*************************************************************************
 *  Compilation:  javac FileSorter.java
 *  Execution:    java FileSorter directory-name
 *
 *  Prints out all of the files in the given directory in
 *  sorted order.
 *
 *  % java FileSorter .
 *
 *************************************************************************/

import java.io.File;
import java.util.Arrays;

public class FileSorter {

    public static void main(String[] args) {
        File directory = new File(args[0]);     // root directory
        File[] files = directory.listFiles();
        Arrays.sort(files);
        for (int i = 0; i < files.length; i++)
            StdOut.println(files[i].getName());
    }

}
