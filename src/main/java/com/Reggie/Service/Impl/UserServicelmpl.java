package com.Reggie.Service.Impl;

import com.Reggie.Entity.User;
import com.Reggie.Mapper.UserMapper;
import com.Reggie.Service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServicelmpl extends ServiceImpl<UserMapper, User> implements UserService {

}
