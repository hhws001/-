package com.Reggie.Service.Impl;

import com.Reggie.Entity.OrderDetail;
import com.Reggie.Mapper.OrderDetailMapper;
import com.Reggie.Service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServicelmpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
