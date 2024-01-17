package pro.sky.homework19;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private  String lastName;
    private final String hashKey;
    public Employee (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hashKey = (firstName + lastName).toLowerCase();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getHashKey() { return hashKey;}
    @Override
    public String toString() {
        return "Сотрудник: " + firstName + " " + lastName;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(firstName, lastName);
    }
}
