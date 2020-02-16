package application.controller.api;

import application.data.model.Category;
import application.data.model.Product;
import application.data.model.ProductCategory;
import application.data.service.CategoryService;
import application.data.service.ProductCategoryService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.ProductDTO;
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

    @Autowired
    private ProductCategoryService productCategoryService;

    private String[] images  = {
            "https://salt.tikicdn.com/cache/w1200/ts/product/2b/b1/b7/60031f112d30ed96bde76d40b5b263dc.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/eb/62/6b/0e56b45bddc01b57277484865818ab9b.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/70/9a/98/e6d54019a2079b9565114bce93b245b7.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/87/c9/c0/bfebf4adcb31c8eb5c39fd3779cc4b68.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/fd/61/1d/a19424cfe9d113c32732d93cf2d5f59a.jpg",
    };
    @GetMapping("/fake")
    public BaseApiResult fakeProduct(){
        BaseApiResult result = new BaseApiResult();

        try {
            long totalProducts = productService.getTotalProducts();
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
    @PostMapping(value = "/create")
    public BaseApiResult createProduct(@RequestBody ProductDTO dto){
        DataApiResult result = new DataApiResult();
        try{
            Product product = new Product();
            product.setName(dto.getName());
            product.setShortDesc(dto.getShortDesc());
            product.setMainImage(dto.getMainImage());
            product.setPrice(dto.getPrice());
            ProductCategory productCategory = new ProductCategory();
            int categoryId = categoryService.findOne(dto.getCategoryId()).getId();
            productService.addNewProduct(product);
             productCategory.setProduct(product);
           productCategory.setCategory(categoryService.findOne(categoryId));
            productCategoryService.addNewProduct(productCategory);
            result.setData(product.getId());
            result.setMessage("Create product successfully: "+ product.getId());
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId,
                                       @RequestBody ProductDTO dto){
        BaseApiResult result = new BaseApiResult();
    System.out.println("chạy tới đây");
        try{
            Product product = productService.findOne(productId);
            product.setName(dto.getName());
            product.setShortDesc(dto.getShortDesc());
            product.setPrice(dto.getPrice());
            product.setMainImage(dto.getMainImage());
            productService.addNewProduct(product);
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategory(categoryService.findOne(dto.getCategoryId()));
            productCategory.setProduct(product);
            result.setSuccess(true);
            result.setMessage("Update product successfully");
            System.out.println("chay tơi day");
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;

}
@GetMapping("/detail/{productId}")
public DataApiResult detailProduct(@PathVariable int productId) {
    DataApiResult result = new DataApiResult();
    try {
        Product productEntity = productService.findOne(productId);
        if(productEntity == null){
            result.setSuccess(false);
            result.setMessage("Can't find this product");
        }else {
            ProductDTO dto = new ProductDTO();
            dto.setId(productEntity.getId());

           if(categoryService.findOne(productCategoryService.getcategoryIdByProductid(productEntity.getId())) != null){
               dto.setCategoryId(productCategoryService.getcategoryIdByProductid(productEntity.getId()));
           }
           dto.setMainImage(productEntity.getMainImage());
           dto.setName(productEntity.getName());
           dto.setPrice(productEntity.getPrice());
           dto.setShortDesc(productEntity.getShortDesc());
           dto.setCreatedDate(productEntity.getPublishedDate());
           result.setMessage("Success");
           result.setData(dto);
           result.setSuccess(true);

        }


    }catch (Exception e){
        result.setSuccess(false);
        result.setMessage(e.getMessage());
    }
    return result;
}
}
