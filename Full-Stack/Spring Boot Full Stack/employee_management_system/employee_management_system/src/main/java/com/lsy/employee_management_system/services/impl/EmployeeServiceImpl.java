package com.lsy.employee_management_system.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsy.employee_management_system.entity.EmployeeEntity;
import com.lsy.employee_management_system.model.Employee;
import com.lsy.employee_management_system.repository.EmployeeRepository;
import com.lsy.employee_management_system.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    
    private EmployeeRepository employeeRepository;
    
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    
}
