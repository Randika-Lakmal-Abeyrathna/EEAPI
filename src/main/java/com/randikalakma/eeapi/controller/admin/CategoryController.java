package com.randikalakma.eeapi.controller.admin;

import com.randikalakma.eeapi.exception.admin.CategoryException;
import com.randikalakma.eeapi.model.Category;
import com.randikalakma.eeapi.service.admin.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/all")
    private ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categoryList = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category addedCategory= categoryService.addCategory(category);
        return new ResponseEntity<>(addedCategory, HttpStatus.CREATED);
    }
    
    @PostMapping("/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category updatedCategory = categoryService.updateCategory(category);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable("name") String name){
        Category category = categoryService.getCategoryByCategory(name);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @GetMapping("/find/like/{name}")
    public ResponseEntity<List<Category>> getCategoryByNameLike(@PathVariable("name") String name){
        List<Category> categoryList = categoryService.getCategoryByCategoryNameLike(name);
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Integer id){
        try{
            categoryService.deleteCategoryById(id);
        }catch(DataIntegrityViolationException e){
            throw new CategoryException("The Category you trying to delete is in use");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    
}
