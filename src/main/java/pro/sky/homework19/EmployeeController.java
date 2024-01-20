package pro.sky.homework19;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping ("/employee")
public class EmployeeController {
    EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    @GetMapping
    public String welcomeUser() {
        return "Программа по управлению списком персонала. Команды: (/find ; /add ; /remove). Необходимые параметры: (name=... ; " +
                "surname=...). Также доступна команда (/printAll) параметры для нее не требуются.";
    }
    @GetMapping ("/find")
    public String findEmployee(@RequestParam ("name") String firstName, @RequestParam ("surname") String lastName) {
            return service.findEmployee(firstName, lastName).toString();
            }
    @GetMapping ("/remove")
    public String removeEmployee(@RequestParam ("name") String firstName, @RequestParam ("surname") String lastName) {
            service.removeEmployee(firstName, lastName);
            return "Сотрудник: " + firstName + " " + lastName + " удален из списка сотрудников.";
            }
    @GetMapping ("/add")
    public String addEmployee(@RequestParam ("name") String firstName, @RequestParam ("surname") String lastName) {
            service.addEmployee(firstName, lastName);
            return "Сотрудник: " + firstName + " " + lastName + " добавлен в список сотрудников.";
            }
    @GetMapping ("/printAll")
    public StringBuilder printAllEmployees() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry <String, Employee> entry: service.employeeMap.entrySet()) {
            result.append(entry.getValue().toString()).append(" || ");}
        return result;
    }
}
