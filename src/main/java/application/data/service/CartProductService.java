package application.data.service;

import application.data.model.CartProduct;
import application.data.repository.CartProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartProductService {
    private static final Logger logger = LogManager.getLogger(CartProductService.class);

    @Autowired
    CartProductRepository cartProductRepository;
    public void addNewCartProduct(CartProduct cartProduct){
        cartProductRepository.save(cartProduct);
    }
    @Transactional
    public void addNewListCartProduct(List<CartProduct> cartProductList){
        cartProductRepository.save(cartProductList);
    }
    public CartProduct findOne(int id){
        return cartProductRepository.findOne(id);
    }
    public boolean updateCartProduct(CartProduct cartProduct){
        try{
            cartProductRepository.save(cartProduct);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delateCartpoduct(int id){
        try {
            cartProductRepository.delete(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public CartProduct findFirstCartProductByCartIdAndProductId(int cartId, int productId) {
        try {
            return cartProductRepository.findFirstCartProductByCartIdAndProductId(cartId,productId);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
