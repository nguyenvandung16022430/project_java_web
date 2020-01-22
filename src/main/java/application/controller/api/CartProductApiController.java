package application.controller.api;

import application.controller.web.BaseController;
import application.data.model.Cart;
import application.data.model.CartProduct;
import application.data.model.Product;
import application.data.service.CartProductService;
import application.data.service.CartService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.dto.CartProductDTO;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping(path = "/api/cart-product")
public class CartProductApiController extends BaseController {
    private static  final Logger logger = LogManager.getLogger(CartProductApiController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public BaseApiResult addToCart(@RequestBody CartProductDTO dto,
                                   HttpServletResponse response,
                                   HttpServletRequest request,
                                   final Principal principal ) {
        BaseApiResult result = new BaseApiResult();
        try{
            if(dto.getGuid() == null) {
                System.out.println("chạy vao day roi");
                this.checkCookie(response, request, principal);
                Cookie cookie[] = request.getCookies();
                if(cookie != null){
                    for(Cookie c : cookie){
                        if(c.getName().equals("guid")){
                            dto.setGuid(c.getValue());
                        }
                    }
                }
            }
            if(dto.getGuid() != null){
                if(principal !=null){
                    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                    System.out.println(userName);
                    if(cartService.findByUserName(userName) == null) {
                     this.checkCookie(response,request,principal);
                     dto.setGuid(cartService.findByUserName(userName).getGuid());
                 } else {
                        if(cartService.findByUserName(userName).getGuid() != dto.getGuid()){
                            this.checkCookie(response,request,principal);
                            dto.setGuid(cartService.findByUserName(userName).getGuid());
                        }
                    }
                }
            }
            if(dto.getAmount() > 0 && dto.getProductId() > 0){
                 Cart cartEntity = cartService.findFirstCartByGuid(dto.getGuid());
                 if(cartEntity == null){
                     System.out.println("chạy tiếp vao đây rồi 1");
                     System.out.println(dto.getGuid());
                     Cart cart2 = new Cart();
                     System.out.println("test");
                     cart2.setGuid(dto.getGuid());
                     System.out.println("test tiep");
                        cartService.addNewCart(cart2);
                         Product productEntity = productService.findOne(dto.getProductId());
                         if(productEntity != null)  {
                             CartProduct cartProductEntity = cartProductService.findFirstCartProductByCartIdAndProductId(cart2.getId(),productEntity.getId());
                             if(cartProductEntity != null) {
                                 cartProductEntity.setAmount(cartProductEntity.getAmount() + dto.getAmount());
                                 cartProductService.updateCartProduct(cartProductEntity);
                             } else {
                                 CartProduct cartProduct = new CartProduct();
                                 cartProduct.setAmount(dto.getAmount());
                                 cartProduct.setCart(cart2);
                                 cartProduct.setProduct(productEntity);
                                 cartProductService.addNewCartProduct(cartProduct);
                             }
                             result.setMessage("Add to cart successfully!");
                             result.setSuccess(true);
                             return result;
                         }
                 }else {
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
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setMessage("Fail!");
        result.setSuccess(false);
        return result;
    }
}
