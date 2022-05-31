package com.test.pims.dao.service;

import com.test.pims.dao.entity.Project;

import java.util.List;

/**
 * Служба для работы с репозиторием "Проект" {@link com.test.pims.dao.repository.ProjectRepository}
 * <p>
 * Created by SafinaAA on 26.05.2022
 */
public interface ProjectService {

    /**
     * Находит сущность "Проект" по идентификатору
     *
     * @param id идентификатор
     * @return найденная сущность
     */
    Project findById(Long id);

    /**
     * Создает сущность "Проект"
     *
     * @param project сущность
     * @return созданная сущность
     */
    Project create(Project project);

    /**
     * Обновляет сущность "Проект"
     *
     * @param project сущность
     * @return обновленная сущность
     */
    Project update(Project project);

    /**
     * Удаляет сущность "Проект" по идентификатору
     *
     * @param id идентификатор сущности
     */
    void delete(Long id);

    /**
     * Возвращает все проекты
     */
    List<Project> findAll();

}
