package application.data.service;

import application.data.model.Product;
import application.data.model.ProductCategory;
import application.data.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    ProductCategoryRepository productCategoryRepository;
    public void addNewProduct(ProductCategory product){
        productCategoryRepository.save(product);
    }
    @Transactional
    public void addNewListProduct(List<ProductCategory> listProduct){
        productCategoryRepository.save(listProduct);
    }
    public ProductCategory findOne(int produtId){
        return productCategoryRepository.findOne(produtId);
    }
    public boolean updateProduct(ProductCategory product){
        try{
            productCategoryRepository.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(int productId){
        try{
            productCategoryRepository.delete(productId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public long getTotalProducts(){
        return productCategoryRepository.getTotalProducts();
    }
}
