package com.test.pims.service;

import com.test.pims.dao.entity.Employee;
import com.test.pims.dto.employee.EmployeeDto;

import java.util.List;
import java.util.Optional;

/**
 * Web сервис для обработки данных сотрудника
 * <p>
 * Created by SafinaAA on 27.05.2022
 */
public interface WebEmployeeService {

    /**
     * Поиск данных сотрудника по идентификатору
     *
     * @param id идентификатор
     * @return найденный сотрудник
     */
    Optional<EmployeeDto> findById(Long id);

    /**
     * Добавление нового сотрудника
     *
     * @param employeeDto данные сотрудника
     * @return данные сотрудника
     */
    Optional<EmployeeDto> create(EmployeeDto employeeDto);

    /**
     * Обновление данных сотрудника
     *
     * @param employeeDto данные сотрудника
     * @return данные сотрудника
     */
    Optional<EmployeeDto> update(EmployeeDto employeeDto);

    /**
     * Удаление данных сотрудника по идентификатору
     *
     * @param id идентификатор сотрудника
     */
    void delete(Long id);

    /**
     * Получение списка сотрудников
     *
     * @return список сотрудников
     */
    List<EmployeeDto> findAll();
}
