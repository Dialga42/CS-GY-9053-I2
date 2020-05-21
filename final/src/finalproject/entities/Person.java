package finalproject.entities;

public class Person implements java.io.Serializable {

    private static final long serialVersionUID = 4190276780070819093L;
    String first, last, city;

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    int age;

    public int getId() {
        return id;
    }

    int id;

    // this is a person object that you will construct with data from the DB
    // table. The "sent" column is unnecessary. It's just a person with
    // a first name, last name, age, city, and ID.
    public Person() {
    }

    public Person(String first, String last, int age, String city, int id) {
        this.first = first;
        this.last = last;
        this.city = city;
        this.age = age;
        this.id = id;
    }

    public String toString() {
        return "Person [last=" + last +
                ", first=" + first +
                ", age=" + age +
                ", city=" + city +
                ", id=" + id + "]";
    }
}
