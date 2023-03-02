package com.Reggie.Service.Impl;

import com.Reggie.Common.CustomException;
import com.Reggie.Entity.Category;
import com.Reggie.Entity.Dish;
import com.Reggie.Entity.Setmeal;
import com.Reggie.Mapper.CategoryMapper;
import com.Reggie.Service.CategoryService;
import com.Reggie.Service.DishService;
import com.Reggie.Service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




//@Service
//public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
//}
@Service
@Slf4j
public class CategoryServicelmpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加dish查询条件，根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);

        //查看当前分类是否关联了菜品，如果已经关联，则抛出异常
        if (count1 > 0){
            //已关联菜品，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加dish查询条件，根据分类id进行查询
        int count2 = setmealService.count(setmealLambdaQueryWrapper);

        //查看当前分类是否关联了套餐，如果已经关联，则抛出异常
        if (count2 > 0){
            //已关联套餐，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        //正常删除
        super.removeById(id);
    }
    public void Hello(){
        System.out.println("hello");
        log.info("hello");
    }

}
