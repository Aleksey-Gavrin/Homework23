package pro.sky.homework19.controller;


import org.springframework.web.bind.annotation.*;
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
                "Команда: ({id}/salary/sum) - необходимый параметр: ({id} диапазон значений [1-5]).<br>" +
                "Возвращает сумму зарплат всех сотрудников в отделе.<br>" +
                "Команда: ({id}/salary/min) - необходимый параметр: ({id} диапазон значений [1-5]).<br>" +
                "Возвращает сотрудника с минимальной зарплатой в отделе.<br>" +
                "Команда: ({id}/salary/max) - необходимый параметр: ({id} диапазон значений [1-5]).<br>" +
                "Возвращает сотрудника с максимальной зарплатой в отделе.<br>" +
                "Команда: ({id}/employees) - c параметром: ({id} диапазон значений [1-5]).<br>" +
                "Выводит список всех сотрудников отдела.<br>" +
                "Команда: (/employees) - без параметра.<br>" +
                "Выводит полный список сотрудников с группировкой по отделам.";
    }
    @GetMapping(path = "{id}/salary/sum")
    public String getSumSalary(@PathVariable("id") int department){
        if (department < 1 || department > 5) {
            throw new BadRequestException();
        }
        return "Сумма зарплат по отделу " + department + ":<br>" + service.getSumSalaryInDptmnt(department);
    }

    @GetMapping(path = "{id}/salary/min")
    public String getMinSalary(@PathVariable("id") int department){
        if (department < 1 || department > 5) {
            throw new BadRequestException();
        }
        return "Сотрудник с минимальной зарплатой в отделе:<br>" + service.getEmplWithMinSalaryInDptmnt(department);
    }

    @GetMapping(path = "{id}/salary/max")
    public String getMaxSalary(@PathVariable("id") int department){
        if (department < 1 || department > 5) {
            throw new BadRequestException();
        }
        return "Сотрудник с максимальной зарплатой в отделе:<br>" + service.getEmplWithMaxSalaryInDptmnt(department);
    }

    @GetMapping(path = "{id}/employees")
    public String getAllInDptmnt(@PathVariable("id") int department){
        if (department < 1 || department > 5) {
            throw new BadRequestException();
        }
        return "Все сотрудники отдела:<br>" + service.getAllEmplInDptmnt(department);
    }

    @GetMapping(path = "/employees")
    public String getAll(){
        return "Все сотрудники:<br>" + service.getAllEmplByDptmnt();
    }
}
