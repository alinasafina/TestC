package com.test.pims.controller;

import com.test.pims.dto.employee.EmployeeDto;
import com.test.pims.dto.project.ExecutorsDto;
import com.test.pims.dto.project.ProjectDto;
import com.test.pims.dto.project.ProjectWithoutPmDto;
import com.test.pims.service.WebProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

/**
 * Контроллер для работы с данными проекта
 * <p>
 * Created by SafinaAA on 27.05.2022
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Работа с данными проекта")
@RequestMapping("api/project")
public class ProjectController {

    public final WebProjectService projectService;

    /**
     * Поиск данных проекта по идентификатору
     *
     * @param id идентификатор
     * @return инфо проекта
     */
    @Operation(summary = "Поиск данных проекта по идентификатору")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable("id") long id) {
        log.info("Получен запрос на поиск данных проекта по идентификатору {}", id);

        return projectService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    /**
     * Добавление нового проекта
     *
     * @param projectDto данные созданного проекта
     * @return данные проекта
     */
    @Operation(summary = "Добавление нового проекта")
    @PostMapping
    public ResponseEntity<ProjectDto> create(@RequestBody ProjectDto projectDto) {
        log.info("Получен запрос на добавление нового проекта {}", projectDto);

        return projectService.create(projectDto)
                .map(ResponseEntity::ok)
                .orElse(badRequest().build());
    }

    /**
     * Обновление данных проекта
     *
     * @param projectDto новые данные проекта
     * @return данные проекта
     */
    @Operation(summary = "Обновление данных проекта")
    @PutMapping
    public ResponseEntity<ProjectDto> update(@RequestBody ProjectDto projectDto) {
        log.info("Получен запрос на обновление данных проекта {}", projectDto);

        return projectService.update(projectDto)
                .map(ResponseEntity::ok)
                .orElse(badRequest().build());
    }

    /**
     * Удаление данные проекта по идентификатору
     *
     * @param id идентификатор проекта
     */
    @Operation(summary = "Удаление данные проекта по идентификатору")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("Получен запрос на удаление проекта по идентфикатору {}", id);

        projectService.delete(id);

        return ok().build();
    }

    /**
     * Список всех проектов
     *
     * @return список проектов
     */
    @Operation(summary = "Список проектов")
    @GetMapping(value = "/all")
    public ResponseEntity<List<ProjectDto>> findAll() {
        log.info("Получен запрос на получение списка проектов");

        return ok(projectService.findAll());
    }

    /**
     * Обновление назначенного руководителя
     *
     * @param projectId идентификатор проекта
     * @param pmId      идентификато проектного менеджера
     * @return обновленные данные проекта
     */
    @Operation(summary = "Обновление руководителя на проекте")
    @GetMapping(value = "/appoint-pm/{projectId}/{pmId}")
    public ResponseEntity<ProjectDto> appointPm(@PathVariable("projectId") Long projectId,
                                                @PathVariable("pmId") Long pmId) {
        log.info("Получен запрос на обновление руководителя {} на проекте {}", pmId, projectId);

        return projectService.appointPm(projectId, pmId)
                .map(ResponseEntity::ok)
                .orElse(badRequest().build());
    }

    /**
     * Добавление исполнителей к проекту
     *
     * @param executorsDto данные для добавления новых исполнителей
     */
    @Operation(summary = "Добавление исполнителей к проекту")
    @PutMapping(value = "/executors/add")
    public ResponseEntity<Void> addProjectExecutors(@RequestBody ExecutorsDto executorsDto) {
        log.info("Получен запрос на добавление исполнителей к проекту {}", executorsDto);

        projectService.addProjectExecutors(executorsDto);

        return ok().build();
    }

    /**
     * Обновление списка исполнителей проекта
     *
     * @param executorsDto данные для добавления новых исполнителей
     */
    @Operation(summary = "Обновление списка исполнителей проекта")
    @PutMapping(value = "/executors/update")
    public ResponseEntity<Void> updateProjectExecutors(@RequestBody ExecutorsDto executorsDto) {
        log.info("Получен запрос на обновление списка исполнителей проекта {}", executorsDto);

        projectService.updateProjectExecutors(executorsDto);

        return ok().build();
    }

    /**
     * Получение списка исполнителей проекта
     *
     * @param projectId идентификатор проекта
     * @return список исполнителей проекта
     */
    @Operation(summary = "Получение списка исполнителей проекта")
    @GetMapping(value = "/search/executors/{projectId}")
    public ResponseEntity<List<EmployeeDto>> findProjectExecutors(@PathVariable("projectId") Long projectId) {
        log.info("Получен запрос на получение списка исполнителей проекта с идентфиикатором {}", projectId);

        return ResponseEntity.ok(projectService.findProjectExecutors(projectId));
    }

    /**
     * Получение списка проектов, в которых сотрудник является исполнителем
     *
     * @param executorId идентификатор исполнителя
     * @return список проектов
     */
    @Operation(summary = "Получение списка проектов, в которых сотрудник является исполнителем")
    @GetMapping(value = "/search/by-executor/{executorId}")
    public ResponseEntity<List<ProjectDto>> findProjectsByExecutor(@PathVariable("executorId") Long executorId) {
        log.info("Получен запрос на получение списка проектов, в которых сотрудник является исполнителем с идентификатором {}", executorId);

        return ResponseEntity.ok(projectService.findProjectsByExecutor(executorId));
    }

    /**
     * Получение списка проектов, в которых сотрудник является руководителем
     *
     * @param projectManagerId идентификатор руководителя
     * @return список проектов
     */
    @Operation(summary = "Получение списка проектов, в которых сотрудник является руководителем")
    @GetMapping(value = "/search/by-project-manager/{projectManagerId}")
    public ResponseEntity<List<ProjectWithoutPmDto>> findProjectsByPm(@PathVariable("projectManagerId") Long projectManagerId) {
        log.info("Получен запрос на получение списка проектов, в которых сотрудник является руководителем с идентификатором {}", projectManagerId);

        return ResponseEntity.ok(projectService.findProjectsByPm(projectManagerId));
    }
}
