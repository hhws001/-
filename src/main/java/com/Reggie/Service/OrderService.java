package com.Reggie.Service;

import com.Reggie.Entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
