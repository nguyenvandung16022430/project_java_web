package application.controller.web;

import application.data.model.Cart;
import application.data.model.CartProduct;
import application.data.service.CartService;
import application.model.viewModel.cart.CartProductVM;
import application.model.viewModel.cart.CartVM;
import application.model.viewModel.common.ProductVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(path = "/cart")
public class CartController extends BaseController {
    private static  final Logger logger = LogManager.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @GetMapping("")

    public String cart(Model model,
                       @Valid @ModelAttribute("productname") ProductVM productName,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal) {

        this.checkCookie(response,request,principal);
        if(principal == null){
            System.out.println("lỗi ở đây");
        }

        CartVM vm = new CartVM();

        int productAmount = 0;
        double totalPrice = 0;
        List<CartProductVM> cartProductVMList = new ArrayList<>();

        String guid = this.getGuid(request);

        DecimalFormat df = new DecimalFormat("####0.00");
        try {
            if (guid != null) {
                Cart cartEntity = cartService.findFirstCartByGuid(guid);
                if (cartEntity != null) {
                    productAmount = cartEntity.getListCartProduct().size();
                    vm.setCartId(cartEntity.getId());
                    if (productAmount == 0) {
                        vm.setCartHeaderVM(this.getCartHeaderVM());
                        model.addAttribute("vm",vm);
                        return "/non-product";
                    }else {
                    for (CartProduct cartProduct : cartEntity.getListCartProduct()) {
                        CartProductVM cartProductVM = new CartProductVM();
                        cartProductVM.setId(cartProduct.getId());
                        cartProductVM.setAmount(cartProduct.getAmount());
                        cartProductVM.setMainImage(cartProduct.getProduct().getMainImage());
                        cartProductVM.setProductName(cartProduct.getProduct().getName());
                        cartProductVM.setProductId(cartProduct.getProduct().getId());
                        double prince = cartProduct.getAmount() * cartProduct.getProduct().getPrice();
                        cartProductVM.setPrice(df.format(prince));
                        totalPrice += prince;
                        cartProductVMList.add(cartProductVM);
                    }
                }
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        vm.setProductAmount(productAmount);
        vm.setCartProductVMS(cartProductVMList);
        vm.setTotalPrice(df.format(totalPrice));
        vm.setCartHeaderVM(this.getCartHeaderVM());

        model.addAttribute("vm",vm);
        return "/cart";
    }
    public String getGuid(HttpServletRequest request) {
        Cookie cookie[] = request.getCookies();

        if(cookie!=null) {
            for(Cookie c :cookie) {
                if(c.getName().equals("guid"))  return c.getValue();
            }
        }
        return null;
    }
}
