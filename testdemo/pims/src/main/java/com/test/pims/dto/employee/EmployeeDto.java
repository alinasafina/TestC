package com.test.pims.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Web представления для сотрудника
 * <p>
 * Created by SafinaAA on 27.05.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    /**
     * Идентификатор
     */
    @JsonProperty("id")
    private Long id;

    /**
     * Фамилия
     */
    @JsonProperty("lastName")
    private String lastName;

    /**
     * Имя
     */
    @JsonProperty("firstName")
    private String firstName;

    /**
     * Отчетсво
     */
    @JsonProperty("patronymic")
    private String patronymic;
}

