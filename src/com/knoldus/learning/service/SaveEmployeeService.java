package com.knoldus.learning.service;

import com.knoldus.learning.entity.Employee;

public interface SaveEmployeeService {
    void saveEmployee(Employee employee);
    int getEmployeeCount();

}
