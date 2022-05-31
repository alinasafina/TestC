package com.test.pims.service;

import com.test.pims.common.Mapper;
import com.test.pims.common.exceptions.IllegalIdException;
import com.test.pims.dao.entity.Employee;
import com.test.pims.dao.service.EmployeeService;
import com.test.pims.dto.employee.EmployeeDto;
import com.test.pims.service.impl.WebEmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Created by SafinaAA on 30.05.2022
 */
@ExtendWith(MockitoExtension.class)
public class WebEmployeeServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private WebEmployeeServiceImpl webEmployeeService;

    /**
     * Тест метода по поиску сотрудника по идентификатору
     * <p>
     * Успешно получен сотрудник по идентификатору
     */
    @Test
    public void findById() {
        Employee employee = getEmployee();
        Long id = employee.getId();

        when(employeeService.findById(id)).thenReturn(employee);
        when(mapper.map(any(Employee.class), eq(EmployeeDto.class))).thenReturn(getEmployeeDto());

        Optional<EmployeeDto> result = webEmployeeService.findById(id);

        assertTrue(result.isPresent());

        assertEquals(employee.getId(), result.get().getId());
        assertEquals(employee.getFirstName(), result.get().getFirstName());
        assertEquals(employee.getLastName(), result.get().getLastName());
        assertEquals(employee.getPatronymic(), result.get().getPatronymic());

        verify(employeeService).findById(anyLong());
        verify(mapper).map(any(Employee.class), eq(EmployeeDto.class));
    }

    /**
     * Тест метода по поиску сотрудника по идентификатору
     * <p>
     * Ошибка: сотрудник не найден
     */
    @Test
    public void findByIdNotFind() {
        Long id = getEmployee().getId();
        String errorMes = format("Сотрудника с идентификатором %s не существует", id);

        when(employeeService.findById(id)).thenThrow(new IllegalIdException(errorMes));

        assertThrows(IllegalArgumentException.class, () -> webEmployeeService.findById(id));
    }

    /**
     * Тест метода по созданию нового сотрудника
     * <p>
     * Успешно
     */
    @Test
    public void create() {
        Employee employee = getEmployee();
        employee.setId(null);

        Employee employeeSaved = getEmployee();

        when(mapper.map(any(EmployeeDto.class), eq(Employee.class))).thenReturn(employee);
        when(employeeService.create(employee)).thenReturn(employeeSaved);
        when(employeeService.findById(employeeSaved.getId())).thenReturn(employee);
        when(mapper.map(any(Employee.class), eq(EmployeeDto.class))).thenReturn(getEmployeeDto());

        Optional<EmployeeDto> result = webEmployeeService.create(getEmployeeDto());

        assertTrue(result.isPresent());

        assertEquals(employeeSaved.getId(), result.get().getId());
        assertEquals(employeeSaved.getFirstName(), result.get().getFirstName());
        assertEquals(employeeSaved.getLastName(), result.get().getLastName());
        assertEquals(employeeSaved.getPatronymic(), result.get().getPatronymic());

        verify(mapper).map(any(EmployeeDto.class), eq(Employee.class));
        verify(employeeService).create(any(Employee.class));
        verify(employeeService).findById(anyLong());
        verify(mapper).map(any(Employee.class), eq(EmployeeDto.class));
    }

    /**
     * Тест метода по обновлению инфы сотрудника сотрудника
     * <p>
     * Успешно
     */
    @Test
    public void update() {
        Employee employee = getEmployee();

        when(mapper.map(any(EmployeeDto.class), eq(Employee.class))).thenReturn(employee);
        when(employeeService.update(employee)).thenReturn(employee);
        when(employeeService.findById(employee.getId())).thenReturn(employee);
        when(mapper.map(any(Employee.class), eq(EmployeeDto.class))).thenReturn(getEmployeeDto());

        Optional<EmployeeDto> result = webEmployeeService.update(getEmployeeDto());

        assertTrue(result.isPresent());

        assertEquals(employee.getId(), result.get().getId());
        assertEquals(employee.getFirstName(), result.get().getFirstName());
        assertEquals(employee.getLastName(), result.get().getLastName());
        assertEquals(employee.getPatronymic(), result.get().getPatronymic());

        verify(mapper).map(any(EmployeeDto.class), eq(Employee.class));
        verify(employeeService).update(any(Employee.class));
        verify(employeeService).findById(anyLong());
        verify(mapper).map(any(Employee.class), eq(EmployeeDto.class));
    }

    /**
     * Тест метода по удалению инфы сотрудника
     * <p>
     * Успешно
     */
    @Test
    public void delete() {
        Long id = 1L;

        webEmployeeService.delete(id);

        verify(employeeService).delete(anyLong());
    }

    /**
     * Тест метода по получению списка сотрудников
     * <p>
     * Успешно
     */
    @Test
    public void findAll() {
        Employee employee = getEmployee();
        EmployeeDto employeeRes = getEmployeeDto();

        when(employeeService.findAll()).thenReturn(Collections.singletonList(employee));
        when(mapper.map(any(Employee.class), eq(EmployeeDto.class))).thenReturn(employeeRes);

        webEmployeeService.findAll();

        verify(employeeService).findAll();
        verify(mapper).map(any(Employee.class), eq(EmployeeDto.class));
    }

    private Employee getEmployee() {
        return Employee.builder().id(1L).firstName("Тестов").lastName("Тест").patronymic("Тестович").build();
    }

    private EmployeeDto getEmployeeDto() {
        return EmployeeDto.builder().id(1L).firstName("Тестов").lastName("Тест").patronymic("Тестович").build();
    }
}
