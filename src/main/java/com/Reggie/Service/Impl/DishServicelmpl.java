package com.Reggie.Service.Impl;

import com.Reggie.Entity.Dish;
import com.Reggie.Mapper.DishMapper;
import com.Reggie.Service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishServicelmpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
