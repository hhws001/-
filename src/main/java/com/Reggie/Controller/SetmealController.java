package com.Reggie.Controller;

import com.Reggie.Common.R;
import com.Reggie.Entity.Dish;
import com.Reggie.Service.SetmealDishService;
import com.Reggie.Service.SetmealService;
import com.Reggie.dto.SetmealDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        log.info("数据传输对象setmealDto:{}",setmealDto.toString());
        return null;
    }

}
