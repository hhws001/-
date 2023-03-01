package com.Reggie.Service.Impl;

import com.Reggie.Entity.Employee;
import com.Reggie.Mapper.EmployeeMapper;
import com.Reggie.Service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl  extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
