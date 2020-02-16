package application.controller.api;

import application.data.model.Author;
import application.data.model.AuthorProduct;
import application.data.model.Product;
import application.data.service.AuthorService;
import application.data.service.ProductAuthorService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/api/authorproduct")
public class AuthorProductApiController {
    @Autowired
    ProductAuthorService productAuthorService;
    @Autowired
    ProductService productService;
    @Autowired
    AuthorService authorService;
    @GetMapping("/fake")
    public BaseApiResult fakeProductAuthor(){
        BaseApiResult result = new BaseApiResult();
        try{
            List<Product> productList = productService.getListAllProducts();
            List<Author> authorList = authorService.getListAllAuthor();
            List<AuthorProduct> authorProductList = new ArrayList<>();
            Random random = new Random();
            for(int i =0 ;i<150;i++){
                AuthorProduct authorProduct= new AuthorProduct();
                authorProduct.setAuthorId(authorList.get(random.nextInt(authorList.size())).getId());
                authorProduct.setAuthor(authorService.findOne(authorProduct.getAuthorId()));
                authorProduct.setProductId(productList.get(random.nextInt(productList.size())).getId());
                authorProduct.setProduct(productList.get(authorProduct.getProductId()));
                authorProductList.add(authorProduct);
                productAuthorService.addNewListCategory(authorProductList);
                result.setSuccess(true);
                result.setMessage("Success");
            }
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}

