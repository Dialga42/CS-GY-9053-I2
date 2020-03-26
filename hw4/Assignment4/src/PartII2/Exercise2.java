package PartII2;

import java.io.*;

public class Exercise2 {
    /**
     * @param args
     * @throws FileNotFoundException and IOException
     */
    public static void main(String[] args) {
        int n = 10;
        int[] v = new int[n];
        try {
            FileReader f = new FileReader("dati.txt");
            BufferedReader in = new BufferedReader(f);
            int i = 0;
            String linea = in.readLine();
            while (linea != null) {
                v[i] = Integer.parseInt(linea);
                linea = in.readLine();
                i++;
            }
            f.close();
        } catch (FileNotFoundException e) {
            System.err.println("Caught FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
}

