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
    public static Map<String, Employee> employeeMap = new HashMap<>();
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
