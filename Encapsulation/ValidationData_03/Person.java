package Encapsulation.ValidationData_03;

import java.text.DecimalFormat;

public class Person {
    private static final String PATTERN = "%s %s gets %s leva";
    private String firstName;
    private String lastName;
    private int age;
    private double salary;


    public Person(String firstName, String lastName, int age, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setSalary(salary);
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
        if (salary < 460){
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
        this.salary = salary;
    }

    private void setFirstName(String firstName) {
        if (firstName.length() < 3){
            throw new IllegalArgumentException("First name cannot be less than 3 symbols");
        }
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        if (lastName.length() < 3){
            throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
        }
        this.lastName = lastName;
    }

    private void setAge(int age) {
        if (age < 1){
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
        this.age = age;
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
