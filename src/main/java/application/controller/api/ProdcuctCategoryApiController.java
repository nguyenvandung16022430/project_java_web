package application.controller.api;

import application.data.model.Category;
import application.data.model.Product;
import application.data.model.ProductCategory;
import application.data.service.CategoryService;
import application.data.service.ProductCategoryService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/productcategory")
public class ProdcuctCategoryApiController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
   private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Transactional
    @GetMapping("/fake")
    public BaseApiResult fakeProductCategory(){
        BaseApiResult result = new BaseApiResult();
        try{
            long totalproduct= productService.getTotalProducts();
            List<Category> categoryList = categoryService.getListAllCategories();
            List<Product> productList = productService.getListAllProducts();
            List<ProductCategory> productCategoryList = new ArrayList<>();
            Random random = new Random();
            for(int i = 1;i< totalproduct; i++ ){
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(i);
                productCategory.setProduct(productService.findOne(i));
                int b = categoryList.get(random.nextInt(categoryList.size())).getId();
                productCategory.setCategoryId(b);
                productCategory.setCategory(categoryService.findOne(b));
                productCategoryList.add(productCategory);
            }
            productCategoryService.addNewListProduct(productCategoryList);
            result.setSuccess(true);
            result.setMessage(("Fake list ProductCategory success !"));
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
