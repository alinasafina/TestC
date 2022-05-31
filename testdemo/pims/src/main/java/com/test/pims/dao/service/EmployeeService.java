package com.test.pims.dao.service;

import com.test.pims.dao.entity.Employee;

import java.util.List;

/**
 * Служба для работы с репозиторием "Сотрудник"
 * <p>
 * Created by SafinaAA on 26.05.2022
 */
public interface EmployeeService {

    /**
     * Находит сущность "Сотрудник" по идентификатору
     *
     * @param id идентификатор
     * @return найденная сущность
     */
    Employee findById(Long id);

    /**
     * Создает сущность "Сотрудник"
     *
     * @param employee сущность
     * @return созданная сущность
     */
    Employee create(Employee employee);

    /**
     * Обновляет сущность "Сотрудник"
     *
     * @param employee сущность
     * @return обновленная сущность
     */
    Employee update(Employee employee);

    /**
     * Удаляет сущность "Сотрудник" по идентификатору
     *
     * @param id идентификатор сущности
     */
    void delete(Long id);

    /**
     * Проверка сущетсвует ли сотрудник с заданным идентификаторов
     *
     * @param id идентификатор проекта
     * @return true/false существут/не существует сотрудник
     */
    boolean existsById(Long id);

    /**
     * Получение списка сотрудников
     *
     * @return список сотрудников
     */
    List<Employee> findAll();
}
