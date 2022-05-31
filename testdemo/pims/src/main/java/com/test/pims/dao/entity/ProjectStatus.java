package com.test.pims.dao.entity;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

/**
 * Перечисление статусов проекта
 * <p>
 * Created by SafinaAA on 26.05.2022
 */
public enum ProjectStatus {

    PLANED(1L, "Не начинался"),
    BY_ASSIGNMENT(2L, "Идет"),
    INITIATIVE(3L, "Закончен");

    private static final Map<Long, ProjectStatus> STATUSES = new HashMap<>();
    private final long id;
    private final String name;

    ProjectStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    static {
        for (ProjectStatus value : values()) {
            STATUSES.put(value.id, value);
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ProjectStatus getById(long id) {
        return STATUSES.get(id);
    }

    /**
     * Конвертер для работы с базой
     */
    public static class PackageStatusConverter implements AttributeConverter<ProjectStatus, Long> {

        @Override
        public Long convertToDatabaseColumn(ProjectStatus projectStatus) {
            return projectStatus.getId();
        }

        @Override
        public ProjectStatus convertToEntityAttribute(Long id) {
            return ofNullable(id).map(ProjectStatus::getById).orElse(null);
        }
    }
}
