package com.test.pims.dao.service.impl;

import com.test.pims.common.exceptions.IllegalIdException;
import com.test.pims.dao.entity.Employee;
import com.test.pims.dao.repository.EmployeeRepository;
import com.test.pims.dao.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

/**
 * Реализация службы для работы с репозиторием "Сотрудник" {@link com.test.pims.dao.repository.EmployeeRepository}
 * <p>
 * Created by SafinaAA on 26.05.2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Находит сущность "Сотрудник" по идентификатору
     *
     * @param id идентификатор
     * @return найденная сущность
     */
    @Override
    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalIdException(format("Сотрудника с идентификатором %s не существует", id)));
    }

    /**
     * Создает сущность "Сотрудник"
     *
     * @param employee сущность
     * @return созданная сущность
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Employee create(Employee employee) {
        ofNullable(employee)
                .orElseThrow(() -> new IllegalIdException("Невозможно создать, данные отсутсвуют"));

        if (employee.getId() != null)
            throw new IllegalIdException("Невозможно создать, идентификатор должен быть пуст");

        if(isNotValid(employee)){
            throw new IllegalIdException("Невозможно создать, обязательные поля (Фамилия/Имя) не заполнены");
        }

        return employeeRepository.save(employee);
    }

    /**
     * Обновляет сущность "Сотрудник"
     *
     * @param employee сущность
     * @return обновленная сущность
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Employee update(Employee employee) {
        Long id = ofNullable(employee)
                .map(Employee::getId)
                .orElseThrow(() -> new IllegalIdException("Невозможно обновить, не указан идентификатор"));

        boolean projectExist = employeeRepository.existsById(id);

        if (!projectExist) {
            throw new IllegalIdException(format("Невозможно обновить, сущности с идентификатором %s не существует", id));
        }

        if(isNotValid(employee)){
            throw new IllegalIdException("Невозможно обновить, обязательные поля (Фамилия/Имя) не заполнены");
        }

        return employeeRepository.save(employee);
    }

    /**
     * Удаляет сущность "Сотрудник" по идентификатору
     *
     * @param id идентификатор сущности
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        ofNullable(id).orElseThrow(() -> new IllegalIdException("Невозможно удалить, идентификатор пуст"));

        if (!employeeRepository.existsById(id)) {
            throw new IllegalIdException(format("Сотрудника с индентификатором %s не существует", id));
        }

        employeeRepository.deleteById(id);
    }

    /**
     * Проверка сущетсвует ли сотрудник с заданным идентификаторов
     *
     * @param id идентификатор проекта
     * @return true/false существут/не существует сотрудник
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return ofNullable(id).map(employeeRepository::existsById)
                .orElseThrow(() -> new IllegalIdException("Отсуствует идентификатор"));
    }

    /**
     * Получение списка сотрудников
     *
     * @return список сотрудников
     */
    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * Проверка заполненности обязательных полей
     *
     * @param employee сотрудник
     * @return true/false валидно/не валидно
     */
    private boolean isNotValid(Employee employee){
        return employee.getFirstName().isBlank() || employee.getLastName().isBlank();
    }
}
