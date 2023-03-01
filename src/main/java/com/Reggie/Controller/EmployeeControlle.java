package com.Reggie.Controller;

import com.Reggie.Common.R;
import com.Reggie.Entity.Employee;
import com.Reggie.Service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeControlle {
    @Autowired
    private EmployeeService employeeService;
    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    //@RequestBody 主要用于接收前端传递给后端的json字符串（请求体中的数据）
    //HttpServletRequest request作用：如果登录成功，将员工对应的id存到session一份，这样想获取一份登录用户的信息就可以随时获取出来
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1.将页面提交的密码进行MD5加密处理
        String password = employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);  //使用getOne：因为user_name字段有unique唯一约束，不会出现查询出多个结果

        //3.如果没有查询到则返回登录失败结果
        if (emp == null){
            return R.error("登录失败");  //因为error为静态方法，所以可以在该类中直接调用
        }

        //4.密码比对，如果不一致则返回登录结果
        if (!emp.getPassword().equals(password)) {
            return R.error("登录失败");
        }

        //5.查看员工状态，如果已为禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        //6.登录成功，将员工id存入session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);

    }
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工的信息：{}",employee.toString());
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //强转为long类型
        Long empID = (Long)request.getSession().getAttribute("employee");

        employee.setCreateUser(empID);
        employee.setUpdateUser(empID);

        employeeService.save(employee);
        return R.success("添加员工成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {}, pageSize = {}, name = {}",page,pageSize,name);

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);

        //添加排序添加
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){
        log.info(employee.toString());

        Long empID = (Long)request.getSession().getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empID);
        employeeService.updateById(employee);
        return R.success("员工修改信息成功");
    }
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){

        log.info("根据id查询员工信息。。。");
        Employee employee = employeeService.getById(id);
        if (employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到该员工信息");
    }


}
