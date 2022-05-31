package com.test.pims.controller;

import com.test.pims.dto.employee.EmployeeDto;
import com.test.pims.service.WebEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

/**
 * Контроллер для работы с данными сотрудника
 * <p>
 * Created by SafinaAA on 27.05.2022
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Работа с данными сотрудников")
@RequestMapping("api/employee")
public class EmployeeController {

    public final WebEmployeeService employeeService;

    /**
     * Поиск сотрудника по идентификатору
     *
     * @param id идентификатор
     * @return найденный сотрудник
     */
    @Operation(summary = "Поиск сотрудника по идентификатору")
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable("id") long id) {
        log.info("Получен запрос на поиск сотрудника по идентфикатору {}", id);

        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    /**
     * Добавление нового сотрудника
     *
     * @param employeeDto данные сотрудника
     * @return данные сотрудника
     */
    @Operation(summary = "Добавление нового сотрудника")
    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) {
        log.info("Получен запрос на добавление нового сотрудника {}", employeeDto);

        return employeeService.create(employeeDto)
                .map(ResponseEntity::ok)
                .orElse(badRequest().build());
    }

    /**
     * Обновление данных сотрудника
     *
     * @param employeeDto данные сотрудника
     * @return данные сотрудника
     */
    @Operation(summary = "Обновление данных сотрудника")
    @PutMapping
    public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employeeDto) {
        log.info("Получен запрос на обновление данных сотрудника {}", employeeDto);

        return employeeService.update(employeeDto)
                .map(ResponseEntity::ok)
                .orElse(badRequest().build());
    }


    /**
     * Удаление данных сотрудника по идентификатору
     *
     * @param id идентификатор сотрудника
     */
    @Operation(summary = "Удаление данных сотрудника по идентификатору")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("Получен запрос на удаление сотрудника по идентфикатору {}", id);

        employeeService.delete(id);

        return ok().build();
    }

    /**
     * Получение списка сотрудников
     *
     * @return список сотрудников
     */
    @Operation(summary = "Получение списка сотрудников")
    @GetMapping(value = "/all")
    public ResponseEntity<List<EmployeeDto>> findAll() {
        log.info("Получен запрос на получение списка сотрудников");

        return ResponseEntity.ok(employeeService.findAll());
    }
}
