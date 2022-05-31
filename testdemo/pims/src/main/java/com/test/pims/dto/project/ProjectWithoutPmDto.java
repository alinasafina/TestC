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
import java.util.List;

/**
 * Web представления основной ифнормации о проекте по исполнителю (без списка исполнителей)
 * <p>
 * Created by SafinaAA on 30.05.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWithoutPmDto {

    /**
     * Идентификатор
     */
    @JsonProperty("id")
    private Long id;

    /**
     * Наименование проекта
     */
    @JsonProperty("")
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
     * Исполнители проекта
     */
    @JsonProperty("executors")
    private List<EmployeeDto> executors;
}
