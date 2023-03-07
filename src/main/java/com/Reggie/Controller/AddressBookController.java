package com.Reggie.Controller;

import com.Reggie.Common.BaseContext;
import com.Reggie.Common.R;
import com.Reggie.Entity.AddressBook;
import com.Reggie.Service.AddressBookService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressBook")
@Slf4j
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @GetMapping("/list")
    public R<List<AddressBook>> list(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook={}", addressBook);

        //条件构造器
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(addressBook.getUserId() != null, AddressBook::getUserId, addressBook.getUserId());
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);

        List<AddressBook> list = addressBookService.list(queryWrapper);
        return R.success(list);
    }

    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook={}", addressBook);

        addressBookService.save(addressBook);

        return R.success(addressBook);
    }
    @PutMapping("/default")
    public R<AddressBook> getDefault(@RequestBody AddressBook addressBook){
        addressBook.setUserId(BaseContext.getCurrentId());

        //条件构造器
        LambdaUpdateWrapper<AddressBook> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(addressBook.getUserId() != null,AddressBook::getUserId,addressBook.getUserId());
        updateWrapper.set(AddressBook::getIsDefault,0);

        //将与用户id所关联的所有地址的is_default字段更新为0
        addressBookService.update(updateWrapper);

        addressBook.setIsDefault(1);
        //再将前端传递的地址id的is_default字段更新为1
        addressBookService.updateById(addressBook);

        return R.success(addressBook);
    }
}
