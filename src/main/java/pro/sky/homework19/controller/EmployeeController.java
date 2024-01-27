package pro.sky.homework19.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework19.Homework19Application;
import pro.sky.homework19.service.EmployeeService;
import pro.sky.homework19.model.Employee;

import java.util.Collection;
@RestController
@RequestMapping ("/employee")
public class EmployeeController {
   private final EmployeeService service = Homework19Application.service;
    @GetMapping
    public String welcomeUser() {
        return  "Программа по управлению списком персонала.<br>" +
                "Команды: (/find  ; /remove) - необходимые параметры: (name=... ; surname=...).<br>" +
                "Команда: (/add) - необходимые параметры: (name=... ; surname=... ; salary=... ; department=...)<br>" +
                "Также доступна команда (/printAll) параметры для нее не требуются.";
    }
    @GetMapping ("/find")
    public String findEmployee(@RequestParam ("name") String firstName, @RequestParam ("surname") String lastName) {
            return service.findEmployee(firstName, lastName).toString();
            }
    @GetMapping ("/remove")
    public String removeEmployee(@RequestParam ("name") String firstName, @RequestParam ("surname") String lastName) {
            return service.removeEmployee(firstName, lastName) + " удален из списка сотрудников.";
            }
    @GetMapping ("/add")
    public String addEmployee(@RequestParam ("name") String firstName, @RequestParam ("surname") String lastName,
                              @RequestParam ("salary") double salary, @RequestParam ("department") int department) {
            return service.addEmployee(firstName, lastName, salary, department) + " добавлен в список сотрудников.";
            }
    @GetMapping ("/printAll")
    public Collection<Employee> printAll() {
            return service.printAll();
    }
}
