package application.controller.api;

import application.data.model.Product;
import application.data.model.ProductImage;
import application.data.service.ProductImageService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "api/productImage")
public class ProductImageApiController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    private String[] images  = {
            "https://salt.tikicdn.com/cache/w1200/ts/product/2b/b1/b7/60031f112d30ed96bde76d40b5b263dc.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/eb/62/6b/0e56b45bddc01b57277484865818ab9b.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/70/9a/98/e6d54019a2079b9565114bce93b245b7.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/87/c9/c0/bfebf4adcb31c8eb5c39fd3779cc4b68.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/fd/61/1d/a19424cfe9d113c32732d93cf2d5f59a.jpg",
    };
    @GetMapping("/fake")
    public BaseApiResult fakeProductImage(){
        BaseApiResult result = new BaseApiResult();
        try{
            Random random = new Random();
            List<Product> productList = productService.getListAllProducts();
            for (Product product : productList) {
                if (product.getProductImagelist().size() == 0) {
                    List<ProductImage> productImages = new ArrayList<>();
                    ProductImage productMainImage = new ProductImage();
                    productMainImage.setLink(product.getMainImage());
                    productMainImage.setProduct(product);
                    productMainImage.setCreatedDate(new Date());
                    productImages.add(productMainImage);
                    for (int i = 0; i < random.nextInt(2) + 1; i++) {
                        ProductImage productImage = new ProductImage();
                        productImage.setLink(images[random.nextInt(images.length)]);
                        productImage.setProduct(product);
                        productImage.setCreatedDate(new Date());
                        productImages.add(productImage);
                    }
                    productImageService.addNewListProductImage(productImages);
                }
            }
            result.setSuccess(true);
            result.setMessage("Fake list product images successfully !");
        }catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
}
