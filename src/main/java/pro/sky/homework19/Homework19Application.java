package pro.sky.homework19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Homework19Application {
	public static EmployeeService service = new EmployeeService();
	public static void main(String[] args) {
		SpringApplication.run(Homework19Application.class, args);
		service.addEmployee("Мирон", "Леонов");
		service.addEmployee("Иван", "Маслов");
		service.addEmployee("Максим", "Смирнов");
		service.addEmployee("Милана", "Елисеева");
		service.addEmployee("Елизавета", "Ушакова");
		service.addEmployee("Иван", "Колосов");
		service.addEmployee("Артемий", "Лапшин");
		service.addEmployee("Даниил", "Титов");
		service.addEmployee("Давид", "Соловьев");
		service.addEmployee("Анастасия", "Анисимова");
		}
}
