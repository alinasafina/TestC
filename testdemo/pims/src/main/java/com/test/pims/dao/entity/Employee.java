package com.test.pims.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность "Сотрудник"
 * <p>
 * Created by SafinaAA on 26.05.2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "employee")
public class Employee {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Фамилия
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Имя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Отчество
     */
    @Column(name = "patronymic")
    private String patronymic;

    /**
     * Список проектов
     */
    @ManyToMany(mappedBy = "executors", fetch = FetchType.LAZY)
    private List<Project> projects;
}
