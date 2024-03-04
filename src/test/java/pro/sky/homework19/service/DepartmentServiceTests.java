package pro.sky.homework19.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.homework19.exeptions.EmployeeNotFoundException;
import pro.sky.homework19.model.Employee;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTests {

EmployeeService employeeServiceMock = mock(EmployeeService.class);
DepartmentService depService = new DepartmentService(employeeServiceMock);

    @BeforeEach
    void setUp() {
        Map<String, Employee> testMap = Map.of(
                "a", new Employee("Test1", "Test1", 10, 1),
                "b", new Employee("Test2", "Test2", 20, 1),
                "c", new Employee("Test3", "Test3", 30, 2),
                "d", new Employee("Test4", "Test4", 40, 2),
                "e", new Employee("Test5", "Test5", 50, 3)
        );
        when(employeeServiceMock.getEmployeeMap()).thenReturn(testMap);
    }

    @Test
    void getSumSalaryInDptmntTest() {
        assertEquals(30, depService.getSumSalaryInDptmnt(1), 0.001);
        assertEquals(70, depService.getSumSalaryInDptmnt(2), 0.001);
    }

    @Test
    void getMinSalaryInDptmntTest() {
        assertEquals(10, depService.getEmplWithMinSalaryInDptmnt(1).getSalary());
        assertEquals(30, depService.getEmplWithMinSalaryInDptmnt(2).getSalary());
    }

    @Test
    void getMinSalaryInDptmntTest_negative_EmployeeNotFound() {
        assertThrows(EmployeeNotFoundException.class, () ->
                depService.getEmplWithMinSalaryInDptmnt(4));
    }

    @Test
    void getMaxSalaryInDptmntTest() {
        assertEquals(20, depService.getEmplWithMaxSalaryInDptmnt(1).getSalary());
        assertEquals(40, depService.getEmplWithMaxSalaryInDptmnt(2).getSalary());
    }

    @Test
    void getMaxSalaryInDptmntTest_negative_EmployeeNotFound() {
        assertThrows(EmployeeNotFoundException.class, () ->
                depService.getEmplWithMaxSalaryInDptmnt(4));
    }

    @Test
    void getAllEmplInDptmntTest(){
        List<Employee> actual = depService.getAllEmplInDptmnt(1);
        for (Employee employee: actual) {
            assertEquals(1, employee.getDepartment());
        }
    }

    @Test
    void getAllEmplByDptmntTest() {
        Map<Integer, List<Employee>> actual = depService.getAllEmplByDptmnt();
        assertEquals(3, actual.keySet().size());
        assertTrue(actual.containsKey(1));
        assertTrue(actual.containsKey(2));
        assertTrue(actual.containsKey(3));
        assertEquals(2, actual.get(1).size());
        assertEquals(2, actual.get(2).size());
        assertEquals(1, actual.get(3).size());
    }
}
