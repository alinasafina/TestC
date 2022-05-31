package com.test.pims.dto.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.pims.dao.entity.ProjectStatus;
import com.test.pims.dto.employee.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

/**
 * Web представления основной ифнормации о проекте (без списка исполнителей)
 * <p>
 * Created by SafinaAA on 29.05.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    /**
     * Идентификатор
     */
    @JsonProperty("id")
    private Long id;

    /**
     * Наименование проекта
     */
    @JsonProperty("name")
    private String name;

    /**
     * Дата начала проекта
     */
    @JsonProperty("startDate")
    private LocalDate startDate;

    /**
     * Дата окончания проекта
     */
    @JsonProperty("finishDate")
    private Date finishDate;

    /**
     * Статус проекта
     */
    @JsonProperty("status")
    private ProjectStatus status;

    /**
     * Руководитель проекта
     */
    @JsonProperty("projectManager")
    private EmployeeDto projectManager;

}
