package com.Reggie.Service.Impl;

import com.Reggie.Entity.ShoppingCart;
import com.Reggie.Mapper.ShoppingCartMapper;
import com.Reggie.Service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServicelmpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
