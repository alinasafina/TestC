package com.test.pims.dto.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Web представления для работы со списком исполнителей по проекту (без идентификатора)
 * <p>
 * Created by SafinaAA on 30.05.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutorsDto {

    /**
     * Идентификатор
     */
    @JsonProperty("id")
    private Long id;

    /**
     * Идентификаторы исполнителей проекта
     */
    @JsonProperty("idExecutors")
    private List<Long> idsExecutor;

}
