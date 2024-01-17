package pro.sky.homework19;

import org.springframework.stereotype.Service;
import pro.sky.homework19.exeptions.BadRequestException;
import pro.sky.homework19.exeptions.EmployeeAlreadyAddedException;
import pro.sky.homework19.exeptions.EmployeeNotFoundException;
import pro.sky.homework19.exeptions.EmployeeStorageIsFullException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
        Employee e1 = new Employee("Мирон", "Леонов");
		Employee e2 = new Employee("Иван", "Маслов");
		Employee e3 = new Employee("Максим", "Смирнов");
		Employee e4 = new Employee("Милана", "Елисеева");
		Employee e5 = new Employee("Елизавета", "Ушакова");
		Employee e6 = new Employee("Иван", "Колосов");
		Employee e7 = new Employee("Артемий", "Лапшин");
		Employee e8 = new Employee("Даниил", "Титов");
		Employee e9 = new Employee("Давид", "Соловьев");
		Employee e10 = new Employee("Анастасия", "Анисимова");
    public Map<String, Employee> employeeMap = new HashMap<>(Map.of(
            e1.getHashKey(), e1,
            e2.getHashKey(), e2,
            e3.getHashKey(), e3,
            e4.getHashKey(), e4,
            e5.getHashKey(), e5,
            e6.getHashKey(), e6,
            e7.getHashKey(), e7,
            e8.getHashKey(), e8,
            e9.getHashKey(), e9,
            e10.getHashKey(), e10
    ));
    final int MAX_EMPLOYEES = 15;

    public void addEmployee(String firstName, String lastName) {
        if (firstName.isBlank() || lastName.isBlank() || !firstName.matches("^[а-яА-я]+$") ||
                !lastName.matches("^[а-яА-я]+$")) {
            throw new BadRequestException();
        }
        if (employeeMap.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }
        Employee empl = new Employee(firstName, lastName);
        if (employeeMap.containsValue(empl)) {
            throw new EmployeeAlreadyAddedException();
        } else {
            employeeMap.put(empl.getHashKey(), empl);
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            throw new BadRequestException();
        }
        String hashKey = (firstName + lastName).toLowerCase();
        if (employeeMap.containsKey(hashKey)) {
            return employeeMap.get(hashKey);
        } else {
            throw new EmployeeNotFoundException();
        }
    }
    public void removeEmployee(String firstName, String lastName) {
        Employee empl = findEmployee(firstName, lastName);
        employeeMap.remove(empl.getHashKey());
    }

}
