package PartII3;

public class Test {

    public static void testMatrix() throws Exception {
        Matrix m = Matrix.read("Matrix_in.txt"), n = Matrix.read("Matrix_in2.txt"), tmp;
        tmp = Matrix.read("Matrix_in3.txt");
        tmp = Matrix.read("Matrix_in4.txt");
        tmp = Matrix.read("Matrix_in5.txt");
        m.print();
        n.print();
        m.save("Matrix_out.txt");
        tmp = m.sum(m);
        tmp.print();
        tmp = m.sum(n);
        System.out.println(tmp == null);
        tmp = n.product(m);
        tmp.print();
        tmp = m.product(m);
        System.out.println(tmp == null);
    }

    public static void main(String[] args) throws Exception {
        testMatrix();
    }
}
