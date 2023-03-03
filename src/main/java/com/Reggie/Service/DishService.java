package com.Reggie.Service;

import com.Reggie.Entity.Dish;
import com.Reggie.Entity.DishFlavor;
import com.Reggie.dto.DishDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);
    void updateWithFlavor(DishDto dishDto);
}
