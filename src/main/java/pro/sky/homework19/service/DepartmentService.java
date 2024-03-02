package pro.sky.homework19.service;

import org.springframework.stereotype.Service;
import pro.sky.homework19.exeptions.EmployeeNotFoundException;
import pro.sky.homework19.model.Employee;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class DepartmentService {

    private final EmployeeService service;

    public DepartmentService(EmployeeService service) {
        this.service = service;
    }

    public double getSumSalaryInDptmnt(int department) {
         return service.getEmployeeMap().values()
                .stream()
                .filter(empl -> empl.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public Employee getEmplWithMinSalaryInDptmnt(int department) {
        return service.getEmployeeMap().values()
                .stream()
                .filter(empl ->  empl.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee getEmplWithMaxSalaryInDptmnt(int department) {
        return service.getEmployeeMap().values()
                .stream()
                .filter(empl ->  empl.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAllEmplInDptmnt(int department) {
        return service.getEmployeeMap().values()
                .stream()
                .filter(empl -> empl.getDepartment() == department)
                .collect(Collectors.toList());

    }

    public Map<Integer, List<Employee>> getAllEmplByDptmnt() {
        return service.getEmployeeMap().values()
                        .stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
