package com.Reggie.Service.Impl;

import com.Reggie.Entity.AddressBook;
import com.Reggie.Mapper.AddressBookMapper;
import com.Reggie.Service.AddressBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServicelmpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
