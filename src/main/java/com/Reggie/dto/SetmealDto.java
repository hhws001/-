package com.Reggie.dto;

import com.Reggie.Entity.Setmeal;
import com.Reggie.Entity.SetmealDish;

import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
