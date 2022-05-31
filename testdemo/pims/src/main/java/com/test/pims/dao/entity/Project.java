package com.test.pims.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Сущность "Проект"
 *
 * Created by SafinaAA on 26.05.2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "project")
public class Project {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Наименование проекта
     */
    @Column(name = "name")
    private String name;

    /**
     * Дата начала проекта
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * Дата окончания проекта
     */
    @Column(name = "finish_date")
    private Date finishDate;

    /**
     * Статус проекта
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    /**
     * Руководитель проекта
     */
    @ManyToOne
    @JoinColumn(name = "project_manager_id", referencedColumnName = "id")
    private Employee projectManager;

    /**
     * Исполнители проекта
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "register_project_executor",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    private List<Employee> executors;

}
