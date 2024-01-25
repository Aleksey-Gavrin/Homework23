package pro.sky.homework19.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework19.Homework19Application;
import pro.sky.homework19.exeptions.BadRequestException;
import pro.sky.homework19.service.DepartmentService;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentService service = Homework19Application.deptmntService;
    @GetMapping
    public String welcomeUser() {
        return  "Предоставление информации по департаментам.<br>" +
                "Команда: (/min-salary) - необходимый параметр: (departmentId диапазон значений [1-5]).<br>" +
                "Возвращает сотрудника с минимальной зарплатой в отделе.<br>" +
                "Команда: (/max-salary) - необходимый параметр: (departmentId диапазон значений [1-5]).<br>" +
                "Возвращает сотрудника с максимальной зарплатой в отделе.<br>" +
                "Команда: (/all) - c параметром: (departmentId диапазон значений [1-5]).<br>" +
                "Выводит список всех сотрудников отдела.<br>" +
                "Команда: (/all) - без параметра.<br>" +
                "Выводит полный список сотрудников с группировкой по отделам.";
    }
    @GetMapping(path = "/min-salary")
    public String getMinSalary(@RequestParam ("departmentId") Integer department){
        if (department < 1 || department > 5) {
            throw new BadRequestException();
        }
        return "Сотрудник с минимальной зарплатой в отделе:<br>" + service.getEmplWithMinSalaryInDptmnt(department);
    }
    @GetMapping(path = "/max-salary")
    public String getMaxSalary(@RequestParam ("departmentId") Integer department){
        if (department < 1 || department > 5) {
            throw new BadRequestException();
        }
        return "Сотрудник с максимальной зарплатой в отделе:<br>" + service.getEmplWithMaxSalaryInDptmnt(department);
    }
    @GetMapping(path = "/all", params = "departmentId")
    public String getAllInDptmnt(@RequestParam ("departmentId") Integer department){
        if (department < 1 || department > 5) {
            throw new BadRequestException();
        }
        return "Все сотрудники отдела:<br>" + service.getAllEmplInDptmnt(department);
    }
    @GetMapping(path = "/all")
    public String getAll(){
        return "Все сотрудники:<br>" + service.getAllEmplByDptmnt();
    }
}
