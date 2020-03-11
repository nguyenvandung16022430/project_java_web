package application.controller.web;

import application.constant.StatusConstant;
import application.data.model.*;
import application.data.service.CartProductService;
import application.data.service.CartService;
import application.data.service.OrderService;
import application.data.service.UserService;
import application.model.viewModel.cart.CartProductVM;
import application.model.viewModel.common.ProductVM;
import application.model.viewModel.order.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController extends BaseController {
    private static  final Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private CartService cartService;

    @GetMapping("/checkout/{cartId}")
    public String checkout(Model model,
                           @PathVariable int cartId,
                           @Valid @ModelAttribute("productname") ProductVM productName,
                           final Principal principal){
        CheckOutVM vm = new CheckOutVM();
        OrderVM order = new OrderVM();

        if(principal != null ){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User userEntity = userService.findUserByUsername(username);
            if(userEntity != null){
                order.setAddress(userEntity.getAddress());
                order.setCustomerName(userEntity.getName());
                order.setPhoneNumber(userEntity.getPhoneNumber());
                order.setEmail(userEntity.getEmail());
            }
        }
        List<CartProductVM> cartProductVMS = new ArrayList<>();
        Cart cartEntity = cartService.findOne(cartId);
        double totalPrice = 0;
        DecimalFormat df = new DecimalFormat("####0.00");
        for (CartProduct cartProduct : cartEntity.getListCartProduct()) {
            CartProductVM cartProductVM = new CartProductVM();
            cartProductVM.setProductName(cartProduct.getProduct().getName());
            cartProductVM.setAmount(cartProduct.getAmount());
            double prince = cartProduct.getAmount()*cartProduct.getProduct().getPrice();
            cartProductVM.setPrice(df.format(prince));
            totalPrice += prince;
            cartProductVMS.add(cartProductVM);
        }
        vm.setTotalPrice(totalPrice);
        vm.setCartProductVMS(cartProductVMS);
        vm.setCartHeaderVM(this.getCartHeaderVM());
        model.addAttribute("order",order);
        model.addAttribute("vm",vm);
        return "/checkout";
    }

    @PostMapping("/checkout")
    public String checkout(Model model,
                           @Valid @ModelAttribute("order") OrderVM orderVM,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           final Principal principal){
        Order order = new Order();
        boolean flag = false;
        Cookie cookie[] = request.getCookies();
        String guid = null;
        if(cookie != null){
            for (Cookie c : cookie){
                if(c.getName().equals("guid")){
                    flag = true;
                    guid = c.getValue();
                }
            }
        }

        if(flag == true){
            double totalPrice = 0;
            if(principal !=null){
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                order.setUserName(userName);
            }
            order.setGuid(guid);
            order.setAddress(orderVM.getAddress());
            order.setEmail(orderVM.getEmail());
            order.setPhoneNumber(orderVM.getPhoneNumber());
            order.setCustomerName(orderVM.getCustomerName());
            order.setCreatedDate(new Date());

            Cart cartEntity = cartService.findFirstCartByGuid(guid);
            if(cartEntity != null){
                List<OrderProduct> orderProducts = new ArrayList<>();
                for(CartProduct cartProduct : cartEntity.getListCartProduct()){
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrder(order);
                    orderProduct.setProduct(cartProduct.getProduct());
                    orderProduct.setAmount(cartProduct.getAmount());
                    double prince = cartProduct.getAmount()*cartProduct.getProduct().getPrice();
                    totalPrice += prince;

                    orderProduct.setPrice(prince);
                    orderProducts.add(orderProduct);
                }
                order.setListProductOrders(orderProducts);
                order.setPrice(totalPrice);
                order.setStatusId(StatusConstant.uncfirmed);
                System.out.println(order.getStatusId());
                orderService.addNewOrder(order);
                cartService.deleteCart(cartEntity.getId());
            }
        }
    return "redirect:/order/history";
    }
    @GetMapping("/history")
    public String orderHistory(Model model,
                               @Valid @ModelAttribute("productname") ProductVM productName,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal){
        OrderHistory vm = new OrderHistory();
        DecimalFormat df = new DecimalFormat("####0.00");

        List<OrderVM> orderVMS = new ArrayList<>();
        Cookie[] cookie = request.getCookies();
        String guid = null;
        boolean flag = false;

        List<Order> orderEntityList = null;
        if(principal != null) {
            String  username = SecurityContextHolder.getContext().getAuthentication().getName();
            orderEntityList = orderService.findOrderByGuidOrUserName(null,username);
        } else {
            if(cookie != null) {
                for(Cookie c : cookie) {
                    if(c.getName().equals("guid")) {
                        flag = true;
                        guid = c.getValue();
                    }
                }
                if(flag == true) {
                    orderEntityList = orderService.findOrderByGuidOrUserName(guid,null);
                }
            }
        }

        if(orderEntityList != null) {
            for(Order order : orderEntityList) {
                OrderVM orderVM = new OrderVM();
                orderVM.setId(order.getId());
                orderVM.setCustomerName(order.getCustomerName());
                orderVM.setEmail(order.getEmail());
                orderVM.setAddress(order.getAddress());
                orderVM.setPhoneNumber(order.getPhoneNumber());
                orderVM.setPrice(df.format(order.getPrice()));
                orderVM.setCreatedDate(order.getCreatedDate());

                orderVMS.add(orderVM);
            }
        }

        vm.setCartHeaderVM(this.getCartHeaderVM());
        vm.setOrderVMS(orderVMS);

        model.addAttribute("vm",vm);

        return "/order-history";

    }
    @GetMapping("/history/{orderId}")
    public  String orderDetail(Model model,
                               @Valid @ModelAttribute("productname") ProductVM productName,
                               @PathVariable("orderId") int orderId){
        OderDetailVM vm = new OderDetailVM();

        DecimalFormat df = new DecimalFormat("####0.00");
        double totalPrice = 0;
        List<OrderProductVM> orderProductVMS = new ArrayList<>();

        Order orderEntity = orderService.findOne(orderId);
        if(orderEntity != null) {
            for(OrderProduct orderProduct : orderEntity.getListProductOrders()) {
                OrderProductVM orderProductVM = new OrderProductVM();

                orderProductVM.setProductId(orderProduct.getProduct().getId());
                orderProductVM.setMainImage(orderProduct.getProduct().getMainImage());
                orderProductVM.setAmount(orderProduct.getAmount());
                orderProductVM.setName(orderProduct.getProduct().getName());

                orderProductVM.setPrice(df.format(orderProduct.getPrice()));

                totalPrice += orderProduct.getPrice();

                orderProductVMS.add(orderProductVM);
                vm.setStatusId(orderEntity.getStatusId());
            }
        }
        vm.setCartHeaderVM(this.getCartHeaderVM());
        vm.setOrderProductVMS(orderProductVMS);
        vm.setTotalPrice(df.format(totalPrice));
        vm.setTotalProduct(orderProductVMS.size());

        model.addAttribute("vm",vm);

        return "/order-detail";

    }

}
