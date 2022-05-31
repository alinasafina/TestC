package com.test.pims.service;

import com.test.pims.dto.employee.EmployeeDto;
import com.test.pims.dto.project.ExecutorsDto;
import com.test.pims.dto.project.ProjectDto;
import com.test.pims.dto.project.ProjectWithoutPmDto;

import java.util.List;
import java.util.Optional;

/**
 * Web сервис для обработки данных проекта
 * <p>
 * Created by SafinaAA on 27.05.2022
 */
public interface WebProjectService {

    /**
     * Поиск данных проекта по идентификатору
     *
     * @param id идентификатор
     * @return инфо проекта
     */
    Optional<ProjectDto> findById(Long id);

    /**
     * Создает новый проект
     *
     * @param projectDto данные созданного проекта
     * @return данные проекта
     */
    Optional<ProjectDto> create(ProjectDto projectDto);

    /**
     * Обновляет данные проекта
     *
     * @param projectDto новые данные проекта
     * @return данные проекта
     */
    Optional<ProjectDto> update(ProjectDto projectDto);

    /**
     * Удаляет данные проекта по идентификатору
     *
     * @param id идентификатор проекта
     */
    void delete(Long id);

    /**
     * Возвращает список проектов
     *
     * @return список проектов
     */
    List<ProjectDto> findAll();

    /**
     * Обновляет назначенного руководителя
     *
     * @param projectId идентификатор проекта
     * @param pmId      идентификато проектного менеджера
     * @return обновленные данные проекта
     */
    Optional<ProjectDto> appointPm(Long projectId, Long pmId);

    /**
     * Добавление исполнителей к проекту
     *
     * @param executorsDto данные для добавления новых исполнителей
     */
    void addProjectExecutors(ExecutorsDto executorsDto);

    /**
     * Обновление списка исполнителей проекта
     *
     * @param executorsDto данные для добавления новых исполнителей
     */
    void updateProjectExecutors(ExecutorsDto executorsDto);

    /**
     * Получить список исполнителей проекта
     *
     * @param projectId идентификатор проекта
     * @return список исполнителей проекта
     */
    List<EmployeeDto> findProjectExecutors(Long projectId);

    /**
     * Получить список проектов, в которых сотрудник является исполнителем
     *
     * @param executorId идентификатор исполнителя
     * @return список проектов
     */
    List<ProjectDto> findProjectsByExecutor(Long executorId);

    /**
     * Получить список проектов, в которых сотрудник является руководителем
     *
     * @param projectManagerId идентификатор руководителя
     * @return список проектов
     */
    List<ProjectWithoutPmDto> findProjectsByPm(Long projectManagerId);
}
