package com.Reggie.Service;

import com.Reggie.Entity.Setmeal;
import com.Reggie.dto.SetmealDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
}
