package application.data.service;

import application.data.model.Cart;
import application.data.repository.CartRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    private static final Logger logger = LogManager.getLogger(CartService.class);
    @Autowired
    CartRepository cartRepository;
    @Transactional
    public void addNewCart(Cart cart){
        cartRepository.save(cart);
    }
    public Cart findOne(int cartId){
        return cartRepository.findOne(cartId);
    }
    public boolean updateCart(Cart cart){
        try{
            cartRepository.save(cart);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
    public  boolean deleteCart(int cartInt){
        try{
            cartRepository.delete(cartInt);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }
}
