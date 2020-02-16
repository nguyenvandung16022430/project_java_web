package application.controller.web;

import application.data.model.Cart;
import application.data.model.Category;
import application.data.model.User;
import application.data.service.CartService;
import application.data.service.CategoryService;
import application.data.service.OrderService;
import application.data.service.UserService;
import application.model.viewModel.common.CartHeaderVM;
import application.model.viewModel.common.CategoryVM;
import application.model.viewModel.common.LayoutHeaderAdminVM;
import application.model.viewModel.common.LayoutHeaderVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class BaseController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private List<CategoryVM> getCategoryVMList(){
        List<Category> categoryList = categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList = new ArrayList<>();
        for(Category category : categoryList){
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVM.setShortDesc(category.getShortDesc());
            categoryVM.setCteatedDate(category.getCreatedDate());
            categoryVMList.add(categoryVM);
        }
        return categoryVMList;
    }
    public LayoutHeaderVM getLayoutHeaderVM(){
        LayoutHeaderVM vm = new LayoutHeaderVM();
        vm.setCategoryVMList(this.getCategoryVMList());
        return vm;
    }
    public CartHeaderVM getCartHeaderVM (){
        CartHeaderVM vm = new CartHeaderVM();
        return vm;
    }
    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {

        LayoutHeaderAdminVM vm = new LayoutHeaderAdminVM();

        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity = userService.findUserByUsername(username);

        if(userEntity!=null) {
            vm.setUserName(username);
            if(userEntity.getAvatar() != null) {
                vm.setAvatar(userEntity.getAvatar());
            } else vm.setAvatar("https://aets.org.es/wp-content/uploads/2014/12/omita-el-icono-del-perfil-avatar-placeholder-gris-de-la-foto-99724602.jpg");
        }
        System.out.println(orderService.getAmountOrderUnconfirm());
        vm.setAmountOrder(orderService.getAmountOrderUnconfirm());

        return vm;

    }

    public void checkCookie(HttpServletResponse response,
                            HttpServletRequest request,
                            final Principal principal){
        Cookie cookie[] = request.getCookies();

        if(principal != null){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Cart cartEntity = cartService.findByUserName(username);
            if(cartEntity !=null){
                Cookie cookie1 = new Cookie("guid",cartEntity.getGuid());
                cookie1.setPath("/");
                response.addCookie(cookie1);
            } else {
                UUID uuid = UUID.randomUUID();
                String guid = uuid.toString();
                Cart cart = new Cart();
                cart.setGuid(guid);
                cart.setUsername(username);
                cartService.addNewCart(cart);
                Cookie cookie2 = new Cookie("guid",guid);
                cookie2.setPath("/");
                response.addCookie(cookie2);
            }
        } else {
            System.out.println("chay vao day rôi nè");
            boolean flag2 = true;
            String guid = null;
            if(cookie != null){
                for(Cookie c : cookie){
                    if(c.getName().equals("guid")){
                        if(cartService.findCartByGuid(c.getValue())==null) {
                            flag2 = false;
                            guid = c.getValue();
                        }
                    }
                }
            }
            if(flag2 == true){
                UUID uuid = UUID.randomUUID();
                String guid2 = uuid.toString();
                Cart cart2 = new Cart();
                cart2.setGuid(guid2);
                cartService.addNewCart(cart2);

                Cookie cookie3 = new Cookie("guid",guid2);
                cookie3.setPath("/");
                cookie3.setMaxAge(60*60*24);
                response.addCookie(cookie3);
            } else {
                Cart cartEntity = cartService.findFirstCartByGuid(guid);
                if(cartEntity == null) {
                    Cart cart3 = new Cart();
                    cart3.setGuid(guid);
                    cartService.addNewCart(cart3);
                }
            }
        }
    }
}
