package com.test.pims.service.impl;

import com.test.pims.common.Mapper;
import com.test.pims.common.exceptions.IllegalIdException;
import com.test.pims.dao.entity.Employee;
import com.test.pims.dao.entity.Project;
import com.test.pims.dao.service.EmployeeService;
import com.test.pims.dao.service.ProjectService;
import com.test.pims.dto.employee.EmployeeDto;
import com.test.pims.dto.project.ExecutorsDto;
import com.test.pims.dto.project.ProjectDto;
import com.test.pims.dto.project.ProjectWithoutPmDto;
import com.test.pims.service.WebProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.of;

/**
 * Реализация Web сервиса для обработки данных проекта
 * <p>
 * Created by SafinaAA on 28.05.2022
 */
@RequiredArgsConstructor
@Service
public class WebProjectServiceImpl implements WebProjectService {

    private final ProjectService projectService;

    private final EmployeeService employeeService;

    private final Mapper mapper;

    /**
     * Поиск данных проекта по идентификатору
     *
     * @param id идентификатор
     * @return данные проекта
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectDto> findById(Long id) {
        Project project = projectService.findById(id);

        return of(mapper.map(project, ProjectDto.class));
    }

    /**
     * Создает новый проект
     *
     * @param projectDto данные созданного проекта
     * @return данные проекта
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<ProjectDto> create(ProjectDto projectDto) {
        Project project = projectService.create(mapper.map(projectDto, Project.class));

        return findById(project.getId());
    }

    /**
     * Обновляет данные проекта
     *
     * @param projectDto новые данные проекта
     * @return данные проекта
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<ProjectDto> update(ProjectDto projectDto) {
        Project newProject = mapper.map(projectDto, Project.class);

        List<Employee> executors = projectService.findById(newProject.getId()).getExecutors();

        newProject.setExecutors(executors);

        projectService.update(newProject);

        return findById(newProject.getId());
    }


    /**
     * Удаляет данные проекта по идентификатору
     *
     * @param id идентификатор проекта
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        projectService.delete(id);
    }

    /**
     * Возвращает список проектов
     *
     * @return список проектов
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> findAll() {
        List<Project> projects = projectService.findAll();

        return projects.stream().map(p -> mapper.map(p, ProjectDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Обновляет назначенного руководителя
     *
     * @param projectId идентификатор проекта
     * @param pmId      идентификато проектного менеджера
     * @return обновленные данные проекта
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<ProjectDto> appointPm(Long projectId, Long pmId) {
        if (projectId == null || pmId == null) {
            throw new IllegalIdException("Невозможно установить руководителя для проекта, отсутсвует идентификатор проекта или руководителя");
        }

        if (employeeService.existsById(pmId)) {
            Project project = projectService.findById(projectId);
            project.setProjectManager(Employee.builder().id(pmId).build());

            return update(mapper.map(project, ProjectDto.class));
        } else {
            throw new IllegalIdException("Невозможно установить руководителя для проекта, отстусвует проект или руководитель");
        }
    }

    /**
     * Добавление исполнителей к проекту
     *
     * @param executorsDto данные для добавления новых исполнителей
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProjectExecutors(ExecutorsDto executorsDto) {
        Project project = projectService.findById(executorsDto.getId());

        executorsDto.getIdsExecutor().stream().peek(employeeService::existsById)
                .forEach(i -> project.getExecutors().add(Employee.builder().id(i).build()));

        projectService.update(project);
    }

    /**
     * Обновление списка исполнителей проекта
     *
     * @param executorsDto данные для добавления новых исполнителей
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProjectExecutors(ExecutorsDto executorsDto) {
        Project project = projectService.findById(executorsDto.getId());

        List<Employee> executors = executorsDto.getIdsExecutor().stream()
                .map(e -> Employee.builder().id(e).build())
                .collect(Collectors.toList());

        project.setExecutors(executors);

        projectService.update(project);
    }


    /**
     * Получить список исполнителей проекта
     *
     * @param projectId идентификатор проекта
     * @return список исполнителей проекта
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> findProjectExecutors(Long projectId) {

        return projectService.findById(projectId).getExecutors().stream()
                .map(e -> mapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Получить список проектов, в которых сотрудник является исполнителем
     *
     * @param executorId идентификатор исполнителя
     * @return список проектов
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> findProjectsByExecutor(Long executorId) {
        return employeeService.findById(executorId).getProjects().stream()
                .map(p -> mapper.map(p, ProjectDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Получить список проектов, в которых сотрудник является руководителем
     *
     * @param projectManagerId идентификатор руководителя
     * @return список проектов
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProjectWithoutPmDto> findProjectsByPm(Long projectManagerId) {
        Employee employee = employeeService.findById(projectManagerId);

        return employee.getProjects().stream()
                .map(p -> mapper.map(p, ProjectWithoutPmDto.class))
                .collect(Collectors.toList());
    }
}
