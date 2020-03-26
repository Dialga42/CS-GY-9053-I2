package PartII1;

import java.io.File;

public class Test {
    public static void testListOfNumbers() {
        ListOfNumbers t = new ListOfNumbers();
        t.readList();
        t.writeList();
        t.cat(new File("12"));
        t.cat(new File("InFile.txt"));
    }

    public static void main(String[] args) throws Exception {
        testListOfNumbers();
    }
}
