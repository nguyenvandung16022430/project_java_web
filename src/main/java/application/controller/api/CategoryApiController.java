package application.controller.api;

import application.data.model.Category;
import application.data.service.CategoryService;

import application.model.api.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryApiController {

    @Autowired
    private CategoryService categoryService;

    @Transactional
    @GetMapping("/fake")
    public BaseApiResult fakeCategory(){
        BaseApiResult result = new BaseApiResult();

        try{
            long totalCategories = categoryService.getTotalCategories();
            List<Category> categoryList = new ArrayList<>();
            for(long i= totalCategories +1;i<=totalCategories +5;i++){
                Category category = new Category();
                category.setName("Category" + i);
                category.setShortDesc("Category" + i +" short desc");
                category.setCreatedDate(new Date());
                categoryList.add(category);
            }
            categoryService.addNewListCategory(categoryList);
            result.setSuccess(true);
            result.setMessage("Fake list category success !");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
