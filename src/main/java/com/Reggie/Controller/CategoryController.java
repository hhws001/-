package com.Reggie.Controller;

import com.Reggie.Common.R;
import com.Reggie.Entity.Category;
import com.Reggie.Service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    /**
     * 删除分类信息
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id){
        log.info("将要删除的分类id:{}",id);

        categoryService.removeById(id);
        return R.success("分类信息删除成功");
    }

}
