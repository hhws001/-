package com.Reggie.Common;

import com.Reggie.Service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class test1 {
    @Autowired
    private CategoryService categoryService;
    @Test
    public void test1(){
       categoryService.Hello();

    }
}
