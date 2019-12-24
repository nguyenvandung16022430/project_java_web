package application.controller.api;

import application.data.model.Category;
import application.data.model.Product;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping(path = "/api/product")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private String[] images  = {
            "https://salt.tikicdn.com/cache/w1200/ts/product/2b/b1/b7/60031f112d30ed96bde76d40b5b263dc.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/eb/62/6b/0e56b45bddc01b57277484865818ab9b.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/70/9a/98/e6d54019a2079b9565114bce93b245b7.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/87/c9/c0/bfebf4adcb31c8eb5c39fd3779cc4b68.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/fd/61/1d/a19424cfe9d113c32732d93cf2d5f59a.jpg",
    };
    @GetMapping("/fake")
    public BaseApiResult fakeCategpry(){
        BaseApiResult result = new BaseApiResult();

        try {
            long totalProducts = productService.getTotalProducts();
            List<Category> categoryList = categoryService.getListAllCategories();
            List<Product> productList = new ArrayList<>();
            Random random = new Random();
            for (long i = totalProducts +1;i<= totalProducts +40 ;i++){
                Product product = new Product();
                product.setName("Prodcut" + i);
                product.setShortDesc("Product" + i + "short desc");
                product.setMainImage(images[random.nextInt(images.length)]);
                double rangeMin = 4;
                double rangeMax = 30;
                double randomPrice = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
                product.setPrice(randomPrice);
                product.setPublishedDate(new Date());
                productList.add(product);
            }
            productService.addNewListProduct(productList);
            result.setSuccess(true);
            result.setMessage("Fake list Product success !");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
