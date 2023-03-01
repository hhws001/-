package com.Reggie.Service.Impl;

import com.Reggie.Entity.Category;
import com.Reggie.Mapper.CategoryMapper;
import com.Reggie.Service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryServicelmpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
