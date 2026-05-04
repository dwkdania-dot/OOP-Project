package users;

public abstract class Person {
    protected String name;
    protected int age;
    protected String cnic;

    public Person(String name, int age, String cnic) {
        this.name = name;
        this.age = age;
        this.cnic = cnic;
    }

    public abstract void displayInfo();
}
