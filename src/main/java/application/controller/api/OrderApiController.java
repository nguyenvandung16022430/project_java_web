package application.controller.api;

import application.constant.StatusConstant;
import application.data.model.Order;
import application.data.model.OrderProduct;
import application.data.model.Product;
import application.data.service.OrderService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/order")
public class OrderApiController {
    private static final Logger logger = LogManager.getLogger(OrderApiController.class);

    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @GetMapping("/confirm/{orderId}")
    public BaseApiResult confirmOrder(@PathVariable int orderId){
        BaseApiResult result = new BaseApiResult();
        try{
            Order order = orderService.findOne(orderId);
            System.out.println(1);
            if(order.getStatusId() == StatusConstant.uncfirmed) {

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
            }
            if(order.getStatusId() == StatusConstant.confirmed) {
                order.setStatusId(StatusConstant.sent);
                result.setMessage("đã gửi hàng");
            }
            if(order.getStatusId() == StatusConstant.sent) {
                order.setStatusId(StatusConstant.complete);
                result.setMessage("Khách đã nhận hàng");
            }
            result.setSuccess(true);
            orderService.updateOrder(order);
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    @GetMapping("/delete/{orderId}")
    public BaseApiResult deleteOrder(@PathVariable int orderId){
        BaseApiResult result = new BaseApiResult();
        try{
            if(orderService.findOne(orderId).getStatusId() != 5){
                result.setSuccess(false);
                result.setMessage("Cần hủy đơn hàn trước");
            }
            orderService.deleteOrder(orderId);
            result.setSuccess(true);
            result.setMessage("cancel success");
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
}
