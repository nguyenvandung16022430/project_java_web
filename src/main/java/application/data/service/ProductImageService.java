package application.data.service;

import application.data.model.ProductImage;
import application.data.repository.ProductImageReprository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductImageService {
    @Autowired
    ProductImageReprository productImageReprository;
    private static final Logger logger = LogManager.getLogger(OrderService.class);
    public void addNewProductImage(ProductImage productImage){
        productImageReprository.save(productImage);
    }
    @Transactional
    public void addNewListProductImage(List<ProductImage> productImageList){
        productImageReprository.save(productImageList);
    }
    public ProductImage findOne(int id){
        return productImageReprository.findOne(id);
    }
    public boolean updateProductImage(ProductImage productImage){
        try {
            productImageReprository.save(productImage);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
    public boolean deleteProductImage(int id){
        try {
            productImageReprository.delete(id);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
}
