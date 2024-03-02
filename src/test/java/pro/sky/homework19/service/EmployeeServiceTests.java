package pro.sky.homework19.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.homework19.exeptions.BadRequestException;
import pro.sky.homework19.exeptions.EmployeeAlreadyAddedException;
import pro.sky.homework19.exeptions.EmployeeNotFoundException;
import pro.sky.homework19.exeptions.EmployeeStorageIsFullException;
import pro.sky.homework19.model.Employee;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTests {

    EmployeeService service = new EmployeeService();

    @BeforeEach
    void setUp() {
        service.addEmployee("firstNAME", "lastNAME", 1, 1);
    }

    @Test
    void add_positive_validated_input() {
        Collection<Employee> actual = service.printAll();
        assertEquals(1, actual.size());
        Employee actualEmployee = actual.iterator().next();
        assertEquals("Firstname", actualEmployee.getFirstName());
        assertEquals("Lastname", actualEmployee.getLastName());
        assertEquals(1, actualEmployee.getSalary());
        assertEquals(1, actualEmployee.getDepartment());
    }

    @Test
    void add_negative_StorageIsFull() {
        for (int i = 0; i < 9; i++) {
            char symbol = (char)('a' + i);
            service.addEmployee("frstName" + symbol, "lastName", 1,1);
        }
        assertThrows(EmployeeStorageIsFullException.class, () ->
                service.addEmployee("Over", "Limit", 1,1));
    }

    @Test
    void add_negative_EmployeeAlreadyAdded() {
        assertThrows(EmployeeAlreadyAddedException.class, () ->
                service.addEmployee("firstNAME", "lastNAME", 1, 1));
    }

    @Test
    void add_negative_BadRequest() {
        assertThrows(BadRequestException.class, () ->
                service.addEmployee("  ", "lastName", 1, 1));
    }

    @Test
    void find_positive() {
        Employee actual = service.findEmployee("Firstname", "Lastname");
        assertEquals("Firstname", actual.getFirstName());
        assertEquals("Lastname", actual.getLastName());
    }

    @Test
    void find_negative_BadRequest() {
        assertThrows(BadRequestException.class, () ->
                service.findEmployee("  ", "lastName"));
    }

    @Test
    void find_negative_EmployeeNotFound() {
        assertThrows(EmployeeNotFoundException.class, () ->
                service.findEmployee("Unknown", "Last"));
    }

    @Test
    void remove_positive() {
        Collection<Employee> actual = service.printAll();
        assertEquals(1, actual.size());
        service.removeEmployee("Firstname", "Lastname");
        assertEquals(0, actual.size());
    }

    @Test
    void remove_negative_BadRequest() {
        assertThrows(BadRequestException.class, () ->
                service.removeEmployee("Firstname", "  "));
    }

    @Test
    void remove_negative_EmployeeNotFound() {
        assertThrows(EmployeeNotFoundException.class, () ->
                service.removeEmployee("Unknown", "Last"));
    }
}
