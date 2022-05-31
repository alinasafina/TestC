package com.test.pims.dao.service.impl;

import com.test.pims.common.exceptions.IllegalIdException;
import com.test.pims.dao.entity.Employee;
import com.test.pims.dao.entity.Project;
import com.test.pims.dao.repository.ProjectRepository;
import com.test.pims.dao.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

/**
 * Реализация службы для работы с репозиторием "Проект" {@link com.test.pims.dao.repository.ProjectRepository}
 * <p>
 * Created by SafinaAA on 26.05.2022
 */
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    /**
     * Находит сущность "Проект" по идентификатору
     *
     * @param id идентификатор
     * @return найденная сущность
     */
    @Override
    @Transactional(readOnly = true)
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalIdException(format("Проекта с индентификатором %s не существует", id)));
    }

    /**
     * Создает сущность "Проект"
     *
     * @param project сущность
     * @return созданная сущность
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Project create(Project project) {
        ofNullable(project)
                .orElseThrow(() -> new IllegalIdException("Невозможно создать, данные отсутсвуют"));

        if (project.getId() != null)
            throw new IllegalIdException("Невозможно создать, идентификатор должен быть пуст");

        if (isNotValid(project)){
            throw new IllegalIdException("Невозможно создать, обязательные поля (Наименование) не заполнены");
        }

        return projectRepository.save(project);
    }

    /**
     * Обновляет сущность "Проект"
     *
     * @param project сущность
     * @return обновленная сущность
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Project update(Project project) {
        Long id = ofNullable(project)
                .map(Project::getId)
                .orElseThrow(() -> new IllegalIdException("Невозможно обновить, не указан идентификатор"));

        boolean projectExist = projectRepository.existsById(id);

        if (!projectExist) {
            throw new IllegalIdException(String.format("Невозможно обновить, сущности с идентификатором %s не существует", id));
        }

        return projectRepository.save(project);
    }

    /**
     * Удаляет сущность "Проект" по идентификатору
     *
     * @param id идентификатор сущности
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        ofNullable(id).orElseThrow(() -> new IllegalIdException("Невозможно удалить, идентификатор пуст"));

        if (!projectRepository.existsById(id)) {
            throw new IllegalIdException(format("Проект с индентификатором %s не существует", id));
        }

        projectRepository.deleteById(id);
    }

    /**
     * Возвращает список проектов
     *
     * @return список проектов
     */
    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {

        return projectRepository.findAll();
    }

    /**
     * Проверка заполненности обязательных полей
     *
     * @param project проект
     * @return true/false не валидно/валидно
     */
    private boolean isNotValid(Project project){
        return project.getName().isBlank();
    }
}
