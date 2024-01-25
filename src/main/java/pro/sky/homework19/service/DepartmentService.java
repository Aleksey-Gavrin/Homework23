package pro.sky.homework19.service;

import org.springframework.stereotype.Service;
import pro.sky.homework19.Homework19Application;
import pro.sky.homework19.exeptions.EmployeeNotFoundException;
import pro.sky.homework19.model.Employee;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class DepartmentService {
    private final EmployeeService service = Homework19Application.service;
        public Employee getEmplWithMinSalaryInDptmnt(Integer department) {
        return service.getEmployeeMap().values()
                .stream()
                .filter(empl ->  empl.getDepartment().equals(department))
                .min(Comparator.comparingDouble(empl -> empl.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public Employee getEmplWithMaxSalaryInDptmnt(Integer department) {
        return service.getEmployeeMap().values()
                .stream()
                .filter(empl ->  empl.getDepartment().equals(department))
                .max(Comparator.comparingDouble(empl -> empl.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }
    public List<Employee> getAllEmplInDptmnt(Integer department) {
        return service.getEmployeeMap().values()
                .stream()
                .filter(empl -> empl.getDepartment().equals(department))
                .collect(Collectors.toList());

    }
    public Map<Integer, List<Employee>> getAllEmplByDptmnt() {
                Map<Integer, List<Employee>> resultMap = new HashMap<>();
                service.getEmployeeMap().values()
                        .forEach(empl -> {
                            Integer department = empl.getDepartment();
                                if (resultMap.containsKey(department)) {
                                    resultMap.get(department).add(empl);
                            }   else {
                                    List<Employee> emplList = new ArrayList<>();
                                    emplList.add(empl);
                                    resultMap.put(department, emplList);
                                }
                });
                return resultMap;
    }

}
