package com.Reggie.Service;

import com.Reggie.Entity.Setmeal;
import com.Reggie.dto.SetmealDto;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    @Transactional
    void removeWithDish(List<Long> ids);
}
