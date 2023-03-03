package com.Reggie.Service.Impl;

import com.Reggie.Entity.Setmeal;
import com.Reggie.Mapper.SetmealMapper;
import com.Reggie.Service.SetmealService;
import com.Reggie.dto.SetmealDto;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SetmealServicelmpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Override
    public void saveWithDish(SetmealDto setmealDto) {

    }
}
