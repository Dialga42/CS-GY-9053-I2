package Question1Inheritance.edu.nyu.cs9053.midterm.hierarchy;

public class CrossCountrySkier extends Skier {
    private String country;

    public CrossCountrySkier(String name, int age, int skiLength, String country) {
        super(name, age, skiLength);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean equals(Object o) {
        if (o instanceof CrossCountrySkier) {
            return super.equals(o) && this.country.equals(((CrossCountrySkier) o).getCountry());
        }
        return false;
    }
}
