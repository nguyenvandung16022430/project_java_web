package application.controller.api;

import application.controller.web.BaseController;
import application.data.model.Cart;
import application.data.model.CartProduct;
import application.data.model.Product;
import application.data.model.User;
import application.data.service.CartProductService;
import application.data.service.CartService;
import application.data.service.ProductService;
import application.data.service.UserService;
import application.model.api.BaseApiResult;
import application.model.dto.CartProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping(value = "api/admin")
public class AdminApiController extends BaseController {
    private static  final Logger logger = LogManager.getLogger(AdminApiController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addcart")
    public BaseApiResult addToCart(@RequestBody CartProductDTO dto,
                                   final Principal principal ){
        BaseApiResult result = new BaseApiResult();
        try{
                if(principal !=null){
                    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                    User userEntity = userService.findUserByUsername(userName);
                    System.out.println(userName);
                    Cart cartEntity = cartService.findByUserName(userName+"admin"+userEntity.getPasswordHash());
                    if(cartEntity == null) {
                        cartEntity = new Cart();
                       cartEntity.setUsername(userName+"admin"+userEntity.getPasswordHash());
                       cartService.addNewCart(cartEntity);
                    }
                    System.out.println(dto.getProductId());
                    if(dto.getAmount() > 0 && dto.getProductId() > 0){
                        {
                            System.out.println("chạy tiếp vao đay rồi 2");
                            Product productEntity = productService.findOne(dto.getProductId());
                            if (productEntity != null) {
                                CartProduct cartProductEntity = cartProductService.findFirstCartProductByCartIdAndProductId(cartEntity.getId(), productEntity.getId());
                                if (cartProductEntity != null) {
                                    cartProductEntity.setAmount(cartProductEntity.getAmount() + dto.getAmount());
                                    cartProductService.updateCartProduct(cartProductEntity);
                                } else {
                                    CartProduct cartProduct = new CartProduct();
                                    cartProduct.setAmount(dto.getAmount());
                                    cartProduct.setCart(cartEntity);
                                    cartProduct.setProduct(productEntity);
                                    cartProductService.addNewCartProduct(cartProduct);
                                }
                                result.setMessage("Add to cart successfully!");
                                result.setSuccess(true);
                                return result;
                            }
                        }
                    }
                }
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setMessage("Fail!");
        result.setSuccess(false);
        return result;

    }

}
