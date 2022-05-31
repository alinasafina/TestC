package com.test.pims.service.impl;

import com.test.pims.common.Mapper;
import com.test.pims.dao.entity.Employee;
import com.test.pims.dao.service.EmployeeService;
import com.test.pims.dto.employee.EmployeeDto;
import com.test.pims.service.WebEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.of;

/**
 * Реализация Web сервиса для обработки данных сотрудника
 * <p>
 * Created by SafinaAA on 27.05.2022
 */
@RequiredArgsConstructor
@Service
public class WebEmployeeServiceImpl implements WebEmployeeService {

    private final EmployeeService employeeService;

    private final Mapper mapper;

    /**
     * Поиск данных сотрудника по идентификатору
     *
     * @param id идентификатор
     * @return найденный сотрудник
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDto> findById(Long id) {
        Employee employee = employeeService.findById(id);

        return of(mapper.map(employee, EmployeeDto.class));
    }

    /**
     * Добавление нового сотрудника
     *
     * @param employeeDto данные сотрудника
     * @return данные сотрудника
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<EmployeeDto> create(EmployeeDto employeeDto) {
        Employee employee = employeeService.create(mapper.map(employeeDto, Employee.class));

        return findById(employee.getId());
    }

    /**
     * Обновление данных сотрудника
     *
     * @param employeeDto новые данные сотрудника
     * @return данные сотрудника
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<EmployeeDto> update(EmployeeDto employeeDto) {
        Employee employee = employeeService.update(mapper.map(employeeDto, Employee.class));

        return findById(employee.getId());
    }

    /**
     * Удаление данных сотрудника по идентификатору
     *
     * @param id идентификатор сотрудника
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        employeeService.delete(id);
    }

    /**
     * Получение списка сотрудников
     *
     * @return список сотрудников
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> findAll() {
        return employeeService.findAll().stream()
                .map(e -> mapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }
}
