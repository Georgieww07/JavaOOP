package Encapsulation.SalaryIncrease_02;

import java.text.DecimalFormat;

public class Person {
    private static final String PATTERN = "%s %s gets %s leva";
    private String firstName;
    private String lastName;
    private int age;
    private double salary;


    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    private String getFirstName() {
        return this.firstName;
    }

    private int getAge() {
        return this.age;
    }

    private String getLastName() {
        return lastName;
    }

    private double getSalary() {
        return salary;
    }

    private void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.0##");
        return String.format(PATTERN, this.getFirstName(), this.getLastName(), format.format(this.getSalary()));
    }

   public void increaseSalary(double bonus){
        if (this.getAge() < 30){
            //this.salary += (this.salary * (bonus / 100)) / 2;
            this.setSalary(this.getSalary() + (this.getSalary() * (bonus / 100)) / 2);
        }else {
            //this.salary += this.salary * (bonus / 100);
            this.setSalary(this.getSalary() + this.getSalary() * (bonus / 100));
        }

    }
}
