package PartII;

public class Complex extends Number implements Comparable<Complex>, Cloneable {

    public double a, b;

    public Complex() {
        this.a = 0;
        this.b = 0;
    }

    @Override
    public int intValue() {
        return (int) abs();
    }

    @Override
    public long longValue() {
        return (long) abs();
    }

    @Override
    public float floatValue() {
        return (float) abs();
    }

    @Override
    public double doubleValue() {
        return (double) abs();
    }

    public Complex clone() {
        return new Complex(a, b);
    }

    public Complex(double a) {
        this.a = a;
        this.b = 0;
    }

    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Complex add(Complex c) {
        return new Complex(a + c.a, b + c.b);
    }

    public Complex subtract(Complex c) {
        return new Complex(a - c.a, b - c.b);
    }

    public Complex multiply(Complex c) {
        return new Complex(a * c.a - b * c.b, b * c.a + a * c.b);
    }

    public Complex divide(Complex c) {
        return new Complex((a * c.a + b * c.b) / (c.a * c.a + c.b * c.b),
                (b * c.a - a * c.b) / (c.a * c.a + c.b * c.b));
    }

    public double abs() {
        return Math.sqrt(a * a + b * b);
    }

    public double getRealPart() {
        return a;
    }

    public double getImaginaryPart() {
        return b;
    }

    public String toString() {
        if (b == 0)
            return String.valueOf(a);
        else return String.valueOf(a) + " + " + String.valueOf(b) + "i";
    }

    @Override
    public int compareTo(Complex complex) {
        return Double.compare(this.doubleValue(), complex.doubleValue());
    }
}

