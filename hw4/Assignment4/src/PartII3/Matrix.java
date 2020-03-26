package PartII3;

import java.io.*;
import java.util.*;

public class Matrix {
    // data
    private double[][] matrix;
    // n*m matrix
    private int n, m;

    /**
     * Constructor that creates a matrix of size nxm, with all values initially set to 0
     *
     * @param n row
     * @param m column
     */
    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        matrix = new double[n][m];
    }

    /**
     * Saves the content of the matrix on the file specified by filename
     *
     * @param filename
     */
    public void save(String filename) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(filename));
            out.println(n + " " + m);
            for (double[] i : matrix) {
                for (double j : i) {
                    out.print(j + " ");
                }
                out.println();
            }
            out.close();
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    public static class ExceptionWrongMatrixValues extends Exception {
        public ExceptionWrongMatrixValues(String msg) {
            super(msg);
        }
    }

    public static class ExceptionWrongMatrixDimension extends Exception {
        public ExceptionWrongMatrixDimension(String msg) {
            super(msg);
        }
    }

    /**
     * Read the data about a matrix from the file specified by filename, creates the matrix, and returns it
     *
     * @param filename
     * @return The matrix to read
     * @throws Exception ExceptionWrongMatrixValue, ExceptionWrongMatrixDimension and FileNotFoundException
     */
    public static Matrix read(String filename) throws Exception {
        try {
            FileReader f = new FileReader(filename);
            Scanner in = new Scanner(f);
            int n, m;
            try {
                n = in.nextInt();
                m = in.nextInt();
            } catch (InputMismatchException e) {
                throw new ExceptionWrongMatrixValues("The data on the file does not correspond to numeric values");
            }
            in.nextLine();
            Matrix res = new Matrix(n, m);
            ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
            while (in.hasNext()) {
                String line[] = in.nextLine().split(" ");
                ArrayList<Double> tmp = new ArrayList<>();
                if (matrix.size() > 0 && line.length != matrix.get(matrix.size() - 1).size()) {
                    throw new ExceptionWrongMatrixValues("The rows have different length");
                }
                for (String s : line) {
                    try {
                        tmp.add(Double.parseDouble(s));
                    } catch (Exception e) {
                        throw new ExceptionWrongMatrixValues("The data on the file does not correspond to numeric values");
                    }
                }
                matrix.add(tmp);
            }
            if (n == matrix.size() && m == matrix.get(0).size()) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        res.matrix[i][j] = matrix.get(i).get(j);
                    }
                }
            } else {
                throw new ExceptionWrongMatrixDimension("Data doesn't conform to the expected dimensions of the PartII3.Matrix");
            }
            f.close();
            return res;
        } catch (ExceptionWrongMatrixValues e) {
            System.err.println("Caught ExceptionWrongMatrixValues: " + e.getMessage());
        } catch (ExceptionWrongMatrixDimension e) {
            System.err.println("Caught ExceptionWrongMatrixDimension: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        return null;
    }

    /**
     * Return the matrix that is the sum of the object and of m, if the two matrices have the same dimensions, and null otherwise
     *
     * @param m the matrix to add
     * @return the sum of two matrix
     */
    public Matrix sum(Matrix m) {
        if (m.n == this.n && m.m == this.m) {
            Matrix ans = new Matrix(this.n, this.m);
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.m; j++) {
                    ans.matrix[i][j] = matrix[i][j] + m.matrix[i][j];
                }
            }
            return ans;
        } else {
            return null;
        }
    }

    /**
     * Return the matrix that is the product of the object and of m, if the two matrices have compatible dimensions, and null otherwise
     *
     * @param m the matrix to multiply
     * @return the product of two matrix
     */
    public Matrix product(Matrix m) {
        if (this.m == m.n) {
            Matrix ans = new Matrix(this.n, m.m);
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.m; j++) {
                    for (int k = 0; k < m.m; k++) {
                        ans.matrix[i][k] += matrix[i][j] * m.matrix[j][k];
                    }
                }
            }
            return ans;
        } else {
            return null;
        }
    }

    /**
     * Print the matrix for testing
     */
    public void print() {
        System.out.println(n + " " + m);
        for (double[] i : matrix) {
            for (double j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
