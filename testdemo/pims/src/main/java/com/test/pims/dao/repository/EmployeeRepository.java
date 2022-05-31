package com.test.pims.dao.repository;

import com.test.pims.dao.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью  "Сотрудник"
 * <p>
 * Created by SafinaAA on 26.05.2022
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
