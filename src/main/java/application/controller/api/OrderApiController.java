package application.controller.api;

import application.constant.StatusConstant;
import application.controller.web.BaseController;
import application.data.model.*;
import application.data.service.CartService;
import application.data.service.OrderService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.dto.OrdeStatusDto;
import application.model.viewModel.order.OrderAdminVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/order")
public class OrderApiController {
    private static final Logger logger = LogManager.getLogger(OrderApiController.class);

    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;

    @PostMapping("/confirm")
    public BaseApiResult confirmOrder(@RequestBody OrdeStatusDto dto){
        BaseApiResult result = new BaseApiResult();
        try{
            Order order = orderService.findOne(dto.getOrderId());
            int status = dto.getStatusId();
            System.out.println(status);
            System.out.println(order.getStatusId());
            if(order.getStatusId() == StatusConstant.uncfirmed){
                if(status == 2){
                    for(OrderProduct orderProduct : order.getListProductOrders()) {
                        if (orderProduct.getAmount() > productService.findOne(orderProduct.getProduct().getId()).getAmount()) {
                            result.setSuccess(false);
                            result.setMessage(orderProduct.getProduct().getName() + "không đủ");
                            return result;
                        }
                    }
                    for(OrderProduct orderProduct : order.getListProductOrders()) {
                        if (orderProduct.getAmount() <= productService.findOne(orderProduct.getProduct().getId()).getAmount()) {
                            Product product =  productService.findOne(orderProduct.getProduct().getId());
                            int newAmount =product.getAmount()-orderProduct.getAmount();
                            product.setAmount(newAmount);
                            productService.updateProduct(product);
                        }
                    }
                    order.setStatusId(StatusConstant.confirmed);
                    result.setMessage("đã xác nhận đơn hàng");
                    result.setSuccess(true);
                    orderService.updateOrder(order);
                    return result;
                }
                if(status == 5){
                    System.out.println("chay vao dây");
                    order.setStatusId(StatusConstant.cancel);
                    result.setMessage("đã huy đơn hàng");
                    result.setSuccess(true);
                    orderService.updateOrder(order);
                    return result;
                }
                if(status == 3 || status == 4){
                    System.out.println("chay vao dây 2");
                    result.setSuccess(false);
                    result.setMessage("bạn cần xác nhận đơn hàng trước");
                    return result;
                }
                else {
                    result.setMessage("success");
                    result.setSuccess(true);
                    order.setStatusId(status);
                    orderService.updateOrder(order);
                    return result;
                }
            }
            if(order.getStatusId()== StatusConstant.confirmed ){
                if(status == 1 || status == 5){
                    for(OrderProduct orderProduct : order.getListProductOrders()) {
                        if (orderProduct.getAmount() <= productService.findOne(orderProduct.getProduct().getId()).getAmount()) {
                            Product product =  productService.findOne(orderProduct.getProduct().getId());
                            int newAmount =product.getAmount()+ orderProduct.getAmount();
                            product.setAmount(newAmount);
                            productService.updateProduct(product);
                        }
                    }
                    order.setStatusId(status);
                    result.setMessage("đã chuyển trạng thái thành công");
                    result.setSuccess(true);
                    orderService.updateOrder(order);
                    return result;
                }
                if(status == 3){
                    order.setStatusId(StatusConstant.sent);
                    result.setMessage("xác nhận đã gửi hàng");
                    result.setSuccess(true);
                    orderService.updateOrder(order);
                    return result;
                }
                if(status == 4){
                    result.setSuccess(false);
                    result.setMessage("bạn cần xác nhận đã gửi hàng trước");
                    return result;
                }
                else {
                    result.setMessage("success");
                    result.setSuccess(true);
                    orderService.updateOrder(order);
                    return result;
                }
            }
            if(order.getStatusId() == StatusConstant.sent){
                if(status == 2 || status == 4){
                    result.setMessage("chuyển trạng thái thành công");
                    order.setStatusId(status);
                }
                if(status == 1 || status == 5){
                    for(OrderProduct orderProduct : order.getListProductOrders()) {
                        if (orderProduct.getAmount() <= productService.findOne(orderProduct.getProduct().getId()).getAmount()) {
                            Product product =  productService.findOne(orderProduct.getProduct().getId());
                            int newAmount =product.getAmount()+ orderProduct.getAmount();
                            product.setAmount(newAmount);
                            productService.updateProduct(product);
                        }
                    }
                    result.setMessage("chuyển trạng thái thành công");
                    order.setStatusId(status);
                    result.setSuccess(true);
                    orderService.updateOrder(order);
                    return result;
                } else {
                    result.setMessage("success");
                    order.setStatusId(status);
                    result.setSuccess(true);
                    orderService.updateOrder(order);
                    return result;
                }
            }
            if(order.getStatusId() == StatusConstant.complete){
                if(status == 2 || status == 3){
                    result.setMessage("chuyển trạng thái thành công");
                    order.setStatusId(status);
                    result.setSuccess(true);
                    orderService.updateOrder(order);
                    return result;
                }
                if(status == 1 || status == 5){
                    for(OrderProduct orderProduct : order.getListProductOrders()) {
                        if (orderProduct.getAmount() <= productService.findOne(orderProduct.getProduct().getId()).getAmount()) {
                            Product product =  productService.findOne(orderProduct.getProduct().getId());
                            int newAmount =product.getAmount()+ orderProduct.getAmount();
                            product.setAmount(newAmount);
                            productService.updateProduct(product);
                        }
                    }
                    result.setMessage("chuyển trạng thái thành công");
                    order.setStatusId(status);
                    result.setSuccess(true);
                    orderService.updateOrder(order);
                    return result;
                } else {
                    result.setMessage("success");
                    result.setSuccess(true);
                    orderService.updateOrder(order);
                    return result;
                }
            }
            if(order.getStatusId() == StatusConstant.cancel){
                if(status ==1){
                    result.setMessage("chuyển trạng thái thành công");
                    order.setStatusId(status);
                }if(status == 2){
                    for(OrderProduct orderProduct : order.getListProductOrders()) {
                        if (orderProduct.getAmount() > productService.findOne(orderProduct.getProduct().getId()).getAmount()) {
                            result.setSuccess(false);
                            result.setMessage(orderProduct.getProduct().getName() + "không đủ");
                            orderService.updateOrder(order);
                            return result;
                        }
                    }
                    for(OrderProduct orderProduct : order.getListProductOrders()) {
                        if (orderProduct.getAmount() <= productService.findOne(orderProduct.getProduct().getId()).getAmount()) {
                            Product product =  productService.findOne(orderProduct.getProduct().getId());
                            int newAmount =product.getAmount()-orderProduct.getAmount();
                            product.setAmount(newAmount);
                            productService.updateProduct(product);
                        }
                    }
                    order.setStatusId(StatusConstant.confirmed);
                    result.setMessage("đã xác nhận đơn hàng");
                }else {
                    result.setSuccess(false);
                    result.setMessage("bạn cần xác nhận đơn hàng trước");
                    orderService.updateOrder(order);
                    return result;
                }

            }
            result.setSuccess(true);
            orderService.updateOrder(order);
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    @GetMapping("/cancel/{orderId}")
    public BaseApiResult cancelOrder(@PathVariable int orderId){
        BaseApiResult result = new BaseApiResult();
        try{
           Order order = orderService.findOne(orderId);
           if(order.getStatusId() != 1){
               for(OrderProduct orderProduct : order.getListProductOrders()) {
                   if (orderProduct.getAmount() <= productService.findOne(orderProduct.getProduct().getId()).getAmount()) {
                       Product product =  productService.findOne(orderProduct.getProduct().getId());
                       int newAmount =product.getAmount()+ orderProduct.getAmount();
                       product.setAmount(newAmount);
                       productService.updateProduct(product);
                   }
               }
           }
           order.setStatusId(StatusConstant.cancel);
           orderService.updateOrder(order);
            result.setSuccess(true);
            result.setMessage("cancel success");
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;

    }
    @PostMapping("/checkout")
    public BaseApiResult  checkout(@RequestBody OrderAdminVM orderVM
                           ){
        BaseApiResult result = new BaseApiResult();
        final Principal principal;
        Order order = new Order();
        try{
            double totalPrice = 0;

                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                order.setUserName(userName);;
            order.setPhoneNumber(orderVM.getPhoneNumber());
            order.setCustomerName(orderVM.getCustomerName());
            order.setCreatedDate(new Date());

            Cart cartEntity = cartService.findOne(orderVM.getId());
            if(cartEntity != null){
                List<OrderProduct> orderProducts = new ArrayList<>();
                for(CartProduct cartProduct : cartEntity.getListCartProduct()){
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrder(order);
                    Product product = productService.findOne(cartProduct.getProductId());
                    int newAmount = product.getAmount() - cartProduct.getAmount();
                    product.setAmount(newAmount);
                    productService.updateProduct(product);
                    orderProduct.setProduct(cartProduct.getProduct());
                    orderProduct.setAmount(cartProduct.getAmount());
                    double prince = cartProduct.getAmount()*cartProduct.getProduct().getPrice();
                    totalPrice += prince;

                    orderProduct.setPrice(prince);
                    orderProducts.add(orderProduct);
                }
                order.setListProductOrders(orderProducts);
                order.setPrice(totalPrice);
                order.setStatusId(StatusConstant.complete);
                System.out.println(order.getStatusId());
                orderService.addNewOrder(order);
                cartService.deleteCart(cartEntity.getId());
                result.setSuccess(true);
                result.setMessage("Success");
            }
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
